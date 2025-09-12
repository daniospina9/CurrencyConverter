package com.example.currencyconverter.domain.exchange.usecases

import com.example.currencyconverter.domain.exchange.models.Currencies
import com.example.currencyconverter.repository.CurrenciesRepository

class SaveAllCurrencies(
    private val repository: CurrenciesRepository
) {
    suspend operator fun invoke(currencies: List<Currencies>) {
        repository.saveAll(currencies)
    }
}