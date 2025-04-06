package com.example.stockapp.ui.screen.home

import com.example.stockapp.model.StockInfo

data class HomeUiState(
    val stockList: List<StockInfo> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)
