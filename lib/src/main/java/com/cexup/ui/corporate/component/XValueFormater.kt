package com.cexup.ui.corporate.component

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class XValueFormatter(FormatXLabel: List<String>) : ValueFormatter(){
    private val xAxis = FormatXLabel
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return xAxis.getOrNull(value.toInt()) ?: ""
    }
}