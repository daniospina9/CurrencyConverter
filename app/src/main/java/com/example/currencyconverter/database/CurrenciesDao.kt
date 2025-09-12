package com.example.currencyconverter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverter.database.dtos.CurrenciesDbDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(currencies: List<CurrenciesDbDto>)

    @Query("SELECT * FROM currencies")
    fun observeAll(): Flow<List<CurrenciesDbDto>>
}