package com.codingcat.conversionapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingcat.conversionapp.utils.Utils.convertCurrency
import com.codingcat.conversionapp.databinding.ItemCurrecyBinding
import com.codingcat.conversionapp.domain.model.ExchangeRate

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.CurrencyVH>() {

    private var dataList: ArrayList<ExchangeRate> = arrayListOf()
    private var currentSelectedCurrencyRate: Double = 1.0
    private var currentAmount: Double = 0.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyVH {
        return CurrencyVH(ItemCurrecyBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CurrencyVH, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    /**
     * Update data of currency list
     */
    fun setDataList(dataList: List<ExchangeRate>) {
        this.dataList.clear()
        this.dataList.addAll(dataList)
        notifyItemRangeChanged(0, dataList.size - 1)
    }

    /**
     * Update the current amount and currency
     */
    fun setCurrentAmount(currentAmount: Double, selectedCurrencyRate: Double) {
        this.currentAmount = currentAmount
        this.currentSelectedCurrencyRate = selectedCurrencyRate
        notifyItemRangeChanged(0, dataList.size - 1)
    }

    inner class CurrencyVH(private val binding: ItemCurrecyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ExchangeRate) {
            binding.currency.text = data.currency

            val result = currentAmount.convertCurrency(currentSelectedCurrencyRate, data.value)

            binding.value.text = String.format("%.2f", result)
        }
    }
}