package com.cexup.ui.utils.mediaquery

import android.content.Context
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
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
    ctx:Configuration,
    defaultScreenWidth: Double = 1003.0,
    defaultScreenHeight: Double = 627.0
): Dp{
//    val density = ctx.resources.displayMetrics.density
//    val currentW=  ctx.resources.displayMetrics.heightPixels.dp/density
//    val currentH = ctx.resources.displayMetrics.widthPixels.dp/density
    val currentH = ctx.screenHeightDp.dp
    val currentW = ctx.screenWidthDp.dp

    val currentDiagonalScreen = sqrt(currentW.value.toDouble().pow(2)+currentH.value.toDouble().pow(2)).dp

    //now design using size with w = 375 h = 812
    val defWidth = defaultScreenWidth.pow(2)
    val defHeight = defaultScreenHeight.pow(2)
    val defScreenDiagonal = sqrt(defWidth + defHeight)

    val resultCompare = currentDiagonalScreen.value/defScreenDiagonal

    val result = (this.value*resultCompare)

    return  result.dp

}
/**
 * Media query for text unit multiple screen
 *
 **/

fun TextUnit.from(
    ctx: Configuration,
    defaultScreenWidth: Double = 1003.0,
    defaultScreenHeight: Double = 627.0
): TextUnit{
//    val density = ctx.resources.displayMetrics.density
//    val currentW=  ctx.resources.displayMetrics.heightPixels.dp/density
//    val currentH = ctx.resources.displayMetrics.widthPixels.dp/density
    val currentH = ctx.screenHeightDp.dp
    val currentW = ctx.screenWidthDp.dp
    val currentDiagonalScreen = sqrt(currentW.value.toDouble().pow(2)+currentH.value.toDouble().pow(2)).dp


    //now design using default size with w = 375 h = 812
    val defWidth = defaultScreenWidth.pow(2)
    val defHeight = defaultScreenHeight.pow(2)
    val defScreenDiagonal = sqrt(defWidth + defHeight)

    val resultCompare = currentDiagonalScreen.value/defScreenDiagonal
    val result = (this.value*resultCompare)

    return  result.sp

}