package com.example.stockapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import com.example.stockapp.ui.screen.home.HomeScreen
import com.example.stockapp.ui.theme.DarkBackground
import com.example.stockapp.ui.theme.LightBackground
import com.example.stockapp.ui.theme.MainTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode by rememberSaveable { mutableStateOf(false) }

            MainTheme(useDarkTheme = isDarkMode) {
                val systemUiController = rememberSystemUiController()
                val statusBarColor = if (!isDarkMode) LightBackground else DarkBackground
                val useDarkIcons = !isDarkMode

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = statusBarColor,
                        darkIcons = useDarkIcons
                    )
                }

                HomeScreen(
                    isDarkMode = isDarkMode,
                    onToggleDarkMode = { isDarkMode = !isDarkMode }
                )
            }
        }
    }
}

