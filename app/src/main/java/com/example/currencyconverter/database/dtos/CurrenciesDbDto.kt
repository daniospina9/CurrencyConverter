package com.example.currencyconverter.database.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrenciesDbDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val currencySymbol: String,
    val currencyName: String,
)
