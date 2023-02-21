package com.cexup.ui.component.chart

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.theme.Heading
import com.cexup.ui.utils.mediaquery.from



/**
 * Component Circular Chart
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 02/09/2021
 */


val normalPrimary = Color(0xFF77DAFA)
@Composable
fun CircularChartHealthStatus(
    modifier: Modifier = Modifier,
    result:String="Normal",
    percent:Float,
    diameter : Dp = 64.dp,
    brush: List<Color> = listOf(
        normalPrimary,
        normalPrimary.copy(0f),
    )
){

    val ctx = LocalConfiguration.current
    val currentPercentage = remember { Animatable(0.01f) }

    LaunchedEffect(percent) {
        currentPercentage.animateTo(
            percent,
            animationSpec = tween(durationMillis = 1000, delayMillis = 1300)
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(diameter)
    ){
        Canvas(modifier = modifier.size(diameter)){
            drawArc(
                color= Color.Gray.copy(alpha = 0.2f),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke((7.dp.from(ctx)).toPx(),
                    cap = StrokeCap.Round)
            )
            drawArc(
                brush = Brush.linearGradient(
                    colors = brush,
                ),
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke((7.dp.from(ctx)).toPx(),
                    cap = StrokeCap.Round)
            )


        }
        Text(
            text = result,//percent.toString() ,//(currentPercentage.value*number).toInt().toString(),
            style = MaterialTheme.typography.subtitle2.copy(
                color= if(isSystemInDarkTheme()) MaterialTheme.colors.primary else Color.Black,
                fontSize = 10.sp.from(ctx),
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp.from(ctx),
                letterSpacing = 0.5.sp.from(ctx)
            ),

            )
    }
}

@Composable
fun CircularBarFeatures(
    modifier: Modifier = Modifier,
//    result:String="-",
    valueOfMeasurement:Float,
    maxValue:Int,
    radius : Dp = 50.dp,
    brush: Brush = Brush.sweepGradient(
        colors=listOf(
            Heading,
            Heading.copy(0.6f),
        ),
    ),
) {
    val currentPercentage = remember { Animatable(0.01f) }
    val value : Float = (valueOfMeasurement*1f/maxValue)
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
//        val primaryBlue = MaterialTheme.colors.primary
        Canvas(modifier = modifier.size(radius*2f)){
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
        Text(
            text = "${valueOfMeasurement.toInt()}%",
            //percent.toString() ,//(currentPercentage.value*number).toInt().toString(),
            color = if(isSystemInDarkTheme()) MaterialTheme.colors.primary else Heading,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

@Preview
@Composable
fun PreviewCircularChart(){
    CircularChartHealthStatus(
        percent =0.2f,
    )
}

