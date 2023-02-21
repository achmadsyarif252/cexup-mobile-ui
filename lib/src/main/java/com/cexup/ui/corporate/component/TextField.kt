package com.cexup.ui.corporate.component

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.component.common.TextFieldCexup
import com.cexup.ui.corporate.theme.BackgroundLight
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.Eye24
import org.joda.time.DateTime
import java.util.*

@ExperimentalComposeUiApi
@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    typeTextField: Int = 0,
    nameTextField: String,
    valueTextField: String = "",
    onValueChange: (String) -> Unit,
    placeholderText: String,
    heightTextField: Dp = 50.54.dp,
    widthTextField: Dp = 208.dp,
    maxCharacter: Int = Int.MAX_VALUE,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable ((isPassword: Boolean) -> Unit)? = null,
    trailingIcon: @Composable ((isPassword: Boolean) -> Unit)? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val ctx = LocalConfiguration.current
    val context = LocalContext.current
    var valueTextField by remember { mutableStateOf(valueTextField) }
    var isHidePassword by remember { mutableStateOf(true) }
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
            fontSize = 16.sp.from(ctx),
            fontWeight = FontWeight(400),
            style = MaterialTheme.typography.body1,
            color = Heading,
        )
        when (typeTextField) {
            0 -> {
                TextFieldCexup(
                    value = valueTextField,
                    onValueChange = {
                        valueTextField = it
                        onValueChange(it)
                    },
                    placeholder = {
                        Text(
                            text = placeholderText,
                            style = MaterialTheme.typography.body1.copy(
                                fontSize = 12.sp.from(ctx),
                                fontWeight = FontWeight(300),
                                color = inactive
                            ),
                        )
                    },

                    singleLine = true,
                    modifier = modifier
                        .width(widthTextField.from(ctx))
                        .height(heightTextField.from(ctx))
                        .coloredShadow(
                            color = Color.Black.copy(0.25f),
                            offsetY = 4.dp.from(ctx),
                            borderRadius = 5.dp.from(ctx),
                            shadowRadius = 2.dp.from(ctx)
                        ),
                    shape = RoundedCornerShape(5.dp.from(ctx)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = BackgroundLight,
                        focusedBorderColor = SecondaryCorporate,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent
                    ),
                    visualTransformation = if (isHidePassword) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    trailingIcon = {
                        Icon(
                            Octicons.Eye24, "",
                            modifier = Modifier.clickable {
                                isHidePassword = !isHidePassword
                            }
                        )
                    },
                )
            }
            1 -> {
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _: DatePicker, year: Int, month: Int, day: Int ->
                        val monthF =  month +1
                        val finalMonth = if(monthF<10) "0${monthF}" else monthF
                        val finalDay = if(day<10) "0$day" else day
                        valueTextField = "$year-$finalMonth-$finalDay"
                        onValueChange(valueTextField)
                    },
                    year, month, day,
                )
                TextFieldCexup(
                    value = valueTextField,
                    onValueChange = {
                        valueTextField = it
                    },
                    placeholder = {
                        Text(
                            text = placeholderText,
                            style = MaterialTheme.typography.body1.copy(
                                fontSize = 12.sp.from(ctx),
                                fontWeight = FontWeight(300),
                                color = inactive
                            ),
                        )
                    },
                    singleLine = true,
                    modifier = modifier
                        .width(widthTextField.from(ctx))
                        .height(heightTextField.from(ctx))
                        .clickable {
                            datePickerDialog.show()
                        }
                        .coloredShadow(
                            color = Color.Black.copy(0.25f),
                            offsetY = 4.dp.from(ctx),
                            borderRadius = 5.dp.from(ctx),
                            shadowRadius = 2.dp.from(ctx)
                        ),
                    shape = RoundedCornerShape(5.dp.from(ctx)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = BackgroundLight,
                        focusedBorderColor = SecondaryCorporate,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent
                    ),
                    enabled = false,
                    readOnly = true,
                    textStyle = MaterialTheme.typography.h6.copy(
                        color = Color.Black
                    ),

                )
            }
            2 -> {
                TextFieldCexup(
                    value = valueTextField,
                    onValueChange = {
                        if (it.length <= maxCharacter) {
                            valueTextField = it
                            onValueChange(it)
                        }
                    },
                    placeholder = {
                        Text(
                            text = placeholderText,
                            style = MaterialTheme.typography.body1.copy(
                                fontSize = 12.sp.from(ctx),
                                fontWeight = FontWeight(300),
                                color = inactive
                            ),
                        )
                    },
                    singleLine = true,
                    modifier = modifier
                        .width(widthTextField.from(ctx))
                        .height(heightTextField.from(ctx))
                        .coloredShadow(
                            color = Color.Black.copy(0.25f),
                            offsetY = 4.dp.from(ctx),
                            borderRadius = 5.dp.from(ctx),
                            shadowRadius = 2.dp.from(ctx)
                        ),
                    shape = RoundedCornerShape(5.dp.from(ctx)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = BackgroundLight,
                        focusedBorderColor = SecondaryCorporate,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                )
            }
        }
    }
}