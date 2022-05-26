package com.codingcat.conversionapp.data.local

import androidx.room.*
import com.codingcat.conversionapp.data.entities.ExchangeRateEntity

@Dao
interface ExchangeRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<ExchangeRateEntity>)

    @Delete
    suspend fun delete(data: ExchangeRateEntity)

    @Query("SELECT * FROM exchange_rate")
    suspend fun getAll(): List<ExchangeRateEntity>
}