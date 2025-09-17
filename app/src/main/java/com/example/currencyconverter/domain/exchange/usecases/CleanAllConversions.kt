package com.example.currencyconverter.domain.exchange.usecases

import com.example.currencyconverter.repository.ConversionsRepository

class CleanAllConversions(
    private val repository: ConversionsRepository
) {
    suspend operator fun invoke() {
        repository.cleanAll()
    }
}