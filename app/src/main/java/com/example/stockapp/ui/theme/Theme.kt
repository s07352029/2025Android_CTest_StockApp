package com.example.stockapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    background = LightBackground,
    error = ErrorRed
)

private val DarkColors = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    background = DarkBackground,
    error = ErrorRed
)

@Composable
fun MainTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
