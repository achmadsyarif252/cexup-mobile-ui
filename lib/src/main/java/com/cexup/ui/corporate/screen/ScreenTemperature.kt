package com.cexup.ui.corporate.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.datum.limitLineTemperature
import com.cexup.datum.listLegendTemperature
import com.cexup.ui.corporate.component.CardPatientInFeature
import com.cexup.ui.corporate.component.DialogInputManualTemperature
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.github.mikephil.charting.data.Entry
import com.cexup.ui.R
import com.cexup.ui.component.chart.BaseChartView
import com.cexup.ui.corporate.theme.GreyBlackStetoscope

@Composable
fun ScreenTemperature(
    modifier: Modifier = Modifier,
    resultAnalytic: Int,
    colorAnalytic: Int,
    value: Float,
    deviceStatus: Boolean,
    listEntryTemperature: Pair<List<String>, List<Entry>>,
    onSave: (temp: Float) -> Unit,
    onButtonBackPressed: () -> Unit,
) {
    val scrollState = rememberScrollState()
    var showDialogManualInput by remember { mutableStateOf(false) }
    var valueTemperature by remember { mutableStateOf(0f) }
    var isSaveManualInput by remember { mutableStateOf(false) }
    DialogInputManualTemperature(
        onCancel = { showDialogManualInput = false},
        show = showDialogManualInput,
        onSave = {
            valueTemperature = it
            onSave(it)
            showDialogManualInput = false
            isSaveManualInput = true
        }
    )
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CardPatientInFeature(thumb = "", name = "John Stones", id = 2202020)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    showDialogManualInput = true
                } ,
                modifier = modifier
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = BlueJade),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 11.dp)
            ){
                Text(
                    text = "Input Manual",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    ),
                )
            }
            Button(
                onClick = {
                    onButtonBackPressed()
                },
                modifier = modifier
                    .width(89.dp)
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 11.dp)
            ) {
                Text(
                    text = "Back",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    )
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState)
        )
        {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = modifier
                        .background(Heading, RoundedCornerShape(10.dp))
                        .padding(horizontal = 18.dp, vertical = 18.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_thermometer),
                        contentDescription = "Temperature",
                        modifier = modifier
                            .size(28.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text =
                        if(isSaveManualInput)
                            valueTemperature.toString()
                        else
                            value.toString(),
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(700),
                            fontSize = 28.sp,
                            letterSpacing = 1.sp,
                            color = Color.White,
                        ),
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                            .padding(top = 5.dp),
                    )
                    Text(
                        text = "o",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(500),
                            fontSize = 12.sp,
                            letterSpacing = 1.sp,
                            color = Color.White,
                        ),
                        modifier = modifier.padding(bottom = 20.dp),
                    )
                    Text(
                        text = "C",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(700),
                            fontSize = 28.sp,
                            letterSpacing = 1.sp,
                            color = Color.White,
                        ),
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                            .padding(top = 5.dp),
                    )

                }
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = resultAnalytic),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 22.sp,
                        letterSpacing = 1.sp,
                        color = colorResource(id = colorAnalytic)
                    ),
                    modifier = modifier.padding(5.dp)
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Device Status : ${if (deviceStatus) "Connected" else "Disconnected"}",
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
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                BaseChartView(
                    data = listEntryTemperature.second,
                    name = listEntryTemperature.first,
                    description = "Temperature",
                    maxAxis = 200f,
                    minAxis = 20f,
                    legends = listLegendTemperature,
                    limitLine = limitLineTemperature
                )

            }

        }

    }
}