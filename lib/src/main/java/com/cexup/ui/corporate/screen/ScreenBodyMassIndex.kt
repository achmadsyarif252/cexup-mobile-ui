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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.datum.limitLineBodyMassIndex
import com.cexup.datum.listLegendBmi
import com.cexup.ui.R
import com.cexup.ui.component.chart.BaseChartView
import com.cexup.ui.corporate.component.*
import com.cexup.ui.theme.*
import com.cexup.ui.utils.mediaquery.from
import com.github.mikephil.charting.data.Entry

data class BodyMassIndexDataUIState(
    var patientName:String,
    var patientUserCode:String,
    var patientThumb:String = "",

    var resultAnalytic: Int,
    var colorAnalytic: Int,

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
    bodyMassIndexDataUIState: BodyMassIndexDataUIState,
    deviceStatus: String,
    onBackPress: () -> Unit,
    onSave: (weight :Float, height: Float) -> Unit,
    onCalculateBmi: (weight: Float, height: Float) -> Float,
) {
    val ctx = LocalConfiguration.current
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
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(30.dp.from(ctx))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp.from(ctx))
        ) {
            CardPatientInFeature(
                thumb = bodyMassIndexDataUIState.patientThumb,
                name = bodyMassIndexDataUIState.patientName,
                id = bodyMassIndexDataUIState.patientUserCode
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
                    onBackPress()
                },
                modifier = modifier
                    .width(89.dp.from(ctx))
                    .height(35.dp.from(ctx)),
                colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                shape = RoundedCornerShape(10.dp.from(ctx)),
                contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
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

        }
        Column(
            modifier = Modifier.fillMaxSize()
        )
        {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = bodyMassIndexDataUIState.resultAnalytic),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 22.sp.from(ctx),
                        letterSpacing = 1.sp.from(ctx),
                        color = colorResource(id = bodyMassIndexDataUIState.colorAnalytic)
                    ),
                    modifier = modifier.padding(5.dp.from(ctx))
                )
            }
            Spacer(modifier = Modifier.height(34.dp.from(ctx)))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier.width(269.dp.from(ctx))
                ) {
                    CardBodyMassIndex(
                        valueOfParameter =
                        if(isSaveManualInput)
                            valueHeight
                        else
                            bodyMassIndexDataUIState.height,
                        parameterName = stringResource(id = R.string.corporate_measurement_height),
                        parameterUnit = stringResource(id = R.string.cm),
                        color = Heading,
                        typeBodyMassIndex = TypeBodyMassIndex.HEIGHT
                    )
                }
                Column(
                    modifier.width(269.dp.from(ctx))
                ) {
                    CardBodyMassIndex(
                        valueOfParameter =
                        if(isSaveManualInput) {
                            onCalculateBmi(valueWeight, valueHeight)
                        }
                        else
                            bodyMassIndexDataUIState.bodyMassIndex,
                        parameterName = stringResource(id = R.string.result_bmi),
                        parameterUnit = stringResource(id = R.string.kg_per_m2),
                        color = Heading,
                        typeBodyMassIndex = TypeBodyMassIndex.BMI
                    )
                    Spacer(modifier = Modifier.height(51.dp.from(ctx)))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.device_status,deviceStatus),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 12.sp.from(ctx),
                                letterSpacing = 1.sp.from(ctx),
                                color = GreyBlackStetoscope
                            ),
                            modifier = modifier.padding(5.dp.from(ctx))
                        )
                    }
                }
                Column(
                    modifier.width(269.dp.from(ctx))
                ) {
                    CardBodyMassIndex(
                        valueOfParameter =
                        if(isSaveManualInput)
                            valueWeight
                        else
                            bodyMassIndexDataUIState.weight,
                        parameterName = stringResource(id = R.string.corporate_measurement_weight),
                        parameterUnit = stringResource(id = R.string.kg),
                        color = SecondaryCorporate,
                        typeBodyMassIndex = TypeBodyMassIndex.WEIGHT
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp.from(ctx)))
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp.from(ctx)),
                shape = RoundedCornerShape(10.dp.from(ctx)),
                elevation = 1.dp.from(ctx)
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
            Spacer(modifier = Modifier.height(20.dp.from(ctx)))
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp.from(ctx))
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
                        satuan = stringResource(id = R.string.kg),
                        analyticName = stringResource(id = R.string.fat_free_body_weight)
                    )
                    when(bodyMassIndexDataUIState.bodyType) {
                        0 -> bodyTypeStr = stringResource(id = R.string.no_body_type)
                        1 -> bodyTypeStr = stringResource(id = R.string.hidden_obesity)
                        2 -> bodyTypeStr = stringResource(id = R.string.lack_of_exercise)
                        3 -> bodyTypeStr = stringResource(id = R.string.lean_type)
                        4 -> bodyTypeStr = stringResource(id = R.string.standard_type)
                        5 -> bodyTypeStr = stringResource(id = R.string.lean_muscle_type)
                        6 -> bodyTypeStr = stringResource(id = R.string.obese)
                        7 -> bodyTypeStr = stringResource(id = R.string.fatty)
                        8 -> bodyTypeStr = stringResource(id = R.string.standard_muscle)
                        9 -> bodyTypeStr = stringResource(id = R.string.very_muscular)
                        else -> {}
                    }
                    CardBmiBodyWeight(
                        type = 3,
                        analyticName = stringResource(id = R.string.body_type),
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
                        analyticName = stringResource(id = R.string.body_fat)
                    )
                    CardBmiBodyWeight(
                        type = 5,
                        analyticName = stringResource(id = R.string.subcutaneous_fat),
                        analytic = stringResource(id = R.string.high),
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
                        analyticName = stringResource(id = R.string.visceral_fat)
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.muscleRate}",
                        colorOfValue = LightGreen,
                        satuan = "%",
                        analyticName = stringResource(id = R.string.skeletal_muscle)
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
                        satuan = stringResource(id = R.string.kg),
                        analyticName = stringResource(id = R.string.muscle_mass)
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.water}",
                        colorOfValue = LightGreen,
                        satuan = "%",
                        analyticName = stringResource(id = R.string.body_water)
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
                        satuan = stringResource(id = R.string.kg),
                        analyticName = stringResource(id = R.string.bone_mass)
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.protein}",
                        colorOfValue = LightGreen,
                        satuan = "%",
                        analyticName = stringResource(id = R.string.protein)
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
                        satuan = stringResource(id = R.string.kcal),
                        analyticName = stringResource(id = R.string.bmr),
//                        analytic = "Below Average"
                    )
                    CardBmiBodyWeight(
                        type = 2,
                        value = "${bodyMassIndexDataUIState.bodyAge}",
                        colorOfValue = LightGreen,
                        satuan = "",
                        analyticName = stringResource(id = R.string.metabolic_age)
                    )
                }

            }
            Spacer(modifier = Modifier.height(20.dp.from(ctx)))
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp.from(ctx)),
                shape = RoundedCornerShape(10.dp.from(ctx)),
                elevation = 1.dp.from(ctx)
            ) {
                BaseChartView(
                    data = bodyMassIndexDataUIState.listEntryBodyMassIndex.second,
                    name = bodyMassIndexDataUIState.listEntryBodyMassIndex.first,
                    description = stringResource(id = R.string.fat_analytic),
                    maxAxis = 150f,
                    minAxis = 50f,
                )
            }


        }

    }
}