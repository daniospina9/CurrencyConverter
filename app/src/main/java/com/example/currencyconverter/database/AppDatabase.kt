package com.example.currencyconverter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currencyconverter.database.dtos.CurrenciesDbDto

@Database(entities = [CurrenciesDbDto::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun currenciesDao(): CurrenciesDao
}