package com.example.currencyconverter.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.currencyconverter.database.dtos.ConversionsDbDto

@Dao
interface ConversionsDao {

    @Insert()
    suspend fun saveConversion(conversion: ConversionsDbDto): Long

    @Query("SELECT * FROM conversions WHERE id = :conversionId")
    suspend fun observeConversionId(conversionId: Long): ConversionsDbDto

    @Query("SELECT * FROM conversions")
    suspend fun getAll(): List<ConversionsDbDto>

    @Query("DELETE FROM conversions")
    suspend fun cleanAll()
}