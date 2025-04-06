package com.example.stockapp.ui.util

import java.text.DecimalFormat

fun formatWithComma(value: String): String {
    return try {
        val number = value.toDouble()
        val formatter = DecimalFormat("#,###")
        formatter.format(number)
    } catch (e: Exception) {
        value // fallback，如果不是數字就原樣返回
    }
}