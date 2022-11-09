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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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

data class TemperatureDataUIState(
    var patientName:String,
    var patientUserCode:String,
    var patientThumb:String = "",
    var resultAnalytic: Int,
    var colorAnalytic: Int,
    var value: Float,
    var deviceStatus: Boolean,
    var listEntryTemperature: Pair<List<String>, List<Entry>> = //Label, Entry(x,y)
        Pair(listOf("label"), listOf(Entry(0f,0f))),
)

@Composable
fun ScreenTemperature(
    temperatureDataUIState: TemperatureDataUIState,
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
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CardPatientInFeature(
                thumb = temperatureDataUIState.patientThumb,
                name = temperatureDataUIState.patientName,
                id = temperatureDataUIState.patientUserCode.toLong()
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    showDialogManualInput = true
                } ,
                modifier = Modifier
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = BlueJade),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 11.dp)
            ){
                Text(
                    text = stringResource(id = R.string.corporate_input_manual),
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
                modifier = Modifier
                    .width(89.dp)
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(horizontal = 11.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.corporate_back),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .align(CenterHorizontally)
                .background(Heading, RoundedCornerShape(10.dp))
                .padding(18.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_thermometer),
                contentDescription = "Temperature",
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text =
                if(isSaveManualInput)
                    valueTemperature.toString()
                else
                    temperatureDataUIState.value.toString(),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 28.sp,
                    letterSpacing = 1.sp,
                    color = Color.White,
                ),
                modifier = Modifier
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
                modifier = Modifier.padding(bottom = 20.dp),
            )
            Text(
                text = "C",
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 28.sp,
                    letterSpacing = 1.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 5.dp),
            )
        }

        Text(
            text = stringResource(id = temperatureDataUIState.resultAnalytic),
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight(600),
                fontSize = 22.sp,
                letterSpacing = 1.sp,
                color = colorResource(id = temperatureDataUIState.colorAnalytic)
            ),
            modifier = Modifier.align(CenterHorizontally)
        )

        Text(
            text = "Device Status : ${if (temperatureDataUIState.deviceStatus) "Connected" else "Disconnected"}",
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight(600),
                fontSize = 12.sp,
                letterSpacing = 1.sp,
                color = GreyBlackStetoscope
            ),
            modifier = Modifier.align(CenterHorizontally)
        )


        Column(
            modifier = Modifier
                .height(300.dp)
        ) {
            BaseChartView(
                data = temperatureDataUIState.listEntryTemperature.second,
                name = temperatureDataUIState.listEntryTemperature.first,
                description = "Temperature",
                maxAxis = 45f,
                minAxis = 25f,
                legends = listLegendTemperature,
                limitLine = limitLineTemperature
            )

        }



        Spacer(modifier = Modifier.height(30.dp))

    }
}