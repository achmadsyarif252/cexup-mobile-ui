package com.cexup.ui.corporate.component

import android.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.cexup.ui.R
import com.cexup.ui.component.chart.XAxisTimeFormatter
import com.cexup.ui.utils.CustomChartMarker

@Composable
fun BarChartPatient(
    data: List<BarEntry>,
    data2 : List<BarEntry>,
    xValueFormatter: List<String> = listOf(),
    description: String,
    maxAxis: Float = 100f,
    minAxis: Float =0f,
){
    val isDark = isSystemInDarkTheme()
    val context = LocalContext.current
//    val limitWidth = 0.7f

    AndroidView(
        modifier= Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Transparent),
        factory = {
            BarChart(ContextThemeWrapper(it, R.style.Chart)).apply {
                setBackgroundResource(R.drawable.bg_chart)
                axisRight.apply {
                    isEnabled = false
                    setDrawAxisLine(false)
                    setDrawGridLines(false)
                    textColor = if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                }
                legend.apply {
                    isEnabled = false
                }

                axisLeft.apply {
                    axisMaximum = maxAxis
                    axisMinimum = minAxis
                    removeAllLimitLines()

                    //formatter
                    isEnabled = true
                    setDrawAxisLine(false)
                    setDrawGridLines(true)
                    textColor =if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                }
                xAxis.apply {

                    //disable axis
                    setDrawGridLines(false)
                    position = XAxis.XAxisPosition.BOTTOM
                    //
                    labelRotationAngle = 0f

                    granularity = 1f

                    axisMaximum = 20+0.1f
                    //
                    textColor = if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK

                }

                this.description.text = description
                this.description.textSize = 16f
                this.description.textColor = if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                this.setPadding(
                    0,20,0,30
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
        update = {
                view ->

            view.xAxis.valueFormatter  = XAxisTimeFormatter(xValueFormatter)
            val lineDataSet = BarDataSet(data, "New Pasien")
            val lineDataSet2 = BarDataSet(data2, "Old Pasien")
            lineDataSet.apply {
                //make chart smooth
                barBorderWidth = 0.9f
                barBorderColor = 0xF16A51




                //set transparencyFF18A0FB
                setColor(0x18A0FB,1000)
                //set value in each circle
                setDrawValues(false)
                //Part4 set color fill (area)
                setDrawIcons(true)


            }
            lineDataSet2.apply {
                //make chart smooth
                //make chart smooth
                barBorderWidth = 0.7f
                barBorderColor = 0xF16A51
                color = 0x21B8CF


                //set transparencyFF18A0FB
                setColor(0xF16A51,1000)
                //set value in each circle
                setDrawValues(false)
                //Part4 set color fill (area)
                setDrawIcons(true)

            }

            val iline = listOf(lineDataSet, lineDataSet2)


            //set data
            view.data = BarData(iline)


            view.invalidate()
        }
    )
}