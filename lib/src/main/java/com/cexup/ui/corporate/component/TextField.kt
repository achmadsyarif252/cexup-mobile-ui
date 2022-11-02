package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.BackgroundLight
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.coloredShadow

@ExperimentalComposeUiApi
@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    nameTextField: String,
    valueTextField: String = "",
    onValueChange:(String)->Unit,
    placeholderText: String,
    heightTextField: Dp = 50.54.dp,
    widthTextField: Dp = 208.dp,
    isPassword: Boolean = false,
    leadingIcon: @Composable ((isPassword: Boolean) -> Unit)? = null,
    trailingIcon: @Composable ((isPassword: Boolean) -> Unit)? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        Text(
            text = nameTextField,
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            style = MaterialTheme.typography.body1,
            color = Heading,

            )
        OutlinedTextField(
            value = valueTextField,
            onValueChange = {
                onValueChange(it)
            },
            placeholder = {
                Text(
                    text = placeholderText,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(300),
                        color = inactive
                    ),
                )
            },

            singleLine = true,
            modifier = modifier
                .width(widthTextField)
                .height(heightTextField)
                .coloredShadow(
                    color = Color.Black.copy(0.25f),
                    offsetY = 4.dp,
                    borderRadius = 5.dp,
                    shadowRadius = 2.dp
                ),
            shape = RoundedCornerShape(5.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BackgroundLight,
                focusedIndicatorColor = SecondaryCorporate,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            trailingIcon = {
                if (trailingIcon == null) {
                    Box {}
                } else {
                    trailingIcon(isPassword)
                }
            },
            leadingIcon = {
                if (leadingIcon == null) {
                    Box {}
                } else {
                    leadingIcon(isPassword)
                }
            }
        )
    }
}