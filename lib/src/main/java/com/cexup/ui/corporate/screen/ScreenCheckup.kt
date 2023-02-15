package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.cexup.ui.R
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.gridItems
import com.cexup.ui.utils.mediaquery.from

data class PatientPagingItemUIState(
    var name: String = "",
    var userCode: String = "",
    var email: String = "",
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScreenCheckup(
    userCode: String,
    valueDoctorActive: Int = 0,
    featureList: List<PhysicalExamination>,
    patients: LazyPagingItems<PatientPagingItemUIState>,
    onClickSyncToCloud: () -> Unit,
    onClickSelectDoctor: () -> Unit,
    onPatientSelected: (String) -> Unit,
) {
    val ctx = LocalConfiguration.current
    val listState = rememberLazyListState()
    var stateSelected by remember { mutableStateOf("") }
    var statePhysicalExamination by remember { mutableStateOf(false) }
    var selectedPatient by remember { mutableStateOf(PatientPagingItemUIState()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Text(
                modifier = Modifier.padding(start = 28.dp.from(ctx), end = 13.dp.from(ctx)),
                text = stringResource(id = R.string.corporate_patient_checkup),
                style = MaterialThemeCexup.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialThemeCexup.colors.color.text.textMain
            )
            Spacer(modifier = Modifier.height(13.dp.from(ctx)))
            LazyColumn(
                contentPadding = PaddingValues(
                    top = 10.dp.from(ctx),
                    bottom = 10.dp.from(ctx),
                    start = 28.dp.from(ctx),
                    end = 13.dp.from(ctx)
                ),
                state = listState,
                content = {
                    if (userCode == "unknown") {
                        itemsIndexed(patients) { _, patient ->
                            CardMedicalInspection(
                                name = patient?.name ?: "",
                                userCode = patient?.userCode ?: "",
                                email = patient?.email ?: "",
                                thumb = "",
                                selectedState = stateSelected == patient?.userCode,
                                onClick = {
                                    stateSelected = it
                                    statePhysicalExamination = true
                                    selectedPatient = PatientPagingItemUIState(
                                        name = patient?.name ?: "",
                                        userCode = patient?.userCode ?: "",
                                        email = patient?.email ?: "",
                                    )
                                    onPatientSelected(patient?.userCode ?: "")
                                },
                            )
                        }
                    } else {
                        var patient: PatientPagingItemUIState? = null
                        for (i in 0 until patients.itemCount) {
                            if (patients[i]?.userCode == userCode) {
                                patient = patients[i]
                            }
                        }
                        if (patient != null) {
                            item {
                                CardMedicalInspection(
                                    name = patient.name,
                                    userCode = patient.userCode,
                                    email = patient.email,
                                    thumb = "",
                                    selectedState = stateSelected == patient.userCode,
                                    onClick = {
                                        stateSelected = it
                                        statePhysicalExamination = true
                                        selectedPatient = PatientPagingItemUIState(
                                            name = patient.name,
                                            userCode = patient.userCode,
                                            email = patient.email,
                                        )
                                        onPatientSelected(patient.userCode)
                                    },
                                )
                            }
                        }
                    }
                },
                verticalArrangement = Arrangement.spacedBy(10.dp.from(ctx))
            )
        }
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp.from(ctx), end = 28.dp.from(ctx)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.corporate_physical_exam),
                    style = MaterialThemeCexup.typography.h6.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialThemeCexup.colors.color.text.textMain
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp.from(ctx)),
                    text = " / " + selectedPatient.name,
                    style = MaterialThemeCexup.typography.hh3,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Button(
                    onClick = { onClickSyncToCloud() },
                    colors = ButtonDefaults.buttonColors(
                        MaterialThemeCexup.colors.color.primary.primaryMain
                    ),
                    contentPadding = PaddingValues(
                        vertical = 8.dp.from(ctx),
                        horizontal = 12.dp.from(ctx)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.corporate_create_soap),
                        style = MaterialThemeCexup.typography.textButton2.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialThemeCexup.colors.palette.neutral.neutral1
                    )
                }
            }
            LazyColumn(
                contentPadding = PaddingValues(
                    top = 10.dp.from(ctx),
                    bottom = 10.dp.from(ctx),
                    start = 13.dp.from(ctx),
                    end = 28.dp.from(ctx)
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp.from(ctx)),
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                if (statePhysicalExamination) {
                    gridItems(
                        count = featureList.size,
                        columnCount = 3,
                        horizontalArrangement = Arrangement.spacedBy(10.dp.from(ctx)),
                    ) { index ->
                        val feature = featureList[index]
                        CardPhysicalExamination(
                            icon = feature.icon,
                            valueRisk = feature.valueRisk,
                            namePhysicalExamination = feature.nameFeature,
                        ) {
                            feature.onClick(selectedPatient.userCode)
                        }
                    }
                    item{
                        CardOrderTeleconsultation(
                            valueDoctorActive = valueDoctorActive,
                            onClickSelectDoctor = onClickSelectDoctor
                        )
                    }
                } else {
                    item {
                        Spacer(modifier = Modifier.height(50.dp.from(ctx)))
                        CardEmptyState(
                            drawable = R.drawable.ic_empty_state,
                            header = stringResource(
                                id = R.string.greet_nurse,
                                formatArgs = arrayOf("Nurse")
                            ),
                            body = stringResource(id = R.string.corporate_checkup_empty)
                        )
                    }
                }
            }
        }

    }
}

data class PhysicalExamination(
    val icon: Int,
    val nameFeature: String,
    val valueRisk: String = "",
//    val unit: String? = null,
//    val typePhysicalExamination: TypePhysicalExamination,
//    val value1: String? = null,
//    val value2: String? = null,
    val onClick: (String) -> Unit,
)