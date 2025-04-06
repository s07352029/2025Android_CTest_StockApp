package com.example.stockapp.data.remote

import retrofit2.http.GET
import com.example.stockapp.model.Bwibbu
import com.example.stockapp.model.StockDay
import com.example.stockapp.model.StockDayAvg

interface ApiService {

    @GET("exchangeReport/BWIBBU_ALL")
    suspend fun getBwibbuList(): List<Bwibbu>

    @GET("exchangeReport/STOCK_DAY_AVG_ALL")
    suspend fun getStockDayAvgList(): List<StockDayAvg>

    @GET("exchangeReport/STOCK_DAY_ALL")
    suspend fun getStockDayList(): List<StockDay>
}