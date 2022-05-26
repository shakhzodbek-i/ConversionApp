package com.codingcat.conversionapp.domain.usecase

import com.codingcat.conversionapp.data.ExchangeRateRepository
import com.codingcat.conversionapp.data.mapper.ExchangeRateMapper.toExchangeRateEntities
import com.codingcat.conversionapp.domain.model.ExchangeRate
import com.codingcat.conversionapp.utils.PreferencesHelper
import com.codingcat.conversionapp.utils.Utils
import kotlinx.coroutines.delay

class GetExchangeRateUseCase(
    private val preferencesHelper: PreferencesHelper,
    private val exchangeRateRepository: ExchangeRateRepository
) {
    suspend operator fun invoke(): List<ExchangeRate> {
        if (Utils.checkNetworkLoadTime(preferencesHelper.networkLoadTime)) {
            preferencesHelper.updateNetworkLoadTime(System.currentTimeMillis())
            val response = exchangeRateRepository.getExchangeRateFromNetwork()
            // It's necessary delay, because a huge amount of data cannot be processed on time,
            // that's why some devices return NullPointerException
            // There should be better way to solve it
            delay(2000)
            val entities = response.toExchangeRateEntities()

            exchangeRateRepository.saveExchangeRateToLocal(entities)
        } else {
            exchangeRateRepository.getExchangeRateFromLocal().ifEmpty {
                preferencesHelper.updateNetworkLoadTime(System.currentTimeMillis())
                val response = exchangeRateRepository.getExchangeRateFromNetwork()
                // It's necessary delay, because a huge amount of data cannot be processed on time,
                // that's why some devices return NullPointerException
                // There should be better way to solve it
                delay(2000)
                val entities = response.toExchangeRateEntities()

                exchangeRateRepository.saveExchangeRateToLocal(entities)
            }
        }

        return exchangeRateRepository.getExchangeRateFromLocal()
    }
}