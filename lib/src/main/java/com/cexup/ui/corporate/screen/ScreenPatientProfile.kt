package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.theme.ColorGray
import com.cexup.ui.corporate.theme.Heading
import com.github.mikephil.charting.data.Entry
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.cexup.ui.R
import com.cexup.ui.utils.mediaquery.from

enum class EwsState {
    SUCCESS, LOADING, FAILED
}

data class PatientProfileUIState(
    var userCode: String = "Unknown",
    var name: String = "Unknown",
    var email: String = "Unknown",
    var date_of_birth: String = "Unknown",
    var gender: String = "Unknown",
    var status: Boolean = true,
    var address: String = "Unknown",
    var phone_number: String = "Unknown",
    var currentDisease: String = "Unknown",
)

data class EwsPatientProfileUIState(
    val userCode : String = "",
    val point: Int? = null,
    val result : String = "",
    val cause : List<String> = listOf(),
    val produce_at : Long = 0,
    val ewsState: EwsState,
)

data class PatientMeasurementUIState(
    // label, entry
    var dataBmi: Pair<List<String>, List<Entry>>,
    var dataWaist: Pair<List<String>, List<Entry>>,
    var dataTemp: Pair<List<String>, List<Entry>>,
    var dataPulseOxiMeter: Pair<List<String>, List<Entry>>,
    var dataHeartRate: Pair<List<String>, List<Entry>>,
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScreenPatientProfile(
    modifier: Modifier = Modifier,
    patientProfileUIState: PatientProfileUIState,
    ewsPatientProfileUIState: EwsPatientProfileUIState,
    patientMeasurementUIState: PatientMeasurementUIState,
    initialPage:Int = 0,
) {
    val ctx = LocalContext.current
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(
        initialPage = initialPage
    )
    val tabs = listOf(
        TabContentRow(header = stringResource(id = R.string.patient_information)) {
            CardPatientProfileInformation(
                name = patientProfileUIState.name,
                email = patientProfileUIState.email,
                date_of_birth = patientProfileUIState.date_of_birth,
                gender = patientProfileUIState.gender,
                status = patientProfileUIState.status,
                address = patientProfileUIState.address,
                phone_number = patientProfileUIState.phone_number,
            )
        },
        TabContentRow(header = stringResource(id = R.string.summary)) {
            CardSummary(
                dataBmi = patientMeasurementUIState.dataBmi,
                dataWaist = patientMeasurementUIState.dataWaist,
                dataTemp = patientMeasurementUIState.dataTemp,
                dataPulseOximeter = patientMeasurementUIState.dataPulseOxiMeter,
                dataHeartRate = patientMeasurementUIState.dataHeartRate,
            )
        }
    )
    Row(
        modifier = modifier
        .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .width(369.25.dp.from(ctx)),
        ) {
            Column(
                modifier = modifier
                    .padding(10.dp.from(ctx))
                    .verticalScroll(scrollState)
            ) {
                CardPatientProfile(
                    patientName = patientProfileUIState.name,
                    patientMail = patientProfileUIState.email,
                )
                Spacer(modifier = modifier.height(16.dp.from(ctx)))
                TabView(
                    tabContents = tabs,
                    pagerState = pagerState,
                )
                Spacer(modifier = modifier.height(23.dp.from(ctx)))
                TabContent(
                    tabContents = tabs,
                    pagerState = pagerState,
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .width(341.dp.from(ctx)),
            content = {
                item {
                    Text(
                        text = stringResource(id = R.string.early_warning_score),
                        fontSize = 15.sp.from(ctx),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(700),
                        color = Heading,
                        modifier = modifier.padding(bottom = 3.dp.from(ctx))
                    )
                    CardEarlyWarningScore(
                        ews = when (ewsPatientProfileUIState.ewsState) {
                            EwsState.SUCCESS -> ewsPatientProfileUIState.point ?: 0
                            EwsState.LOADING -> 0
                            EwsState.FAILED -> 0
                        }
                    )
                    Spacer(modifier = modifier.height(16.dp.from(ctx)))
                    Text(
                        text = stringResource(id = R.string.electronic_medical_record),
                        fontSize = 15.sp.from(ctx),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(700),
                        color = Heading,
                        modifier = modifier.padding(bottom = 10.dp.from(ctx))
                    )
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.this_month),
                            fontSize = 12.sp.from(ctx),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Normal,
                            color = ColorGray,
                            modifier = modifier.padding(bottom = 3.dp.from(ctx))
                        )
                        Text(
                            text = stringResource(id = R.string.view_all),
                            fontSize = 12.sp.from(ctx),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Normal,
                            color = ColorGray,
                            modifier = modifier.padding(bottom = 3.dp.from(ctx))
                        )
                    }
                    Spacer(modifier = modifier.height(13.dp.from(ctx)))
                }
                items(10) {
                    CardReportDocument(nameFile = "Health Rate Report.Doc", fileSize = "3 Mb")
                }
            }
        )
    }
}