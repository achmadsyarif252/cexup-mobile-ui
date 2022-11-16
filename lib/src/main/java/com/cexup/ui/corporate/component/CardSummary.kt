package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.datum.*
import com.cexup.ui.component.chart.BaseChartView
import com.cexup.ui.corporate.theme.Heading
import com.github.mikephil.charting.data.Entry
import com.cexup.ui.R

@Composable
fun CardSummary(
    modifier: Modifier = Modifier,
    dataBmi: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    dataWaist: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    dataTemp: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    dataPulseOximeter: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    dataHeartRate: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
) {
    Column {
        Column(
            modifier = modifier.height(300.dp)
        ) {
            Text(
                text = stringResource(id = R.string.waist),
                fontSize = 22.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading,
                modifier = modifier.padding(bottom = 3.dp)
            )
            BaseChartView(
                data = dataWaist.second,
                name = dataWaist.first,
                description = stringResource(id = R.string.waist),
                maxAxis = 60f,
                minAxis = 0f,
            )
        }
        Column(
            modifier = modifier.height(300.dp)
        ) {
            Text(
                text = stringResource(id = R.string.corporate_measurement_temperature),
                fontSize = 22.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading,
                modifier = modifier.padding(bottom = 3.dp)
            )
            BaseChartView(
                data = dataTemp.second,
                name = dataTemp.first,
                description = stringResource(id = R.string.corporate_measurement_temperature),
                maxAxis = 150f,
                minAxis = 50f,
                limitLine = limitLineTemperature,
                legends = listLegendTemperature
            )
        }
        Column(
            modifier = modifier.height(300.dp)
        ) {
            Text(
                text = stringResource(id = R.string.bmi),
                fontSize = 22.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading,
                modifier = modifier.padding(bottom = 3.dp)
            )
            BaseChartView(
                data = dataBmi.second,
                name = dataBmi.first,
                description = stringResource(id = R.string.bmi),
                maxAxis = 50f,
                minAxis = 10f,
                limitLine = limitLineBodyMassIndex,
                legends = listLegendBmi
            )
        }
        Column(
            modifier = modifier.height(300.dp)
        ) {
            Text(
                text = stringResource(id = R.string.pulse_oximetry),
                fontSize = 22.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading,
                modifier = modifier.padding(bottom = 3.dp)
            )
            BaseChartView(
                data = dataPulseOximeter.second,
                name = dataPulseOximeter.first,
                description = stringResource(id = R.string.pulse_oximetry),
                maxAxis = 150f,
                minAxis = 50f,
                limitLine = limitLineBloodOxygen,
                legends = listLegendBloodOxygen
            )
        }
        Column(
            modifier = modifier.height(300.dp)
        ) {
            Text(
                text = stringResource(id = R.string.corporate_measurement_heart_rate),
                fontSize = 22.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading,
                modifier = modifier.padding(bottom = 3.dp)
            )
            BaseChartView(
                data = dataHeartRate.second,
                name = dataHeartRate.first,
                description = stringResource(id = R.string.corporate_measurement_heart_rate),
                maxAxis = 150f,
                minAxis = 50f,
                limitLine = limitLineHeartRate,
                legends = listLegendHeartRate
            )
        }
    }
}
