package com.example.currencyconverter.domain.exchange.models

data class Conversions(

    val id: Long = 0L,
    val fromCurrencyName: String,
    val toCurrencyName: String,
    val amount: String,
    val conversion: String
)
