package com.codingcat.conversionapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingcat.conversionapp.data.entities.CurrencyEntity
import com.codingcat.conversionapp.data.entities.ExchangeRateEntity

@Database(entities = [ExchangeRateEntity::class, CurrencyEntity::class], version = 1, exportSchema = false)
abstract class ConverterDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao

    abstract fun currencyDao(): CurrencyDao
}