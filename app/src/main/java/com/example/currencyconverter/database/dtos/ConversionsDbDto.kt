package com.example.currencyconverter.database.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversions")
data class ConversionsDbDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val fromCurrencyName: String,
    val toCurrencyName: String,
    val amount: String,
    val conversion: String
)
