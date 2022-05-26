package com.codingcat.conversionapp.di

import com.codingcat.conversionapp.data.ExchangeRateRepository
import com.codingcat.conversionapp.data.local.ExchangeRateDao
import com.codingcat.conversionapp.data.network.ConverterApi
import com.codingcat.conversionapp.domain.usecase.GetExchangeRateUseCase
import com.codingcat.conversionapp.utils.PreferencesHelper
import org.koin.dsl.module

object ExchangeRateModule {

    val module = module {
        single { provideRepository(get(), get()) }
        factory { provideGetExchangeRateUseCase(get(), get()) }
    }

    private fun provideRepository(api: ConverterApi, dao: ExchangeRateDao): ExchangeRateRepository {
        return ExchangeRateRepository(api, dao)
    }

    private fun provideGetExchangeRateUseCase(preferences: PreferencesHelper, repository: ExchangeRateRepository): GetExchangeRateUseCase {
        return GetExchangeRateUseCase(preferences, repository)
    }
}