package com.codingcat.conversionapp.data.entities


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CurrencyListResponse(
    @SerializedName("currencies")
    val currencies: Map<String, String>,
    @SerializedName("privacy")
    val privacy: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("terms")
    val terms: String?
)