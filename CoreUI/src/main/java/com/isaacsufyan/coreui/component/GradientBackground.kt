package com.isaacsufyan.coreui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.isaacsufyan.coreui.theme.AppBackGroundGradient

@Composable
fun gradientBackgroundBrush(
    isLinear: Boolean = false
): Brush {

    val endOffset = if (isLinear) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }
    return Brush.linearGradient(
        colors = AppBackGroundGradient,
        start = Offset.Zero,
        end = endOffset
    )
}