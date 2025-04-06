package com.example.stockapp.ui.item.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.stockapp.ui.theme.PriceGreen
import com.example.stockapp.ui.theme.PriceRed
import com.example.stockapp.ui.util.formatWithComma

@Composable
fun RowInfo(
    leftLabel: String,
    leftValue: String,
    rightLabel: String,
    rightValue: String,
    compareLabel: String,
    compareValue: String,
    isChangeField: Boolean = false,      // 是否為漲跌價差
    isClosingVsAvg: Boolean = false,     // 是否為收盤 vs 月平均
    isDealRow: Boolean = false           // 是否為成交欄位
) {
    val leftColor = when {
        isChangeField -> {
            if ((leftValue.toFloatOrNull() ?: 0f) >= 0f) PriceRed else PriceGreen
        }
        else -> Color.Unspecified
    }

    val rightColor = when {
        isClosingVsAvg -> {
            if ((rightValue.toFloatOrNull() ?: 0f) >= (compareValue.toFloatOrNull() ?: 0f)) PriceRed else PriceGreen
        }
        else -> Color.Unspecified
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        if(isDealRow){
            Text(text = "$leftLabel ${formatWithComma(leftValue)}", color = leftColor, fontSize = 10.sp)
            Text(text = "$compareLabel ${formatWithComma(compareValue)}", color = leftColor, fontSize = 10.sp)
            Text(text = "$rightLabel ${formatWithComma(rightValue)}", color = rightColor, fontSize = 10.sp)
        }else{
            Text(text = "$leftLabel $leftValue", color = leftColor)
            Text(text = "$rightLabel $rightValue", color = rightColor)
        }
    }
}
