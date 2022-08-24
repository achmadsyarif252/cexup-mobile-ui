package com.cexup.ui.component.picker

import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.cexup.ui.utils.mediaquery.dpToMultipleScreen
import java.time.LocalDate
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun SpinnerDatePicker(
    modifier: Modifier=Modifier,
    day: Int=1,
    month:Int=1,
    year:Int=1970,
    onChange:(day:Int,month:Int,year:Int)->Unit={
        _,_,_->
    }
){
    val ctx = LocalContext.current
    val screenWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density

    val screenHeight = ctx
        .resources
        .displayMetrics.heightPixels.dp /
            LocalDensity.current.density

    val a = screenHeight.value.toDouble().pow(2.0)
    val b = screenWidth.value.toDouble().pow(2.0)
    val currentDiagonal = sqrt(a + b).dp

    val listMonth = arrayOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "Augustus",
        "September",
        "October",
        "November",
        "December"
    )
    val colo = Color (0xFF008CA3)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp.dpToMultipleScreen(currentDiagonal), horizontal = 16.dp.dpToMultipleScreen(currentDiagonal)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AndroidView(
                factory = {
                    NumberPicker(it).apply {
                        minValue = 1
                        maxValue = 31
                    }
                },
                update = {
                    it.setOnValueChangedListener { _, _, newVal ->
                        onChange(newVal,month,year)
                    }
                },
                modifier = modifier.background(colo)
            )
            AndroidView(
                factory = {
                    NumberPicker(it).apply {
                        minValue=1
                        maxValue = listMonth.size
                        displayedValues = listMonth
                    }
                },
                update = {
                    it.setOnValueChangedListener { _, _, newVal ->
                        onChange(day,newVal,year)
                    }
                },
                modifier = modifier.background(colo)
            )
            AndroidView(
                factory = {
                    NumberPicker(it).apply {
                        minValue = 1970
                        maxValue = LocalDate.now().year
                    }
                },
                update = {
                    it.setOnValueChangedListener { _, _, newVal ->
                        onChange(day,month,newVal)
                    }
                },
                modifier = modifier.background(colo)
            )

        }
    }
}
@Preview
@Composable
fun PreviewSpinnerDatePicker(){
    MaterialTheme {
        SpinnerDatePicker()
    }
}