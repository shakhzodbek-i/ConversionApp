package com.codingcat.conversionapp.data

import com.codingcat.conversionapp.data.entities.ExchangeRateEntity
import com.codingcat.conversionapp.data.entities.ExchangeRateResponse
import com.codingcat.conversionapp.data.local.ExchangeRateDao
import com.codingcat.conversionapp.data.mapper.ExchangeRateMapper.toExchangeRates
import com.codingcat.conversionapp.data.network.ConverterApi
import com.codingcat.conversionapp.domain.model.ExchangeRate

class ExchangeRateRepository(
    private val api: ConverterApi,
    private val dao: ExchangeRateDao
) {

    suspend fun getExchangeRateFromNetwork(): ExchangeRateResponse {
        return api.getExchangeRate()
    }

    suspend fun getExchangeRateFromLocal(): List<ExchangeRate> {
        return dao.getAll().toExchangeRates()
    }

    suspend fun saveExchangeRateToLocal(data: List<ExchangeRateEntity>) {
        dao.insertAll(data)
    }
}