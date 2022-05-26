package com.codingcat.conversionapp.utils

import com.codingcat.conversionapp.utils.Utils.convertCurrency
import com.google.common.collect.Range
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilsTest {

    @Test
    fun `currency converter returns NaN if firstCurrencyValue is 0`() {
        val result = 500.0.convertCurrency(0.0, 12.3)

        assertThat(result).isPositiveInfinity()
    }

    @Test
    fun `currency converter returns expected value`() {
        val result = 1.0.convertCurrency(0.92, 80.75)

        assertThat(result).isIn(Range.closed(87.77,87.78))
    }
}