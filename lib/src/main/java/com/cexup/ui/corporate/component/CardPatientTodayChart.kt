package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.theme.AlternativeHeading
import com.cexup.ui.theme.Heading
import com.cexup.ui.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from
import com.github.mikephil.charting.data.BarEntry
import com.cexup.ui.R

@Composable
fun CardPatientTodayChart(
    modifier: Modifier = Modifier,
    name:String,
    xValueFormatter: List<String> = listOf(),
    index:Int=0,
    data:List<BarEntry>,
    data2:List<BarEntry>,
    maxAxis:Float,
    minAxis:Float,
){
    val ctx = LocalConfiguration.current
    Row(modifier = modifier
        .fillMaxWidth()
        .background(Color.Transparent)
        .height(265.74.dp.from(ctx))) {

        Column(modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 22.sp.from(ctx),
                    fontWeight = FontWeight(700),
                    color = Heading
                ),
                modifier = modifier.padding(top = 6.dp.from(ctx),bottom = 6.dp.from(ctx))
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = modifier
                            .background(
                                color = AlternativeHeading,
                                shape = RoundedCornerShape(10.dp.from(ctx))
                            )
                            .width(9.77.dp.from(ctx))
                            .height(6.6.dp.from(ctx))

                    ) {

                    }
                    Spacer(modifier = Modifier.width(6.dp.from(ctx)))
                    Text(
                        text = stringResource(id = R.string.discharged),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp.from(ctx),
                            fontWeight = FontWeight(400),
                            color = Color.Black
                        )
                    )
                }
                Spacer(modifier = Modifier.width(18.32.dp.from(ctx)))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = modifier
                            .width(9.77.dp.from(ctx))
                            .height(6.6.dp.from(ctx))
                            .background(
                                color = SecondaryCorporate,
                                shape = RoundedCornerShape(10.dp.from(ctx))
                            )
                    )
                    Spacer(modifier = Modifier.width(6.dp.from(ctx)))
                    Text(
                        text = stringResource(id = R.string.admitted),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp.from(ctx),
                            fontWeight = FontWeight(400),
                            color = Color.Black
                        )
                    )
                }

            }
            Spacer(modifier = Modifier.height(20.dp.from(ctx)))
            BarChartPatient(
                data = data,
                data2 = data2,
                description = "",
                xValueFormatter = xValueFormatter
            )
        }
    }


}