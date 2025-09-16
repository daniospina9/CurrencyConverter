package com.example.currencyconverter.datasource

import com.example.currencyconverter.domain.exchange.models.Currencies
import kotlinx.coroutines.flow.Flow

interface CurrenciesDataSource {

    suspend fun fetchCurrenciesList(): List<Currencies>
    suspend fun replaceAll(currencies: List<Currencies>)

    fun observeAll(): Flow<List<Currencies>>
}