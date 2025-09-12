package com.example.currencyconverter.remote

import retrofit2.http.GET

interface CurrenciesAPI {

    @GET("currencies")
    suspend fun getCurrenciesList(): Map<String, String>
}