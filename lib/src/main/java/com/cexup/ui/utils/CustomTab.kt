package com.cexup.ui.utils

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.domain.models.response.SpecialityResponse

/**
 * Custom tab indicator
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 10/09/2021
 */

@Composable
fun TextTab(
    modifier:Modifier=Modifier,
    tabSelected: Int,
    tabData: List<SpecialityResponse>,
    onSelected: (index: Int, specialist: SpecialityResponse) -> Unit
) {
    LaunchedEffect(key1 = Unit, block = {
        if(tabData.isNotEmpty()){
            onSelected(0,tabData.first())
        }
    })
    ScrollableTabRow(
        backgroundColor = MaterialTheme.colors.background,
        selectedTabIndex = tabSelected,
        divider = {},
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .customTabIndicatorOffset(tabPositions[tabSelected],tabData.size.toFloat())
                    .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                height = 4.dp,
                color = MaterialTheme.colors.primary
            )
        },
        edgePadding = 0.dp,
        tabs = {
            tabData.forEachIndexed { index, text ->
                Tab(
                    selected = tabSelected == index,
                    onClick = {
                        onSelected(index, text)
                    },
                    modifier=modifier.padding(10.dp),
                    text = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ){
                            CoilImage(
                                modifier = modifier
                                    .clip(CircleShape)
                                    .coloredShadow(MaterialTheme.colors.primary)
                                    .width(40.dp)
                                    .height(40.dp),
                                imageModel = text.icon ?: "",
                                // Crop, Fit, Inside, FillHeight, FillWidth, None
                                contentScale = ContentScale.Crop,
                                // shows an image with a circular revealed animation.
                                circularReveal = CircularReveal(duration = 250),
                                // shows a placeholder ImageBitmap when loading.
                                placeHolder = ImageBitmap.imageResource(com.trian.component.R.drawable.dummy_profile),
                                // shows an error ImageBitmap when the request failed.
                                error = ImageBitmap.imageResource(com.trian.component.R.drawable.dummy_doctor)
                            )
                            Spacer(modifier=modifier.height(5.dp))
                            Text(
                                text = text.name,
                                color = when (tabSelected) {
                                    index -> MaterialTheme.colors.primary
                                    else -> Color.DarkGray
                                },
                                style = MaterialTheme.typography.subtitle2
                            )
                        }
                    }
                )
            }
        })
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    divider : Float,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val ctx = LocalContext.current
    val currentWidth2 = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val indicatorWidth = currentWidth2/divider
    val currentTabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + currentTabWidth / 2 - indicatorWidth / 2,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.custom2TabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val indicatorWidth = 80.dp
    val currentTabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + currentTabWidth / 2 - indicatorWidth / 2,
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(indicatorWidth)
}