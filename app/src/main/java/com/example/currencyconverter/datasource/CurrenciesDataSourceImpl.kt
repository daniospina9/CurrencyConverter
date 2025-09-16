package com.example.currencyconverter.datasource

import com.example.currencyconverter.database.CurrenciesDao
import com.example.currencyconverter.datasource.converters.toCurrencies
import com.example.currencyconverter.datasource.converters.toDbDto
import com.example.currencyconverter.domain.exchange.models.Currencies
import com.example.currencyconverter.remote.CurrenciesAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrenciesDataSourceImpl(
    val currenciesDao: CurrenciesDao,
    val api: CurrenciesAPI
): CurrenciesDataSource {

    override suspend fun fetchCurrenciesList(): List<Currencies> {
        return api.toCurrencies()
    }

    override suspend fun replaceAll(currencies: List<Currencies>) {
        currenciesDao.replaceAll(currencies.map { it.toDbDto() })
    }

    override fun observeAll(): Flow<List<Currencies>> = currenciesDao.observeAll().map { flowList ->
        flowList.map { it.toCurrencies() }
    }
}