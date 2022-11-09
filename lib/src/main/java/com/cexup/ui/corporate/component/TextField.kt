package com.cexup.ui.corporate.component

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import org.joda.time.DateTime
import java.util.*

@ExperimentalComposeUiApi
@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    typeTextField:Int = 0,
    nameTextField: String,
    valueTextField: String = "",
    onValueChange:(String)->Unit,
    placeholderText: String,
    heightTextField: Dp = 50.54.dp,
    widthTextField: Dp = 208.dp,
    isPassword: Boolean = false,
    keyboardType: KeyboardType =KeyboardType.Text,
    leadingIcon: @Composable ((isPassword: Boolean) -> Unit)? = null,
    trailingIcon: @Composable ((isPassword: Boolean) -> Unit)? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    var valueTextField by remember{ mutableStateOf("") }
    var dateSelect by remember {
        mutableStateOf<Long>(0)
    }
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    Column {
        Text(
            text = nameTextField,
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            style = MaterialTheme.typography.body1,
            color = Heading,
        )
        when(typeTextField) {
            0 -> {
                OutlinedTextField(
                    value = valueTextField,
                    onValueChange = {
                        valueTextField = it
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = BackgroundLight,
                        focusedBorderColor = SecondaryCorporate,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent
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
            1 -> {
                val datePickerDialog = DatePickerDialog(
                    ctx,
                    { _: DatePicker, year: Int, month: Int, day: Int ->
                        valueTextField = "$year-${month + 1}-${day}"
                        val date = DateTime(year, (month + 1), day, 0, 0)
                        dateSelect = date.millis
                    },
                    year, month, day,
                )
                Box{
                    OutlinedTextField(
                        modifier = Modifier
                            .width(widthTextField)
                            .height(heightTextField)
                            .clickable {
                                datePickerDialog.show()
                            }
                            .coloredShadow(
                                color = Color.Black.copy(0.25f),
                                offsetY = 4.dp,
                                borderRadius = 5.dp,
                                shadowRadius = 2.dp
                            ),
                        value = valueTextField,
                        onValueChange = {
                            valueTextField = it
                            onValueChange(it)
                        },
                        shape = RoundedCornerShape(5.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = BackgroundLight,
                            focusedBorderColor = SecondaryCorporate,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent
                        ),
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
                        textStyle = MaterialTheme.typography.h6.copy(
                            color = Color.Black
                        ),
                        enabled = false,
                        readOnly = true
                    )
                }
            }
            2 -> {
                OutlinedTextField(
                    value = valueTextField,
                    onValueChange = {
                        valueTextField = it
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = BackgroundLight,
                        focusedBorderColor = SecondaryCorporate,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
                )
            }
        }
    }
}