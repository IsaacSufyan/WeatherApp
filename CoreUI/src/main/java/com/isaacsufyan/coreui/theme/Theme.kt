package com.isaacsufyan.coreui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val DarkColorPalette = darkColors(
    primary = DarkBlue,
    onPrimary = White,
    secondary = MediumBlue,
    onSecondary = White,
    surface = White,
    onSurface = White,
    error = Purple,
    background = White,
    onBackground = White,
    primaryVariant = White,
    secondaryVariant = White
)

private val LightColorPalette = lightColors(
    primary = DarkBlue,
    onPrimary = White,
    secondary = MediumBlue,
    onSecondary = White,
    surface = White,
    onSurface = White,
    error = Purple,
    background = White,
    onBackground = White,
    primaryVariant = White,
    secondaryVariant = White
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors()
    } else {
        lightColors()
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
