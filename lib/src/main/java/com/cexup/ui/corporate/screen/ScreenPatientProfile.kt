package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.AllergyLevel
import com.cexup.ui.corporate.component.CardAllergies
import com.cexup.ui.corporate.component.CardInformationPatient
import com.cexup.ui.corporate.component.CardProfilePatientNew
import com.cexup.ui.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from
import com.github.mikephil.charting.data.Entry

data class ScreenPatientProfileUIState(
    val error: Boolean = false,
    val loading: Boolean = true,
    val message: String = "",
    val data: DataPatientProfile = DataPatientProfile()
)

data class DataPatientProfile(
    val patientFullname: String = "-",
    val patientFirstName: String = "-",
    val patientLastName: String = "-",
    val patientEws: String = "Normal",
    val patientGender: String = "-",
    val patientAge: Int = 1,
    val patientWeight: String = "-",
    val patientHeight: String = "-",
    val patientGolDarah: String = "-",
    val patientStatusActive: Boolean = false,
    val patientAllergy: List<Pair<String, AllergyLevel>> = listOf(),
    val patientBirthDate: String = "-",
    val patientReligion: String = "-",
    val patientAssignedDoctor: String = "-",
    val patientStatusMarried: String = "-",
    val patientDateIn: String = "-",
    val patientID: String = "-",
    val patientAddress: String = "-",
    val patientPhoneNumber: String = "-",
    val patientEmail: String = "-",
    val patientNoteFromNurse: String = "-",
    val patientSystoleValue: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    val patientDiastoleValue: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    val patientHeartRateValue: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    val patientSpo2Value: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    val patientBMIValue: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf()),
    val patientTemperatureValue: Pair<List<String>, List<Entry>> = Pair(listOf(), listOf())
)

@Composable
fun ScreenPatientProfile(
    patientProfileUIState: ScreenPatientProfileUIState = ScreenPatientProfileUIState(),
    onValueNoteChange: (valueText: String) -> Unit = {},
    onToDetailChart: (typeChart: String) -> Unit = {},
    onAddAllergy: () -> Unit = {},
    onRemoveAllergy: (index: Int) -> Unit = {},
    onTextChanged: (index: Int, value: String) -> Unit = {_,_ ->},
    onChangeAllergies: (index: Int, value: AllergyLevel) -> Unit = {_,_ ->},
) {
    val ctx = LocalConfiguration.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 25.dp.from(ctx), bottom = 27.dp.from(ctx))
            .padding(horizontal = 28.dp.from(ctx)),
        verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
    ) {
        Text(
            text = stringResource(id = R.string.patient_information),
            style = MaterialThemeCexup.typography.h6.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        )
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp.from(ctx))) {
            Column {
                CardProfilePatientNew(
                    patientFullName = patientProfileUIState.data.patientFullname,
                    ewsStatus = patientProfileUIState.data.patientEws,
                    patientGender = patientProfileUIState.data.patientGender,
                    patientAge = patientProfileUIState.data.patientAge.toString(),
                    patientHeight = patientProfileUIState.data.patientHeight,
                    patientWeight = patientProfileUIState.data.patientWeight,
                    patientGolDarah = patientProfileUIState.data.patientGolDarah,
                    patientStatus = patientProfileUIState.data.patientStatusActive,
                )
                Spacer(modifier = Modifier.height(21.dp.from(ctx)))
                CardAllergies(
                    listAllergies = patientProfileUIState.data.patientAllergy,
                    onAddAllergy = onAddAllergy,
                    onRemoveAllergy = onRemoveAllergy,
                    onTextChanged = onTextChanged,
                    onChangeAllergies = onChangeAllergies,
                )
            }
            CardInformationPatient(
                patientFirstName = patientProfileUIState.data.patientFirstName,
                patientLastName = patientProfileUIState.data.patientLastName,
                patientUserCode = patientProfileUIState.data.patientID,
                patientAddress = patientProfileUIState.data.patientAddress,
                patientAssignedDoctor = patientProfileUIState.data.patientAssignedDoctor,
                patientBirthDate = patientProfileUIState.data.patientBirthDate,
                patientDateIn = patientProfileUIState.data.patientDateIn,
                patientEmail = patientProfileUIState.data.patientEmail,
                patientNoteFromNurse = patientProfileUIState.data.patientNoteFromNurse,
                patientPhoneNumber = patientProfileUIState.data.patientPhoneNumber,
                patientReligion = patientProfileUIState.data.patientReligion,
                patientStatusMarried = patientProfileUIState.data.patientStatusMarried,
                patientSystoleValue = patientProfileUIState.data.patientSystoleValue.second,
                patientDiastoleValue = patientProfileUIState.data.patientDiastoleValue.second,
                patientBMIValue = patientProfileUIState.data.patientBMIValue.second,
                patientHeartRateValue = patientProfileUIState.data.patientHeartRateValue.second,
                patientSpo2Value = patientProfileUIState.data.patientSpo2Value.second,
                patientTemperatureValue = patientProfileUIState.data.patientTemperatureValue.second,
                DateTemperature = patientProfileUIState.data.patientTemperatureValue.first,
                DateSpo2 = patientProfileUIState.data.patientSpo2Value.first,
                DateHeartRate = patientProfileUIState.data.patientHeartRateValue.first,
                DateBMI = patientProfileUIState.data.patientBMIValue.first,
                DateBloodPressure = patientProfileUIState.data.patientSystoleValue.first,
                onToDetailChart = {
                    onToDetailChart(it)
                },
                onValueChangeNote = {
                    onValueNoteChange(it)
                },
            )

        }
    }
}