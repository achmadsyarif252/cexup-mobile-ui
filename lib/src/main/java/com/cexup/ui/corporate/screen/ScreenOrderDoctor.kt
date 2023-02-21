package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from

data class ScreenOrderDoctorUIState(
    var message: String = "",
    var loading: Boolean = true,
    var error: Boolean = false,
    var data: DataOrderDoctorInformation = DataOrderDoctorInformation()
)

data class DataOrderDoctorInformation(
    var doctorName: String = "",
    var doctorSpeciality: String = "",
    var doctorPrice: String = "",
    var doctorID: String = "",
    var doctorHospital: String = "",
    var doctorExperience: Int = 1,
    var doctorRating: String = "",
    var doctorCountRating: Int = 0,
    var nextScheduleTeleconsultationDate: String = "Selasa, 11 Okt 2022",
    var nextScheduleTeleconsultationTime: String = "10:00 - 10:30 WIB",
    var listDoctorSchedule: List<DataConsultationSchedule> = listOf()
)

@Composable
fun ScreenOrderDoctor(
    orderDoctorUIState: ScreenOrderDoctorUIState = ScreenOrderDoctorUIState(),
    onRemindeMePressed: () -> Unit = {},
    onButtonNextPressed: (patientComplain: String, scheduleDate: String, scheduleSession: String) -> Unit = { _, _, _ -> },
    onButtonBackPressed: () -> Unit,
) {
    val ctx = LocalConfiguration.current
    var valueComplain by remember {
        mutableStateOf("")
    }
    var selectedDate by remember {
        mutableStateOf(DataConsultationSchedule("", "", listOf()))
    }
    var selectedSession by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp.from(ctx))
            .padding(bottom = 47.dp.from(ctx)),
        verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.doctor_information),
                style = MaterialThemeCexup.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialThemeCexup.colors.color.text.textMain
            )
            Button(
                onClick = { onButtonBackPressed() },
                contentPadding = PaddingValues(
                    vertical = 8.dp.from(ctx),
                    horizontal = 12.dp.from(ctx)
                ),
                colors = ButtonDefaults.buttonColors(MaterialThemeCexup.colors.palette.tertiary.redTertiary6),
                shape = RoundedCornerShape(4.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.back),
                    style = MaterialThemeCexup.typography.textButton2.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialThemeCexup.colors.palette.neutral.neutral1
                )
            }
        }
        CardDoctorInformationOrderDoctor(
            doctorName = orderDoctorUIState.data.doctorName,
            doctorSpeciality = orderDoctorUIState.data.doctorSpeciality,
            doctorPrice = orderDoctorUIState.data.doctorPrice
        )
        CardDetailDoctorInformationOrderDoctor(
            doctorNoType = orderDoctorUIState.data.doctorID,
            doctorHospital = orderDoctorUIState.data.doctorHospital,
            doctorExperience = orderDoctorUIState.data.doctorExperience,
            doctorRating = orderDoctorUIState.data.doctorRating,
            doctorCountRating = orderDoctorUIState.data.doctorCountRating
        )
        CardNextScheduleTeleconsultation(
            nextConsultationDate = orderDoctorUIState.data.nextScheduleTeleconsultationDate,
            nextConsultationTime = orderDoctorUIState.data.nextScheduleTeleconsultationTime,
            onRemindeMePressed = onRemindeMePressed
        )
        CardPatientComplaint(
            valueComplain = valueComplain,
            onValueChangeComplain = { valueComplain = it }
        )
        CardChooseConsultationSchedule(
            listDateConsultationSchedule = orderDoctorUIState.data.listDoctorSchedule,
            selected = selectedDate,
            onScheduleSelected = {
                selectedDate = it
                selectedSession = ""
            }
        )
        if (selectedDate != DataConsultationSchedule("", "", listOf())) {
            orderDoctorUIState.data.listDoctorSchedule[orderDoctorUIState.data.listDoctorSchedule.indexOf(
                selectedDate
            )].listSession.forEach {
                CardSessionSchedule(
                    selectedSession = selectedSession,
                    sessionTitle = it.sessionName,
                    listTimeSession = it.listSession,
                    onSessionSelected = { selectedSession = it })
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onButtonNextPressed(valueComplain, selectedDate.date, selectedSession) },
            contentPadding = PaddingValues(vertical = 16.dp.from(ctx)),
            shape = RoundedCornerShape(4.dp.from(ctx)),
            colors = ButtonDefaults.buttonColors(MaterialThemeCexup.colors.color.primary.primaryMain),
            enabled = selectedSession.isNotEmpty() && valueComplain.isNotEmpty()
        ) {
            Text(
                text = stringResource(id = R.string.next),
                style = MaterialThemeCexup.typography.textButton1.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialThemeCexup.colors.palette.neutral.neutral1
            )
        }
    }
}