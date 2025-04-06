package com.example.stockapp.model

data class StockInfo(
    val Code: String,               // 股票代號
    val Name: String,               // 股票名稱
    val TradeVolume: String,        // 成交股數
    val TradeValue: String,         // 成交金額
    val Transaction: String,        // 成交筆數
    val OpeningPrice: String,       // 開盤價
    val ClosingPrice: String,       // 收盤
    val HighestPrice: String,       // 最高價
    val LowestPrice: String,        // 最低價
    val MonthlyAvgPrice: String,    // 月平均價
    val Change: String,             // 漲跌價差
    val PEratio: String,            // 本益比
    val DividendYield: String,      // 殖利率(%)
    val PBratio: String             // 股價淨值比
)