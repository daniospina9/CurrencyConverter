package com.example.currencyconverter.remote

import com.example.currencyconverter.remote.dtos.RateConverterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RateConverterAPI {

    @GET("convert/{amount}/{from}/{to}")
    suspend fun convertCurrency(
        @Path("amount") amount: Int,
        @Path("from") from: String,
        @Path("to") to: String
    ): RateConverterResponseDto
}