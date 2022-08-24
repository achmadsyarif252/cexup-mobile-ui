package com.cexup.ui.component.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    value: String = "",
    isError: Boolean = false,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Number,
    suffix: @Composable (() -> Unit) = {},
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onClick: () -> Unit = {},
    onChange: (String) -> Unit,
) {
    var inputValue by remember {
        mutableStateOf(value)
    }
    TextField(
        value = inputValue,
        isError = isError,
        onValueChange = {
            inputValue = it
            onChange(it)
        },
        enabled = enabled,
        readOnly = readOnly,
        placeholder = {
            Text(text = placeholder)
        },
        trailingIcon = suffix,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        modifier = modifier
            .navigationBarsWithImePadding()
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
    )
}