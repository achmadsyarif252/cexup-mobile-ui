package com.cexup.ui.component.picker

import android.annotation.SuppressLint
import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.cexup.ui.R
import com.cexup.ui.utils.mediaquery.from
import java.time.LocalDate
import kotlin.math.pow
import kotlin.math.sqrt

@SuppressLint("NewApi")
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
    val ctx = LocalConfiguration.current


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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(
                vertical = 16.dp.from(ctx),
                horizontal = 16.dp.from(ctx)
            ),
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

                        textColor = it.getColor(androidx.appcompat.R.color.primary_dark_material_dark)
                    }
                },
                update = {
                    it.setOnValueChangedListener { _, _, newVal ->
                        onChange(newVal,month,year)
                    }
                },
            )
            AndroidView(
                factory = {
                    NumberPicker(it).apply {
                        minValue=1
                        maxValue = listMonth.size
                        displayedValues = listMonth
                        textColor = it.getColor(androidx.appcompat.R.color.primary_dark_material_dark)
                    }
                },
                update = {
                    it.setOnValueChangedListener { _, _, newVal ->
                        onChange(day,newVal,year)
                    }
                },
            )
            AndroidView(
                factory = {
                    NumberPicker(it).apply {
                        minValue = 1970
                        maxValue = LocalDate.now().year
                        textColor = it.getColor(androidx.appcompat.R.color.primary_dark_material_dark)
                    }
                },
                update = {
                    it.setOnValueChangedListener { _, _, newVal ->
                        onChange(day,month,newVal)
                    }
                },
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