package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.datum.limitLineBloodOxygen
import com.cexup.datum.limitLineHeartRate
import com.cexup.datum.listLegendBloodOxygen
import com.cexup.datum.listLegendHeartRate
import com.cexup.ui.component.chart.BaseChartView
import com.cexup.ui.component.chart.CircularBarFeatures
import com.cexup.ui.corporate.component.CardPatientInFeature
import com.cexup.ui.corporate.theme.GreyBlackStetoscope
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.github.mikephil.charting.data.Entry

@Composable
fun ScreenBloodOxygen(
    modifier: Modifier = Modifier,
//    resultAnalytic: String = "No Result",
//    colorAnalytic: Color = Heading,
    listEntrySpo2: List<Entry>,
    listEntryHeartRate: List<Entry>,
    listLabel: List<String>,
    deviceStatus: String = "Disconnect",
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(30.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardPatientInFeature(thumb = "", name = "John Stones", id = 2202020)
            Button(
                onClick = {

                },
                modifier = modifier
                    .width(89.dp)
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 11.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Back",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 14.sp,
                            letterSpacing = 1.sp,
                            color = Color.White
                        ),
                        modifier = modifier.padding(5.dp)
                    )
                }
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState))
        {
            Spacer(modifier = Modifier.height(23.24.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = modifier
                        .width(365.dp)
                ) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 2.dp,
                    ) {
                        Row(
                            modifier = modifier
                                .height(89.85.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = modifier.fillMaxHeight()
                            ) {
                                Text(
                                    text = "SPO2",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(600),
                                        fontSize = 16.sp,
                                        letterSpacing = 1.sp,
                                        color = Color.Black
                                    )
                                )
                            }
                            CircularBarFeatures(
                                valueOfMeasurement = 0f,
                                maxValue = 100,
                                radius = 40.dp,
                                brush = Brush.sweepGradient(
                                    colors=listOf(
                                        Heading,
                                        Heading
                                    ),
                                )
                            )

                        }

                    }
                    Spacer(modifier = Modifier.height(28.92.dp))
                    Card(
                        elevation = 2.dp,
                        modifier = modifier.height(235.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        BaseChartView(
                            data = listEntrySpo2,
                            name = listLabel,
                            description = "Spo2", //deskripsi heartrate,temperature,SpO2,Respiratory
                            maxAxis = 200f,
                            minAxis = 20f,
                            legends = listLegendBloodOxygen,
                            limitLine = limitLineBloodOxygen
                        )
                    }

                }

                Column(
                    modifier = modifier
                        .width(365.dp)
                ) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 2.dp,
                    ) {
                        Row(
                            modifier = modifier
                                .height(89.85.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = modifier.fillMaxHeight()
                            ) {
                                Text(
                                    text = "Heart Rate",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight(600),
                                        fontSize = 16.sp,
                                        letterSpacing = 1.sp,
                                        color = Color.Black
                                    )
                                )
                            }
                            CircularBarFeatures(
                                valueOfMeasurement = 0f,
                                maxValue = 120,
                                radius = 40.dp,
                                brush = Brush.sweepGradient(
                                    colors=listOf(
                                        SecondaryCorporate,
                                        SecondaryCorporate
                                    ),
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(28.92.dp))
                    Card(
                        elevation = 2.dp,
                        modifier = modifier.height(235.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        BaseChartView(
                            data = listEntryHeartRate,
                            name = listLabel,
                            description = "Heart Rate", //deskripsi heartrate,temperature,SpO2,Respiratory
                            maxAxis = 200f,
                            minAxis = 20f,
                            legends = listLegendHeartRate,
                            limitLine = limitLineHeartRate
                        )
                    }

                }

            }

            // CTG Testing
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 2.dp,
            ) {
                Row(
                    modifier = modifier
                        .height(89.85.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier.fillMaxHeight()
                    ) {
                        Text(
                            text = "SPO2",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.Black
                            )
                        )
                    }
                    CircularBarFeatures(
                        valueOfMeasurement = 0f,
                        maxValue = 100,
                        radius = 40.dp,
                        brush = Brush.sweepGradient(
                            colors=listOf(
                                Heading,
                                Heading
                            ),
                        )
                    )

                }

            }


            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {

                    },
                    modifier = modifier
                        .width(348.dp)
                        .height(35.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
                    shape = RoundedCornerShape(30.dp),
                    contentPadding = PaddingValues(horizontal = 11.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Select Device",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ),
                            modifier = modifier.padding(5.dp)
                        )
                    }


                }

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Device Status : $deviceStatus",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 12.sp,
                            letterSpacing = 1.sp,
                            color = GreyBlackStetoscope
                        ),
                        modifier = modifier.padding(5.dp)
                    )
                }
            }
        }

    }
}