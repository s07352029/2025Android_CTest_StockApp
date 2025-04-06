package com.example.stockapp.data.repository

import com.example.stockapp.model.*
import com.example.stockapp.data.remote.RetrofitInstance

class StockRepository {

    suspend fun getAllStockInfo(): List<StockInfo> {
        // 拉取三筆原始資料
        val bwibbuList = RetrofitInstance.api.getBwibbuList()
        val avgList = RetrofitInstance.api.getStockDayAvgList()
        val dayList = RetrofitInstance.api.getStockDayList()

        // 以股票代碼為 key，做 map 合併
        val bwibbuMap = bwibbuList.associateBy { it.Code }
        val avgMap = avgList.associateBy { it.Code }
        val dayMap = dayList.associateBy { it.Code }

        // 把有出現在 dayList 的股票，進行合併
        val mergedList = dayList.mapNotNull { day ->
            val code = day.Code
            val bwibbu = bwibbuMap[code]
            val avg = avgMap[code]

            if (bwibbu != null && avg != null) {
                StockInfo(
                    Code = code,
                    Name = day.Name,
                    TradeVolume = day.TradeVolume,
                    TradeValue = day.TradeValue,
                    Transaction = day.Transaction,
                    OpeningPrice = day.OpeningPrice,
                    ClosingPrice = day.ClosingPrice,
                    HighestPrice = day.HighestPrice,
                    LowestPrice = day.LowestPrice,
                    MonthlyAvgPrice = avg.MonthlyAveragePrice,
                    Change = day.Change,
                    PEratio = bwibbu.PEratio,
                    DividendYield = bwibbu.DividendYield,
                    PBratio = bwibbu.PBratio
                )
            } else {
                null
            }
        }

        return mergedList
    }
}