@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.app_corporate.ui.component.cards

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.ValueHemoglobin
import com.cexup.ui.corporate.screen.InsulinType
import com.cexup.ui.corporate.screen.MealType
import com.cexup.ui.corporate.screen.StateGlucoseSDK
import com.cexup.ui.corporate.screen.ValueBloodGlucose
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import com.example.app_corporate.ui.component.ChartGlucose

@Composable
fun CardHemoglobin(
    dataHemoglobin: List<ValueHemoglobin> = listOf(),
    onHistoryClicked: (Boolean) -> Unit,
) {
    val interactionSource = MutableInteractionSource()
    val ctx = LocalContext.current
    val textValue =
        if (dataHemoglobin.isEmpty()) {
            "-"
        } else {
            dataHemoglobin.get(0).valueHemoglobin.toString()
        }
    Card(
        modifier = Modifier
            .widthIn(max = 343.dp.from(ctx))
            .height(116.dp.from(ctx))
            .coloredShadow(
                color = Color.Black,
                alpha = 0.1f,
                borderRadius = 10.dp.from(ctx),
                shadowRadius = 20.dp.from(ctx),
                offsetY = 0.dp.from(ctx)
            ),
        shape = RoundedCornerShape(10.dp.from(ctx)),
        elevation = 0.6.dp.from(ctx),
    ) {
        Column(
            modifier = Modifier.padding(16.dp.from(ctx)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.hemoglobin) + ": ",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 28.sp.from(ctx)
                    )
                )
                Text(
                    text = "$textValue ",
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp.from(ctx),
                        lineHeight = 32.sp.from(ctx),
                        letterSpacing = -2.sp.from(ctx),
                        color = GreenGlucose
                    )
                )
                Text(
                    text = stringResource(id = R.string.mg_per_dl),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 28.sp.from(ctx),
                        color = GreenGlucose
                    )
                )
            }
            Divider(color = GrayDivider, thickness = 1.dp.from(ctx))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp.from(ctx))
                    .heightIn(min = 30.dp.from(ctx))
                    .clickable(
                        enabled = textValue != "-",
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onHistoryClicked(true)
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(16.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_history),
                        contentDescription = "History Hemoglobin",
                        tint = BlueJade
                    )
                    Spacer(modifier = Modifier.width(8.dp.from(ctx)))
                    Text(
                        text = stringResource(id = R.string.history),
                        style = MaterialTheme.typography.h2.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            letterSpacing = -0.25.sp.from(ctx),
                            color = BlueJade
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CardPills(valuePills: Int = 0) {

    val ctx = LocalContext.current
    Box(
        modifier = Modifier.background(BlueGlucose, RoundedCornerShape(10.dp.from(ctx)))
    ) {
        Column(
            modifier = Modifier
                .height(116.dp.from(ctx))
                .widthIn(max = 164.dp.from(ctx))
                .padding(16.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
        ) {
            Row {
                Text(
                    text = stringResource(id = R.string.pills),
                    style = MaterialTheme.typography.h1.copy(
                        color = BlueDarkGlucose,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(id = R.drawable.ic_pills), contentDescription = "")
            }
            Row {
                Text(
                    text = valuePills.toString(),
                    style = MaterialTheme.typography.h1.copy(
                        color = BlueDarkGlucose,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = " " + stringResource(id = R.string.taken),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                    )
                )
            }
        }
    }
}

@Composable
fun CardValueGlucose(valueGlucose: Int = 0) {

    val ctx = LocalContext.current
    Box(
        modifier = Modifier.background(RedGlucose, RoundedCornerShape(10.dp.from(ctx)))
    ) {
        Column(
            modifier = Modifier
                .height(116.dp.from(ctx))
                .widthIn(max = 164.dp.from(ctx))
                .padding(16.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
        ) {
            Row {
                Text(
                    text = stringResource(id = R.string.glucose),
                    style = MaterialTheme.typography.h1.copy(
                        color = RedTextGlucose,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_blood_glucose),
                    contentDescription = ""
                )
            }
            Row {
                Text(
                    text = valueGlucose.toString(),
                    style = MaterialTheme.typography.h1.copy(
                        color = RedTextGlucose,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = " " + stringResource(id = R.string.mg_per_dl),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                    )
                )
            }
        }
    }
}

@Composable
fun CardConnectGlucose(
    onAddDataClicked: (Boolean) -> Unit,
    stateGlucoseSDK: StateGlucoseSDK,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
) {
    val ctx = LocalContext.current
    Box(
        modifier = Modifier.background(Heading, RoundedCornerShape(10.dp.from(ctx)))
    ) {
        Column(
            modifier = Modifier
                .height(116.dp.from(ctx))
                .widthIn(max = 201.dp.from(ctx))
                .padding(16.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Row {
                Text(
                    text = stringResource(id = R.string.add_data),
                    style = MaterialTheme.typography.h1.copy(
                        color = Color.White,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier
                    .clickable { onAddDataClicked(true) }
                    .background(Color.White, RoundedCornerShape(8.dp.from(ctx)))) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "",
                        tint = Heading
                    )
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (stateGlucoseSDK.connect) {
                        onDisconnect()
                    } else {
                        onConnect()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (stateGlucoseSDK.connect)
                        RedGlucose
                    else
                        BlueConnectGlucose
                ),
                contentPadding = PaddingValues(vertical = 8.dp.from(ctx)),
                shape = RoundedCornerShape(8.dp.from(ctx))
            ) {
                Text(
                    text =
                    if (stateGlucoseSDK.connect)
                        stringResource(id = R.string.disconnect)
                    else if (stateGlucoseSDK.connecting)
                        stringResource(id = R.string.connecting)
                    else
                        stringResource(id = R.string.connect),
                    style = MaterialTheme.typography.h1.copy(
                        color = if (stateGlucoseSDK.connect) RedHyperGlucose else BlueJade,
                        fontSize = 18.sp.from(ctx),
                        lineHeight = 28.sp.from(ctx),
                        letterSpacing = -0.25f.sp.from(ctx),
                        fontWeight = FontWeight.Medium
                    )
                )

            }
        }
    }
}

@Composable
fun CardGlucoseLevels(
    isList: Boolean,
    is1Day: Boolean,
    onAddMedicine: (Boolean, Long) -> Unit,
    onAddFoodAndDrink: (Boolean, Long) -> Unit,
    listDataValueGlucose: List<ValueBloodGlucose>,
    onRemoveData: (id: Long) -> Unit,
    onNoteRemovedClicked: (noteRemoved:String) -> Unit,
    onDetailsClicked: (typeMedicine: Int, brandMedicine: String, valueMedicine: Int, valueDetailMedicine: Int, FoodAndDrink: String) -> Unit,
    onIconClick: (isList: Boolean) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Add Medicine", "Add Food & Drink", "Remove")
    var selectedItem by remember { mutableStateOf(0) }
    var isAllData by remember { mutableStateOf(true) }
    val ctx = LocalContext.current
    val interactionSource = MutableInteractionSource()

    var dataGlucose = listDataValueGlucose
    var dataGlucoseStillAlive = mutableListOf<ValueBloodGlucose>()
    listDataValueGlucose.forEach{
        if (!it.isDeleted)
            dataGlucoseStillAlive.add(it)
    }
    if (isAllData)
        dataGlucose = listDataValueGlucose
    else
        dataGlucose = dataGlucoseStillAlive

    Card(
        modifier = Modifier
            .padding(horizontal = 15.dp.from(ctx))
            .fillMaxWidth()
            .coloredShadow(
                color = Color.Black,
                alpha = 0.1f,
                borderRadius = 2.dp.from(ctx),
                shadowRadius = 10.dp.from(ctx),
                offsetY = 0.dp.from(ctx)
            ),
        shape = RoundedCornerShape(10.dp.from(ctx)),
        elevation = 1.dp.from(ctx),
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp.from(ctx))
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp.from(ctx)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.weeks_glucose_levels),
                    style = MaterialTheme.typography.h2.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp.from(ctx),
                        lineHeight = 28.sp.from(ctx),
                        letterSpacing = -0.25f.sp.from(ctx),
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                if(isList) {
                    Text(
                        text = if (isAllData)
                            stringResource(id = R.string.show_all_data_on)
                        else
                            stringResource(id = R.string.show_all_data_off),
                        style = MaterialTheme.typography.h2.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            letterSpacing = -0.25f.sp.from(ctx),
                        )
                    )
                    Switch(
                        checked = isAllData,
                        onCheckedChange = {
                            isAllData = it
                        },
                        interactionSource = interactionSource,
                        colors = SwitchDefaults.colors(BlueJade)
                    )
                }
                Icon(
                    modifier = Modifier.clickable(
                        enabled = !isList,
                        onClick = {
                            onIconClick(true)
                        }
                    ),
                    painter = painterResource(id = R.drawable.ic_list_bullet),
                    contentDescription = "",
                    tint = if (isList) Heading else LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                )
                Icon(
                    modifier = Modifier.clickable(
                        enabled = isList,
                        onClick = {
                            onIconClick(false)
                        }
                    ),
                    painter = painterResource(id = R.drawable.ic_chart_bar),
                    contentDescription = "",
                    tint = if (!isList) Heading else LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                )
            }
            when (isList) {
                true -> {
                    Divider(color = GrayDivider, thickness = 1.dp.from(ctx))
                    dataGlucose.forEachIndexed { indexData, data ->
                        Row(
                            modifier = Modifier
                                .background(
                                    color =
                                    if (data.isDeleted)
                                        MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
                                    else
                                        Color.Transparent
                                )
                                .padding(vertical = 14.dp.from(ctx), horizontal = 16.dp.from(ctx)),
                            horizontalArrangement = Arrangement.spacedBy(32.dp.from(ctx))
                        ) {
                            Text(
                                modifier = Modifier.width(178.dp.from(ctx)),
                                text = data.time,
                                style = MaterialTheme.typography.body2.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp.from(ctx),
                                    lineHeight = 20.sp.from(ctx),
                                    color = GrayGlucose
                                ),
                            )
                            Text(
                                modifier = Modifier.width(178.dp.from(ctx)),
                                text = "${data.value} mg/dl",
                                style = MaterialTheme.typography.body2.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp.from(ctx),
                                    lineHeight = 20.sp.from(ctx),
                                    color = if (data.value > 160) RedHyperGlucose else if (data.value < 80) BlueHypoGlucose else GreenNormalGlucose
                                ),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = Modifier.width(178.dp.from(ctx)),
                                text =
                                if (data.mealType == MealType.AfterMeal)
                                    stringResource(id = R.string.after_meal)
                                else if (data.mealType == MealType.NoMeal)
                                    stringResource(id = R.string.no_meal)
                                else
                                    stringResource(id = R.string.before_meal),
                                style = MaterialTheme.typography.body2.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp.from(ctx),
                                    lineHeight = 20.sp.from(ctx),
                                ),
                                textAlign = TextAlign.Center
                            )
                            if (data.isDetail) {
                                Text(
                                    modifier = Modifier
                                        .width(178.dp.from(ctx))
                                        .clickable(
                                            interactionSource = interactionSource,
                                            indication = null
                                        ) {
                                            onDetailsClicked(
                                                data.typeMedicine,
                                                data.brandMedicine ?: "",
                                                data.valueMedicine ?: 0,
                                                data.valueDetailMedicine ?: 0,
                                                data.foodAndDrink ?: ""
                                            )
                                        },
                                    text = stringResource(id = R.string.details),
                                    style = MaterialTheme.typography.body2.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp.from(ctx),
                                        lineHeight = 20.sp.from(ctx),
                                        color = BlueJade,
                                        textDecoration = TextDecoration.Underline
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            if (!data.isDeleted) {
                                Box {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_threedot),
                                        contentDescription = "",
                                        modifier = Modifier.clickable {
                                            selectedItem = indexData
                                            expanded = true
                                        }
                                    )
                                    if (selectedItem == indexData) {
                                        DropdownMenu(
                                            expanded = expanded,
                                            onDismissRequest = { expanded = false },
                                            modifier = Modifier.width(170.dp.from(ctx))
                                        ) {
                                            items.forEachIndexed { index, text ->
                                                DropdownMenuItem(
                                                    onClick = {
                                                        if (items[index].equals("Add Food & Drink")) {
                                                            onAddFoodAndDrink(true, data.id)
                                                        } else if (items[index].equals("Remove")) {
                                                            onRemoveData(data.id)
                                                        } else {
                                                            onAddMedicine(true, data.id)
                                                        }
                                                        expanded = false
                                                    },

                                                    ) {
                                                    Text(
                                                        text = text,
                                                        style = MaterialTheme.typography.subtitle1.copy(
                                                            fontSize = 16.sp.from(ctx),
                                                            lineHeight = 28.sp.from(ctx),
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                Text(
                                    modifier = Modifier
                                        .width(100.dp.from(ctx))
                                        .clip(RoundedCornerShape(10.dp.from(ctx)))
                                        .background(Color.White)
                                        .clickable(
                                            interactionSource = interactionSource,
                                            indication = null
                                        ) {
                                            onNoteRemovedClicked(data.noteDeleted!!)
                                        },
                                    text = stringResource(id = R.string.note_removed),
                                    style = MaterialTheme.typography.body2.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp.from(ctx),
                                        lineHeight = 20.sp.from(ctx),
                                        color = MaterialTheme.colors.onSurface,
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }


                        }
                        Divider(color = GrayDivider, thickness = 1.dp.from(ctx))

                    }

                }
                else -> {
                    Column(
                        modifier = Modifier.height(380.dp.from(ctx))
                    ) {

                        ChartGlucose(
//                            dataWithInsulin = dataWithInsulin,
                            listGlucose = dataGlucoseStillAlive as List<ValueBloodGlucose>,
                            description = "",
                            is1Day = is1Day
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun CardAddDataGlucose(
    onValueChange: (String) -> Unit,
) {
    var valueTextField by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    Box(
        modifier = Modifier.background(RedGlucose, RoundedCornerShape(10.dp.from(ctx)))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx)),
            modifier = Modifier.padding(16.dp.from(ctx))
        ) {
            Text(
                text = stringResource(id = R.string.glucose_level),
                style = MaterialTheme.typography.h1.copy(
                    color = RedTextGlucose,
                    fontSize = 16.sp.from(ctx),
                    lineHeight = 24.sp.from(ctx),
                    fontWeight = FontWeight.Medium
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = valueTextField,
                onValueChange = {
                    valueTextField = it
                    onValueChange(it)
                },
                shape = RoundedCornerShape(10.dp.from(ctx)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    disabledBorderColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                textStyle = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 20.sp.from(ctx),
                    color = Color.Black
                ),
                trailingIcon = {
                    Text(
                        text = stringResource(id = R.string.mg_per_dl),
                        style = MaterialTheme.typography.body2.copy(
                            color = Color.Black,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.value),
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                            color = GrayAddDataTextField
                        ),
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            )

        }
    }
}

@Composable
fun CardAddDataInsulin(
    valueText: String = "",
    enable: Boolean = true,
    onValueChange: (String) -> Unit,
) {
    var valueTextField by remember { mutableStateOf(valueText) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    Box(
        modifier = Modifier.background(OrangeGlucose, RoundedCornerShape(10.dp.from(ctx)))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx)),
            modifier = Modifier.padding(16.dp.from(ctx))
        ) {
            Text(
                text = stringResource(id = R.string.insulin_amount),
                style = MaterialTheme.typography.h1.copy(
                    color = OrangeTextGlucose,
                    fontSize = 16.sp.from(ctx),
                    lineHeight = 24.sp.from(ctx),
                    fontWeight = FontWeight.Medium
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = valueTextField,
                onValueChange = {
                    valueTextField = it
                    onValueChange(it)
                },
                enabled = enable,
                shape = RoundedCornerShape(10.dp.from(ctx)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    disabledBorderColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                textStyle = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 20.sp.from(ctx),
                    color = Color.Black
                ),
                trailingIcon = {
                    Text(
                        text = stringResource(id = R.string.mg_per_dl),
                        style = MaterialTheme.typography.body2.copy(
                            color = Color.Black,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.value),
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                            color = GrayAddDataTextField
                        ),
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            )

        }
    }
}

@Composable
fun CardAddDataPill(
    valueText: String = "",
    enable: Boolean = true,
    onValueChange: (String) -> Unit,
) {
    var valueTextField by remember { mutableStateOf(valueText) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    Box(
        modifier = Modifier.background(BlueGlucose, RoundedCornerShape(10.dp.from(ctx)))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx)),
            modifier = Modifier.padding(16.dp.from(ctx))
        ) {
            Text(
                text = stringResource(id = R.string.dossage),
                style = MaterialTheme.typography.h1.copy(
                    color = BlueDarkGlucose,
                    fontSize = 16.sp.from(ctx),
                    lineHeight = 24.sp.from(ctx),
                    fontWeight = FontWeight.Medium
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = valueTextField,
                onValueChange = {
                    valueTextField = it
                    onValueChange(it)
                },
                enabled = enable,
                shape = RoundedCornerShape(10.dp.from(ctx)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    disabledBorderColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                textStyle = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 20.sp.from(ctx),
                    color = Color.Black
                ),
                trailingIcon = {
                    Text(
                        text = stringResource(id = R.string.mg_per_dl),
                        style = MaterialTheme.typography.body2.copy(
                            color = Color.Black,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.value),
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                            color = GrayAddDataTextField
                        ),
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            )
        }
    }
}

@Composable
fun CardDetailValueMedicine(
    valueText: String,
) {
    val ctx = LocalContext.current
    Box(
        modifier = Modifier
            .border(1.dp, GrayDivider, RoundedCornerShape(10.dp.from(ctx)))
            .clip(RoundedCornerShape(10.dp.from(ctx)))
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp.from(ctx))
                .fillMaxWidth(),
            text = valueText,
            style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp.from(ctx),
                lineHeight = 24.sp.from(ctx),
                color = Color.Black
            ),
            maxLines = 1,
        )

    }
}

@Composable
fun CardBrandMedicine(
    resourceStringTypeBrand: Int,
    valueText: String = "",
    enable: Boolean = true,
    onValueChange: (String) -> Unit,
) {
    var valueTextField by remember { mutableStateOf(valueText) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    Box(
        modifier = Modifier
            .border(1.dp, GrayDivider, RoundedCornerShape(10.dp.from(ctx)))
            .clip(RoundedCornerShape(10.dp.from(ctx)))
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = valueTextField,
            onValueChange = {
                valueTextField = it
                onValueChange(it)
            },
            enabled = enable,
            shape = RoundedCornerShape(10.dp.from(ctx)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                disabledBorderColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White
            ),
            textStyle = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp.from(ctx),
                lineHeight = 24.sp.from(ctx),
                color = Color.Black
            ),
            placeholder = {
                Text(
                    text = stringResource(id = resourceStringTypeBrand),
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        color = GrayAddDataTextField
                    ),
                )
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        )

    }
}

@Composable
fun CardGlucoseMeal(
    onSelectedMeal: (Int) -> Unit = {},
) {
    var stateMeal by remember { mutableStateOf(MealType.BeforeMeal) }
    val ctx = LocalContext.current
    Box(
        modifier = Modifier.background(BlueConnectGlucose, RoundedCornerShape(10.dp.from(ctx))),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp.from(ctx)),
        ) {
            OutlinedButton(
                modifier = Modifier
                    .height(100.dp.from(ctx))
                    .width(145.dp.from(ctx)),
                onClick = {
                    stateMeal = MealType.BeforeMeal
                    onSelectedMeal(MealType.BeforeMeal)
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    if (stateMeal == MealType.BeforeMeal)
                        BlueButtonSelected
                    else
                        BlueConnectGlucose
                ),
                border = BorderStroke(
                    1.dp.from(ctx),
                    if (stateMeal == MealType.BeforeMeal)
                        Heading
                    else
                        BlueConnectGlucose
                ),
                shape = RoundedCornerShape(20.dp.from(ctx))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(48.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_glucose_beforemeal),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(id = R.string.before_meal),
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                            color =
                            if (stateMeal == MealType.BeforeMeal)
                                Heading
                            else
                                GrayAddDataTextField,
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
            OutlinedButton(
                modifier = Modifier
                    .height(100.dp.from(ctx))
                    .width(145.dp.from(ctx)),
                onClick = {
                    stateMeal = MealType.AfterMeal
                    onSelectedMeal(MealType.AfterMeal)
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    if (stateMeal == MealType.AfterMeal)
                        BlueButtonSelected
                    else
                        BlueConnectGlucose
                ),
                border = BorderStroke(
                    1.dp.from(ctx),
                    if (stateMeal == MealType.AfterMeal)
                        Heading
                    else
                        BlueConnectGlucose
                ),
                shape = RoundedCornerShape(20.dp.from(ctx))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(48.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_glucose_aftermeal),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(id = R.string.after_meal),
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                            color =
                            if (stateMeal == MealType.AfterMeal)
                                Heading
                            else
                                GrayAddDataTextField
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
            OutlinedButton(
                modifier = Modifier
                    .height(100.dp.from(ctx))
                    .width(145.dp.from(ctx)),
                onClick = {
                    stateMeal = MealType.NoMeal
                    onSelectedMeal(MealType.NoMeal)
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    if (stateMeal == MealType.NoMeal)
                        BlueButtonSelected
                    else
                        BlueConnectGlucose
                ),
                border = BorderStroke(
                    1.dp.from(ctx),
                    if (stateMeal == MealType.NoMeal)
                        Heading
                    else
                        BlueConnectGlucose
                ),
                shape = RoundedCornerShape(20.dp.from(ctx))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(48.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_glucose_nomeal),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(id = R.string.no_meal),
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                            color =
                            if (stateMeal == MealType.NoMeal)
                                Heading
                            else
                                GrayAddDataTextField
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun CardGlucoseActing(
    onSelectedActing: (Int) -> Unit = {},
) {
    var stateActing by remember { mutableStateOf(InsulinType.ShortActing) }
    val ctx = LocalContext.current
    Box(
        modifier = Modifier.background(OrangeGlucose, RoundedCornerShape(10.dp.from(ctx))),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp.from(ctx)),
            horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx))
        ) {
            OutlinedButton(
                modifier = Modifier
                    .height(100.dp.from(ctx))
                    .width(245.5f.dp.from(ctx)),
                onClick = {
                    stateActing = InsulinType.ShortActing
                    onSelectedActing(stateActing)
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    if (stateActing == InsulinType.ShortActing)
                        OrangeButtonSelected
                    else
                        OrangeGlucose
                ),
                border = BorderStroke(
                    1.dp.from(ctx),
                    if (stateActing == InsulinType.ShortActing)
                        OrangeTextGlucose
                    else
                        OrangeGlucose
                ),
                shape = RoundedCornerShape(20.dp.from(ctx))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(48.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_glucose_shortacting),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(id = R.string.short_acting),
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                            color =
                            if (stateActing == InsulinType.ShortActing)
                                OrangeTextGlucose
                            else
                                GrayAddDataTextField,
                        ),
                    )
                }
            }
            OutlinedButton(
                modifier = Modifier
                    .height(100.dp.from(ctx))
                    .width(245.5f.dp.from(ctx)),
                onClick = {
                    stateActing = InsulinType.LongActing
                    onSelectedActing(stateActing)
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    if (stateActing == InsulinType.LongActing)
                        OrangeButtonSelected
                    else
                        OrangeGlucose
                ),
                border = BorderStroke(
                    1.dp.from(ctx),
                    if (stateActing == InsulinType.LongActing)
                        OrangeTextGlucose
                    else
                        OrangeGlucose
                ),
                shape = RoundedCornerShape(20.dp.from(ctx))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(48.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_glucose_longacting),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(id = R.string.long_acting),
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                            color =
                            if (stateActing == InsulinType.LongActing)
                                OrangeTextGlucose
                            else
                                GrayAddDataTextField
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun CardGlucosePill(
    onValueChange: (Int) -> Unit = {},
) {
    var numberPills by remember { mutableStateOf(1) }
    val ctx = LocalContext.current
    Box(
        modifier = Modifier.background(BlueGlucose, RoundedCornerShape(32.dp.from(ctx))),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp.from(ctx))
        ) {
            Box(modifier = Modifier
                .size(40.dp.from(ctx))
                .background(
                    BlueDarkGlucose.copy(
                        alpha =
                        if (numberPills == 1)
                            0.2f
                        else
                            1f
                    ), CircleShape
                )
                .then(
                    if (numberPills > 1)
                        Modifier.clickable {
                            numberPills -= 1
                            onValueChange(numberPills)
                        }
                    else
                        Modifier
                ),
                contentAlignment = Alignment.Center) {
                Image(painter = painterResource(id = R.drawable.ic_minus), contentDescription = "")
            }
            Text(
                modifier = Modifier.weight(1f),
                text = "$numberPills " + stringResource(id = R.string.pill),
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp.from(ctx),
                    lineHeight = 28.sp.from(ctx),
                    letterSpacing = -0.25f.sp.from(ctx),
                    textAlign = TextAlign.Center
                ),
            )
            Box(
                modifier = Modifier
                    .size(40.dp.from(ctx))
                    .background(BlueDarkGlucose, CircleShape)
                    .clickable {
                        numberPills += 1
                        onValueChange(numberPills)
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(id = R.drawable.ic_plus), contentDescription = "")
            }
        }
    }
}

@Composable
fun CardGlucoseHemoglobin(
    onValueChange: (String) -> Unit,
) {
    var valueTextField by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    Box(
        modifier = Modifier.background(PinkGlucose, RoundedCornerShape(10.dp.from(ctx)))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx)),
            modifier = Modifier.padding(16.dp.from(ctx))
        ) {
            Text(
                text = stringResource(id = R.string.hemoglobin_level),
                style = MaterialTheme.typography.h1.copy(
                    color = PinkGlucoseText,
                    fontSize = 16.sp.from(ctx),
                    lineHeight = 24.sp.from(ctx),
                    fontWeight = FontWeight.Medium
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = valueTextField,
                onValueChange = {
                    valueTextField = it
                    onValueChange(it)
                },
                shape = RoundedCornerShape(10.dp.from(ctx)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    disabledBorderColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                textStyle = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 20.sp.from(ctx),
                    color = Color.Black
                ),
                trailingIcon = {
                    Text(
                        text = stringResource(id = R.string.mg_per_dl),
                        style = MaterialTheme.typography.body2.copy(
                            color = Color.Black,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.value),
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                            color = GrayAddDataTextField
                        ),
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            )
        }
    }
}

@Composable
fun CardNoteGlucose(
    titleText: String,
    valueText: String = "",
    placeHolderText: String,
    enable: Boolean = true,
    isNote: Boolean = false,
    onCancel: () -> Unit = {},
    onValueChange: (String) -> Unit
) {
    var valueTextField by remember { mutableStateOf(valueText) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp.from(ctx)))
            .border(1.dp.from(ctx), GrayDivider, RoundedCornerShape(24.dp.from(ctx)))
    ) {
        Column(
            modifier = Modifier.padding(24.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Row {
                Text(
                    text = titleText,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 28.sp.from(ctx),
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                if (isNote) {
                    Image(
                        modifier = Modifier
                            .clickable {
                                onCancel()
                            }
                            .padding(5.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = ""
                    )
                }
            }

            Divider(
                color = GrayDivider,
                thickness = 1.dp.from(ctx)
            )
            BasicTextField(
                modifier = Modifier
                    .heightIn(min = 114.dp.from(ctx))
                    .fillMaxWidth(),
                value = valueTextField,
                onValueChange = {
                    valueTextField = it
                    onValueChange(it)
                },
                enabled = enable,
                textStyle = MaterialTheme.typography.body1.copy(
                    fontSize = 16.sp.from(ctx),
                    lineHeight = 24.sp.from(ctx),
                    color = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                decorationBox = { innerTextField ->
                    Box {
                        if (valueTextField.isEmpty() && enable) {
                            Text(
                                text = placeHolderText,
                                style = MaterialTheme.typography.body1.copy(
                                    fontSize = 16.sp.from(ctx),
                                    lineHeight = 24.sp.from(ctx),
                                    color = GrayAddDataTextField
                                ),

                                )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}