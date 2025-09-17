package com.example.currencyconverter.domain.exchange.usecases

import com.example.currencyconverter.domain.exchange.models.Conversions
import com.example.currencyconverter.repository.ConversionsRepository

class GetAllConversions(
    private val repository: ConversionsRepository
) {
    suspend operator fun invoke(): List<Conversions> {
        return repository.getAll()
    }
}