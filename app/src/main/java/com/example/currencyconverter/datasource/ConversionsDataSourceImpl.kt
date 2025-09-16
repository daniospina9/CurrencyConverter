package com.example.currencyconverter.datasource

import com.example.currencyconverter.database.ConversionsDao
import com.example.currencyconverter.datasource.converters.toConversions
import com.example.currencyconverter.datasource.converters.toDbDto
import com.example.currencyconverter.domain.exchange.models.Conversions
import com.example.currencyconverter.remote.RateConverterAPI

class ConversionsDataSourceImpl(
    private val conversionsDao: ConversionsDao,
    private val api: RateConverterAPI
): ConversionsDataSource {

    override suspend fun bringConversion(amount: Int, from: String, to: String): Float {
        return api.convertCurrency(amount, from, to).response
    }

    override suspend fun saveConversions(conversions: Conversions): Long {
        return conversionsDao.saveConversion(conversions.toDbDto())
    }

    override suspend fun observeConversionId(conversionId: Long): Conversions {
        return conversionsDao.observeConversionId(conversionId).toConversions()
    }
}