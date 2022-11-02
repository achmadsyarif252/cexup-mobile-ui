package com.cexup.ui.corporate.component

import android.os.Build
import android.view.ContextThemeWrapper
import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.fontsCorp

enum class LimitType {
    ECG_LIMIT,
    SPO2_LIMIT,
    PULSE_LIMIT,
    TEMPERATURE_LIMIT,
    HIGH_BP_LIMIT,
    LOW_BP_LIMIT,
    AUTOMATIC_INTERVAL,
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogLimitMultiparameter(
    show: Boolean = false,
    type: LimitType,
    onDismiss: () -> Unit,
    onConfig: (
        selectedUpper: Double,
        selectedLower: Double,
    ) -> Unit,
) {
    var upper by remember { mutableStateOf(0.0) }
    var lower by remember { mutableStateOf(0.0) }
    if (show) {

        when(type) {
            LimitType.ECG_LIMIT -> {
                upper = 120.0
                lower = 60.0
            }
            LimitType.SPO2_LIMIT -> {
                upper = 100.0
                lower = 94.0
            }
            LimitType.PULSE_LIMIT -> {
                upper = 120.0
                lower = 60.0
            }
            LimitType.TEMPERATURE_LIMIT -> {
                upper = 38.0
                lower = 34.0
            }
            LimitType.HIGH_BP_LIMIT -> {
                upper = 140.0
                lower = 90.0
            }
            LimitType.LOW_BP_LIMIT -> {
                upper = 90.0
                lower = 60.0
            }
            LimitType.AUTOMATIC_INTERVAL -> {}
        }

        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            LayoutLimitMultiparameter(
                limitType = type,
                onTempSave = {
                        selectedTempUpper, selectedTempComaUpper,
                        selectedTempLower, selectedTempComaLower ->
                    val selectedUpper = "$selectedTempUpper.$selectedTempComaUpper".toDouble()
                    val selectedLower = "$selectedTempLower.$selectedTempComaLower".toDouble()
                    onConfig(selectedUpper, selectedLower)
                    onDismiss()
                },
                onSave = { selectedUpper, selectedLower ->
                    onConfig(selectedUpper.toDouble(), selectedLower.toDouble())
                    onDismiss()
                },
                valueUpper = upper.toInt(),
                valueLower = lower.toInt(),
            )
        }
    }

}

