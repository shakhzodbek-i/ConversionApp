package com.codingcat.conversionapp.data.network

import com.codingcat.conversionapp.data.entities.CurrencyListResponse
import com.codingcat.conversionapp.data.entities.ExchangeRateResponse
import com.codingcat.conversionapp.utils.Constants
import retrofit2.http.GET

interface ConverterApi {
    @GET("live?access_key=${Constants.API_KEY}")
    suspend fun getExchangeRate(): ExchangeRateResponse

    @GET("list?access_key=${Constants.API_KEY}")
    suspend fun getCurrencyList(): CurrencyListResponse
}