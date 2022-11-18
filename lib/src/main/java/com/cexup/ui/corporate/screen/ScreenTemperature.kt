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
import androidx.compose.ui.platform.LocalContext
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
import com.cexup.ui.utils.mediaquery.from

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
    val ctx = LocalContext.current
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
            .padding(horizontal = 30.dp.from(ctx))
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(30.dp.from(ctx)))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp.from(ctx))
        ) {
            CardPatientInFeature(
                thumb = temperatureDataUIState.patientThumb,
                name = temperatureDataUIState.patientName,
                id = temperatureDataUIState.patientUserCode,
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    showDialogManualInput = true
                } ,
                modifier = Modifier
                    .height(35.dp.from(ctx)),
                colors = ButtonDefaults.buttonColors(backgroundColor = BlueJade),
                shape = RoundedCornerShape(10.dp.from(ctx)),
                contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
            ){
                Text(
                    text = stringResource(id = R.string.corporate_input_manual),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp.from(ctx),
                        letterSpacing = 1.sp.from(ctx),
                        color = Color.White
                    ),
                )
            }
            Button(
                onClick = {
                    onButtonBackPressed()
                },
                modifier = Modifier
                    .width(89.dp.from(ctx))
                    .height(35.dp.from(ctx)),
                colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                shape = RoundedCornerShape(10.dp.from(ctx)),
                contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.corporate_back),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp.from(ctx),
                        letterSpacing = 1.sp.from(ctx),
                        color = Color.White
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .align(CenterHorizontally)
                .background(Heading, RoundedCornerShape(10.dp.from(ctx)))
                .padding(18.dp.from(ctx)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_thermometer),
                contentDescription = "Temperature",
                modifier = Modifier
                    .size(28.dp.from(ctx))
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
                    fontSize = 28.sp.from(ctx),
                    letterSpacing = 1.sp.from(ctx),
                    color = Color.White,
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 5.dp.from(ctx)),
            )
            Text(
                text = "o",
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(500),
                    fontSize = 12.sp.from(ctx),
                    letterSpacing = 1.sp.from(ctx),
                    color = Color.White,
                ),
                modifier = Modifier.padding(bottom = 20.dp.from(ctx)),
            )
            Text(
                text = "C",
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 28.sp.from(ctx),
                    letterSpacing = 1.sp.from(ctx),
                    color = Color.White,
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 5.dp.from(ctx)),
            )
        }

        Text(
            text = stringResource(id = temperatureDataUIState.resultAnalytic),
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight(600),
                fontSize = 22.sp.from(ctx),
                letterSpacing = 1.sp.from(ctx),
                color = colorResource(id = temperatureDataUIState.colorAnalytic)
            ),
            modifier = Modifier.align(CenterHorizontally)
        )

        Text(
            text = "Device Status : ${if (temperatureDataUIState.deviceStatus) "Connected" else "Disconnected"}",
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight(600),
                fontSize = 12.sp.from(ctx),
                letterSpacing = 1.sp.from(ctx),
                color = GreyBlackStetoscope
            ),
            modifier = Modifier.align(CenterHorizontally)
        )


        Column(
            modifier = Modifier
                .height(300.dp.from(ctx))
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



        Spacer(modifier = Modifier.height(30.dp.from(ctx)))

    }
}