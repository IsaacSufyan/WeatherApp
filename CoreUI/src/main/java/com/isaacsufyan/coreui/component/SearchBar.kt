package com.isaacsufyan.coreui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.isaacsufyan.coreui.theme.White


@Preview
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    maxLines: Int = 1,
    cornerShape: CornerBasedShape = MaterialTheme.shapes.small,
    singleLine: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    focusedBorderColor: Color = Color.White,
    unFocusedBorderColor: Color = Color.White,
    leadingIconColor: Color = Color.White,
    trailingIconColor: Color = Color.White,
    cursorColor: Color = Color.White,
    onDoneActionClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {},
    onValueChanged: (String) -> Unit = {}
) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { onFocusChanged(it) },
        value = value,
        onValueChange = {
            onValueChanged(it)
        },
        placeholder = {
            Text(
                text = label,
                color = White
            )
        },
        textStyle = MaterialTheme.typography.subtitle1,
        shape = cornerShape,
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Search Icon"
                )
            }
        },
        singleLine = singleLine,
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusedBorderColor,
            unfocusedBorderColor = unFocusedBorderColor,
            cursorColor = cursorColor,
            leadingIconColor = leadingIconColor,
            trailingIconColor = trailingIconColor
        ),
        trailingIcon = {
            if (value.isNotBlank()) {
                if (trailingIcon != null) {
                    Icon(
                        modifier = Modifier.clickable { onClearClick() },
                        imageVector = trailingIcon,
                        contentDescription = "Clear Icon"
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                onDoneActionClick()
            }
        )
    )
}
