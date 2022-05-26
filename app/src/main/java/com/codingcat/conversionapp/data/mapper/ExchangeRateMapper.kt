package com.codingcat.conversionapp.data.mapper

import com.codingcat.conversionapp.data.entities.ExchangeRateEntity
import com.codingcat.conversionapp.data.entities.ExchangeRateResponse
import com.codingcat.conversionapp.domain.model.ExchangeRate

object ExchangeRateMapper {

    /**
     * Remove unnecessary data from API response to make applicable for local database
     */
    fun ExchangeRateResponse.toExchangeRateEntities(): List<ExchangeRateEntity> {
        val exchangeRateEntities = arrayListOf<ExchangeRateEntity>()
        val exchangeRateData = this.quotes

        for (data in exchangeRateData) {
            exchangeRateEntities.add(
                ExchangeRateEntity(
                    currency = data.key.removePrefix("USD"),
                    value = String.format("%.2f", data.value).replace(",", ".").toDouble()
                )
            )
        }

        return exchangeRateEntities
    }

    /**
     * Remove unnecessary data to make it simple
     */
    fun List<ExchangeRateEntity>.toExchangeRates(): List<ExchangeRate> {
        val exchangeRates = arrayListOf<ExchangeRate>()

        for (data in this) {
            exchangeRates.add(ExchangeRate(data.currency, data.value))
        }

        return exchangeRates
    }
}