package com.cexup.ui.corporate.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import com.cexup.ui.R
import com.cexup.ui.component.chart.BaseChartView
import com.cexup.ui.consumer.theme.GreyDividerAccount
import com.cexup.ui.consumer.theme.GreySoft
import com.cexup.ui.corporate.theme.GreyBorder
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.ChevronRight16
import androidx.compose.foundation.layout.padding as padding1

@Composable
fun CardWeightToAge(
    modifier: Modifier = Modifier,
    title: String = "Weight To Age Curve",
    xAxisUnit: String = "Age(Month)",
    yAxisUnit: String = "Weight(kg)",
    listDropDown: List<String> = listOf("Chart 1", "Chart 2"),
    listDataChart : List<Int> = listOf(),
    index: Int,
    onLeftClick : () -> Unit,
    onRightClick : () -> Unit
) {
    val ctx = LocalContext.current

    var stateOnClick by remember {
        mutableStateOf(false)
    }
    var valueOf by remember {
        mutableStateOf(listDropDown[0])
    }

    Card(
        modifier = modifier
            .coloredShadow(
                color = Color.Black.copy(0.05f),
                alpha = 0.1f,
                offsetY = 4.dp.from(ctx),
                shadowRadius = 5.dp.from(ctx)
            )
            .width(457.dp.from(ctx))
            .height(535.dp.from(ctx)),
        shape = RoundedCornerShape(12.dp.from(ctx)),
        elevation = 0.1.dp,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.onPrimary,
                        shape = RoundedCornerShape(topEnd = 12.dp.from(ctx), topStart = 12.dp.from(ctx))
                    )
                    .padding1(16.dp.from(ctx)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.circle_arrow),
                    contentDescription = "",
                    tint = if (index == 0) GreySoft else Color.White,
                    modifier = modifier
                        .size(16.dp.from(ctx))
                        .clickable {
                            if (index != 0){
                                onLeftClick()
                            }
                        }
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.body2.copy(
                        fontSize = 14.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        letterSpacing = 0.1.sp.from(ctx),
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )
                )
                Icon(
                    painter = painterResource(id = R.drawable.circlearrowright),
                    contentDescription = "",
                    tint = if (index == listDataChart.lastIndex) GreySoft else Color.White,
                    modifier = modifier
                        .size(16.dp.from(ctx))
                        .clickable {
                            if (index != listDataChart.lastIndex){
                                onRightClick()
                            }
                        }
                )
            }

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding1(horizontal = 16.dp.from(ctx), vertical = 12.dp.from(ctx))
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = RoundedCornerShape(6.dp.from(ctx))
                        )
                        .border(
                            color = GreyBorder,
                            width = 1.dp,
                            shape = RoundedCornerShape(6.dp.from(ctx))
                        )
                        .padding1(horizontal = 16.dp.from(ctx), vertical = 6.dp.from(ctx)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = valueOf,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp.from(ctx),
                            fontWeight = FontWeight(400),
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.25.sp.from(ctx),
                            color = MaterialTheme.colors.onBackground
                        )
                    )
                    Column(
                        modifier = modifier.clickable {
                            stateOnClick = !stateOnClick
                        }

                    ) {
                        Icon(
                            Octicons.ChevronRight16,
                            contentDescription = "",
                            modifier = modifier
                                .size(23.11.dp.from(ctx))
                                .rotate(if (stateOnClick) 90f else 0f),
                            tint = MaterialTheme.colors.onSecondary
                        )
                        if (stateOnClick) {
                            Popup(
                                offset = IntOffset(-1080, 125),
                                onDismissRequest = { stateOnClick = false }
                            ) {
                                Card(
                                    modifier = modifier
                                        .width(457.dp.from(ctx))
                                        .padding1(horizontal = 16.dp.from(ctx)),
                                    elevation = 1.dp,
                                    shape = RoundedCornerShape(10.dp.from(ctx)),
                                ) {
                                    Column(
                                        modifier = modifier
                                            .padding1(
                                                vertical = 6.dp.from(ctx),
                                                horizontal = 12.dp.from(ctx)),
                                        verticalArrangement = Arrangement.spacedBy(10.dp.from(ctx))
                                    ) {
                                        listDropDown.forEachIndexed { _, pair ->
                                            Row(
                                                modifier = modifier
                                                    .fillMaxWidth()
                                                    .clickable {
                                                        valueOf = pair
                                                        stateOnClick = false
                                                    }
                                            ) {
                                                Text(
                                                    text = pair,
                                                    style = MaterialTheme.typography.body1.copy(
                                                        color = MaterialTheme.colors.onBackground,
                                                        fontWeight = FontWeight(500),
                                                        lineHeight = 20.sp.from(ctx),
                                                        letterSpacing = 0.25.sp.from(ctx),
                                                        fontSize = 12.sp.from(ctx)
                                                    ),
                                                )
                                            }

                                        }

                                    }
                                }
                            }

                        }
                    }

                }
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding1(horizontal = 16.dp.from(ctx)),
            ) {
                Text(
                    text = yAxisUnit,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 12.sp.from(ctx),
                        fontWeight = FontWeight(400),
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.25.sp.from(ctx),
                        color = MaterialTheme.colors.onSecondary
                    )
                )
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(296.62.dp.from(ctx))
                ) {
                    BaseChartView(data = listOf(), description = "")
                }
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = xAxisUnit,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp.from(ctx),
                            fontWeight = FontWeight(400),
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.25.sp.from(ctx),
                            color = MaterialTheme.colors.onSecondary
                        )
                    )
                }
                Spacer(modifier = modifier.height(10.dp.from(ctx)))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_legend_chart),
                        contentDescription = "",
                        modifier = modifier
                            .width(18.dp.from(ctx))
                            .height(6.dp.from(ctx))
                    )
                    Spacer(modifier = modifier.width(8.dp.from(ctx)))
                    Text(
                        text = "Little One's Growth Data",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp.from(ctx),
                            fontWeight = FontWeight(400),
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.25.sp.from(ctx),
                            color = MaterialTheme.colors.onBackground
                        )
                    )
                }
            }

        }

    }
}

