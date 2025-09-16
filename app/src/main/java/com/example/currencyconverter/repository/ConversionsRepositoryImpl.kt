package com.example.currencyconverter.repository

import com.example.currencyconverter.datasource.ConversionsDataSource
import com.example.currencyconverter.domain.exchange.models.Conversions

class ConversionsRepositoryImpl(
    private val conversionsDataSource: ConversionsDataSource
): ConversionsRepository {

    override suspend fun bringConversion(amount: Int, from: String, to: String): Float {
        return conversionsDataSource.bringConversion(amount, from, to)
    }

    override suspend fun saveConversions(conversions: Conversions): Long {
        return conversionsDataSource.saveConversions(conversions)
    }

    override suspend fun observeConversionId(conversionId: Long): Conversions {
        return conversionsDataSource.observeConversionId(conversionId)
    }
}