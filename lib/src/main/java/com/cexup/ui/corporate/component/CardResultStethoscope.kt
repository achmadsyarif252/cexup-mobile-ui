package com.cexup.ui.corporate.component

import android.view.ContextThemeWrapper
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.cexup.ui.corporate.theme.GreyBlackStetoscope
import com.github.mikephil.charting.charts.LineChart
import compose.icons.Octicons
import compose.icons.octicons.Play24
import com.cexup.ui.R

@Composable
fun CardResultStethoscope(
    modifier: Modifier = Modifier,
    timeCaptureStethoscope : String,
    countDataHeart : Int,
    onClickPlay: () -> Unit,
){
    Card(
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp,
            bottomEnd = 30.dp
        ),
        modifier = modifier
            .width(343.dp)
            .height(144.dp),
        border = BorderStroke(width = 2.dp, MaterialTheme.colors.primaryVariant),
        backgroundColor = Color.Transparent

    ) {
        Column(
            modifier = modifier
                .padding(15.dp)
                .background(Color.Transparent)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Heart $countDataHeart:",
                        style = MaterialTheme.typography.body1.copy(
                            color = GreyBlackStetoscope,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600)
                        ),
                    )
                    Text(
                        text = timeCaptureStethoscope,
                        style = MaterialTheme.typography.body1.copy(
                            color = GreyBlackStetoscope,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400)
                        ),
                        modifier = modifier.align(Alignment.CenterVertically)

                    )

                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = modifier
                        .fillMaxHeight()
                ) {
                    Column(
                        modifier = modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Icon(
                            Octicons.Play24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.primaryVariant,
                            modifier = modifier
                                .clickable {
                                    onClickPlay()
                                })

                    }
                }

                AndroidView(
                    modifier= Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Transparent),
                    factory = {
                        LineChart(ContextThemeWrapper(it, R.style.Chart)).apply {

                        }
                    }

                )

            }

        }

    }

}