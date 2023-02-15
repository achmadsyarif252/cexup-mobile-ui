package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.component.CardWeightToAge
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.R
import com.cexup.ui.corporate.component.CardBabyChartShimmer
import com.cexup.ui.corporate.theme.Natural90
import com.github.mikephil.charting.data.Entry

data class ScreenBabyChartGrowthUIState(
    val data: DataBabyChartGrowth = DataBabyChartGrowth(),
    val loading: Boolean = true,
    val error: Boolean = false,
    val message: String = ""
)

data class DataBabyChartGrowth(
    val listWeightToHeight: List<Entry> = listOf(),
    val listWeightToAge: List<Entry> = listOf(),
)

@Composable
fun ScreenBabyChartGrowth(
    dataBabyChart: ScreenBabyChartGrowthUIState,
    onButtonBackPressed:() -> Unit,
) {
    val ctx = LocalConfiguration.current
    Column(
        modifier = Modifier
            .verticalScroll(
                rememberScrollState()
            )
            .padding(32.dp.from(ctx)),
        verticalArrangement = Arrangement.spacedBy(32.dp.from(ctx)),
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.baby_chart_growth),
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp.from(ctx),
                    lineHeight = 32.sp.from(ctx),
                    letterSpacing = -2.sp.from(ctx),
                    color = Natural90.copy(alpha = 0.9f)
                )
            )
            Spacer(modifier = Modifier.weight(1f))
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
                    text = "Back",
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (dataBabyChart.loading) {
                CardBabyChartShimmer()
                CardBabyChartShimmer()
            } else {
                CardWeightToAge(
                    index = 0,
                    title = stringResource(id = R.string.weight_to_height_curve),
                    xAxisUnit = stringResource(id = R.string.height_cm),
                    yAxisUnit = stringResource(id = R.string.weight_kg),
                    listDataChart = dataBabyChart.data.listWeightToHeight
                )
                CardWeightToAge(
                    index = 1,
                    title = stringResource(id = R.string.weight_curve_for_age),
                    xAxisUnit = stringResource(id = R.string.age_month),
                    yAxisUnit = stringResource(id = R.string.weight_kg),
                    listDataChart = dataBabyChart.data.listWeightToAge
                )
            }
        }
    }
}