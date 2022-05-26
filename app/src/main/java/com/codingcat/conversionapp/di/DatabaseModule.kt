package com.codingcat.conversionapp.di

import android.content.Context
import androidx.room.Room
import com.codingcat.conversionapp.data.local.ExchangeRateDao
import com.codingcat.conversionapp.data.local.ConverterDatabase
import com.codingcat.conversionapp.data.local.CurrencyDao
import com.codingcat.conversionapp.utils.Constants
import com.codingcat.conversionapp.utils.PreferencesHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {

    val module = module {
        single { providerConverterDatabase(androidApplication().applicationContext) }
        single { provideExchangeRateDao(get()) }
        single { provideCurrencyDao(get()) }
        single { PreferencesHelper(androidContext()) }
    }

    private fun providerConverterDatabase(applicationContext: Context): ConverterDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ConverterDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }

    private fun provideExchangeRateDao(database: ConverterDatabase): ExchangeRateDao {
        return database.exchangeRateDao()
    }

    private fun provideCurrencyDao(database: ConverterDatabase): CurrencyDao {
        return database.currencyDao()
    }
}