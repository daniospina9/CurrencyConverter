package com.example.currencyconverter.domain.exchange.usecases

import com.example.currencyconverter.repository.ConversionsRepository

class BringConversion(
    private val repository: ConversionsRepository
) {
    suspend operator fun invoke(amount: Int, from: String, to: String): Float {
        return repository.bringConversion(amount, from, to)
    }
}