package com.cexup.ui.corporate.component

import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.utils.mediaquery.from
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter


data class PieChartData(
    var dataName: String? = "",
    var value: Float? = 0f,
    var color: Color = Color.White
)

//val getPieChartData = listOf(
//    PieChartData("Male", 26F),
//    PieChartData("Female", 24F),
//    PieChartData("Children", 50F),
//)


@Composable
fun PieChartDashboard(
    totalPatients: Int = 0,
    dataPieChart :List<PieChartData> = listOf()
) {
    val ctx = LocalContext.current
    val centerText = "$totalPatients\nPatients"
    val index: Int = centerText.indexOf("\n")

    val tempSpannable: Spannable = SpannableString(centerText)
    tempSpannable.setSpan(RelativeSizeSpan(2.2857144f), 0, index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    tempSpannable.setSpan(
        ForegroundColorSpan(GrayDashboardNew.toArgb()),
        0, index, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    var listLegend = mutableListOf<LegendEntry>()
    dataPieChart.forEach {
        listLegend.add(LegendEntry(it.dataName,Legend.LegendForm.CIRCLE,10f,2f, null,it.color.toArgb()))
    }

    // If width is 0, it will crash. Always have a minimum of 1
//    mCenterTextLayout = StaticLayout(
//        tempSpannable, 0, centerText.length(),
//        mCenterTextPaint, Math.max(Math.ceil(mCenterTextLastBounds.width()), 1.0).toInt(),
//        Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false
//    )
    val textCenterSize = 14.dp.value
    Crossfade(
        targetState = dataPieChart,
//        modifier = Modifier.padding(16.dp.from(ctx))
    ) { pieChartData ->
        AndroidView(
            factory = { context ->
                // on below line we are creating a pie chart
                // and specifying layout params.
                PieChart(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        // on below line we are specifying layout
                        // params as MATCH PARENT for height and width.
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    this.setUsePercentValues(true)
                    // on below line we are setting description
                    // enables for our pie chart.
                    this.description.isEnabled = false
                    this.isHighlightPerTapEnabled = false
                    this.setExtraOffsets(10f,10f,10f,10f)
                    this.setCenterTextColor(GrayDashboardNew.toArgb())
                    this.centerText = tempSpannable
                    this.setCenterTextTypeface(Typeface.DEFAULT_BOLD)
                    this.setCenterTextSize(textCenterSize)

                    // on below line we are setting draw hole
                    // to false not to draw hole in pie chart
                    this.isDrawHoleEnabled = true
                    this.holeRadius = 81f
                    this.transparentCircleRadius = 0f

                    // on below line we are enabling legend.
                    this.legend.isEnabled = true

                    // on below line we are specifying
                    // text size for our legend.
                    this.legend.textSize = 14F
                    this.legend.setCustom(listLegend)
                    this.legend.xOffset = -(10f)

                    // on below line we are specifying
                    // alignment for our legend.
                    this.legend.horizontalAlignment =
                        Legend.LegendHorizontalAlignment.CENTER
                    // on below line we are specifying entry label color as white.
//                    this.setEntryLabelColor(resources.getColor(R.color.white))
                }
            },
            // on below line we are specifying modifier
            // for it and specifying padding to it.
            modifier = Modifier
                .wrapContentSize(),
            update = {
                // on below line we are calling update pie chart
                // method and passing pie chart and list of data.
                updatePieChartWithData(it, pieChartData)
            }
        )

    }
}

// on below line we are creating a update pie
// chart function to update data in pie chart.
fun updatePieChartWithData(
    // on below line we are creating a variable
    // for pie chart and data for our list of data.
    chart: PieChart,
    data: List<PieChartData>
) {
    // on below line we are creating
    // array list for the entries.
    val entries = ArrayList<PieEntry>()
    val colorsPie = ArrayList<Int>()

    // on below line we are running for loop for
    // passing data from list into entries list.
    for (i in data.indices) {
        val item = data[i]
        entries.add(PieEntry(item.value ?: 0.toFloat(), item.dataName ?: ""))
        colorsPie.add(item.color.toArgb())
    }

    // on below line we are creating
    // a variable for pie data set.
    val ds = PieDataSet(entries, "")

    // on below line we are specifying color
    // int the array list from colors.
    ds.colors = colorsPie
    // on below line we are specifying position for value
    ds.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

    // on below line we are specifying position for value inside the slice.
    ds.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

    // on below line we are specifying
    // slice space between two slices.
    ds.sliceSpace = 4f

//    ds.valueLineWidth = 0f

    // on below line we are specifying text color
    ds.valueTextColor = Neutral8.toArgb()

    // on below line we are specifying
    // text size for value.
    ds.valueTextSize = 12f

    // on below line we are specifying type face as bold.
    ds.valueTypeface = Typeface.DEFAULT_BOLD

    // on below line we are creating
    // a variable for pie data
    val d = PieData(ds)
    d.setValueFormatter(PercentFormatter(chart))
    // on below line we are setting this
    // pie data in chart data.
    chart.data = d

    // on below line we are
    // calling invalidate in chart.
    chart.invalidate()

}
