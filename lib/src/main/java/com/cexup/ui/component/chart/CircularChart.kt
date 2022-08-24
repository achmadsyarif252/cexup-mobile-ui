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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.utils.mediaquery.dpToMultipleScreen
import com.cexup.ui.utils.mediaquery.textUnitToMultipleScreen
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * Component Circular Chart
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 02/09/2021
 */

val RedPrimary = Color(0xFFF24822)
val YellowPrimary = Color(0xFFFFEB00)
val OrangePrimary = Color(0xFFFFAC4B)
val normalPrimary = Color(0xFF77DAFA)
@Composable
fun CircularChartHealthStatus(
    modifier: Modifier = Modifier,
    result:String="Normal",
    percent:Float,
    number:Int,
    diameter : Dp = 64.dp,
    brush: List<Color> = listOf(
        normalPrimary,
        normalPrimary.copy(0f),
    )
){

    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val currentHeight = LocalContext
        .current
        .resources
        .displayMetrics.heightPixels.dp /
            LocalDensity.current.density

    val a = currentHeight.value.toDouble().pow(2.0)
    val b = currentWidth.value.toDouble().pow(2.0)
    val screenDiagonal = sqrt(a + b).dp

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
        val primaryBlue = MaterialTheme.colors.onPrimary
        Canvas(modifier = modifier.size(diameter)){
            drawArc(
                color= Color.Gray.copy(alpha = 0.2f),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke((7.dp.dpToMultipleScreen(screenDiagonal)).toPx(),
                    cap = StrokeCap.Round)
            )
            drawArc(
                brush = Brush.linearGradient(
                    colors = brush,
                ),
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke((7.dp.dpToMultipleScreen(screenDiagonal)).toPx(),
                    cap = StrokeCap.Round)
            )


        }
        Text(
            text = result,//percent.toString() ,//(currentPercentage.value*number).toInt().toString(),
            style = MaterialTheme.typography.subtitle2.copy(
                color= if(isSystemInDarkTheme()) MaterialTheme.colors.primary else Color.Black,
                fontSize = 10.sp.textUnitToMultipleScreen(screenDiagonal),
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp.textUnitToMultipleScreen(screenDiagonal),
                letterSpacing = 0.5.sp.textUnitToMultipleScreen(screenDiagonal)
            ),

            )
    }
}



@Preview
@Composable
fun PreviewCircularChart(){
    CircularChartHealthStatus(
        percent =0.2f,
        number = 40
    )
}

