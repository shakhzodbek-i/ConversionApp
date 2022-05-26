package com.codingcat.conversionapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat

class CurrencyFormatterTextWatcher(private val editText: EditText) : TextWatcher {

    private lateinit var amountListener: (amount: Double) -> Unit
    private val decimalFormat: DecimalFormat = DecimalFormat("#,##0.00")
    private var current: String = ""

    init {
        decimalFormat.isDecimalSeparatorAlwaysShown = true
    }

    fun setListener(amountListener: (amount: Double) -> Unit) {
        this.amountListener = amountListener
    }

    override fun afterTextChanged(text: Editable?) {}

    override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        if (text.toString() != current) {
            editText.removeTextChangedListener(this)

            var cleanString: String = text.toString().replace(",", "")
            cleanString = cleanString.replace("[\\s|\\u00A0]+".toRegex(), "")

            val parsed = cleanString.toDouble()
            val formatted = decimalFormat.format((parsed / 100))

            current = formatted
            editText.setText(formatted)
            editText.setSelection(formatted.length)
            amountListener.invoke(parsed / 100)

            editText.addTextChangedListener(this)
        }
    }
}