package com.codingcat.conversionapp.presentation.main

import com.codingcat.conversionapp.domain.model.ExchangeRate

sealed class MainUiState {
    object Empty : MainUiState()
    object Loading : MainUiState()
    class ExchangeRateLoaded(val data: List<ExchangeRate>) : MainUiState()
    class CurrencyListLoaded(val data: List<String>) : MainUiState()
    class Error(val msg: String) : MainUiState()
}