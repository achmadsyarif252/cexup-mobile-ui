package com.example.app_corporate.ui.component

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.cexup.ui.R

/**
 * `Custom Marker`
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 08/09/2021
 */
@SuppressLint("ViewConstructor")
class CustomChartMarkerGlucose(
    context: Context,
    layoutResource:Int,
    isMedicine: Boolean,
    typeMedicine:String = "",
    valueMedicine: Int = 0,
    valueDateAndTime: String = "",
    valueFoodAndDrink: String = "Ketoprak, Pop Ice Vanila Blue"
):MarkerView(context,layoutResource) {
    private val totalWidth = resources.displayMetrics.widthPixels
    val isMedicine = isMedicine
    val valueMedicine = valueMedicine
    val valueFoodAndDrink = valueFoodAndDrink
    val typeMedicine = typeMedicine
    val valueDateAndTime = valueDateAndTime
    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        val layoutWithoutInsulin = findViewById<LinearLayout>(R.id.valueOnly)
        val layoutWithInsulin = findViewById<LinearLayout>(R.id.valueWithInsulin)
        if (isMedicine){
            layoutWithInsulin.visibility = View.VISIBLE
            layoutWithoutInsulin.visibility = View.GONE
            val value = e?.y?.toDouble() ?: 0.0
            var resText = ""
            resText = if(value.toString().length > 8){
                value.toString().substring(0,7)
            }else{
                value.toString()
            }
            val titleMedicine = findViewById<TextView>(R.id.titleMedicine)
            val titleFoodAndDrink = findViewById<TextView>(R.id.titleFoodAndDrink)
            val spaceFoodAndDrink = findViewById<Space>(R.id.spaceFoodAndDrink)
            val tvMedicine = findViewById<TextView>(R.id.tvMedicine)
            val tvFoodAndDrink = findViewById<TextView>(R.id.tvFoodAndDrink)
            val tvValueGlucose = findViewById<TextView>(R.id.tvValueIfMedicine)
            val tvDateTime = findViewById<TextView>(R.id.tvValueDateAndTime)
            if (typeMedicine == "Pills")
                tvMedicine.text = "$valueMedicine $typeMedicine"
            else
                tvMedicine.text = "$typeMedicine $valueMedicine mg/dl"
            if (valueFoodAndDrink.isEmpty()) {
                titleFoodAndDrink.visibility = View.GONE
                tvFoodAndDrink.visibility = View.GONE
                spaceFoodAndDrink.visibility = View.GONE
            }
            if (valueMedicine == 0 || valueMedicine == null){
                titleMedicine.visibility = View.GONE
                tvMedicine.visibility = View.GONE
            }
            tvFoodAndDrink.text = valueFoodAndDrink
            tvValueGlucose.text = "$resText mg/dl"
            tvDateTime.text = valueDateAndTime
        }else {
            layoutWithoutInsulin.visibility = View.VISIBLE
            layoutWithInsulin.visibility = View.GONE
            val value = e?.y?.toDouble() ?: 0.0
            val tv = findViewById<TextView>(R.id.tvValue)
            val tvDateTime = findViewById<TextView>(R.id.tvValueDateAndTime2)
            var resText = ""
            resText = if(value.toString().length > 8){
                value.toString().substring(0,7)
            }else{
                value.toString()
            }
            tv.text = "$resText mg/dl"
            tvDateTime.text = valueDateAndTime
        }

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