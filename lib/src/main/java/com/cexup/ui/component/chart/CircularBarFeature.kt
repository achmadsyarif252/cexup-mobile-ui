package com.cexup.ui.component.chart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.trian.domain.models.bean.MedicalRecordType
val Heading = Color(0xFF008CA3)
@Composable
fun CircularBarFeatures(
    modifier: Modifier = Modifier,
    result:String="-",
    valueOfMeasurement:Float,
    maxValue:Int,
    radius : Dp = 50.dp,
    brush: Brush = Brush.sweepGradient(
        colors=listOf(
            Heading,
            Heading.copy(0.6f),
            ),
    ),
    medicalRecordType: MedicalRecordType = MedicalRecordType.BLOOD_OXYGEN,


    ){
    val currentPercentage = remember { Animatable(0.01f) }
    var value : Float = (valueOfMeasurement*1f/maxValue)
    LaunchedEffect(value) {
        currentPercentage.animateTo(
            value,
            animationSpec = tween(durationMillis = 1000, delayMillis = 1300)
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(radius*2f)
    ){
        val primaryBlue = MaterialTheme.colors.primary
        Canvas(modifier = modifier.size(radius*2f),){
                drawArc(
                    color= Color.Gray.copy(alpha = 0.2f),
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(8.dp.toPx(),
                        cap = StrokeCap.Round)
                )
                drawArc(
                    /**
                     * there ara 4 common color in health meter
                     * green
                     * soft green
                     * yellow
                     * orange
                     * red
                     * **/
                    /**
                     * there ara 4 common color in health meter
                     * green
                     * soft green
                     * yellow
                     * orange
                     * red
                     * **/
                    /**
                     * there ara 4 common color in health meter
                     * green
                     * soft green
                     * yellow
                     * orange
                     * red
                     * **/
                    /**
                     * there ara 4 common color in health meter
                     * green
                     * soft green
                     * yellow
                     * orange
                     * red
                     * **/

                    brush = brush,
                    startAngle = -90f,
                    sweepAngle = 360 * currentPercentage.value,
                    useCenter = false,
                    style = Stroke(8.dp.toPx(),
                        cap = StrokeCap.Round)
                )


        }
        when(medicalRecordType){
            MedicalRecordType.BLOOD_OXYGEN ->{
                Text(
                    text = "${valueOfMeasurement.toInt().toString()}%" ,//percent.toString() ,//(currentPercentage.value*number).toInt().toString(),
                    color= if(isSystemInDarkTheme()) MaterialTheme.colors.primary else Heading,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            MedicalRecordType.HEART_RATE ->{
                Text(
                    text = "${valueOfMeasurement.toInt().toString()}" ,//percent.toString() ,//(currentPercentage.value*number).toInt().toString(),
                    color= if(isSystemInDarkTheme()) MaterialTheme.colors.primary else Heading,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}


@Composable
@Preview
fun PreviewCircularBarFeature(){
    MaterialTheme() {
        CircularBarFeatures(valueOfMeasurement= 90f, maxValue = 100 )
    }
}
