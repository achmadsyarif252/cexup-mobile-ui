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

@Composable
fun ScreenBodyMassIndex(
    modifier: Modifier = Modifier,
    resultAnalytic: Int,
    colorAnalytic: Int,
    valueOfHeight: Float = 0f,
    valueOfBmi: Float = 0f,
    valueOfWeight: Float = 0f,
    valueOfBfr: Float = 0f,
    valueOfSubFat: Float = 0f,
    valueOfVisFat: Float = 0f,
    valueOfWater: Float = 0f,
    valueOfMuscleRate: Float = 0f,
    valueOfMuscleMass: Float = 0f,
    valueOfBoneMass: Float = 0f,
    valueOfBmr: Float = 0f,
    valueOfProtein: Float = 0f,
//    valueOfLbw: Float = 0f,
    valueOfBodyAge: Float = 0f,
//    valueOfObesityDegree: Float = 0f,
    valueOfBodyType: Int = 0,
    deviceStatus: String,
    listEntry: Pair<List<String>, List<Entry>>,
    onBackPress: () -> Unit,
    onSave: (weight :Float, height: Float) -> Unit = { _, _ -> },
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
                            valueOfHeight,
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
                            valueOfBmi,
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
                            valueOfWeight,
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
                    data = listEntry.second,
                    name = listEntry.first,
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
                        value = (valueWeight * (1 - (valueOfBfr/100))).toString(),
                        colorOfValue = LightGreen,
                        satuan = "kg",
                        analyticName = "Fat-free Body Weight"
                    )
                    when(valueOfBodyType) {
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
                        value = "$valueOfBfr",
                        colorOfValue = LightGreen,
                        satuan = "%",
                        analyticName = "Body Fat"
                    )
                    CardBmiBodyWeight(
                        type = 5,
                        analyticName = "Subcutaneous Fat",
                        analytic = "High",
                        colorOfValue = LightOrange,
                        value = "$valueOfSubFat",
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
                        value = "$valueOfVisFat",
                        colorOfValue = LightGreen,
                        analyticName = "Visceral Fat"
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "$valueOfMuscleRate",
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
                        value = "$valueOfMuscleMass",
                        colorOfValue = LightGreen,
                        satuan = "kg",
                        analyticName = "Muscle Mass"
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "$valueOfWater",
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
                        value = "$valueOfBoneMass",
                        colorOfValue = LightGreen,
                        satuan = "kg",
                        analyticName = "Bone Mass"
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "$valueOfProtein",
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
                        value = "$valueOfBmr",
                        colorOfValue = LightOrange,
                        satuan = "kcal",
                        analyticName = "BMR",
//                        analytic = "Below Average"
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "$valueOfBodyAge",
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
                    data = listEntry.second,
                    name = listEntry.first,
                    description = "Fat Analytic",
                    maxAxis = 150f,
                    minAxis = 50f,
                )
            }


        }

    }
}