package com.cexup.ui.corporate.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cexup.ui.corporate.theme.GreyBlackStetoscope
import com.cexup.ui.corporate.theme.GreyBorder
import com.cexup.ui.corporate.theme.inactive
import compose.icons.Octicons
import compose.icons.octicons.HorizontalRule16
import compose.icons.octicons.Plus16
import compose.icons.octicons.X16

enum class  ModeSwitch(val parameter : String){
    NORMAL("NORMAL"),
    UPPERLOWER("UPPERLOWER"),
    INTERVAL("INTERVAL")
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogSettingMultiparameter(
    modifier: Modifier = Modifier,
    show:Boolean= false,
    onDismiss : ()-> Unit,
    onLimitConfigSave: (limitType: LimitType, upper: Double, lower: Double) -> Unit,
    onSwitchChanged: (limitType: LimitType) -> Unit
) {

    var showLimit by remember {
        mutableStateOf(false)
    }

    var limitType by remember {
        mutableStateOf(LimitType.HIGH_BP_LIMIT)
    }

    var hpUpperLimit by remember { mutableStateOf(140) }
    var hpLowerLimit by remember { mutableStateOf(90) }
    var lpUpperLimit by remember { mutableStateOf(90) }
    var lpLowerLimit by remember { mutableStateOf(60) }
    var ecgUpperLimit by remember { mutableStateOf(120) }
    var ecgLowerLimit by remember { mutableStateOf(60) }
    var spo2UpperLimit by remember { mutableStateOf(100) }
    var spo2LowerLimit by remember { mutableStateOf(94) }
    var prUpperLimit by remember { mutableStateOf(120) }
    var prLowerLimit by remember { mutableStateOf(60) }
    var tempUpperLimit by remember { mutableStateOf(34.0) }
    var tempLowerLimit by remember { mutableStateOf(34.0) }

    if(show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            LayoutSettingMultiparameter(
                onDismiss = { onDismiss() },
                onConfig = {
                    showLimit = true
                    limitType = it

                },
                onSwitchChange = {
                    onSwitchChanged(it)
                },
                hpUpperLimit = hpUpperLimit,
                hpLowerLimit = hpLowerLimit,
                lpUpperLimit = lpUpperLimit,
                lpLowerLimit = lpLowerLimit,
                ecgUpperLimit = ecgUpperLimit,
                ecgLowerLimit = ecgLowerLimit,
                spo2UpperLimit = spo2UpperLimit,
                spo2LowerLimit = spo2LowerLimit,
                prUpperLimit = prUpperLimit,
                prLowerLimit = prLowerLimit,
                tempUpperLimit = tempUpperLimit,
                tempLowerLimit = tempLowerLimit,
            )
        }
    }

    DialogLimitMultiparameter(
        show = showLimit,
        type = limitType,
        onDismiss = { showLimit = false },
        onConfig = { selectedUpper, selectedLower ->
            when(limitType) {
                LimitType.HIGH_BP_LIMIT -> {
                    hpUpperLimit = selectedUpper.toInt()
                    hpLowerLimit = selectedLower.toInt()
                }
                LimitType.LOW_BP_LIMIT -> {
                    lpUpperLimit = selectedUpper.toInt()
                    lpLowerLimit = selectedLower.toInt()
                }
                LimitType.ECG_LIMIT -> {
                    ecgUpperLimit = selectedUpper.toInt()
                    ecgLowerLimit = selectedLower.toInt()
                }
                LimitType.SPO2_LIMIT -> {
                    spo2UpperLimit = selectedUpper.toInt()
                    spo2LowerLimit = selectedLower.toInt()
                }
                LimitType.PULSE_LIMIT -> {
                    prUpperLimit = selectedUpper.toInt()
                    prLowerLimit = selectedLower.toInt()
                }
                LimitType.TEMPERATURE_LIMIT -> {
                    tempUpperLimit = selectedUpper
                    tempLowerLimit = selectedLower
                }
                LimitType.AUTOMATIC_INTERVAL -> {}
            }
            onLimitConfigSave(
                limitType, selectedUpper, selectedLower
            )
        }
    )

}


