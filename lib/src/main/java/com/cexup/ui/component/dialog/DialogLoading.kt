package com.cexup.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.utils.mediaquery.dpToMultipleScreen
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun DialogLoading(
    modifier:Modifier=Modifier,
    show:Boolean,
    onDismiss:()->Unit
){
    val ctx = LocalContext.current
    val screenHeight = ctx
        .resources
        .displayMetrics.heightPixels.dp /
            LocalDensity.current.density
    val screenWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density

    val a = screenHeight.value.toDouble().pow(2.0)
    val b = screenWidth.value.toDouble().pow(2.0)
    val currentDiagonal = sqrt(a + b).dp

    if(show){
        Dialog(onDismissRequest = onDismiss) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier= modifier
                        .size(100.dp.dpToMultipleScreen(currentDiagonal))
                        .background(Color.White, shape = RoundedCornerShape(8.dp.dpToMultipleScreen(currentDiagonal)))
                ) {
                    CircularProgressIndicator()
                }

        }
    }
}