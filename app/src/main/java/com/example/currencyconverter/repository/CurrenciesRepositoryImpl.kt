package com.example.currencyconverter.repository

import com.example.currencyconverter.datasource.CurrenciesDataSource
import com.example.currencyconverter.domain.exchange.models.Currencies
import kotlinx.coroutines.flow.Flow

class CurrenciesRepositoryImpl(
    val currenciesDataSource: CurrenciesDataSource
): CurrenciesRepository {

    override suspend fun fetchCurrenciesList(): List<Currencies> {
        return currenciesDataSource.fetchCurrenciesList()
    }

    override suspend fun saveAll(currencies: List<Currencies>) {
        currenciesDataSource.saveAll(currencies)
    }

    override fun observeAll(): Flow<List<Currencies>> = currenciesDataSource.observeAll()
}