@Composable
fun LayoutLimitMultiparameter(
    modifier: Modifier = Modifier,
    limitType: LimitType,
    valueUpper: Int = 0,
    valueLower: Int = 0,
    onTempSave: (
        selectedTempUpper: Int,
        selectedTempComaUpper: Int,
        selectedTempLower: Int,
        selectedTempComaLower: Int
    ) -> Unit,
    onSave: (
        selectedUpper: Int,
        selectedLower: Int,
    ) -> Unit,
) {
    val valueComa = 0
    var selectedUpper by remember {
        mutableStateOf(valueUpper)
    }
    var selectedLower by remember {
        mutableStateOf(valueLower)
    }
    var selectedTempUpper by remember {
        mutableStateOf(valueUpper)
    }
    var selectedTempComaUpper by remember {
        mutableStateOf(valueComa)
    }
    var selectedTempLower by remember {
        mutableStateOf(valueLower)
    }
    var selectedTempComaLower by remember {
        mutableStateOf(valueComa)
    }
    val ctx = LocalContext.current
    val currentWidth = ctx.resources.displayMetrics.widthPixels.dp / LocalDensity.current.density
    val currentHeight = ctx.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density
    val widthDialog = currentWidth/4f
    val heightDialog = currentHeight/1.75f
    val widthDialogTemp = currentWidth/3f
    when(limitType){
        LimitType.TEMPERATURE_LIMIT ->{
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = 0.dp,
                modifier = modifier
                    .width(widthDialogTemp)
                    .height(heightDialog)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text =
                        when (limitType) {
                            LimitType.TEMPERATURE_LIMIT -> "Temp Limits"
                            LimitType.SPO2_LIMIT -> "SPO2 Limits"
                            LimitType.PULSE_LIMIT -> "Pulse Rate Limits"
                            LimitType.HIGH_BP_LIMIT -> "High Pressure Limits"
                            LimitType.LOW_BP_LIMIT -> "Low Pressure Limits"
                            LimitType.AUTOMATIC_INTERVAL -> ""
                            LimitType.ECG_LIMIT -> "Heart Rate Limits"
                        },
                        fontFamily = fontsCorp,
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 20.sp,
                        color = Heading
                    )
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = modifier
                                .width(widthDialog/2),
                            verticalArrangement = Arrangement.spacedBy(21.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Upper",
                                fontFamily = fontsCorp,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(600),
                                lineHeight = 20.sp,
                                color = Heading
                            )
                            Row(
                                modifier = modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                LimitNumberPicker(
                                    min = 25,
                                    max = 45,
                                    onValueChange = {
                                            old, new ->
                                        selectedTempUpper = new
                                    }
                                )
                                Box(
                                    modifier = modifier
                                        .size(7.dp)
                                        .background(
                                            color = Heading,
                                            shape = CircleShape
                                        )
                                )
                                LimitNumberPicker(
                                    min = 0,
                                    max = 9,
                                    onValueChange = {
                                            old, new ->
                                        selectedTempComaUpper = new
                                    }
                                )

                            }

                        }
                        Column(
                            modifier = modifier
                                .width(widthDialog/2),
                            verticalArrangement = Arrangement.spacedBy(21.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Lower",
                                fontFamily = fontsCorp,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(600),
                                lineHeight = 20.sp,
                                color = Heading
                            )
                            Row(
                                modifier = modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                LimitNumberPicker(
                                    min = 25,
                                    max = 45,
                                    onValueChange = {
                                            old, new ->
                                        selectedTempLower = new
                                    }
                                )
                                Box(
                                    modifier = modifier
                                        .size(7.dp)
                                        .background(
                                            color = Heading,
                                            shape = CircleShape
                                        )
                                )
                                LimitNumberPicker(
                                    min = 0,
                                    max = 9,
                                    onValueChange = {
                                            old, new ->
                                        selectedTempComaLower = new
                                    }
                                )

                            }
                        }
                    }
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = modifier
                                .width(widthDialog/2),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    selectedTempUpper = 34
                                    selectedTempComaUpper = 0
                                    selectedTempLower = 34
                                    selectedTempComaLower = 0
                                    onTempSave(
                                        selectedTempUpper,
                                        selectedTempComaUpper,
                                        selectedTempLower,
                                        selectedTempComaLower
                                    ) },
                                contentPadding = PaddingValues(vertical = 6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Heading
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = modifier
                                    .width(widthDialog/2.4f)
                            )
                            {
                                Text(
                                    text = "Default",
                                    fontFamily = fontsCorp,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(600),
                                    lineHeight = 20.sp,
                                    color = Color.White
                                )
                            }
                        }

                        Row(
                            modifier = modifier
                                .width(widthDialog/2),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    onTempSave(
                                        selectedTempUpper,
                                        selectedTempComaUpper,
                                        selectedTempLower,
                                        selectedTempComaLower
                                    ) },
                                contentPadding = PaddingValues(vertical = 6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Heading
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = modifier
                                    .width(widthDialog/2.4f)
                            )
                            {
                                Text(
                                    text = "Save",
                                    fontFamily = fontsCorp,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(600),
                                    lineHeight = 20.sp,
                                    color = Color.White
                                )
                            }
                        }

                    }
                }

            }
        }
        else ->{
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = 0.dp,
                modifier = modifier
                    .width(widthDialog)
                    .height(heightDialog)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(vertical = 24.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text =
                        when (limitType) {
                            LimitType.ECG_LIMIT -> "Heart Rate Limits"
                            LimitType.SPO2_LIMIT -> "SPO2 Limits"
                            LimitType.TEMPERATURE_LIMIT -> "Temp Limits"
                            LimitType.PULSE_LIMIT -> "Pulse Rate Limits"
                            LimitType.HIGH_BP_LIMIT -> "High Pressure Limits"
                            LimitType.LOW_BP_LIMIT -> "Low Pressure Limits"
                            LimitType.AUTOMATIC_INTERVAL -> ""
                        },
                        fontFamily = fontsCorp,
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 20.sp,
                        color = Heading
                    )
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = modifier
                                .width(widthDialog/2),
                            verticalArrangement = Arrangement.spacedBy(21.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Upper",
                                fontFamily = fontsCorp,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(600),
                                lineHeight = 20.sp,
                                color = Heading
                            )
                            LimitNumberPicker(
                                min = 30,
                                max = 300,
                                onValueChange = {
                                        old, new ->
                                    selectedUpper = new
                                }
                            )
                        }
                        Column(
                            modifier = modifier
                                .width(widthDialog/2),
                            verticalArrangement = Arrangement.spacedBy(21.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Lower",
                                fontFamily = fontsCorp,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(600),
                                lineHeight = 20.sp,
                                color = Heading
                            )
                            LimitNumberPicker(
                                min = 30,
                                max = 300,
                                onValueChange = {
                                        old, new ->
                                    selectedLower = new
                                }
                            )
                        }
                    }
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row() {
                            Button(
                                onClick = {
                                    when (limitType) {
                                        LimitType.ECG_LIMIT -> {
                                            selectedUpper = 120
                                            selectedLower = 60
                                        }
                                        LimitType.SPO2_LIMIT -> {
                                            selectedUpper = 100
                                            selectedLower = 94
                                        }
                                        LimitType.PULSE_LIMIT -> {
                                            selectedUpper = 120
                                            selectedLower = 60
                                        }
                                        LimitType.HIGH_BP_LIMIT -> {
                                            selectedUpper = 140
                                            selectedLower = 90
                                        }
                                        LimitType.LOW_BP_LIMIT -> {
                                            selectedUpper = 90
                                            selectedLower = 60
                                        }
                                        else -> {}
                                    }
                                    onSave(selectedUpper, selectedLower)
                                },
                                contentPadding = PaddingValues(vertical = 6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Heading
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = modifier
                                    .width(widthDialog/2.4f)
                            )
                            {
                                Text(
                                    text = "Default",
                                    fontFamily = fontsCorp,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(600),
                                    lineHeight = 20.sp,
                                    color = Color.White
                                )
                            }
                        }

                        Row() {
                            Button(
                                onClick = { onSave(selectedUpper, selectedLower) },
                                contentPadding = PaddingValues(vertical = 6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Heading
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = modifier
                                    .width(widthDialog/2.4f)
                            )
                            {
                                Text(
                                    text = "Save",
                                    fontFamily = fontsCorp,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(600),
                                    lineHeight = 20.sp,
                                    color = Color.White
                                )
                            }
                        }

                    }
                }
            }
        }
    }

}

@Composable
fun LimitNumberPicker(
    min: Int,
    max: Int,
    value:Int=0,
    onValueChange: (old: Int, new: Int) -> Unit,
) {
    val isDark = isSystemInDarkTheme()
    AndroidView({
        NumberPicker(
            ContextThemeWrapper(it, R.style.Chart).apply { }

        ).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                textColor = if (isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
            }
        }
    }, update = { view ->
        view.maxValue = max
        view.minValue = min
        view.setOnValueChangedListener { _, oldval, newval ->
            onValueChange(oldval, newval)
        }
        view.value = value

    })
}