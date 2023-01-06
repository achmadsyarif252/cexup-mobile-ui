package com.cexup.ui.corporate.component

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.cexup.ui.R
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF


/**
 * `Custom Marker`
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 08/09/2021
 */
@SuppressLint("ViewConstructor")
class CustomChartMarkerBabyGrowth(context: Context, layoutResource:Int):MarkerView(context,layoutResource) {
    private val totalWidth = resources.displayMetrics.widthPixels
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        val value = e?.y?.toDouble() ?: 0.0
        val resText: String = if(value.toString().length > 8){
            value.toString().substring(0,7)
        }else{
            value.toString()
        }

        val tv = findViewById<TextView>(R.id.tvValue)
        tv.text = resText
        super.refreshContent(e, highlight)
    }

    override fun getOffsetForDrawingAtPoint(posX: Float, posY: Float): MPPointF {
        val supposedX = posX + width
        val mpPointF = MPPointF()

        mpPointF.x = when {
            supposedX > totalWidth -> -width.toFloat()
            posX - width < 0 -> 0f
            else -> 0f
        }

        mpPointF.y = if (posY > height)
            -height.toFloat()
        else
            0f
        return mpPointF
    }
}