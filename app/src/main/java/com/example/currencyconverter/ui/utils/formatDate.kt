package com.example.currencyconverter.ui.utils

import android.icu.util.Calendar

fun formatDate(
    date: Long
): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH) + 1
    val year = calendar.get(Calendar.YEAR)

    val formattedDay = String.format("%02d", day)
    val formattedMonth = String.format("%02d", month)

    val formattedDate = "$formattedDay/$formattedMonth/$year"

    return formattedDate
}