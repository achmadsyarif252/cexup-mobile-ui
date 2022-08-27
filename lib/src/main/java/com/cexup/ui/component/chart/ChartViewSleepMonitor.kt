package com.cexup.ui.component.chart

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.graphics.Typeface

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Base Chart
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 07/09/2021
 */

data class SleepChatUiState(
    val rect:Rect,
    val color: Color,
    val time: String,
    val name:String
)

@Composable
fun ChartViewSleepMonitor(
    modifier: Modifier = Modifier,
    state:List<SleepChatUiState> = listOf(),
    height:Boolean=false,
    startTime :String="00:00",
    endTime :String="00:00",

    ){
    val isDark = isSystemInDarkTheme()
    val screenWidth = LocalContext.current.resources.displayMetrics.widthPixels.dp/ LocalDensity.current.density

    val screenHeight = if(!height){
        (LocalContext.current.resources.displayMetrics.heightPixels.dp/ LocalDensity.current.density).value
    }else{
        screenWidth.value
    }

    var selectedOffset by remember {
        mutableStateOf<Offset?>(null)
    }
    var selectedText by remember {
        mutableStateOf<String>("")
    }
    val paint = Paint().asFrameworkPaint()
    SideEffect {
        selectedOffset = null
        selectedText = ""
    }
    val deepColor = MaterialTheme.colors.primary.copy(alpha = 0.8f)
    val lightColor = MaterialTheme.colors.primary.copy(alpha = 0.4f)

    Canvas(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .fillMaxHeight()
            .pointerInput(Unit) {
                detectTapGestures { offset: Offset ->
                    state.forEach {
                        if (it.rect.contains(offset = offset)) {
                            selectedOffset = offset
                            selectedText = "${it.name}-${it.time}"
                        }
                    }
                }
            },
        onDraw = {
            state.forEachIndexed {
                _,t->
                drawRect(
                    t.color,
                    topLeft = t.rect.topLeft,
                    size = t.rect.size
                )
            }
            drawRect(
                deepColor,
                topLeft = Offset(20f,screenHeight+30 ),
                size = Size(
                    40f,
                    50f
                )
            )
            drawRect(
                lightColor,
                topLeft = Offset( 200f,screenHeight+30 ),
                size = Size(
                    40f,
                    50f
                )
            )

            selectedOffset?.let {
                offset->
                drawIntoCanvas {
                    it.nativeCanvas.drawText(selectedText,offset.x,offset.y,paint)
                }
            }
            paint.apply {
                isAntiAlias = true
                textSize = 24f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }

            drawIntoCanvas {
                paint.color = if(isDark) android.graphics.Color.WHITE else android.graphics.Color.BLACK
                it.nativeCanvas.drawText("Deep Sleep",60f,screenHeight+40,paint)
                it.nativeCanvas.drawText("Light Sleep",250f,screenHeight+40,paint)


                it.nativeCanvas.drawText(startTime,0f,screenHeight+20,paint)
                it.nativeCanvas.drawText(endTime,screenWidth.value*2,screenHeight+20,paint)
            }

        }
    )
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun PreviewBaseChartViewSleepMonitor(){
    MaterialTheme {
        ChartViewSleepMonitor()
    }
}
