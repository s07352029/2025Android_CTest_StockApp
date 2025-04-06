package com.example.stockapp.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockapp.model.SortType
import com.example.stockapp.ui.item.home.SortOptionItem
import com.example.stockapp.ui.item.home.StockItem
import com.example.stockapp.ui.theme.DarkBackground
import com.example.stockapp.ui.theme.LightBackground
import com.example.stockapp.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by rememberSaveable { mutableStateOf(false) }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = if (isDarkMode) DarkBackground else LightBackground
        ) {
            Column(
                modifier = Modifier
                    .padding(WindowInsets.safeContent.asPaddingValues())
                    .fillMaxWidth()
            ) {
                SortOptionItem(SortType.CODE,
                    currentSortType = viewModel.getCurrentSortType(),
                    currentSortOrder = viewModel.getCurrentSortOrder(),
                    onClick = {
                        viewModel.sortStockList(SortType.CODE)
                        showSheet = false
                    })
                SortOptionItem(SortType.CHANGE,
                    currentSortType = viewModel.getCurrentSortType(),
                    currentSortOrder = viewModel.getCurrentSortOrder(),
                    onClick = {
                        viewModel.sortStockList(SortType.CHANGE)
                        showSheet = false
                    })
                SortOptionItem(SortType.PRICE,
                    currentSortType = viewModel.getCurrentSortType(),
                    currentSortOrder = viewModel.getCurrentSortOrder(),
                    onClick = {
                        viewModel.sortStockList(SortType.PRICE)
                        showSheet = false
                    })
                SortOptionItem(SortType.DIVIDEND,
                    currentSortType = viewModel.getCurrentSortType(),
                    currentSortOrder = viewModel.getCurrentSortOrder(),
                    onClick = {
                        viewModel.sortStockList(SortType.DIVIDEND)
                        showSheet = false
                    })
            }
        }
    }

    Scaffold(
        containerColor = if (isDarkMode) DarkBackground else LightBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "臺灣證券查詢",
                        style = MaterialTheme.typography.titleLarge,
                        color = Primary,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { showSheet = true }) {
                        Icon(Icons.Default.FilterList, contentDescription = "排序", tint = Primary)
                    }
                    IconButton(onClick = onToggleDarkMode) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "切換主題",
                            tint = Primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (isDarkMode) DarkBackground else LightBackground,
                    titleContentColor = Primary
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f))
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Primary
                        )
                    }
                }

                uiState.errorMessage != null -> {
                    Text(
                        text = uiState.errorMessage ?: "錯誤",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    LazyColumn(modifier = Modifier.padding(16.dp)) {
                        items(uiState.stockList) { stock ->
                            StockItem(stock = stock)
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
        }
    }
}