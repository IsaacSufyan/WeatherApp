package com.isaacsufyan.coreui.reusable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.isaacsufyan.coreui.component.gradientBackgroundBrush

@Composable
fun IsaacSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background( gradientBackgroundBrush(true))
                .safeDrawingPadding()
        ) {
            content()
        }
    }
}
