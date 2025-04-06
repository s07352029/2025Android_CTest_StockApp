package com.example.stockapp.model

data class StockDayAvg(
    val Code: String,                   // 股票代號
    val Name: String,                   // 股票名稱
    val ClosingPrice: String,           // 收盤價
    val MonthlyAveragePrice: String     // 月平均價
)