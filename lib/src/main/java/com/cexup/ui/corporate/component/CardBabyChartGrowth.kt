package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.Natural
import com.cexup.ui.utils.mediaquery.from
import com.github.mikephil.charting.data.Entry

@Composable
fun CardWeightToAge(
    modifier: Modifier = Modifier,
    title: String = "",
    xAxisUnit: String = "",
    yAxisUnit: String = "",
    listDataChart: List<Entry> = listOf(),
    index: Int,
) {
    val ctx = LocalContext.current

    Card(
        modifier = modifier
            .width(457.dp.from(ctx)),
        shape = RoundedCornerShape(12.dp.from(ctx)),
        elevation = 2.dp.from(ctx),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = BlueJade,
                        shape = RoundedCornerShape(
                            topEnd = 16.dp.from(ctx),
                            topStart = 16.dp.from(ctx)
                        )
                    )
                    .padding(horizontal = 16.dp.from(ctx), vertical = 12.dp.from(ctx)),
                contentAlignment = Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontSize = 16.sp.from(ctx),
                        letterSpacing = 0.1.sp.from(ctx),
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                    )
                )
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp.from(ctx)),
                verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
            ) {
                Text(
                    text = yAxisUnit,
                    style = MaterialTheme.typography.body2.copy(
                        fontSize = 14.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = Natural.copy(alpha = 0.7f)
                    )
                )
                Column(
                    modifier = modifier
                        .width(400.dp.from(ctx))
                        .height(263.46.dp.from(ctx))
                ) {
                    ChartBabyGrowth(
                        data = if (listDataChart.isEmpty()) listOf(Entry(-1f,-1f)) else listDataChart,
                        description = "",
                        maxAxisX = if (listDataChart.isEmpty())5f else listDataChart[listDataChart.size-1].x,
                        maxAxisY = if (listDataChart.isEmpty())10f else listDataChart[listDataChart.size-1].y,
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = xAxisUnit,
                    style = MaterialTheme.typography.body2.copy(
                        fontSize = 14.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = Natural.copy(alpha = 0.7f),
                        textAlign = TextAlign.End
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_legend_baby_bmi),
                        contentDescription = "",
                        modifier = modifier
                            .width(24.dp.from(ctx))
                            .height(8.dp.from(ctx))
                    )
                    Spacer(modifier = modifier.width(8.dp.from(ctx)))
                    Text(
                        text = stringResource(id = R.string.little_one_growth_data),
                        style = MaterialTheme.typography.subtitle2.copy(
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 22.sp.from(ctx),
                            color = MaterialTheme.colors.onBackground
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp.from(ctx)))
            }

        }

    }
}


