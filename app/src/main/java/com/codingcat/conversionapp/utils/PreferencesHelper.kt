package com.codingcat.conversionapp.utils

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        private const val NETWORK_LOAD_TIME_KEY = "converter_network_time_key"
    }

    val networkLoadTime: Long = preferences.getLong(NETWORK_LOAD_TIME_KEY, -1L)

    fun updateNetworkLoadTime(time: Long) {
        preferences.edit().putLong(NETWORK_LOAD_TIME_KEY, time).apply()
    }
}