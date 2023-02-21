package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.R
import com.cexup.ui.theme.BlueConnectGlucose
import com.cexup.ui.theme.GrayDivider
import com.cexup.ui.theme.GrayGlucose
import com.cexup.ui.theme.GreenNormalGlucose
import com.cexup.ui.utils.mediaquery.from

data class ValueHemoglobin(
    val valueHemoglobin: Int,
    val date: String,
)

@Composable
fun DialogHistoryHemoglobin(
    show: Boolean = false,
    onCancel: () -> Unit,
    dataHemoglobin: List<ValueHemoglobin> = listOf(),
    onSortDateClicked: () -> Unit,
    onSortResultClicked: () -> Unit,
) {
    val ctx = LocalConfiguration.current
    val interactionSource = MutableInteractionSource()
    if (show) {
        Dialog(
            onDismissRequest = {
                onCancel()
            }
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp.from(ctx)),
                color = Color.White
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp.from(ctx)),
                    verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
                ) {
                    item {
                        Row {
                            Text(
                                text = stringResource(id = R.string.history_hemoglobin),
                                style = MaterialTheme.typography.subtitle1.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp.from(ctx),
                                    lineHeight = 28.sp.from(ctx),
                                )
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                modifier = Modifier
                                    .clickable {
                                        onCancel()
                                    }
                                    .padding(5.dp.from(ctx)),
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = ""
                            )
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .background(BlueConnectGlucose)
                                .padding(horizontal = 19.dp.from(ctx), vertical = 16.dp.from(ctx)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier
                                    .width(41.dp.from(ctx)),
                                text = stringResource(id = R.string.no),
                                style = MaterialTheme.typography.subtitle1.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp.from(ctx),
                                    lineHeight = 28.sp.from(ctx),
                                )
                            )
                            Box(
                                modifier = Modifier
                                    .width(255.dp.from(ctx))
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) {
                                        onSortDateClicked()
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp.from(ctx))
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.date),
                                        style = MaterialTheme.typography.subtitle1.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 16.sp.from(ctx),
                                            lineHeight = 28.sp.from(ctx),
                                        )
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_sort),
                                        contentDescription = "Sort"
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .width(255.dp.from(ctx))
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) {
                                        onSortResultClicked()
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp.from(ctx))
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.result),
                                        style = MaterialTheme.typography.subtitle1.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 16.sp.from(ctx),
                                            lineHeight = 28.sp.from(ctx),
                                        )
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_sort),
                                        contentDescription = "Sort"
                                    )
                                }
                            }
                        }
                        Divider(color = GrayDivider, thickness = 1.dp.from(ctx))
                    }
                    items(count = dataHemoglobin.size) { index ->
                        Column {
                            Row(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 19.dp.from(ctx),
                                        vertical = 16.dp.from(ctx)
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    modifier = Modifier
                                        .width(41.dp.from(ctx)),
                                    text = "${index + 1}",
                                    style = MaterialTheme.typography.subtitle1.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp.from(ctx),
                                        lineHeight = 28.sp.from(ctx),
                                        color = GrayGlucose
                                    ),
                                    maxLines = 1,
                                )
                                Text(
                                    modifier = Modifier
                                        .width(255.dp.from(ctx)),
                                    text = dataHemoglobin.get(index).date,
                                    style = MaterialTheme.typography.subtitle1.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp.from(ctx),
                                        lineHeight = 28.sp.from(ctx),
                                        color = GrayGlucose
                                    ),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    modifier = Modifier
                                        .width(255.dp.from(ctx)),
                                    text = dataHemoglobin.get(index).valueHemoglobin.toString() + " mg/dl",
                                    style = MaterialTheme.typography.subtitle1.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp.from(ctx),
                                        lineHeight = 28.sp.from(ctx),
                                        color = GreenNormalGlucose
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Divider(color = GrayDivider, thickness = 1.dp.from(ctx))
                        }
                    }
                }
            }
        }
    }
}