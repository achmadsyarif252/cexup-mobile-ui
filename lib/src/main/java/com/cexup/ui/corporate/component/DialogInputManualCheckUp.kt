@file:OptIn(ExperimentalComposeUiApi::class)

package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.R
import com.cexup.ui.utils.mediaquery.from

@Composable
fun DialogInputManualTemperature(
    modifier: Modifier = Modifier,
    show:Boolean= false,
    onCancel : ()-> Unit,
    onSave: (temp: Float) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    var textTemperature by remember { mutableStateOf("") }
    if (show) {
        Dialog(onDismissRequest = { onCancel() }) {
            Surface(
                modifier = modifier
                    .padding(5.dp.from(ctx))
                    .width(416.dp.from(ctx)),
                shape = RoundedCornerShape(25.dp.from(ctx)),
                color = BackgroundLight
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 31.dp.from(ctx), vertical = 23.dp.from(ctx)),
                ) {
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_temperature),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textTemperature,
                        onValueChange = {textTemperature = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "o",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(500),
                                        fontSize = 12.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier.padding(bottom = 20.dp.from(ctx)),
                                )
                                Text(
                                    text = "C",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Spacer(modifier = Modifier.height(21.dp.from(ctx)))
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = { onCancel() },
                            modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = SecondaryCorporate,
                                contentColor = Color.White,
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)
                            )
                        }
                        Spacer(modifier = modifier.width(8.dp.from(ctx)))
                        Button(
                            onClick = {

                                onSave(textTemperature.toFloat())
                            }, modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BlueButtonLogout,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.save),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)

                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun DialogInputManualBMI(
    modifier: Modifier = Modifier,
    show:Boolean= false,
    onCancel : ()-> Unit,
    onSave: (Weight: Float,Height: Float) -> Unit,
){
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    var textHeight by remember { mutableStateOf("") }
    var textWeight by remember { mutableStateOf("") }
    if (show) {
        Dialog(onDismissRequest = { onCancel() }) {
            Surface(
                modifier = modifier
                    .padding(5.dp.from(ctx))
                    .width(416.dp.from(ctx)),
                shape = RoundedCornerShape(25.dp.from(ctx)),
                color = BackgroundLight
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 31.dp.from(ctx), vertical = 23.dp.from(ctx)),
                    verticalArrangement = Arrangement.spacedBy(3.dp.from(ctx))
                ) {
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_height),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textHeight,
                        onValueChange = {textHeight = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "Cm",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_weight),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textWeight,
                        onValueChange = {textWeight = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "Kg",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Spacer(modifier = Modifier.height(21.dp.from(ctx)))
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = { onCancel() },
                            modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = SecondaryCorporate,
                                contentColor = Color.White,
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)
                            )
                        }
                        Spacer(modifier = modifier.width(8.dp.from(ctx)))
                        Button(
                            onClick = {
                                onSave(textWeight.toFloat(),textHeight.toFloat())
                            }, modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BlueButtonLogout,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.save),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)

                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DialogInputManualBloodOxygen(
    modifier: Modifier = Modifier,
    show:Boolean= false,
    onCancel : ()-> Unit,
    onSave: (BloodOxygen: Int,HeartRate: Int) -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    var textBloodOxygen by remember { mutableStateOf("") }
    var textHeartRate by remember { mutableStateOf("")}
    if (show) {
        Dialog(onDismissRequest = { onCancel() }) {
            Surface(
                modifier = modifier
                    .padding(5.dp.from(ctx))
                    .width(416.dp.from(ctx)),
                shape = RoundedCornerShape(25.dp.from(ctx)),
                color = BackgroundLight
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 31.dp.from(ctx), vertical = 23.dp.from(ctx)),
                ) {
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_blood_oxygen),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textBloodOxygen,
                        onValueChange = {textBloodOxygen = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "%",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_heart_rate),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textHeartRate,
                        onValueChange = {textHeartRate = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "bpm",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Spacer(modifier = Modifier.height(21.dp.from(ctx)))
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = { onCancel() },
                            modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = SecondaryCorporate,
                                contentColor = Color.White,
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)
                            )
                        }
                        Spacer(modifier = modifier.width(8.dp.from(ctx)))
                        Button(
                            onClick = {
                                onSave(
                                    textBloodOxygen.toInt(),
                                    textHeartRate.toInt()
                                )
                            }, modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BlueButtonLogout,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.save),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)

                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun DialogInputManualBloodPressure(
    modifier: Modifier = Modifier,
    show:Boolean= false,
    onCancel : ()-> Unit,
    onSave: (Systole: Int,Diastol: Int, PulseRate: Int) -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    var textSystole by remember { mutableStateOf("") }
    var textDiastole by remember { mutableStateOf("")}
    var textPulseRate by remember { mutableStateOf("")}
    if (show) {
        Dialog(onDismissRequest = { onCancel() }) {
            Surface(
                modifier = modifier
                    .padding(5.dp.from(ctx))
                    .width(416.dp.from(ctx)),
                shape = RoundedCornerShape(25.dp.from(ctx)),
                color = BackgroundLight
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 31.dp.from(ctx), vertical = 23.dp.from(ctx)),
                ) {
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_systole),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textSystole,
                        onValueChange = {textSystole = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "bpm",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_diastole),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textDiastole,
                        onValueChange = {textDiastole = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "bpm",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_pulse_rate),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textPulseRate,
                        onValueChange = {textPulseRate = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "bpm",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Spacer(modifier = Modifier.height(21.dp.from(ctx)))
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = { onCancel() },
                            modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = SecondaryCorporate,
                                contentColor = Color.White,
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)
                            )
                        }
                        Spacer(modifier = modifier.width(8.dp.from(ctx)))
                        Button(
                            onClick = {
                                onSave(
                                    textSystole.toInt(),
                                    textDiastole.toInt(),
                                    textPulseRate.toInt()
                                )
                            }, modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BlueButtonLogout,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.save),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)

                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun DialogInputManualWaist(
    modifier: Modifier = Modifier,
    show:Boolean= false,
    textWaist: String = "",
    onCancel: () -> Unit,
    onSave: (valueWaist:Float) -> Unit,
){
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    if (show) {
    var textValueWaist by remember { mutableStateOf("") }
        Dialog(onDismissRequest = { onCancel() }) {
            Surface(
                modifier = modifier
                    .padding(5.dp.from(ctx))
                    .width(416.dp.from(ctx)),
                shape = RoundedCornerShape(25.dp.from(ctx)),
                color = BackgroundLight
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 31.dp.from(ctx), vertical = 23.dp.from(ctx)),
                ) {
                    Text(
                        text = textWaist,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textValueWaist,
                        onValueChange = {textValueWaist = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "Cm",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Spacer(modifier = Modifier.height(21.dp.from(ctx)))
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = { onCancel() },
                            modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = SecondaryCorporate,
                                contentColor = Color.White,
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)
                            )
                        }
                        Spacer(modifier = modifier.width(8.dp.from(ctx)))
                        Button(
                            onClick = {
                                onSave(textValueWaist.toFloat())
                            }, modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BlueButtonLogout,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.save),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)

                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun DialogInputManualMultiparameter(
    modifier: Modifier = Modifier,
    show:Boolean= false,
    onCancel : ()-> Unit,
    onSave: (HeartRate: Int, systole:Int, diastole:Int, Spo2: Int, Temperature:Float, Respiration:Int) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalContext.current
    var textHeartRate by remember { mutableStateOf("0") }
    var textSystole by remember { mutableStateOf("0") }
    var textDiastole by remember { mutableStateOf("0") }
    var textSpo2 by remember { mutableStateOf("0") }
//    var textPulseRate by remember { mutableStateOf("") }
    var textTemperature by remember { mutableStateOf("0") }
    var textRespiration by remember { mutableStateOf("0") }
    if (show) {
        Dialog(onDismissRequest = { onCancel() }) {
            Surface(
                modifier = modifier
                    .padding(5.dp.from(ctx))
                    .width(416.dp.from(ctx))
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(25.dp.from(ctx)),
                color = BackgroundLight
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 31.dp.from(ctx), vertical = 23.dp.from(ctx)),
                ) {
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_heart_rate) +
                            "/${stringResource(id = R.string.corporate_measurement_pulse_rate)}",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textHeartRate,
                        onValueChange = {textHeartRate = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "Bpm",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_systole),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textSystole,
                        onValueChange = {textSystole = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "bpm",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_diastole),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textDiastole,
                        onValueChange = {textDiastole = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "bpm",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Text(
                        text = stringResource(id = R.string.spo2),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textSpo2,
                        onValueChange = {textSpo2 = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "%",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_temperature),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textTemperature,
                        onValueChange = {textTemperature = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "o",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(500),
                                        fontSize = 12.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier.padding(bottom = 20.dp.from(ctx)),
                                )
                                Text(
                                    text = "C",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_respiration),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(400),
                        fontSize = 22.sp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        color = GreyBlackStetoscope
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textRespiration,
                        onValueChange = {textRespiration = it},
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        trailingIcon = {
                            Row {
                                Text(
                                    text = "bpm",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(700),
                                        fontSize = 28.sp.from(ctx),
                                        letterSpacing = 1.sp.from(ctx),
                                        color = GreyBorder,
                                    ),
                                    modifier = modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(top = 5.dp.from(ctx)),
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    )
                    Spacer(modifier = Modifier.height(21.dp.from(ctx)))
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = { onCancel() },
                            modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = SecondaryCorporate,
                                contentColor = Color.White,
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)
                            )
                        }
                        Spacer(modifier = modifier.width(8.dp.from(ctx)))
                        Button(
                            onClick = {
                                onSave(
                                    textHeartRate.toInt(),
                                    textSystole.toInt(),
                                    textDiastole.toInt(),
                                    textSpo2.toInt(),
//                                    textPulseRate.toFloat(),
                                    textTemperature.toFloat(),
                                    textRespiration.toInt(),
                                )
                            }, modifier = modifier
                                .width(149.dp.from(ctx))
                                .height(45.dp.from(ctx)),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BlueButtonLogout,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp.from(ctx)),
                            contentPadding = PaddingValues(
                                horizontal = 41.38.dp.from(ctx),
                                vertical = 12.69.dp.from(ctx)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.save),
                                style = MaterialTheme.typography.body1,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight(600)

                            )
                        }
                    }
                }
            }
        }
    }
}

