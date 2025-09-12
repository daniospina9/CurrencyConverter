package com.example.currencyconverter.domain.exchange.usecases

import com.example.currencyconverter.domain.exchange.models.Currencies
import com.example.currencyconverter.repository.CurrenciesRepository

class FetchCurrenciesList(
    private val repository: CurrenciesRepository
) {
    suspend operator fun invoke(): List<Currencies> {
        return repository.fetchCurrenciesList()
    }
}