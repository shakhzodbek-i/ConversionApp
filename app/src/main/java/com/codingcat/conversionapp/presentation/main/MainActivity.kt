package com.codingcat.conversionapp.presentation.main

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.codingcat.conversionapp.R
import com.codingcat.conversionapp.databinding.ActivityMainBinding
import com.codingcat.conversionapp.domain.model.ExchangeRate
import com.codingcat.conversionapp.presentation.adapter.CurrencyAdapter
import com.codingcat.conversionapp.utils.CurrencyFormatterTextWatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: AlertDialog

    private val currencyAdapter: CurrencyAdapter by inject()
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private val mainViewModel: MainViewModel by viewModel()

    private var selectedCurrencyRate: Double = 0.0
    private var currentAmount: Double = 0.0
    private var exchangeRateList: List<ExchangeRate> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCurrencyList()

        initAmountEditText()

        initCurrencySpinner()

        initProgressDialog()

        fetchData()

        mainViewModel.getCurrencyList()
    }

    private fun fetchData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uiState.collect { state ->
                    when (state) {
                        is MainUiState.ExchangeRateLoaded -> onExchangeRateLoaded(state.data)
                        is MainUiState.CurrencyListLoaded -> onCurrencyListLoaded(state.data)
                        is MainUiState.Error -> showError(state.msg)
                        else -> showLoading()
                    }
                }
            }
        }
    }

    private fun onExchangeRateLoaded(data: List<ExchangeRate>) {
        progressDialog.dismiss()
        binding.swipeToRefresh.isRefreshing = false
        selectedCurrencyRate = data[0].value
        exchangeRateList = data
        currencyAdapter.setDataList(data)
    }

    private fun onCurrencyListLoaded(data: List<String>) {
        progressDialog.dismiss()
        binding.swipeToRefresh.isRefreshing = false
        spinnerAdapter.addAll(data)
        spinnerAdapter.notifyDataSetChanged()
        mainViewModel.getExchangeRate()
    }

    private fun showError(msg: String) {
        progressDialog.dismiss()
        binding.swipeToRefresh.isRefreshing = false
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        progressDialog.show()
    }

    private fun initCurrencySpinner() {
        spinnerAdapter = ArrayAdapter(
            this,
            R.layout.item_spinner_currency_header,
            R.id.text,
            arrayListOf()
        )
        binding.currency.adapter = spinnerAdapter
        binding.currency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (exchangeRateList.isNotEmpty()) {
                    selectedCurrencyRate = exchangeRateList[position].value
                    currencyAdapter.setCurrentAmount(currentAmount, selectedCurrencyRate)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun initAmountEditText() {
        val textWatcher = CurrencyFormatterTextWatcher(binding.amount.editText!!)
        textWatcher.setListener {
            currentAmount = it
            currencyAdapter.setCurrentAmount(currentAmount, selectedCurrencyRate)
        }
        binding.amount.editText!!.addTextChangedListener(textWatcher)
    }

    private fun initCurrencyList() {
        binding.currencyList.layoutManager =
            GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        binding.currencyList.adapter = currencyAdapter
        binding.swipeToRefresh.setOnRefreshListener {
            mainViewModel.getExchangeRate()
        }
    }

    private fun initProgressDialog() {
        val progressDialogBuilder = AlertDialog.Builder(this)
        val progressView = ProgressBar(this)
        progressView.setPadding(20, 20, 20, 20)
        progressView.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.bg,
                resources.newTheme()
            )
        )
        progressDialogBuilder.setView(progressView)
        progressDialogBuilder.setCancelable(false)
        progressDialog = progressDialogBuilder.create()
    }
}