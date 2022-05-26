package com.codingcat.conversionapp.domain.usecase

import com.codingcat.conversionapp.data.CurrencyListRepository
import com.codingcat.conversionapp.data.mapper.CurrencyListMapper.toCurrencyListEntities
import com.codingcat.conversionapp.domain.model.Currency
import com.codingcat.conversionapp.utils.PreferencesHelper
import com.codingcat.conversionapp.utils.Utils
import kotlinx.coroutines.delay

class GetCurrencyListUseCase(
    private val preferencesHelper: PreferencesHelper,
    private val currencyRepository: CurrencyListRepository
) {
    suspend operator fun invoke(): List<Currency> {
        if (Utils.checkNetworkLoadTime(preferencesHelper.networkLoadTime)) {
            preferencesHelper.updateNetworkLoadTime(System.currentTimeMillis())
            val response = currencyRepository.getCurrencyListFromNetwork()
            // It's necessary delay, because a huge amount of data cannot be processed on time,
            // that's why some devices return NullPointerException
            // There should be better way to solve it
            delay(2000)
            val entities = response.toCurrencyListEntities()

            currencyRepository.saveCurrencyListToLocal(entities)
        } else {
            currencyRepository.getCurrencyListFromLocal().ifEmpty {
                preferencesHelper.updateNetworkLoadTime(System.currentTimeMillis())
                val response = currencyRepository.getCurrencyListFromNetwork()
                // It's necessary delay, because a huge amount of data cannot be processed on time,
                // that's why some devices return NullPointerException
                // There should be better way to solve it
                delay(2000)
                val entities = response.toCurrencyListEntities()

                currencyRepository.saveCurrencyListToLocal(entities)
            }
        }

        return currencyRepository.getCurrencyListFromLocal()
    }
}