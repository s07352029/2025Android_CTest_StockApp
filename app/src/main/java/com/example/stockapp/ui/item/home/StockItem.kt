package com.example.stockapp.ui.item.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockapp.model.StockInfo

@Composable
fun StockItem(stock: StockInfo) {
    val colorScheme = MaterialTheme.colorScheme
    val primary = colorScheme.primary
    val background = colorScheme.background

    var showDialog by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDialog = true },
        colors = CardDefaults.cardColors(containerColor = background),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 股票代號＋名稱
            Text(
                text = stock.Code,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = primary
            )

            Text(
                text = stock.Name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = primary
            )

            Spacer(Modifier.height(8.dp))

            // 第一排：開盤、收盤
            RowInfo(
                leftLabel = "開盤價",
                leftValue = stock.OpeningPrice,
                rightLabel = "收盤價",
                rightValue = stock.ClosingPrice,
                compareLabel = "月平均價",
                compareValue = stock.MonthlyAvgPrice,
                isClosingVsAvg = true
            )

            // 第二排：最高、最低
            RowInfo(
                leftLabel = "最高價",
                leftValue = stock.HighestPrice,
                rightLabel = "最低價",
                rightValue = stock.LowestPrice,
                compareLabel = "",
                compareValue = ""
            )

            // 第三排：漲跌價差、月平均價
            RowInfo(
                leftLabel = "漲跌價差",
                leftValue = stock.Change,
                rightLabel = "月平均價",
                rightValue = stock.MonthlyAvgPrice,
                compareLabel = "",
                compareValue = "",
                isChangeField = true
            )

            Spacer(Modifier.height(6.dp))

            // 最下排：成交資訊
            RowInfo(
                leftLabel = "成交筆數",
                leftValue = stock.Transaction,
                compareLabel = "成交股數",
                compareValue = stock.TradeVolume,
                rightLabel = "成交金額",
                rightValue = stock.TradeValue,
                isDealRow = true
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "${stock.Code} ${stock.Name}",
                    color = primary,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text("本益比：${stock.PEratio}")
                    Text("殖利率：${stock.DividendYield}%")
                    Text("股價淨值比：${stock.PBratio}")
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("關閉", color = primary)
                }
            },
            containerColor = background
        )
    }
}

