package com.cexup.ui.component.chart

import android.annotation.SuppressLint

import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.cexup.ui.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.cexup.ui.utils.CustomChartMarker
import com.cexup.ui.utils.mediaquery.from

/**
 * Base Chart
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 07/09/2021
 */
@SuppressLint("ResourceAsColor", "NewApi", "UseCompatLoadingForDrawables")
@Composable
fun BaseChartView(
    data: List<Entry>,
    name: List<String> = listOf(),
    legends: List<LegendEntry> = listOf(),
    limitLine : List<LimitLine> = listOf(),
    description: String,
    maxAxis: Float = 200f,
    minAxis: Float = 10f,
    paddingValues: PaddingValues = PaddingValues(vertical = 16.dp)
) {

    val isDark = isSystemInDarkTheme()
    val context = LocalContext.current
    val ctx = LocalConfiguration.current

    AndroidView(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp.from(ctx)))
            .padding(paddingValues)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Transparent),
        factory = { it ->
            LineChart(ContextThemeWrapper(it, R.style.Chart)).apply {
                setBackgroundResource(R.drawable.bg_chart)
                axisRight.apply {
                    isEnabled = false
                    setDrawAxisLine(false)
                    setDrawGridLines(false)
                    textColor =
                        if (isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                }
                legend.apply {
                    isEnabled = true
                    setCustom(
                        legends
                    )
                }


                axisLeft.apply {
                    axisMaximum = maxAxis
                    axisMinimum = minAxis
                    removeAllLimitLines()
                    limitLine.forEach {
                        limitLine ->
                        addLimitLine(limitLine)
                    }

                    //formatter
                    isEnabled = true
                    setDrawAxisLine(false)
                    setDrawGridLines(true)
                    textColor =
                        if (isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                }
                xAxis.apply {

                    //disable axis
                    setDrawGridLines(false)
                    position = XAxis.XAxisPosition.BOTTOM
                    //
                    labelRotationAngle = 0f

                    granularity = 1f

                    axisMaximum = 20 + 0.1f
                    //
                    textColor =
                        if (isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK

                }

                this.description.text = description
                this.description.textSize = 16f
                this.description.textColor =
                    if (isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                this.setPadding(
                    0, 20, 0, 30
                )


                //Part8
                setTouchEnabled(true)
                setPinchZoom(true)

                //Part9

                setNoDataText("No Data to be shown!")

                //Part10
                animateX(1800, Easing.EaseInExpo)

                //add custom marker
                val markerView = CustomChartMarker(context, R.layout.layout_marker_chart)
                marker = markerView

            }

        },
        update = { view ->

            view.xAxis.valueFormatter = XAxisTimeFormatter(name)
            val lineDataSet = LineDataSet(data, "My Type")
            lineDataSet.apply {
                //make chart smooth
                mode = LineDataSet.Mode.CUBIC_BEZIER
                cubicIntensity = 0.05f

                //set transparencyFF18A0FB
                setColor(0x18A0FB, 1000)
                //set value in each circle
                setDrawValues(false)
                //Part4 set color fill (area)
                setDrawFilled(true)
                setDrawCircles(false)

                lineWidth = 3f

                fillDrawable = context.getDrawable(R.drawable.bg_fill_chart)


            }


            //set data
            view.data = LineData(lineDataSet)

            view.invalidate()
        }
    )
}

@Preview
@Composable
fun PreviewBaseChartView() {
    MaterialTheme {
        BaseChartView(
            data = arrayListOf<Entry>(),
            description = "Test",
            name = listOf(),
            limitLine = listOf()
        )
    }
}

