package com.codingcat.conversionapp.data.mapper

import com.codingcat.conversionapp.data.entities.CurrencyEntity
import com.codingcat.conversionapp.data.entities.CurrencyListResponse
import com.codingcat.conversionapp.domain.model.Currency


object CurrencyListMapper {

    /**
     * Remove unnecessary data from API response to make applicable for local database
     */
    fun CurrencyListResponse.toCurrencyListEntities(): List<CurrencyEntity> {
        val currencyEntities = arrayListOf<CurrencyEntity>()
        val currencyData = this.currencies

        for (data in currencyData) {
            currencyEntities.add(CurrencyEntity(name = data.key))
        }

        return currencyEntities
    }

    /**
     * Remove unnecessary data to make it simple
     */
    fun List<CurrencyEntity>.toCurrencyList(): List<Currency> {
        val currencies = arrayListOf<Currency>()

        for (data in this) {
            currencies.add(Currency(data.name))
        }

        return currencies
    }
}