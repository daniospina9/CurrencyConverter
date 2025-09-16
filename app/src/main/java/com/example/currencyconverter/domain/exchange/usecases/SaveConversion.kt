package com.example.currencyconverter.domain.exchange.usecases

import com.example.currencyconverter.domain.exchange.models.Conversions
import com.example.currencyconverter.repository.ConversionsRepository

class SaveConversion(
    private val repository: ConversionsRepository
) {
    suspend operator fun invoke(conversions: Conversions): Long {
        return repository.saveConversions(conversions)
    }
}