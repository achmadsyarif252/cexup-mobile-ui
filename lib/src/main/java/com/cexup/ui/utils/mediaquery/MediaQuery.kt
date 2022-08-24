package com.cexup.ui.utils.mediaquery

import android.content.Context
import android.util.DisplayMetrics
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * `Support Different Screen Size`
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 13/10/2021
 */
fun Dp.from(
    ctx:Context,
    defaultScreenWidth: Double = 375.0,
    defaultScreenHeight: Double = 812.0
): Dp{
    val density = ctx.resources.displayMetrics.density
    val currentW=  ctx.resources.displayMetrics.heightPixels.dp/density
    val currentH = ctx.resources.displayMetrics.widthPixels.dp/density
    val currentDiagonalScreen = sqrt(currentW.value.toDouble().pow(2)+currentH.value.toDouble().pow(2)).dp

    //now design using size with w = 375 h = 812
    val defWidth = defaultScreenWidth.pow(2)
    val defHeight = defaultScreenHeight.pow(2)
    val defScreenDiagonal = sqrt(defWidth + defHeight)

    val resultCompare = currentDiagonalScreen.value/defScreenDiagonal

    val result = (this.value*resultCompare).toInt()

    return  result.dp

}
/**
 * Media query for text unit multiple screen
 *
 **/

fun TextUnit.from(
    ctx: Context,
    defaultScreenWidth: Double = 375.0,
    defaultScreenHeight: Double = 812.0
): TextUnit{
    val density = ctx.resources.displayMetrics.density
    val currentW=  ctx.resources.displayMetrics.heightPixels.dp/density
    val currentH = ctx.resources.displayMetrics.widthPixels.dp/density
    val currentDiagonalScreen = sqrt(currentW.value.toDouble().pow(2)+currentH.value.toDouble().pow(2)).dp


    //now design using default size with w = 375 h = 812
    val defWidth = defaultScreenWidth.pow(2)
    val defHeight = defaultScreenHeight.pow(2)
    val defScreenDiagonal = sqrt(defWidth + defHeight)

    val resultCompare = currentDiagonalScreen.value/defScreenDiagonal
    val result = (this.value*resultCompare).toInt()

    return  result.sp

}