@Composable
fun LayoutSettingMultiparameter(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
    onConfig: (limitType: LimitType) -> Unit = {},
    onSwitchChange: (limitType: LimitType) -> Unit = {},
    hpUpperLimit: Int,
    hpLowerLimit: Int,
    lpUpperLimit: Int,
    lpLowerLimit: Int,
    ecgUpperLimit: Int,
    ecgLowerLimit: Int,
    spo2UpperLimit: Int,
    spo2LowerLimit: Int,
    prUpperLimit: Int,
    prLowerLimit: Int,
    tempUpperLimit: Double,
    tempLowerLimit: Double,
) {
    var valueAlaram by remember {
        mutableStateOf(0)
    }
    var listSwitchText = listOf(SwitchParameter.ON, SwitchParameter.OFF)
    var listSwitchInterval = listOf(EnableParameter.Enable, EnableParameter.Disable)

    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        modifier = modifier.width(768.dp)
    ) {
        var verticalSocroll = rememberScrollState()
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(18.dp),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    Octicons.X16,
                    contentDescription = " Close",
                    tint = inactive,
                    modifier = modifier
                        .size(17.02.dp)
                        .clickable {
                            onDismiss()
                        })
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 23.dp)
                    .verticalScroll(verticalSocroll),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //left
                Column(
                    modifier = modifier.width(316.dp),
                ) {
                    //general Settings
                    Column {
                        Text(
                            text = "General Settings",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight(700),
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        //card
                        CardGeneralSetting(
                            nameParameter = "Alarm Volume"
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        CardGeneralSetting(
                            nameParameter = "Pulse Volume"
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        CardGeneralSetting(
                            nameParameter = "Brightness"
                        )
//                        Spacer(modifier = Modifier.height(10.dp))
//                        CardUpperLower(
//                            nameParameter = "Demo Mode",
//                            listSwitch = listSwitchText,
//                            startPosition = SwitchParameter.OFF,
//                            modeSwitch = ModeSwitch.NORMAL
//                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    //NIBP
                    Column {
                        Text(
                            text = "NIBP",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight(700),
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "High Pressure Limits",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight(600),
                                fontSize = 12.sp
                            )
                        )
                        //card
                        CardUpperLower(
                            nameParameter = "Demo Mode",
                            listSwitch = listSwitchText,
                            startPosition = SwitchParameter.OFF,
                            modeSwitch = ModeSwitch.UPPERLOWER,
                            upperValue = "$hpUpperLimit",
                            lowerValue = "$hpLowerLimit",
                            onUpperLowerConfig = {
                                onConfig(LimitType.HIGH_BP_LIMIT)
                            },
                            limitType = LimitType.HIGH_BP_LIMIT,
                            onSwitch = { limitType ->
                                onSwitchChange(limitType)
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Low Pressure Limits",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight(600),
                                fontSize = 12.sp
                            )
                        )
                        //card
                        CardUpperLower(
                            nameParameter = "Demo Mode",
                            listSwitch = listSwitchText,
                            startPosition = SwitchParameter.OFF,
                            modeSwitch = ModeSwitch.UPPERLOWER,
                            upperValue = "$lpUpperLimit",
                            lowerValue = "$lpLowerLimit",
                            onUpperLowerConfig = {
                                onConfig(LimitType.LOW_BP_LIMIT)
                            },
                            limitType = LimitType.LOW_BP_LIMIT,
                            onSwitch = { limitType ->
                                onSwitchChange(limitType)
                            }
                        )
                        Spacer(Modifier.height(10.dp))
                        CardUpperLower(
                            nameParameter = "Automatic Interval:",
                            listSwitch = listSwitchText,
                            startEnable = EnableParameter.Disable,
                            modeSwitch = ModeSwitch.INTERVAL,
                            listenable = listSwitchInterval,
                            limitType = LimitType.AUTOMATIC_INTERVAL,
                            onSwitch = { limitType ->
                                onSwitchChange(limitType)
                            }
                        )
                    }

                }

                //right
                Column(
                    modifier = modifier.width(316.dp),
                ) {
                    //ECG
                    Column {
                        Text(
                            text = "ECG",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight(700),
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Heart Rate Limits",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight(600),
                                fontSize = 12.sp
                            )
                        )
                        //card
                        CardUpperLower(
                            nameParameter = "Demo Mode",
                            listSwitch = listSwitchText,
                            startPosition = SwitchParameter.OFF,
                            modeSwitch = ModeSwitch.UPPERLOWER,
                            upperValue = "$ecgUpperLimit",
                            lowerValue = "$ecgLowerLimit",
                            onUpperLowerConfig = {
                                onConfig(LimitType.ECG_LIMIT)
                            },
                            limitType = LimitType.ECG_LIMIT,
                            onSwitch = { limitType ->
                                onSwitchChange(limitType)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    //SPO2
                    Column {
                        Text(
                            text = "SPO2",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight(700),
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "SPO2 Limits",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight(600),
                                fontSize = 12.sp
                            )
                        )
                        //card
                        CardUpperLower(
                            nameParameter = "Demo Mode",
                            listSwitch = listSwitchText,
                            startPosition = SwitchParameter.OFF,
                            modeSwitch = ModeSwitch.UPPERLOWER,
                            upperValue = "$spo2UpperLimit",
                            lowerValue = "$spo2LowerLimit",
                            onUpperLowerConfig = {
                                onConfig(LimitType.SPO2_LIMIT)
                            },
                            limitType = LimitType.SPO2_LIMIT,
                            onSwitch = { limitType ->
                                onSwitchChange(limitType)
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Pulse Rate Limits",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight(600),
                                fontSize = 12.sp
                            )
                        )
                        //card
                        CardUpperLower(
                            nameParameter = "Demo Mode",
                            listSwitch = listSwitchText,
                            startPosition = SwitchParameter.OFF,
                            modeSwitch = ModeSwitch.UPPERLOWER,
                            upperValue = "$prUpperLimit",
                            lowerValue = "$prLowerLimit",
                            onUpperLowerConfig = {
                                onConfig(LimitType.PULSE_LIMIT)
                            },
                            limitType = LimitType.PULSE_LIMIT,
                            onSwitch = { limitType ->
                                onSwitchChange(limitType)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                    //Temperature
                    Column {
                        Text(
                            text = "Temperature",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight(700),
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        //card
                        CardUnitTemperature()
                        Spacer(modifier = Modifier.height(10.dp))
                        //card
                        CardUpperLower(
                            nameParameter = "Demo Mode",
                            listSwitch = listSwitchText,
                            startPosition = SwitchParameter.OFF,
                            modeSwitch = ModeSwitch.UPPERLOWER,
                            upperValue = "$tempUpperLimit",
                            lowerValue = "$tempLowerLimit",
                            onUpperLowerConfig = {
                                onConfig(LimitType.TEMPERATURE_LIMIT)
                            },
                            limitType = LimitType.TEMPERATURE_LIMIT,
                            onSwitch = { limitType ->
                                onSwitchChange(limitType)
                            }
                        )
                    }
                }
            }

        }
    }

}

@Composable
fun CardGeneralSetting(
    modifier: Modifier = Modifier,
    nameParameter : String = ""
){
    var valueChange by remember {
        mutableStateOf(0)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .border(
                color = GreyBorder,
                shape = RoundedCornerShape(5.dp),
                width = 1.dp
            )
            .padding(horizontal = 18.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text(
                text = nameParameter,
                style = MaterialTheme.typography.body1.copy(
                    color = GreyBlackStetoscope,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400)
                )
            )
        }

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            //minus
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(
                        color = GreyBorder,
                        shape = RoundedCornerShape(5.dp),
                        width = 1.dp
                    )
                    .clickable {
                        if (valueChange != 0) {
                            valueChange--
                        } else {
                            valueChange = 0
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Octicons.HorizontalRule16,
                    contentDescription = "minus",
                    modifier = modifier.width(8.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            //value
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .border(
                        color = GreyBorder,
                        shape = RoundedCornerShape(5.dp),
                        width = 1.dp
                    )
                ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = valueChange.toString(),
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.primaryVariant,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                    )
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            //plus
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(
                        color = GreyBorder,
                        shape = RoundedCornerShape(5.dp),
                        width = 1.dp
                    )
                    .clickable {
                        valueChange++
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Octicons.Plus16,
                    contentDescription = "plus",
                    modifier = modifier.width(8.dp)
                )
            }

        }
    }
}