package com.cexup.ui.corporate.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cexup.ui.theme.GreyBorder
import com.cexup.ui.R
import com.cexup.ui.component.chart.ChartEws

@Composable
fun CardEarlyWarningScore(
    modifier: Modifier = Modifier,
    ews: Int,
) {

    val ewsPercentage = when (ews) {
        0 -> 0.1f
        1 -> 0.1f
        2 -> 0.2f
        3 -> 0.3f
        4 -> 0.4f
        5 -> 0.5f
        6 -> 0.6f
        7 -> 0.7f
        8 -> 0.8f
        9 -> 0.9f
        10 -> 0.9f
        11 -> 0.9f
        12 -> 0.9f
        13 -> 0.9f
        else -> 0.0f

    }

    val ewsResult = when (ews) {
        0 -> "Normal"
        1 -> "Low Risk"
        2 -> "Low Risk"
        3 -> "Low Risk"
        4 -> "Low Risk"
        5 -> "Moderate Risk"
        6 -> "Moderate Risk"
        7 -> "High Risk"
        8 -> "High Risk"
        9 -> "High Risk"
        10 -> "High Risk"
        11 -> "High Risk"
        12 -> "High Risk"
        13 -> "High Risk"
        else -> "Normal"
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, color = GreyBorder),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(id = R.string.health_condition))
        ChartEws(percent = ewsPercentage, result = ewsResult, number = 100)
    }
}