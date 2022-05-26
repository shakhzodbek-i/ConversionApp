package com.codingcat.conversionapp.di

import com.codingcat.conversionapp.data.network.ConverterApi
import com.codingcat.conversionapp.utils.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    val module = module {
        single { provideOkHttpClient() }
        single { provideRetrofit(get()) }
        single { provideConverterApi(get()) }
    }

    /**
     * Custom OkHttpClient instance provider
     *
     * (custom interceptors can be added if necessary)
     */
    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    /**
     * Retrofit instance provider
     */
    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * ConverterApi instance provider
     */
    private fun provideConverterApi(retrofit: Retrofit): ConverterApi =
        retrofit.create(ConverterApi::class.java)
}