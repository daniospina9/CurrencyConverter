package com.example.currencyconverter.repository

import com.example.currencyconverter.domain.exchange.models.Conversions

interface ConversionsRepository {

    suspend fun bringConversion(amount: Int, from: String, to: String): Float

    suspend fun saveConversions(conversions: Conversions): Long

    suspend fun observeConversionId(conversionId: Long): Conversions
}