package com.cexup.ui.corporate.component

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.Octicons
import compose.icons.octicons.ChevronRight16
import java.util.*
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.GrayGlucose
import com.cexup.ui.corporate.theme.GrayTextField

object dropDownType{
    const val DEFFAULT = 0
    const val HOURS = 1
    const val DATE = 2
}

@Composable
fun DropDownCorporate(
    dropDownWidth: Dp = 0.dp,
    textFieldWidth: Dp = 0.dp,
    typeDropDown:Int,
    listDropDown: List<String> = listOf(),
    valueTextFieldDefault: String = "",
    isEnable:Boolean = true,
    onChange:(String) -> Unit = {},
){
    var valueTextField by remember{ mutableStateOf(valueTextFieldDefault) }
    var stateOnClick by remember { mutableStateOf(false) }
    val ctx = LocalContext.current

    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val hours = c.get(Calendar.HOUR_OF_DAY)
    val minutes = c.get(Calendar.MINUTE)
    when(typeDropDown){
        dropDownType.DEFFAULT -> {
            Box {
                OutlinedTextField(
                    modifier = Modifier
                        .width(textFieldWidth)
                        .clickable {
                            if(isEnable)
                                stateOnClick = !stateOnClick
                        },
                    value = valueTextField.ifEmpty { "Glucose" },
                    onValueChange = {
                        valueTextField = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = GrayTextField,
                        disabledBorderColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White
                    ),
                    textStyle = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color.Black
                    ),
                    trailingIcon = {
                        Icon(
                            Octicons.ChevronRight16,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(23.11.dp)
                                .rotate(if (stateOnClick) 90f else 0f),
                            tint = Color.Black
                        )
                    },
                    enabled = false,
                    readOnly = true,
                )
                DropdownMenu(
                    expanded = stateOnClick,
                    onDismissRequest = { stateOnClick = false },
                    modifier = Modifier
                        .width(dropDownWidth)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    listDropDown.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                valueTextField = item
                                onChange(valueTextField)
                                stateOnClick = false
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = item,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = MaterialTheme.colors.onBackground,
                                        fontWeight = FontWeight(500),
                                        lineHeight = 20.sp,
                                        letterSpacing = 0.5.sp,
                                        fontSize = 12.sp
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
        dropDownType.HOURS -> {
            val timePicker = TimePickerDialog(
                ctx,{ _,hour,minute ->
                    val minutefinal = if(minute<10)"0$minute" else minute
                    val hourfinal = if(hour<10)"0$hour" else hour
                    valueTextField = "$hourfinal:$minutefinal"
                    onChange(valueTextField)
                },hours,minutes, true
            )
            Box {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            timePicker.show()
                        },
                    value = "Time",
                    onValueChange = {
                        valueTextField = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = GrayTextField,
                        disabledBorderColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White
                    ),
                    textStyle = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color.Black
                    ),
                    trailingIcon = {
                        Row {
                            Text(
                                text = valueTextField,
                                style = MaterialTheme.typography.h1.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    color = GrayGlucose
                                ),
                            )
                        }
                    },
                    enabled = false,
                    readOnly = true,
                )
            }
        }
        dropDownType.DATE -> {
            val datePickerDialog = DatePickerDialog(
                ctx,
                { _: DatePicker, year: Int, month: Int, day: Int ->
                    val finalMonth = if(month<10) "0${month}" else month
                    val finalDay = if(day<10) "0$day" else day
                    valueTextField = "$year-$finalMonth-$finalDay"
                    onChange(valueTextField)
                },
                year, month, day,
            )
            Box{
                OutlinedTextField(
                    modifier = Modifier
                        .width(textFieldWidth)
                        .clickable {
                            datePickerDialog.show()
                        },
                    value = stringResource(id = R.string.date),
                    onValueChange = {
                        valueTextField = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = GrayTextField,
                        disabledBorderColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White
                    ),
                    textStyle = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color.Black
                    ),
                    trailingIcon = {
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = valueTextField,
                                style = MaterialTheme.typography.h1.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    color = GrayGlucose
                                ),
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .width(15.dp)
                                    .height(16.67.dp)
                            )
                        }
                    },
                    enabled = false,
                    readOnly = true
                )
            }
        }
    }
}