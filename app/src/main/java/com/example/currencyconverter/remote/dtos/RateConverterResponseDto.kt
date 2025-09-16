package com.example.currencyconverter.remote.dtos

import com.google.gson.annotations.SerializedName

data class RateConverterResponseDto(
    @SerializedName("response") val response: Float
)
