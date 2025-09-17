package com.example.currencyconverter.feature.main

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.exchange.models.Conversions
import com.example.currencyconverter.domain.exchange.usecases.BringConversion
import com.example.currencyconverter.domain.exchange.usecases.ObserveAllCurrencies
import com.example.currencyconverter.domain.exchange.usecases.ObserveConversionId
import com.example.currencyconverter.domain.exchange.usecases.RefreshCurrenciesList
import com.example.currencyconverter.domain.exchange.usecases.SaveConversion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
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

data class MainComponentsState(
    val fromCurrenciesExpanded: Boolean = false,
    val fromCurrencySelected: String = "",
    val toCurrenciesExpanded: Boolean = false,
    val toCurrencySelected: String = ""
)

data class SaveConversionsState(
    val fromCurrencySymbol: String = "",
    val fromCurrencyName: String = "",
    val toCurrencySymbol: String = "",
    val toCurrencyName: String = ""
)

data class LastConversionState(
    val fromCurrencyName: String = "",
    val toCurrencyName: String = "",
    val amount: String = "",
    val conversion: String = ""
)

@HiltViewModel
class MainViewModel @Inject constructor(
    refreshCurrenciesList: RefreshCurrenciesList,
    observeAllCurrencies: ObserveAllCurrencies,
    private val bringConversion: BringConversion,
    private val saveConversion: SaveConversion,
    private val observeConversionId: ObserveConversionId
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _navigationBarState = MutableStateFlow(NavigationBarState())
    val navigationBarState = _navigationBarState.asStateFlow()

    private val _mainComponentsState = MutableStateFlow(MainComponentsState())
    val mainComponentsState = _mainComponentsState.asStateFlow()

    private val _saveConversionsState = MutableStateFlow(SaveConversionsState())

    private val _lastConversionState = MutableStateFlow(LastConversionState())
    val lastConversionState = _lastConversionState.asStateFlow()

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    val currencies = observeAllCurrencies()
        .map { list ->
        list.sortedBy { it.currencySymbol } }
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

    fun setFromCurrenciesExpanded(fromCurrenciesExpanded: Boolean) {
        _mainComponentsState.update { it.copy(fromCurrenciesExpanded = fromCurrenciesExpanded) }
    }

    fun setFromCurrencySelected(fromCurrencySelected: String) {
        _mainComponentsState.update { it.copy(fromCurrencySelected = fromCurrencySelected) }
    }

    fun setToCurrenciesExpanded(toCurrenciesExpanded: Boolean) {
        _mainComponentsState.update { it.copy(toCurrenciesExpanded = toCurrenciesExpanded) }
    }

    fun setToCurrencySelected(toCurrencySelected: String) {
        _mainComponentsState.update { it.copy(toCurrencySelected = toCurrencySelected) }
    }

    fun setFromCurrencySymbolAndName(fromCurrencySymbol: String, fromCurrencyName: String) {
        _saveConversionsState.update { it.copy(
            fromCurrencySymbol = fromCurrencySymbol, fromCurrencyName = fromCurrencyName
        ) }
    }

    fun setToCurrencySymbolAndName(toCurrencySymbol: String, toCurrencyName: String) {
        _saveConversionsState.update { it.copy(
            toCurrencySymbol = toCurrencySymbol, toCurrencyName = toCurrencyName
        ) }
    }

    fun makeConversion() {
        viewModelScope.launch(Dispatchers.IO) {
            val amountString = _state.value.amount
            if (amountString.isNotBlank()) {
                try {
                    val from = _saveConversionsState.value.fromCurrencySymbol
                    val to = _saveConversionsState.value.toCurrencySymbol
                    val fromName = _saveConversionsState.value.fromCurrencyName
                    val toName = _saveConversionsState.value.toCurrencyName
                    val amountInt = _state.value.amount.toInt()
                    val conversion = bringConversion(amount = amountInt, from = from, to = to)
                    val date = Calendar.getInstance().timeInMillis
                    val conversionId = saveConversion(Conversions(
                        fromCurrencyName = fromName,
                        toCurrencyName = toName,
                        amount = amountInt.toString(),
                        conversion = conversion.toString(),
                        date = date
                    ))
                    showMessageConversion(conversionId = conversionId)
                } catch (e: Exception) {
                    _events.send(Event.ShowMessage("Error: ${e.message}"))
                }
            } else {
                _events.send(Event.ShowMessage("Amount cannot be empty"))
            }
        }
    }

    fun showMessageConversion(conversionId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val lastConversion = observeConversionId(conversionId)
            _lastConversionState.update { it.copy(
                fromCurrencyName = "${lastConversion.fromCurrencyName} =",
                toCurrencyName = lastConversion.toCurrencyName,
                amount = lastConversion.amount,
                conversion = lastConversion.conversion
            ) }
        }
    }

    fun navigateToHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            _events.send(Event.NavigateToHistory)
        }
    }

    sealed class Event {
        data class ShowMessage(val message: String): Event()
        data object NavigateToHistory: Event()
    }
}