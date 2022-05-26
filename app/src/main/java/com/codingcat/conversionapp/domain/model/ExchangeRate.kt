package com.codingcat.conversionapp.domain.model

import androidx.annotation.Keep

@Keep
data class ExchangeRate(
    val currency: String,
    val value: Double
)
