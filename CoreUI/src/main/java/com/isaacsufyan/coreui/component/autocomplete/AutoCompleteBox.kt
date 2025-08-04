package com.isaacsufyan.coreui.component.autocomplete

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp


@Composable
fun <T : AutoCompleteEntity> AutoCompleteBox(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemContent: @Composable (T) -> Unit,
    content: @Composable AutoCompleteScope<T>.() -> Unit
) {
    val autoCompleteState = remember { AutoCompleteState(startItems = items) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        autoCompleteState.content()
        val contentPadding =
            if (autoCompleteState.filteredItems.isNotEmpty()) PaddingValues(vertical = 16.dp)
            else PaddingValues(0.dp)

        AnimatedVisibility(visible = autoCompleteState.isSearching) {
            LazyColumn(
                modifier = Modifier.customModifier(autoCompleteState),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = contentPadding
            ) {
                items(autoCompleteState.filteredItems) { item ->
                    Box(
                        modifier = Modifier.clickable {
                            autoCompleteState.selectItem(item)
                        }
                    ) {
                        itemContent(item)
                    }
                }
            }
        }
    }
}

@SuppressLint("UnnecessaryComposedModifier")
private fun Modifier.customModifier(
    autoCompleteItemScope: AutoCompleteDesignScope
): Modifier = composed {
    val baseModifier = if (autoCompleteItemScope.shouldWrapContentHeight) wrapContentHeight()
    else heightIn(0.dp, autoCompleteItemScope.boxMaxHeight)

    baseModifier
        .fillMaxWidth(autoCompleteItemScope.boxWidthPercentage)
        .border(
            border = autoCompleteItemScope.boxBorderStroke,
            shape = MaterialTheme.shapes.small
        )
}
