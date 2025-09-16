package com.example.currencyconverter.datasource.converters

import com.example.currencyconverter.database.dtos.ConversionsDbDto
import com.example.currencyconverter.domain.exchange.models.Conversions

fun Conversions.toDbDto(): ConversionsDbDto {
    return ConversionsDbDto(
        id = id,
        fromCurrencyName = fromCurrencyName,
        toCurrencyName = toCurrencyName,
        amount = amount,
        conversion = conversion
    )
}

fun ConversionsDbDto.toConversions(): Conversions {
    return Conversions(
        id = id,
        fromCurrencyName = fromCurrencyName,
        toCurrencyName = toCurrencyName,
        amount = amount,
        conversion = conversion
    )
}