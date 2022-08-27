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
import com.cexup.ui.utils.mediaquery.from
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun DialogLoading(
    modifier:Modifier=Modifier,
    show:Boolean,
    onDismiss:()->Unit
){
    val ctx = LocalContext.current

    if(show){
        Dialog(onDismissRequest = onDismiss) {
            Box(
                contentAlignment = Alignment.Center,
                    modifier= modifier
                        .size(100.dp.from(ctx))
                        .background(Color.White, shape = RoundedCornerShape(8.dp.from(ctx)))
                ) {
                    CircularProgressIndicator()
                }

        }
    }
}