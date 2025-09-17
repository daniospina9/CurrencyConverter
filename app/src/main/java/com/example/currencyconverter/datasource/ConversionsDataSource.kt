package com.example.currencyconverter.datasource

import com.example.currencyconverter.domain.exchange.models.Conversions

interface ConversionsDataSource {

    suspend fun bringConversion(amount: Int, from: String, to: String): Float

    suspend fun saveConversions(conversions: Conversions): Long

    suspend fun observeConversionId(conversionId: Long): Conversions

    suspend fun getAll(): List<Conversions>

    suspend fun cleanAll()
}