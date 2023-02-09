package com.cexup.ui.corporate.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.CardPatientInFeature
import com.cexup.ui.corporate.component.ChartPatientProfile
import com.cexup.ui.corporate.theme.BlueDashboardNew
import com.cexup.ui.corporate.theme.GreenPatientProfileNew
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from
import com.github.mikephil.charting.data.Entry

data class ScreenPatientChartExtendUIState(
    val error: Boolean = false,
    val loading: Boolean = true,
    val message: String = "",
    val data: DataChartPatient = DataChartPatient()
)
data class DataChartPatient(
    val patientThumb: String = "",
    val patientName: String = "",
    val patientUserCode: String = "",
    val lastValue: String = "",
    val satuanValue: String = "",
    val listChart1: List<Entry> = listOf(),
    val listChart2: List<Entry> = listOf(),
    val listDateChart: List<String> = listOf(),
    val chartName: String = "",
    val maxYValue: Float = 100f,
    val minYValue: Float = 10f,
)

@Composable
fun ScreenPatientChartExtend(
    screenPatientChartExtendUIState: ScreenPatientChartExtendUIState = ScreenPatientChartExtendUIState(),
    onButtonBackPressed: () -> Unit = {},
){
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(30.dp.from(ctx))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp.from(ctx))
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                CardPatientInFeature(
                    thumb = screenPatientChartExtendUIState.data.patientThumb,
                    name = screenPatientChartExtendUIState.data.patientName,
                    id = screenPatientChartExtendUIState.data.patientUserCode,
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ){
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
        }
        Spacer(modifier = Modifier.height(15.dp.from(ctx)))
        Card(
            elevation = MaterialThemeCexup.elevation.skim,
            shape = RoundedCornerShape(8.dp.from(ctx))
        ) {
            Column(
                modifier = Modifier.padding(16.dp.from(ctx))
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 2.dp.from(ctx)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp.from(ctx))
                            .clip(CircleShape)
                            .background(
                                MaterialThemeCexup.colors.color.primary.primarySurface,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_heart_beat),
                            contentDescription = "",
                            tint = MaterialThemeCexup.colors.color.primary.primaryMain
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp.from(ctx)))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp.from(ctx))
                    ) {
                        Text(
                            text = screenPatientChartExtendUIState.data.chartName,
                            style = MaterialThemeCexup.typography.hh4.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialThemeCexup.colors.color.text.textMain
                            )
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = screenPatientChartExtendUIState.data.lastValue,
                                style = MaterialThemeCexup.typography.hh2.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialThemeCexup.colors.color.text.textMain
                                )
                            )
                            Text(
                                text = screenPatientChartExtendUIState.data.satuanValue,
                                style = MaterialThemeCexup.typography.hh6.copy(
                                    color = MaterialThemeCexup.colors.color.text.textSecondary
                                )
                            )
                        }
                    }
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp.from(ctx))) {
                    ChartPatientProfile(
                        data = screenPatientChartExtendUIState.data.listChart1,
                        data2 = screenPatientChartExtendUIState.data.listChart2,
                        description = "",
                        label1 = Pair(
                            "",
                            MaterialThemeCexup.colors.palette.tertiary.redTertiary6.toArgb()
                        ),
                        label2 = Pair(
                            "",
                            BlueDashboardNew.toArgb()
                        ),
                        FormatXLabel = screenPatientChartExtendUIState.data.listDateChart,
                        maxAxis = screenPatientChartExtendUIState.data.maxYValue,
                        minAxis = screenPatientChartExtendUIState.data.minYValue,
                    )
                }
            }
        }

    }
}