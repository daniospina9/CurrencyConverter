package com.example.currencyconverter.domain.exchange.usecases

import com.example.currencyconverter.domain.exchange.models.Conversions
import com.example.currencyconverter.repository.ConversionsRepository

class ObserveConversionId(
    private val repository: ConversionsRepository
) {
    suspend operator fun invoke(conversionId: Long): Conversions {
        return repository.observeConversionId(conversionId)
    }
}