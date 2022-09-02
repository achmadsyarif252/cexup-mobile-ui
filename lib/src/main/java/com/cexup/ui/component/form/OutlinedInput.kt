package com.cexup.ui.component.form


import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.Darkgrayishblue
import com.cexup.ui.consumer.theme.fonts
import com.cexup.ui.utils.mediaquery.from

@Composable
fun OutlinedInput(
    value: String="",
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "",
    placeholder: String = "",
    errorMessage:String?=null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
) {
    val ctx = LocalContext.current


    Column(
        modifier = Modifier
            .padding(
                bottom = if (errorMessage != null) {
                    0.dp
                } else {
                    10.dp.from(ctx)
                }
            )
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = modifier,
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2.copy(
                        letterSpacing = 0.1.sp.from(ctx),
                        fontSize = 14.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = MaterialTheme.colors.onSecondary
                    )
                )
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.h1.copy(
                        letterSpacing = 0.1.sp.from(ctx),
                        fontSize = 14.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = Darkgrayishblue
                    )
                )
            },
            textStyle = TextStyle(
                fontFamily = fonts,
                fontWeight = FontWeight.Normal,
                letterSpacing = 0.1.sp.from(ctx),
                fontSize = 14.sp.from(ctx),
                lineHeight = 20.sp.from(ctx),
                color = MaterialTheme.colors.onBackground
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.onSecondary,
                cursorColor = MaterialTheme.colors.onBackground
            ),
            enabled = enabled,
            singleLine=singleLine,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError=errorMessage != null,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = shape,
            readOnly = readOnly
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontSize = 8.sp.from(ctx),
                    lineHeight = 20.sp.from(ctx),
                    letterSpacing = 0.8.sp.from(ctx)
                ),
                modifier = Modifier.padding(start = 16.dp.from(ctx))
            )
        }
    }

}



@Preview
@Composable
fun PreviewInput(){
    ConsumerTheme {
        val ctx = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            OutlinedInput(onChange = {})

            OutlinedInput(onChange = {}, errorMessage = "Error", label = "Nama", placeholder = "Nama Kamu")

            OutlinedInput(onChange = {},  label = "Nama", placeholder = "Nama Kamu")

        }

    }
}