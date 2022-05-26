package com.codingcat.conversionapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingcat.conversionapp.domain.usecase.GetCurrencyListUseCase
import com.codingcat.conversionapp.domain.usecase.GetExchangeRateUseCase
import com.codingcat.conversionapp.utils.ErrorParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val getExchangeRates: GetExchangeRateUseCase,
    private val getCurrencies: GetCurrencyListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Empty)
    val uiState: StateFlow<MainUiState> = _uiState

    fun getExchangeRate() {
        _uiState.value = MainUiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getExchangeRates()

                _uiState.value = MainUiState.ExchangeRateLoaded(result)
            } catch (error: Exception) {
                _uiState.value = MainUiState.Error(ErrorParser.parse(error))
            }
        }
    }

    fun getCurrencyList() {
        _uiState.value = MainUiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getCurrencies()

                _uiState.value = MainUiState.CurrencyListLoaded(result.map { it.name })
            } catch (error: Exception) {
                _uiState.value = MainUiState.Error(ErrorParser.parse(error))
            }
        }
    }
}