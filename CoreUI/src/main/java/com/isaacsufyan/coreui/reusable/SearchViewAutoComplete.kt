package com.isaacsufyan.coreui.reusable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.isaacsufyan.coreui.R
import com.isaacsufyan.coreui.component.SearchBar
import com.isaacsufyan.coreui.component.autocomplete.AutoCompleteBox
import com.isaacsufyan.coreui.component.autocomplete.utils.asAutoCompleteEntities
import java.util.Locale

@Composable
fun SearchViewAutoComplete(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItem: (String) -> Unit
) {

    val autoCompleteEntities = items.asAutoCompleteEntities(
        filter = { item, query ->
            item.lowercase(Locale.getDefault())
                .startsWith(query.lowercase(Locale.getDefault()))
        }
    )

    AutoCompleteBox(
        modifier = modifier,
        items = autoCompleteEntities,
        itemContent = { item ->
            ValueAutoCompleteItem(item.value)
        }
    ) {
        var value by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        onItemSelected { item ->
            value = item.value
            filter(value)
            selectedItem(value)
            focusManager.clearFocus()
        }

        SearchBar(
            value = value,
            label = stringResource(R.string.select_cities),
            onDoneActionClick = {
                selectedItem(value)
                focusManager.clearFocus()
            },
            onClearClick = {
                value = ""
                filter(value)
                focusManager.clearFocus()
            },
            onFocusChanged = { focusState ->
                isSearching = focusState.hasFocus
            },
            onValueChanged = { query ->
                value = query
                filter(value)
            }
        )
    }
}

@Composable
private fun ValueAutoCompleteItem(item: String) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.subtitle2
        )
    }
}
