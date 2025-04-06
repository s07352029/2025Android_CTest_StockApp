package com.example.stockapp.ui.item.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stockapp.model.SortOrder
import com.example.stockapp.model.SortType

@Composable
fun SortOptionItem(type: SortType, currentSortType: SortType?, currentSortOrder: SortOrder, onClick: () -> Unit) {

    val icon = when {
        currentSortType != type || currentSortOrder == SortOrder.NONE -> null
        currentSortOrder == SortOrder.ASCENDING -> Icons.Default.ArrowUpward
        currentSortOrder == SortOrder.DESCENDING -> Icons.Default.ArrowDownward
        else -> null
    }

    val primaryColor = MaterialTheme.colorScheme.primary
    val isSelected = currentSortType == type && currentSortOrder != SortOrder.NONE

    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        headlineContent = {
            Text(
                text = type.label,
                color = if (isSelected) primaryColor else MaterialTheme.colorScheme.onSurface
            )
        },
        trailingContent = {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = primaryColor
                )
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = if (isSelected) primaryColor.copy(alpha = 0.1f) else Color.Transparent
        )
    )
}
