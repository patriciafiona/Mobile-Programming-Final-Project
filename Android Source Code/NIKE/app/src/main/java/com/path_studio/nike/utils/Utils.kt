package com.path_studio.nike.utils

import android.util.Log

object Utils {
    fun getNumberThousandFormat(num: Int):String{
        try {
            // The comma in the format specifier does the trick
            return String.format("%,d", num.toLong()).replace(',', '.')
        } catch (e: NumberFormatException) {
            Log.e("Error Formatting Number per thousand", e.toString())
        }
        return ""
    }

    fun getNumberThousandFormat(num: Double):String{
        try {
            // The comma in the format specifier does the trick
            return String.format("%,d", num.toLong()).replace(',', '.')
        } catch (e: NumberFormatException) {
            Log.e("Error Formatting Number per thousand", e.toString())
        }
        return ""
    }
}