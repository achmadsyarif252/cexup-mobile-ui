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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.theme.ColorGray
import com.cexup.ui.corporate.theme.Heading
import com.github.mikephil.charting.data.Entry
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

enum class EwsState {
    SUCCESS, LOADING, FAILED
}

data class PatientData(
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

data class EwsData(
    val userCode : String = "",
    val point: Int? = null,
    val result : String = "",
    val cause : List<String> = listOf(),
    val produce_at : Long = 0,
    val ewsState: EwsState,
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScreenPatientProfile(
    modifier: Modifier = Modifier,
    patientData: PatientData?,
    ewsData: EwsData,
    dataBmi: Pair<List<String>, List<Entry>>,
    dataWaist: Pair<List<String>, List<Entry>>,
    dataTemp: Pair<List<String>, List<Entry>>,
    dataPulseOximeter: Pair<List<String>, List<Entry>>,
    dataHeartRate: Pair<List<String>, List<Entry>>,
) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState()
    val tabs = listOf(
        TabContentRow(header = "Patient Information") {
            CardPatientProfileInformation()
        },
        TabContentRow(header = "Summary") {
            CardSummary(
                dataBmi = dataBmi,
                dataWaist = dataWaist,
                dataHeartRate = dataHeartRate,
                dataPulseOximeter = dataPulseOximeter,
                dataTemp = dataTemp,
            )
        }
    )
    Row(
        modifier = modifier
        .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .width(369.25.dp),
        ) {
            Column(
                modifier = modifier
                    .padding(10.dp)
                    .verticalScroll(scrollState)
            ) {
                CardPatientProfile(
                    patientName = patientData?.name ?: "empty name",
                    patientMail = patientData?.email ?: "empty mail"
                )
                Spacer(modifier = modifier.height(16.dp))
                TabView(
                    tabContents = tabs,
                    pagerState = pagerState,
                )
                Spacer(modifier = modifier.height(23.dp))
                TabContent(
                    tabContents = tabs,
                    pagerState = pagerState,
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .width(341.dp),
            content = {
                item {
                    Text(
                        text = "Early Warning Score",
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(700),
                        color = Heading,
                        modifier = modifier.padding(bottom = 3.dp)
                    )
                    CardEarlyWarningScore(
                        ews = when (ewsData.ewsState) {
                            EwsState.SUCCESS -> ewsData.point ?: 0
                            EwsState.LOADING -> 0
                            EwsState.FAILED -> 0
                        }
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Text(
                        text = "Electronic Medical Record",
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight(700),
                        color = Heading,
                        modifier = modifier.padding(bottom = 10.dp)
                    )
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "This month",
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Normal,
                            color = ColorGray,
                            modifier = modifier.padding(bottom = 3.dp)
                        )
                        Text(
                            text = "View All",
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Normal,
                            color = ColorGray,
                            modifier = modifier.padding(bottom = 3.dp)
                        )
                    }
                    Spacer(modifier = modifier.height(13.dp))
                }
                items(10) {
                    CardReportDocument(nameFile = "Health Rate Report.Doc", fileSize = "3 Mb")
                }
            }
        )
    }
}