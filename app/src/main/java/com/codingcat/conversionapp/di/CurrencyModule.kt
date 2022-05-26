package com.codingcat.conversionapp.di

import com.codingcat.conversionapp.data.CurrencyListRepository
import com.codingcat.conversionapp.data.local.CurrencyDao
import com.codingcat.conversionapp.data.network.ConverterApi
import com.codingcat.conversionapp.domain.usecase.GetCurrencyListUseCase
import com.codingcat.conversionapp.utils.PreferencesHelper
import org.koin.dsl.module

object CurrencyModule {

    val module = module {
        single { provideRepository(get(), get()) }
        factory { provideGetCurrencyListUseCase(get(), get()) }
    }

    private fun provideRepository(api: ConverterApi, dao: CurrencyDao): CurrencyListRepository {
        return CurrencyListRepository(api, dao)
    }

    private fun provideGetCurrencyListUseCase(preferences: PreferencesHelper, repository: CurrencyListRepository): GetCurrencyListUseCase {
        return GetCurrencyListUseCase(preferences, repository)
    }
}