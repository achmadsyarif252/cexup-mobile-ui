package com.cexup.ui.corporate.component

import android.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.cexup.ui.R
import com.cexup.ui.utils.CustomChartMarker
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun ChartPatientProfile(
    data: List<Entry>,
    data2 : List<Entry>,
    FormatXLabel: List<String> = listOf(),
    description: String,
    maxAxis: Float = 100f,
    minAxis: Float =0f,
    label1 : Pair<String, Int>,
    label2 : Pair<String, Int>
){
    val isDark = isSystemInDarkTheme()
    val context = LocalContext.current
//    val limitWidth = 0.7f

    AndroidView(
        modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Transparent),
        factory = {
            LineChart(ContextThemeWrapper(it, R.style.Chart)).apply {
                setBackgroundResource(R.drawable.bg_chart)
                axisRight.apply {
                    isEnabled = false
                    setDrawAxisLine(false)
                    setDrawGridLines(false)
                    textColor = if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                }
                legend.apply {
                    isEnabled = false
                    form = Legend.LegendForm.CIRCLE

                }


                axisLeft.apply {
                    axisMaximum = maxAxis
                    axisMinimum = minAxis
                    removeAllLimitLines()

                    //formatter
                    isEnabled = true
                    setDrawAxisLine(false)
                    setDrawGridLines(true)
//                    enableGridDashedLine(10f, 5f, 0f)
                    textColor =if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                }
                xAxis.apply {
                    valueFormatter = XValueFormatter(FormatXLabel)

                    //disable axis
//                    setDrawGridLines(false)
                    setDrawGridLines(true)
//                    enableGridDashedLine(10f, 5f, 0f)
                    position = XAxis.XAxisPosition.BOTTOM
                    labelCount = 25


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

//            view.xAxis.valueFormatter  = XAxisTimeFormatter(xValueFormatter)
            val lineDataSet = LineDataSet(data, label1.first)
            val lineDataSet2 = LineDataSet(data2, label2.first)
            lineDataSet.apply {
                //make chart smooth
                mode = LineDataSet.Mode.CUBIC_BEZIER
                cubicIntensity = 0.05f

                //set transparencyFF18A0FB
                setColor(label1.second,1000)
                //set value in each circle
                setDrawValues(false)

                setDrawCircleHole(false)
                setCircleColor(label1.second)
                circleRadius = 5f
                //Part4 set color fill (area)
//                setDrawFilled(true)
//                setDrawCircles(false)

                lineWidth = 3f

//                fillDrawable = AppCompatResources.getDrawable(context, label1.third)

            }
            lineDataSet2.apply {
                //make chart smooth
                mode = LineDataSet.Mode.CUBIC_BEZIER
                cubicIntensity = 0.05f

                //set transparencyFF18A0FB
                setColor(label2.second,1000)
                //set value in each circle
                setDrawValues(false)

                setDrawCircleHole(false)
                setCircleColor(label2.second)
                circleRadius = 5f
                //Part4 set color fill (area)
//                setDrawFilled(true)
//                setDrawCircles(false)

                lineWidth = 3f

//                fillDrawable = AppCompatResources.getDrawable(context, label2.third)

            }

            val iline = listOf(lineDataSet, lineDataSet2)


            //set data
            view.data = LineData(iline)


            view.invalidate()
        }
    )
}