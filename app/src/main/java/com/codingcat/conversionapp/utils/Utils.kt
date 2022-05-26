package com.codingcat.conversionapp.utils

import android.content.res.Resources
import android.util.TypedValue


object Utils {

    /**
     * This special function to convert from one to another by using USD as base
     * (It is a specification of API)
     *
     * Example - Convert EUR to RUB:
     * 1 USD - 0.924695 EUR
     * 1 USD - 80.750373 RUB
     * 1 EUR - X RUB
     * 1 EUR = 80.750373 / 0.924695 = 87,326495 RUB
     *
     */
    fun Double.convertCurrency(firstCurrencyValue: Double, secondCurrencyValue: Double): Double {
        return this * secondCurrencyValue / firstCurrencyValue
    }

    fun checkNetworkLoadTime(lastTime: Long): Boolean {
        return System.currentTimeMillis() - lastTime >= 1800000L
    }
}