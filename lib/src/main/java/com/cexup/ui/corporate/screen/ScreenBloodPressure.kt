package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.datum.listLegendDiastole
import com.cexup.datum.listLegendPulse
import com.cexup.datum.listLegendSystole
import com.cexup.ui.R
import com.cexup.ui.component.chart.BaseChartView
import com.cexup.ui.corporate.component.CardPatientInFeature
import com.cexup.ui.corporate.component.DialogInputManualBloodPressure
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.GreyBlackStetoscope
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from
import com.github.mikephil.charting.data.Entry

data class BloodPressureDataUIState(
    var patientName:String,
    var patientUserCode:String,
    var patientThumb:String = "",

    var systole : Int = 0,
    var diastole : Int = 0,
    var heartRate : Int = 0,

    var resultAnalytic: Int,
    var colorAnalytic: Int,
    // label, entry
    var listEntrySystole : Pair<List<String>, List<Entry>>
    = Pair(listOf("label"), listOf(Entry(0f,0f))),
    var listEntryDiastole: Pair<List<String>, List<Entry>>
    = Pair(listOf("label"), listOf(Entry(0f,0f))),
    var listEntryPulse: Pair<List<String>, List<Entry>>
    = Pair(listOf("label"), listOf(Entry(0f,0f))),
)

