package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.theme.DangerMain
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.corporate.theme.PrimaryMain
import com.cexup.ui.utils.mediaquery.from

data class DataDiagnostic(
    val initialValue: Float = 0f,
    val maxValue: Float = 100f,
    val diagnosticName: String = "Penyakit",
    val colorProgressBar: Color = PrimaryMain
)
data class DataDoctorDashboard(
    val thumb: String = "",
    val doctorName: String = "",
    val doctorSpeciality: String = "",
)

data class ScreenDashboardUIState(
    val error:Boolean = false,
    val loading: Boolean = true,
    val message: String = "",
    val data: DataScreenDashboard = DataScreenDashboard()
)
data class DataScreenDashboard(
    val valueNewPatients: Int = 0,
    val valueLastUpdatedNewPatients: String = "",
    val valuePercentRatioPatientBeforeAndToday: String = "",
    val valueActiveDoctors: Int = 0,
    val valueLastUpdatedActiveDoctors: String = "",
    val valuePercentRatioActiveDoctorsBeforeAndToday: String = "",
    val valueTotalPatients: Int = 0,
    val dataPieChart: List<PieChartData> = listOf(),
    val listDoctorActive: List<DataDoctorDashboard> = listOf(),
    val sizeDoctorActive: Int = 3,
    val currentPageDoctorActive: Int = 1,
    val listDataDiagnostic: List<DataDiagnostic> = listOf(),
    val valueNameLastConsultationDoctor: String = "",
    val valueSpecialityLastConsultationDoctor: String = "",
    val valueDateLastConsultationDoctor: String = "",
)

@Composable
fun ScreenDashboard(
    dashboardUIState: ScreenDashboardUIState = ScreenDashboardUIState(),
    onBackListDoctorActive: () -> Unit = {},
    onNextListDoctorActive: () -> Unit = {},
) {
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(28.dp.from(ctx)),
    ) {
        Text(
            text = stringResource(id = R.string.corporate_menu_dashboard),
            style = MaterialThemeCexup.typography.h6.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
            ){
                CardNewPatients(
                    valueNewPatients = dashboardUIState.data.valueNewPatients,
                    valueLastUpdated = dashboardUIState.data.valueLastUpdatedNewPatients,
                    valuePercentRatioPatientBeforeAndToday = dashboardUIState.data.valuePercentRatioPatientBeforeAndToday
                )
                CardChartPatients(
                    dataPieChart = dashboardUIState.data.dataPieChart,
                    totalPatients = dashboardUIState.data.valueTotalPatients,
                )

            }
            Column (
                verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
            ){
                CardDoctorActive(
                    valueDoctorsActive = dashboardUIState.data.valueActiveDoctors,
                    valueLastUpdated = dashboardUIState.data.valueLastUpdatedActiveDoctors,
                    valuePercentRatioDoctorsBeforeAndToday = dashboardUIState.data.valuePercentRatioActiveDoctorsBeforeAndToday,
                )
                CardActiveDoctorsList(
                    listDoctor = dashboardUIState.data.listDoctorActive,
                    sizeData = dashboardUIState.data.sizeDoctorActive,
                    currentPage = dashboardUIState.data.currentPageDoctorActive,
                    onBackPressed = {
                        onBackListDoctorActive()
                    },
                    onNextPressed = {
                        onNextListDoctorActive()
                    }
                )
            }
            Column (
                verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
            ) {
                CardPatientsDiagnostic(
                    listDiagnostic = dashboardUIState.data.listDataDiagnostic,
                )
                CardLastConsultationDoctor(
                    doctorName = dashboardUIState.data.valueNameLastConsultationDoctor,
                    doctorSpeciality = dashboardUIState.data.valueSpecialityLastConsultationDoctor,
                    dateLastConsultation = dashboardUIState.data.valueDateLastConsultationDoctor,
                )
            }

        }
    }
}