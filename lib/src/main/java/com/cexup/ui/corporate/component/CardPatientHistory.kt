package com.cexup.ui.corporate.component

import android.view.ContextThemeWrapper
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.cexup.ui.R
import com.cexup.ui.component.chart.XAxisTimeFormatter
import com.cexup.ui.corporate.theme.AlternativeHeading
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.CustomChartMarker

data class LabelAndColorChart(
    val label : String,
    val colorLine : Int,
    val coloGradient : Int
)

@Composable
fun CardPatientHistory(
    modifier: Modifier = Modifier,
    name:String,
    xValueFormatter: List<String> = listOf(),
    data:List<Entry>,
    data2:List<Entry>,
    maxAxis:Float,
    minAxis:Float,
    label1 : LabelAndColorChart,
    label2 : LabelAndColorChart,
    heightChart : Dp = 267.74.dp
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .height(heightChart)
    ) {
        Column(modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700),
                    color = Heading
                ),
                modifier = modifier.padding(top = 6.dp,bottom = 6.dp)
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = modifier
                            .background(
                                color = AlternativeHeading,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .width(9.77.dp)
                            .height(6.6.dp)

                    ) {

                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "New Patient",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color.Black
                        )
                    )
                }
                Spacer(modifier = Modifier.width(18.32.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = modifier
                            .width(9.77.dp)
                            .height(6.6.dp)
                            .background(
                                color = SecondaryCorporate,
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {

                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Old patient",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color.Black
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            ChartPatientHistory(
                data = data,
                data2 = data2,
                description = "",
                minAxis = minAxis,
                maxAxis = maxAxis,
                label1 = label1,
                label2 = label2,
                xValueFormatter = xValueFormatter
            )
        }
    }
}

@Composable
fun ChartPatientHistory(
    data: List<Entry>,
    data2 : List<Entry>,
    xValueFormatter: List<String> = listOf(),
    description: String,
    maxAxis: Float = 100f,
    minAxis: Float =0f,
    label1 : LabelAndColorChart,
    label2 : LabelAndColorChart
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
            val lineDataSet = LineDataSet(data, label1.label)
            val lineDataSet2 = LineDataSet(data2, label2.label)
            lineDataSet.apply {
                //make chart smooth
                mode = LineDataSet.Mode.CUBIC_BEZIER
                cubicIntensity = 0.05f

                //set transparencyFF18A0FB
                setColor(label1.colorLine,1000)
                //set value in each circle
                setDrawValues(false)
                //Part4 set color fill (area)
                setDrawFilled(true)
                setDrawCircles(false)

                lineWidth = 3f

                fillDrawable = AppCompatResources.getDrawable(context, label1.coloGradient)

            }
            lineDataSet2.apply {
                //make chart smooth
                mode = LineDataSet.Mode.CUBIC_BEZIER
                cubicIntensity = 0.05f

                //set transparencyFF18A0FB
                setColor(label2.colorLine,1000)
                //set value in each circle
                setDrawValues(false)
                //Part4 set color fill (area)
                setDrawFilled(true)
                setDrawCircles(false)

                lineWidth = 3f

                fillDrawable = AppCompatResources.getDrawable(context, label2.coloGradient)

            }

            val iline = listOf(lineDataSet, lineDataSet2)


            //set data
            view.data = LineData(iline)


            view.invalidate()
        }
    )
}