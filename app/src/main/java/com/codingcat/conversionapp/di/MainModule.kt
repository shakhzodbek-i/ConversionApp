package com.codingcat.conversionapp.di

import com.codingcat.conversionapp.presentation.adapter.CurrencyAdapter
import com.codingcat.conversionapp.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {

    val module = module {
        factory { provideCurrencyAdapter() }
        viewModel { MainViewModel(get(), get()) }
    }

    private fun provideCurrencyAdapter(): CurrencyAdapter {
        return CurrencyAdapter()
    }
}