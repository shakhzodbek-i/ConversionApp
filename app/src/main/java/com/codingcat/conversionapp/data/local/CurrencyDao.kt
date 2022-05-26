package com.codingcat.conversionapp.data.local

import androidx.room.*
import com.codingcat.conversionapp.data.entities.CurrencyEntity

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<CurrencyEntity>)

    @Delete
    suspend fun delete(data: CurrencyEntity)

    @Query("SELECT * FROM currency_list")
    suspend fun getAll(): List<CurrencyEntity>
}