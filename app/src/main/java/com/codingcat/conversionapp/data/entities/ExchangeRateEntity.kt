package com.codingcat.conversionapp.data.entities

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "exchange_rate")
data class ExchangeRateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "currency")
    val currency: String,
    @ColumnInfo(name = "value")
    val value: Double
)
