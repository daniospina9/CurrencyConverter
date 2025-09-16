package com.example.currencyconverter.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.currencyconverter.database.dtos.CurrenciesDbDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(currencies: List<CurrenciesDbDto>)

    @Query("DELETE FROM currencies")
    suspend fun deleteAll()

    @Query("SELECT * FROM currencies")
    fun observeAll(): Flow<List<CurrenciesDbDto>>

    @Transaction
    suspend fun replaceAll(currencies: List<CurrenciesDbDto>) {
        deleteAll()
        saveAll(currencies)
    }
}