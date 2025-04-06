package com.example.stockapp.model

enum class SortType(val label: String) {
    CODE("依股票代號"),
    CHANGE("依漲跌幅"),
    PRICE("依收盤價"),
    DIVIDEND("依殖利率")
}