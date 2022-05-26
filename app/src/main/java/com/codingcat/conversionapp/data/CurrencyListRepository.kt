package com.codingcat.conversionapp.data

import com.codingcat.conversionapp.data.entities.CurrencyEntity
import com.codingcat.conversionapp.data.entities.CurrencyListResponse
import com.codingcat.conversionapp.data.local.CurrencyDao
import com.codingcat.conversionapp.data.mapper.CurrencyListMapper.toCurrencyList
import com.codingcat.conversionapp.data.network.ConverterApi
import com.codingcat.conversionapp.domain.model.Currency

class CurrencyListRepository(
    private val api: ConverterApi,
    private val dao: CurrencyDao
) {

    suspend fun getCurrencyListFromNetwork(): CurrencyListResponse {
        return api.getCurrencyList()
    }

    suspend fun getCurrencyListFromLocal(): List<Currency> {
        return dao.getAll().toCurrencyList()
    }

    suspend fun saveCurrencyListToLocal(data: List<CurrencyEntity>) {
        dao.insertAll(data)
    }
}