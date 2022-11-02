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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.datum.listLegendDiastole
import com.cexup.datum.listLegendPulse
import com.cexup.datum.listLegendSystole
import com.cexup.ui.component.chart.BaseChartView
import com.cexup.ui.corporate.component.CardPatientInFeature
import com.cexup.ui.corporate.component.DialogInputManualBloodPressure
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.GreyBlackStetoscope
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.github.mikephil.charting.data.Entry

data class BloodPressureData(
    var systole : Int = 0,
    var diastole : Int = 0,
    var heartRate : Int = 0,
)

@Composable
fun ScreenBloodPressure(
    modifier: Modifier = Modifier,
    resultAnalytic: Int,
    colorAnalytic: Int,
    deviceStatus: Boolean,
    bloodPressureData: BloodPressureData = BloodPressureData(),
    listEntrySystole: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    listEntryDiastole: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    listEntryPulse: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    isReadDataEnabled: Boolean,
    onReadData: () -> Unit,
    onSave: (Systole: Int, Diastole: Int, PulseRate: Int) -> Unit = { _, _, _ ->},
    onButtonBackPressed: () -> Unit,
) {
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
                    ),
                    modifier = modifier.padding(5.dp)
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
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val widthValue = 290.dp
                val heightValue = 320.dp
                Column(
                    modifier = modifier
                        .width(widthValue)
                        .height(heightValue)
                ) {
                    CardBloodPressureValue(
                        valueName = "Systole",
                        value =
                        if (isSaveManualInput)
                            systoleValue
                        else
                            bloodPressureData.systole.toString(),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 1.dp,
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        BaseChartView(
                            data = listEntrySystole.second,
                            name = listEntrySystole.first,
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
                        valueName = "Diastole",
                        value =
                        if(isSaveManualInput)
                            diastoleValue
                        else
                            bloodPressureData.diastole.toString()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 1.dp,
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        BaseChartView(
                            data = listEntryDiastole.second,
                            name = listEntryDiastole.first,
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
                        valueName = "Pulse",
                        value =
                        if(isSaveManualInput)
                            pulseValue
                        else
                            bloodPressureData.heartRate.toString()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = 1.dp,
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        BaseChartView(
                            data = listEntryPulse.second,
                            name = listEntryPulse.first,
                            description = "Pulse", //deskripsi heartrate,temperature,SpO2,Respiratory
                            maxAxis = 200f,
                            minAxis = 20f,
                            legends = listLegendPulse,

                            )
                    }

                }


            }
            Spacer(modifier = Modifier.height(20.dp))
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
                            text = "Read Data",
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
                        text = "Device Status : ${if (deviceStatus) "Connected" else "Disconnected"}",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 15.sp,
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

@Composable
fun CardBloodPressureValue(
    modifier: Modifier = Modifier,
    widthRow: Dp = 289.dp,
    valueName: String,
    value: String,
) {
    Card(
        elevation = 1.dp,
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = modifier
                .width(widthRow)
                .padding(horizontal = 17.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = valueName,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(600),
                    fontSize = 22.sp,
                    letterSpacing = 1.sp,
                    color = GreyBlackStetoscope
                ),
                modifier = modifier.padding(5.dp)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(700),
                    fontSize = 26.sp,
                    letterSpacing = 1.sp,
                    color = Heading
                ),
                modifier = modifier.padding(5.dp)
            )
        }
    }

}