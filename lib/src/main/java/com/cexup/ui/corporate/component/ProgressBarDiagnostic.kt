package com.cexup.ui.corporate.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.cexup.ui.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from

@Composable
fun ProgressBarDiagnostic(
    initialValue: Float = 0f,
    maxValue: Float = 100f,
    colorProgressBar: Color = MaterialThemeCexup.colors.palette.tertiary.redTertiary6,
    colorBackgroundProgressBar: Color = MaterialThemeCexup.colors.palette.grey.grey4
){
    var progress by remember { mutableStateOf(initialValue) }
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value
    val ctx = LocalConfiguration.current

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp.from(ctx))
    ) {
        // Background indicator
        drawLine(
            color = colorBackgroundProgressBar,
            cap = StrokeCap.Round,
            strokeWidth = size.height,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = 0f)
        )

        val progress =
            (animatedProgress / maxValue) * size.width // size.width returns the width of the canvas

        // Foreground indicator
        drawLine(
            color = colorProgressBar,
            cap = StrokeCap.Round,
            strokeWidth = size.height,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = progress, y = 0f)
        )

    }
}