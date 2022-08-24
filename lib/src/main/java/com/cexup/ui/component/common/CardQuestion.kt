package com.cexup.ui.component.common

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.component.form.TextInput


/**
 *
 *  Created at 14/01/22 11.10
 * Created by Trian Damai
 * https://dribbble.com/shots/16627806--Dogma-Greyscale-design
 */
@Composable
fun CardQuestion(
    modifier: Modifier = Modifier,
    question: String,
    textValue: String,
    placeHolder: String,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    onClick:()->Unit = {},
    onChange:(value:String)->Unit = {},
    suffix: @Composable (() -> Unit) = {},
) {

    var inputValue by remember { mutableStateOf(textValue) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.surface)
            .padding(all = 16.dp)

    ) {
        Column {
            Text(
                text = question,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = modifier.height(16.dp))
            TextInput(
                modifier = modifier.fillMaxWidth(),
                value = inputValue,
                isError = false,
                placeholder = placeHolder,
                onChange = {
                    inputValue = it
                    onChange(it)
                },
                suffix = suffix,
                onClick = onClick,
                readOnly = readOnly,
                enabled = enabled
            )
        }
    }
}

@Composable
fun CardInputManualObgyn(
    modifier: Modifier = Modifier,
    isError:Boolean = false,
    question: String,
    title1: String = "",
    title2: String = "",
    value1: String,
    value2: String = "",
    placeHolder1: String,
    placeHolder2: String = "",
    suffix1: String = "",
    suffix2: String = "",
    lengthTextField: Int = 1,
) {
    var inputValue1 by remember {
        mutableStateOf(value1)
    }

    var inputValue2 by remember {
        mutableStateOf(value2)
    }

    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        backgroundColor = Color.DarkGray
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = question,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = modifier.height(10.dp))
            if (lengthTextField == 1) {
                TextInput(modifier.fillMaxWidth(),
                    value = inputValue1,
                    placeholder = placeHolder1,
                    isError = isError,
                    onChange = {
                        inputValue1 = it
                    },
                    suffix = { Text(text = suffix1) }
                )
            }
            if (lengthTextField == 2) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = title1,
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 12.sp,
                                letterSpacing = 1.sp
                            ),
                        )
                        Spacer(modifier = modifier.height(2.dp))
                        TextInput(modifier = modifier.width(currentWidth / 2 - 32.dp),
                            value = inputValue1,
                            isError = false,
                            placeholder = placeHolder1,
                            onChange = {
                                inputValue1 = it
                            },
                            suffix = { Text(text = suffix1) })
                    }
                    Column {
                        Text(
                            text = title2,
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 12.sp,
                                letterSpacing = 1.sp
                            ),
                        )
                        Spacer(modifier.height(2.dp))
                        TextInput(modifier = modifier.width(currentWidth / 2 - 32.dp),
                            value = inputValue2,
                            isError = false,
                            placeholder = placeHolder2,
                            onChange = {
                                inputValue2 = it
                            },
                            suffix = { Text(text = suffix2) })
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewCardQustion() {
    MaterialTheme {
        CardInputManualObgyn(
            question = "3. Berapakah Body Mass Index anda?",
            value1 = "bmi",
            placeHolder1 = "22",
            lengthTextField = 1,
        )
    }
}

@Preview
@Composable
fun PreviewCardQustionn() {
    MaterialTheme {
        CardInputManualObgyn(
            question = "2. Berapakah tinggi dan berat badan anda?",
            title1 = "Weight",
            value1 = "weight",
            placeHolder1 = "68.00",
            suffix1 = "Kg",
            lengthTextField = 2,
            title2 = "Height",
            value2 = "height",
            placeHolder2 = "170",
            suffix2 = "Cm"
        )
    }
}


@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun PreviewCardQuestion() {
    MaterialTheme {
        CardQuestion(textValue = "", placeHolder = "2", question = "1. Berapa 1+1?")
    }
}