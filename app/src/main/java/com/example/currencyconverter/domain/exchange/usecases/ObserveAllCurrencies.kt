package com.example.currencyconverter.domain.exchange.usecases

import com.example.currencyconverter.domain.exchange.models.Currencies
import com.example.currencyconverter.repository.CurrenciesRepository
import kotlinx.coroutines.flow.Flow

class ObserveAllCurrencies(
    private val repository: CurrenciesRepository
) {
    operator fun invoke(): Flow<List<Currencies>> = repository.observeAll()
}