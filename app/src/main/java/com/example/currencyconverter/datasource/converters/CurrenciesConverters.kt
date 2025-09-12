package com.example.currencyconverter.datasource.converters

import com.example.currencyconverter.database.dtos.CurrenciesDbDto
import com.example.currencyconverter.domain.exchange.models.Currencies
import com.example.currencyconverter.remote.CurrenciesAPI

fun Currencies.toDbDto(): CurrenciesDbDto {
    return CurrenciesDbDto(
        id = id,
        currencySymbol = currencySymbol,
        currencyName = currencyName
    )
}

fun CurrenciesDbDto.toCurrencies(): Currencies {
    return Currencies(
        id = id,
        currencySymbol = currencySymbol,
        currencyName = currencyName
    )
}

suspend fun CurrenciesAPI.toCurrencies(): List<Currencies> {

    val currenciesMap = this.getCurrenciesList()
    return currenciesMap.map { (symbol, name) ->
        Currencies(
            currencySymbol = symbol,
            currencyName = name
        )
    }
}