package com.example.stockapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.stockapp.model.StockInfo
import com.example.stockapp.data.repository.StockRepository
import com.example.stockapp.model.SortOrder
import com.example.stockapp.model.SortType

class HomeViewModel(
    private val repository: StockRepository = StockRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private var originalList: List<StockInfo> = emptyList()
    private var currentSortType: SortType = SortType.CODE
    private var currentSortOrder: SortOrder = SortOrder.NONE

    init {
        fetchStockData()
    }

    fun sortStockList(type: SortType) {
        // 判斷排序循環狀態
        currentSortOrder = when {
            currentSortType != type -> SortOrder.DESCENDING
            currentSortOrder == SortOrder.DESCENDING -> SortOrder.ASCENDING
            currentSortOrder == SortOrder.ASCENDING -> SortOrder.NONE
            else -> SortOrder.DESCENDING
        }

        currentSortType = type

        val sortedList = when (currentSortOrder) {
            SortOrder.DESCENDING -> sortListByType(originalList, type, descending = true)
            SortOrder.ASCENDING -> sortListByType(originalList, type, descending = false)
            SortOrder.NONE -> originalList
        }

        _uiState.value = _uiState.value.copy(
            stockList = sortedList
        )
    }

    private fun sortListByType(list: List<StockInfo>, type: SortType, descending: Boolean): List<StockInfo> {
        val comparator = when (type) {
            SortType.CODE -> compareBy<StockInfo> { it.Code.toFloatOrNull() ?: 0f }
            SortType.CHANGE -> compareBy { it.Change.toFloatOrNull() ?: 0f }
            SortType.PRICE -> compareBy { it.ClosingPrice.toFloatOrNull() ?: 0f }
            SortType.DIVIDEND -> compareBy { it.DividendYield.toFloatOrNull() ?: 0f }
        }

        return if (descending) list.sortedWith(comparator.reversed())
        else list.sortedWith(comparator)
    }

    fun getCurrentSortType() = currentSortType
    fun getCurrentSortOrder() = currentSortOrder

    private fun fetchStockData() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val stockList: List<StockInfo> = repository.getAllStockInfo()
                originalList = stockList
                _uiState.value = HomeUiState(stockList = stockList, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = HomeUiState(
                    isLoading = false,
                    errorMessage = e.message ?: "資料讀取錯誤"
                )
            }
        }
    }
}
