package com.codingcat.conversionapp.utils

import android.util.Log
import retrofit2.HttpException
import java.lang.Exception

object ErrorParser {

    fun parse(error: Exception): String {
        Log.e("ERROR_PARSER", error.message!!)
        return when (error) {
            is HttpException -> getHttpErrorMessage(error)
            else -> "Something went wrong"
        }
    }

    private fun getHttpErrorMessage(httpError: HttpException): String {
        return when (httpError.code()) {
            101 -> "User did not supply an access key or supplied an invalid access key."
            103 -> "User requested a non-existent API function."
            104 -> "User has reached or exceeded his subscription plan's monthly API request allowance."
            105 -> "The user's current subscription plan does not support the requested API function."
            106 -> "The user's query did not return any results"
            102 -> "The user's account is not active. User will be prompted to get in touch with Customer Support."
            201 -> "User entered an invalid Source Currency."
            202 -> "User entered one or more invalid currency codes."
            301 -> "User did not specify a date."
            302 -> "User entered an invalid date."
            401 -> "User entered an invalid \"from\" property."
            402 -> "User entered an invalid \"to\" property."
            403 -> "User entered no or an invalid \"amount\" property."
            404 -> "User requested a resource which does not exist."
            500 -> "Server-side error"
            501 -> "User did not specify a Time-Frame."
            502 -> "User entered an invalid \"start_date\" property."
            503 -> "User entered an invalid \"end_date\" property."
            504 -> "User entered an invalid Time-Frame."
            505 -> "The Time-Frame specified by the user is too long - exceeding 365 days."
            else -> "Something went wrong"
        }
    }
}