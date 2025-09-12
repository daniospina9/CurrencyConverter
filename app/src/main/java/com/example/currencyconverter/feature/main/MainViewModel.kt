package com.example.currencyconverter.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.exchange.usecases.ObserveAllCurrencies
import com.example.currencyconverter.domain.exchange.usecases.RefreshCurrenciesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainState(
    val amount: String = ""
)

data class NavigationBarState(
    val selectedIndex: Int = 0
)

@HiltViewModel
class MainViewModel @Inject constructor(
    refreshCurrenciesList: RefreshCurrenciesList,
    observeAllCurrencies: ObserveAllCurrencies
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _navigationBarState = MutableStateFlow(NavigationBarState())
    val navigationBarState = _navigationBarState.asStateFlow()

    val currencies = observeAllCurrencies()
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            refreshCurrenciesList()
        }
    }
    fun setAmount(amount: String) {
        _state.update { it.copy(amount =  amount) }
    }

    fun setSelectedIndex(selectedIndex: Int) {
        _navigationBarState.update { it.copy(selectedIndex = selectedIndex) }
    }
}