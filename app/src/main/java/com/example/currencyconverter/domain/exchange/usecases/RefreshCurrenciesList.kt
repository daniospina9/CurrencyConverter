package com.example.currencyconverter.domain.exchange.usecases

class RefreshCurrenciesList(
    private val fetchCurrenciesList: FetchCurrenciesList,
    private val replaceAllCurrencies: ReplaceAllCurrencies
) {
    suspend operator fun invoke() {
        replaceAllCurrencies(fetchCurrenciesList())
    }
}