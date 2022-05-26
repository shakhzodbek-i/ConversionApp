package com.codingcat.conversionapp.data.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ExchangeRateResponse(
    @SerializedName("privacy")
    val privacy: String?,
    @SerializedName("quotes")
    val quotes: Map<String, Double>,
    @SerializedName("source")
    val source: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("terms")
    val terms: String?,
    @SerializedName("timestamp")
    val timestamp: Int?
)