@Composable
fun ScreenBloodPressure(
    modifier: Modifier = Modifier,
    deviceStatus: Boolean,
    bloodPressureDataUIState: BloodPressureDataUIState,
    isReadDataEnabled: Boolean,
    onReadData: () -> Unit,
    onSave: (Systole: Int, Diastole: Int, PulseRate: Int) -> Unit,
    onButtonBackPressed: () -> Unit,
) {
    val ctx = LocalContext.current
    val scrollState = rememberScrollState()
    var showDialogManualInput by remember { mutableStateOf(false) }
    var systoleValue by remember { mutableStateOf("0") }
    var diastoleValue by remember { mutableStateOf("0") }
    var pulseValue by remember { mutableStateOf("0") }
    var isSaveManualInput by remember { mutableStateOf(false) }

    DialogInputManualBloodPressure(
        show = showDialogManualInput,
        onCancel = {showDialogManualInput = false},
        onSave = {sys,dias,pulse ->
            onSave(sys,dias,pulse)
            systoleValue = sys.toString()
            diastoleValue = dias.toString()
            pulseValue = pulse.toString()
            showDialogManualInput = false
            isSaveManualInput = true
        }
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp.from(ctx))

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp.from(ctx))
        ) {
            CardPatientInFeature(
                thumb = bloodPressureDataUIState.patientThumb,
                name = bloodPressureDataUIState.patientName,
                id = bloodPressureDataUIState.patientUserCode.toLong()
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    showDialogManualInput = true
                } ,
                modifier = modifier
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
                modifier = modifier
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
                    ),
                    modifier = modifier.padding(5.dp.from(ctx))
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
                Text(
                    text = stringResource(id = bloodPressureDataUIState.resultAnalytic),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 22.sp.from(ctx),
                        letterSpacing = 1.sp.from(ctx),
                        color = colorResource(id = bloodPressureDataUIState.colorAnalytic)
                    ),
                    modifier = modifier.padding(5.dp.from(ctx))
                )
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val widthValue = 290.dp.from(ctx)
                val heightValue = 320.dp.from(ctx)
                Column(
                    modifier = modifier
                        .width(widthValue)
                        .height(heightValue)
                ) {
                    CardBloodPressureValue(
                        valueName = stringResource(id = R.string.corporate_measurement_systole),
                        value =
                        if (isSaveManualInput)
                            systoleValue
                        else
                            bloodPressureDataUIState.systole.toString(),
                    )
                    Spacer(modifier = Modifier.height(20.dp.from(ctx)))
                    Card(
                        shape = RoundedCornerShape(10.dp.from(ctx)),
                        elevation = 1.dp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        BaseChartView(
                            data = bloodPressureDataUIState.listEntrySystole.second,
                            name = bloodPressureDataUIState.listEntrySystole.first,
                            description = "Systole", //deskripsi heartrate,temperature,SpO2,Respiratory
                            maxAxis = 200f,
                            minAxis = 20f,
                            legends = listLegendSystole,

                        )
                    }
                }
                Column(
                    modifier = modifier
                        .width(widthValue)
                        .height(heightValue)
                ) {
                    CardBloodPressureValue(
                        valueName = stringResource(id = R.string.corporate_measurement_diastole),
                        value =
                        if(isSaveManualInput)
                            diastoleValue
                        else
                            bloodPressureDataUIState.diastole.toString()
                    )
                    Spacer(modifier = Modifier.height(20.dp.from(ctx)))
                    Card(
                        shape = RoundedCornerShape(10.dp.from(ctx)),
                        elevation = 1.dp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        BaseChartView(
                            data = bloodPressureDataUIState.listEntryDiastole.second,
                            name = bloodPressureDataUIState.listEntryDiastole.first,
                            description = "Diastole", //deskripsi heartrate,temperature,SpO2,Respiratory
                            maxAxis = 200f,
                            minAxis = 20f,
                            legends = listLegendDiastole,
                        )
                    }

                }
                Column(
                    modifier = modifier
                        .width(widthValue)
                        .height(heightValue)
                ) {
                    CardBloodPressureValue(
                        valueName = stringResource(id = R.string.corporate_measurement_pulse_rate),
                        value =
                        if(isSaveManualInput)
                            pulseValue
                        else
                            bloodPressureDataUIState.heartRate.toString()
                    )
                    Spacer(modifier = Modifier.height(20.dp.from(ctx)))
                    Card(
                        shape = RoundedCornerShape(10.dp.from(ctx)),
                        elevation = 1.dp.from(ctx),
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        BaseChartView(
                            data = bloodPressureDataUIState.listEntryPulse.second,
                            name = bloodPressureDataUIState.listEntryPulse.first,
                            description = "Pulse", //deskripsi heartrate,temperature,SpO2,Respiratory
                            maxAxis = 200f,
                            minAxis = 20f,
                            legends = listLegendPulse,

                            )
                    }

                }


            }
            Spacer(modifier = Modifier.height(20.dp.from(ctx)))
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    enabled = isReadDataEnabled,
                    onClick = {
                        onReadData()
                    },
                    modifier = modifier
                        .width(348.dp.from(ctx))
                        .height(35.dp.from(ctx)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
                    shape = RoundedCornerShape(30.dp.from(ctx)),
                    contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.read_data),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp.from(ctx),
                                letterSpacing = 1.sp.from(ctx),
                                color = Color.White
                            ),
                            modifier = modifier.padding(5.dp.from(ctx))
                        )
                    }


                }

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.device_status,
                            if (deviceStatus) stringResource(
                                id = R.string.connected
                            )else stringResource(
                                id = R.string.disconnected
                            )
                        ),
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 15.sp.from(ctx),
                            letterSpacing = 1.sp.from(ctx),
                            color = GreyBlackStetoscope
                        ),
                        modifier = modifier.padding(5.dp.from(ctx))
                    )
                }
            }
        }

    }
}

@Composable
fun CardBloodPressureValue(
    modifier: Modifier = Modifier,
    widthRow: Dp = 289.dp,
    valueName: String,
    value: String,
) {
    val ctx = LocalContext.current
    Card(
        elevation = 1.dp.from(ctx),
        shape = RoundedCornerShape(10.dp.from(ctx)),
    ) {
        Row(
            modifier = modifier
                .width(widthRow.from(ctx))
                .padding(horizontal = 17.dp.from(ctx)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = valueName,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(600),
                    fontSize = 22.sp.from(ctx),
                    letterSpacing = 1.sp.from(ctx),
                    color = GreyBlackStetoscope
                ),
                modifier = modifier.padding(5.dp.from(ctx))
            )
            Text(
                text = value,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 26.sp.from(ctx),
                    letterSpacing = 1.sp.from(ctx),
                    color = Heading
                ),
                modifier = modifier.padding(5.dp.from(ctx))
            )
        }
    }

}