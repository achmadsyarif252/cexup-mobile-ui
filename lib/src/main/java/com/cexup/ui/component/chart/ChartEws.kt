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
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.theme.PrimaryCorporate


@Composable
fun ChartEws(
    modifier: Modifier = Modifier,
    result:String="Normal",
    percent:Float,
    number:Int = 0,
){
    val currentPercentage = remember { Animatable(0.01f) }

    LaunchedEffect(percent) {
        currentPercentage.animateTo(
            percent,
            animationSpec = tween(durationMillis = 1000, delayMillis = 1300)
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(30.dp*2f)
    ){
        Canvas(modifier = modifier.size(30.dp*2f),){
            rotate(90f){
                drawArc(
                    color= Color.Gray.copy(alpha = 0.2f),
                    startAngle = 3f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(2.dp.toPx(),
                        cap = StrokeCap.Round)
                )
                drawArc(
                    brush = Brush.sweepGradient(
                        colors=listOf(
                            PrimaryCorporate,
                            PrimaryCorporate,
                            PrimaryCorporate,
                            PrimaryCorporate,
                            PrimaryCorporate
                        ),
                    ),
                    startAngle = 3f,
                    sweepAngle = 360 * currentPercentage.value,
                    useCenter = false,
                    style = Stroke(8.dp.toPx(),
                        cap = StrokeCap.Round
                    ),
                )
            }
        }
        Text(
            text = result,//percent.toString() ,//(currentPercentage.value*number).toInt().toString(),
            color= if(isSystemInDarkTheme()) MaterialTheme.colors.primary else Color.Black,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
fun Preview2(){
    ChartEws(
        percent = 0.2f,

        )
}
