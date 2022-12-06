package com.cexup.ui.corporate.component

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class XValueFormatter : ValueFormatter(){
    private val xAxis = arrayOf(
        "Night","Wake Up","Breakfast","Lunch","Dinner","Sleeping",""
    )
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return xAxis.getOrNull(value.toInt()) ?: value.toString()
    }
}