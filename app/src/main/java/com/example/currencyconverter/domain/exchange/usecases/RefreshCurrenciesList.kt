package com.example.currencyconverter.domain.exchange.usecases

class RefreshCurrenciesList(
    val fetchCurrenciesList: FetchCurrenciesList,
    val saveAllCurrencies: SaveAllCurrencies
) {
    suspend operator fun invoke() {
        saveAllCurrencies(fetchCurrenciesList())
    }
}