@ExperimentalMaterialApi
@Composable
fun ExpandableCard(
    title: String,
    description: String,
    descriptionMaxLines: Int = 6,
    padding: Dp = 16.dp,
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 90f else 0f
    )
    val ctx = LocalContext.current

    Card(
        modifier = Modifier
            .coloredShadow(
                color = Color.Black.copy(0.05f),
                alpha = 0.1f,
                offsetY = 4.dp.from(ctx),
                shadowRadius = 5.dp.from(ctx)
            )
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(12.dp.from(ctx)),
        elevation = 0.1.dp,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding1(padding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 14.sp.from(ctx),
                        fontWeight = FontWeight(600),
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.1.sp.from(ctx)

                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Column(
                    modifier = Modifier
                        .clickable {
                            expandedState = !expandedState
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.rightarrow),
                        contentDescription = "",
                        tint = if(expandedState) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary,
                        modifier = Modifier
                            .width(16.dp.from(ctx))
                            .height(16.dp.from(ctx))
                            .alpha(ContentAlpha.medium)
                            .rotate(rotationState),
                    )
                }
            }
            if (expandedState) {
                Spacer(modifier = Modifier.height(16.dp.from(ctx)))
                Divider(
                    color = GreyDividerAccount
                )
                Spacer(modifier = Modifier.height(16.dp.from(ctx)))
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 14.sp.from(ctx),
                        fontWeight = FontWeight(400),
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.1.sp.from(ctx),
                        textAlign = TextAlign.Justify
                    ),
                    maxLines = descriptionMaxLines,
                    overflow = TextOverflow.Ellipsis

                )
            }
        }
    }
}
