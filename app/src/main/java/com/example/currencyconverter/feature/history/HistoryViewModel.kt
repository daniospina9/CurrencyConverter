package com.example.currencyconverter.feature.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.exchange.models.Conversions
import com.example.currencyconverter.domain.exchange.usecases.CleanAllConversions
import com.example.currencyconverter.domain.exchange.usecases.GetAllConversions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NavigationBarState(
    val selectedIndex: Int = 1
)
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllConversions: GetAllConversions,
    private val cleanAllConversions: CleanAllConversions
): ViewModel() {

    private val _conversionsList = MutableStateFlow<List<Conversions>>(emptyList())
    val conversionsList = _conversionsList.asStateFlow()

    private val _navigationBarState = MutableStateFlow(NavigationBarState())
    val navigationBarState = _navigationBarState.asStateFlow()

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    init {
        refreshConversions()
    }

    fun refreshConversions() {
        viewModelScope.launch(Dispatchers.IO) {
            _conversionsList.update { getAllConversions() }
        }
    }

    fun navigateToMain() {
        viewModelScope.launch(Dispatchers.IO) {
            _events.send(Event.NavigateToMain)
        }
    }

    fun cleanHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            cleanAllConversions.invoke()
            refreshConversions()
        }
    }

    sealed class Event{
        data object NavigateToMain: Event()
    }
}