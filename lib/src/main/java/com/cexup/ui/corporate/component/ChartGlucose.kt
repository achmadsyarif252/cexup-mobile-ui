package com.example.app_corporate.ui.component

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.appcompat.view.ContextThemeWrapper
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
import com.cexup.ui.R
import com.cexup.ui.corporate.screen.ValueBloodGlucose
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

@SuppressLint("ResourceType")
@Composable
fun ChartGlucose(
    listGlucose: List<ValueBloodGlucose>,
//    dataWithInsulin: List<Entry>,
    xValueFormater: List<String> = listOf(),
    is1Day: Boolean = false,
    description: String,
) {
    val isDark = isSystemInDarkTheme()
    val context = LocalContext.current
    val limitWidth = 0.7f

//    val dataBeforeMealChart = mutableListOf<BarEntry>()
//    val dataAfterMealChart = mutableListOf<BarEntry>()
//    dataBeforeMeal.forEachIndexed {index: Int, fl: Float ->
//        dataBeforeMealChart.add(BarEntry(index.toFloat(),fl))
//    }
//    dataAfterMeal.forEachIndexed {index: Int, fl: Float ->
//        dataAfterMealChart.add(BarEntry(index.toFloat(),fl))
//    }

    var dataWithDetails = mutableListOf<Entry>()
    var dataGlucose = mutableListOf<Entry>()
    listGlucose.mapIndexed { index, valueBloodGlucose ->
        if (is1Day) {
            dataGlucose.add(
                Entry(
                    valueBloodGlucose.time
                        .removeRange(0, 11)
                        .replace(":", ".")
                        .toFloat(),
                    valueBloodGlucose.value.toFloat()
                )
            )
            if (valueBloodGlucose.isDetail) {
                dataWithDetails.add(
                    Entry(
                        valueBloodGlucose.time
                            .removeRange(0, 11)
                            .replace(":", ".")
                            .toFloat(),
                        valueBloodGlucose.value.toFloat()
                    )
                )
//                                Log.d("Ichikiwer",valueBloodGlucose.time.removeRange(0,11).replace(":","."))
            } else {

            }
        }
        else {
            dataGlucose.add(
                Entry(
                    index.toFloat(),
                    valueBloodGlucose.value.toFloat()
                )
            )
            if (valueBloodGlucose.isDetail) {
                dataWithDetails.add(
                    Entry(
                        index.toFloat(),
                        valueBloodGlucose.value.toFloat()
                    )
                )
                //Log.d("Ichikiwer",valueBloodGlucose.time.removeRange(0,11).replace(":","."))
            } else {

            }
        }
    }
    AndroidView(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
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
                    setDrawLabels(false)

                    textColor =
                        if (isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                }
                legend.apply {
                    isEnabled = false
                }

                axisLeft.apply {
                    axisMaximum = 400f
                    axisMinimum = 20f
                    removeAllLimitLines()
                    setLabelCount(10)
                    //formatter
                    isEnabled = true
                    setDrawAxisLine(false)
                    setDrawGridLines(true)

//                    addLimitLine(LimitLine(50f).apply {
//                        lineColor = android.graphics.Color.RED
//                        lineWidth = 150f
//                    })
//                    setDrawLimitLinesBehindData(true)
                    textColor =
                        if (isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                }
                xAxis.apply {

                    if (!is1Day) {
                        setDrawLabels(false)
                    }
                    //disable axis
                    setDrawGridLines(false)
                    position = XAxis.XAxisPosition.BOTTOM
                    //
                    labelRotationAngle = 0f
                    setLabelCount(25)

                    granularity = 1f
                    if (is1Day) {
                        axisMinimum = 0f

                        axisMaximum = 24.9f
                    }
                    //
                    textColor =
                        if (isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                    textAlignment = View.TEXT_ALIGNMENT_CENTER

//                    valueFormatter = XValueFormatter()

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
//                val markerView = CustomChartMarker(context, R.layout.layout_marker_chart,false)
//                marker = markerView

            }

        },
        update = { view ->

//            view.xAxis.valueFormatter = XAxisTimeFormatter(xValueFormater)
            val lineDataSet = LineDataSet(dataGlucose, "Glucose")
            val lineDataSet2 = LineDataSet(dataWithDetails,"DataWithDetails")
//            val lineDataSet2 = BarDataSet(dataAfterMealChart, "After Meal")
            lineDataSet.apply {
                //make chart smooth
//                barBorderWidth = 0.3f
//                barBorderColor = 0x18C8EF

                //set transparencyFF18A0FB#F94144
                setColor(android.graphics.Color.parseColor(
                    context.resources.getString(
                        R.color.glucose_chart
                    )
                ), 1000)
                //set value in each circle
                setDrawValues(false)
                //Part4 set color fill (area)
                setDrawFilled(false)
                setDrawCircleHole(false)
                setCircleColor(android.graphics.Color.parseColor(
                    context.resources.getString(
                        R.color.glucose_chart
                    )
                ))
//                setCircleColors(listOf(
//                    android.graphics.Color.parseColor(
//                        context.resources.getString(
//                            R.color.glucose_chart
//                        )
//                    ),
//                    android.graphics.Color.parseColor(
//                        "#00000000"
//                    )
//                ))


                setDrawIcons(true)

                circleRadius = 4f



            }
            lineDataSet2.apply {
                //make chart smooth
                //make chart smooth
                //set transparencyFF18A0FB
                setColor(android.graphics.Color.parseColor(
                    context.resources.getString(
                        R.color.glucose_chart
                    )
                ))
                //set value in each circle
                setDrawValues(false)
                //Part4 set color fill (area)
                setDrawFilled(false)
                setCircleColor(android.graphics.Color.parseColor(
                    context.resources.getString(
                        R.color.glucose_chart
                    )
                ))
                enableDashedLine(0f, 1f, 0f)
                circleRadius = 7f
                circleHoleRadius = 4f

            }
            val iline = listOf(lineDataSet,lineDataSet2)

            view.data = LineData(iline)
            //set data
//            view.data = BarData(iline)
//
//            val groupSpace = 0.55f
//            val barSpace = 0.025f
//            val barWidth = 0.2f
//            // (barSpace + barWidth) * 2 + groupSpace = 1
//            // (barSpace + barWidth) * 2 + groupSpace = 1
//            view.data.setBarWidth(barWidth)
//            view.groupBars(-0.5f,groupSpace,barSpace)


            //set OnChartCLick Selected
            view.setOnChartValueSelectedListener(object : OnChartValueSelectedListener{
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    var cek = false
                    var xIndex = 0
                    dataWithDetails.forEach {entryDetails ->
                        if (entryDetails.x.toInt() == e?.x?.toInt() && entryDetails.y == e.y) {
                            if (!is1Day)
                                xIndex = entryDetails.x.toInt()
                            else
                                dataGlucose.forEachIndexed { index, entry ->
                                    if (entry.x == entryDetails.x)
                                        xIndex = index
                                }
                            cek = true
                        }
                    }
                    if (cek){
                        h.apply {
                            view.marker = CustomChartMarkerGlucose(
                                context = context,
                                layoutResource = R.layout.layout_marker_chart_glucose,
                                true,
                                valueDateAndTime = listGlucose[xIndex].time,
                                valueFoodAndDrink = listGlucose[xIndex].foodAndDrink
                                    ?: "",
                                typeMedicine = if (listGlucose[xIndex].insulin == 0)
                                   "Pills"
                                else
                                    "Insulin",
                                valueMedicine =
                                if (listGlucose[xIndex].insulin == 0)
                                    listGlucose[xIndex].pills ?: 0
                                else
                                    listGlucose[xIndex].insulin ?: 0
                            )
//                            if (is1Day){
//                                view.marker = CustomChartMarkerGlucose(
//                                    context,
//                                    R.layout.layout_marker_chart_glucose,
//                                    true,
//                                    valueFoodAndDrink = listGlucose[e?.x!!.toInt()].foodAndDrink
//                                        ?: "",
//                                    valueMedicine =
//                                    if (listGlucose[e?.x!!.toInt()].insulin == 0)
//                                        listGlucose[e?.x!!.toInt()].pills ?: 0
//                                    else
//                                        listGlucose[e?.x!!.toInt()].insulin ?: 0
//                                )
//                            }
//                            else {
//                                view.marker = CustomChartMarkerGlucose(
//                                    context,
//                                    R.layout.layout_marker_chart_glucose,
//                                    true,
//                                    valueFoodAndDrink = listGlucose[e?.x!!.toInt()].foodAndDrink
//                                        ?: "",
//                                    valueMedicine =
//                                    if (listGlucose[e?.x!!.toInt()].insulin == 0)
//                                        listGlucose[e?.x!!.toInt()].pills ?: 0
//                                    else
//                                        listGlucose[e?.x!!.toInt()].insulin ?: 0
//                                )
//                            }
                        }
                    }else if(e?.y == 0f){
                        h.apply { view.marker = null }
                    }
                    else{
                        h.apply {
                            view.marker = CustomChartMarkerGlucose(
                                context = context,
                                layoutResource = R.layout.layout_marker_chart_glucose,
                                isMedicine = false,
                                valueDateAndTime = listGlucose[xIndex].time,
                            )
                        }
                    }
                }

                override fun onNothingSelected() {
                    null
                }
            }

        )


            view.invalidate()
        }
    )
}