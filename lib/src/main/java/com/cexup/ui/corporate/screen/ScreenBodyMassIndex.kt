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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.datum.limitLineBodyMassIndex
import com.cexup.datum.listLegendBmi
import com.cexup.ui.component.chart.BaseChartView
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.theme.*
import com.github.mikephil.charting.data.Entry

data class BodyMassIndexDataUIState(
    var height: Float = 0f,
    var bodyMassIndex: Float = 0f,
    var weight: Float = 0f,
    var bodyFatRate: Float = 0f,
    var subFat: Float = 0f,
    var visFat: Float = 0f,
    var water: Float = 0f,
    var muscleRate: Float = 0f,
    var muscleMass: Float = 0f,
    var boneMass: Float = 0f,
    var bodyMassRate: Float = 0f,
    var protein: Float = 0f,
    var leanBodyWeight: Float = 0f,
    var bodyAge: Float = 0f,
    var obesityDegree: Float = 0f,
    var bodyType: Int = 0,
    var listEntryBodyMassIndex: Pair<List<String>, List<Entry>>,
)

@Composable
fun ScreenBodyMassIndex(
    modifier: Modifier = Modifier,
    resultAnalytic: Int,
    colorAnalytic: Int,
    bodyMassIndexDataUIState: BodyMassIndexDataUIState,
    deviceStatus: String,
    onBackPress: () -> Unit,
    onSave: (weight :Float, height: Float) -> Unit,
    onCalculateBmi: (weight: Float, height: Float) -> Float,
) {
    val scrollState = rememberScrollState()
    var bodyTypeStr by remember {
        mutableStateOf("")
    }

    var valueHeight by remember { mutableStateOf(0f) }
    var valueWeight by remember { mutableStateOf(0f) }
    var showDialogManualInput by remember { mutableStateOf(false) }
    var isSaveManualInput by remember { mutableStateOf(false) }

    DialogInputManualBMI(
        show = showDialogManualInput,
        onCancel = { showDialogManualInput = false },
        onSave = { weight,height ->
            onSave(weight,height)
            valueHeight = height
            valueWeight = weight
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
                    onBackPress()
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
            Spacer(modifier = Modifier.height(34.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier.width(269.dp)
                ) {
                    CardBodyMassIndex(
                        valueOfParameter =
                        if(isSaveManualInput)
                            valueHeight
                        else
                            bodyMassIndexDataUIState.height,
                        parameterName = "Height",
                        parameterUnit = "cm",
                        color = Heading,
                        typeBodyMassIndex = TypeBodyMassIndex.HEIGHT
                    )
                }
                Column(
                    modifier.width(269.dp)
                ) {
                    CardBodyMassIndex(
                        valueOfParameter =
                        if(isSaveManualInput) {
                            onCalculateBmi(valueWeight, valueHeight)
                        }
                        else
                            bodyMassIndexDataUIState.bodyMassIndex,
                        parameterName = "Result BMI",
                        parameterUnit = "kg/m2",
                        color = Heading,
                        typeBodyMassIndex = TypeBodyMassIndex.BMI
                    )
                    Spacer(modifier = Modifier.height(51.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
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
                Column(
                    modifier.width(269.dp)
                ) {
                    CardBodyMassIndex(
                        valueOfParameter =
                        if(isSaveManualInput)
                            valueWeight
                        else
                            bodyMassIndexDataUIState.weight,
                        parameterName = "Weight",
                        parameterUnit = "kg",
                        color = SecondaryCorporate,
                        typeBodyMassIndex = TypeBodyMassIndex.WEIGHT
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = 1.dp
            ) {
                BaseChartView(
                    data = bodyMassIndexDataUIState.listEntryBodyMassIndex.second,
                    name = bodyMassIndexDataUIState.listEntryBodyMassIndex.first,
                    description = "Body Mass Index",
                    maxAxis = 50f,
                    minAxis = 0f,
                    legends = listLegendBmi,
                    limitLine = limitLineBodyMassIndex
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CardBmiBodyWeight(
                        type = 1,
                        value = (valueWeight * (1 - (bodyMassIndexDataUIState.bodyFatRate/100))).toString(),
                        colorOfValue = LightGreen,
                        satuan = "kg",
                        analyticName = "Fat-free Body Weight"
                    )
                    when(bodyMassIndexDataUIState.bodyType) {
                        0 -> bodyTypeStr = "No Body Type"
                        1 -> bodyTypeStr = "Hidden obesity"
                        2 -> bodyTypeStr = "Lack of exercise"
                        3 -> bodyTypeStr = "Lean type"
                        4 -> bodyTypeStr = "Standard type"
                        5 -> bodyTypeStr = "Lean muscle type"
                        6 -> bodyTypeStr = "Obese"
                        7 -> bodyTypeStr = "Fatty"
                        8 -> bodyTypeStr = "Standard muscle"
                        9 -> bodyTypeStr = "Very muscular"
                        else -> {}
                    }
                    CardBmiBodyWeight(
                        type = 3,
                        analyticName = "Body Type",
                        analytic = bodyTypeStr
                    )
                }
                //2
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.bodyFatRate}",
                        colorOfValue = LightGreen,
                        satuan = "%",
                        analyticName = "Body Fat"
                    )
                    CardBmiBodyWeight(
                        type = 5,
                        analyticName = "Subcutaneous Fat",
                        analytic = "High",
                        colorOfValue = LightOrange,
                        value = "${bodyMassIndexDataUIState.subFat}",
                        satuan = "%"
                    )
                }
                //3
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.visFat}",
                        colorOfValue = LightGreen,
                        analyticName = "Visceral Fat"
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.muscleRate}",
                        colorOfValue = LightGreen,
                        satuan = "%",
                        analyticName = "Skeletal Muscle"
                    )
                }
                //4
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.muscleMass}",
                        colorOfValue = LightGreen,
                        satuan = "kg",
                        analyticName = "Muscle Mass"
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.water}",
                        colorOfValue = LightGreen,
                        satuan = "%",
                        analyticName = "Body Water"
                    )
                }
                //5
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.boneMass}",
                        colorOfValue = LightGreen,
                        satuan = "kg",
                        analyticName = "Bone Mass"
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.protein}",
                        colorOfValue = LightGreen,
                        satuan = "%",
                        analyticName = "Protein"
                    )
                }
                //6
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CardBmiBodyWeight(
                        type = 4,
                        value = "${bodyMassIndexDataUIState.bodyMassRate}",
                        colorOfValue = LightOrange,
                        satuan = "kcal",
                        analyticName = "BMR",
//                        analytic = "Below Average"
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.bodyAge}",
                        colorOfValue = LightGreen,
                        satuan = "",
                        analyticName = "Metabolic Age"
                    )
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = 1.dp
            ) {
                BaseChartView(
                    data = bodyMassIndexDataUIState.listEntryBodyMassIndex.second,
                    name = bodyMassIndexDataUIState.listEntryBodyMassIndex.first,
                    description = "Fat Analytic",
                    maxAxis = 150f,
                    minAxis = 50f,
                )
            }


        }

    }
}