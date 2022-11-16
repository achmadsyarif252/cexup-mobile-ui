package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.cexup.ui.R
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.gridItems
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.Sync24

data class PatientPagingItemUIState(
    var name: String = "",
    var userCode: String = "",
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScreenCheckup(
    userCode:String,
    featureList: List<PhysicalExamination>,
    onClickSyncToCloud: () -> Unit,
    patients: LazyPagingItems<PatientPagingItemUIState>,
    onPatientSelected: (String) -> Unit,
) {
    val ctx = LocalContext.current
    val listState = rememberLazyListState()
    var stateSelected by remember { mutableStateOf("") }
    var statePhysicalExamination by remember { mutableStateOf(false) }
    var selectedPatient by remember { mutableStateOf(PatientPagingItemUIState()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp.from(ctx))
    ) {
        Column(modifier = Modifier.padding(bottom = 10.dp.from(ctx))) {
            Text(
                text = stringResource(id = R.string.corporate_waiting_checkup),
                style = MaterialTheme.typography.body1.copy(
                    color = Heading,
                    fontSize = 22.sp.from(ctx),
                    fontWeight = FontWeight(700)
                ),
            )
            Spacer(modifier = Modifier.height(13.dp.from(ctx)))
            LazyColumn(
                state = listState,
                content = {
                    if (userCode == "uknown") {
                        itemsIndexed(patients) { _, patient ->
                            CardMedicalInspection(
                                name = patient?.name ?: "",
                                userCode = patient?.userCode ?: "",
                                thumb = "",
                                selectedState = stateSelected == patient?.userCode,
                                onClick = {
                                    stateSelected = it
                                    statePhysicalExamination = true
                                    selectedPatient = PatientPagingItemUIState(
                                        name = patient?.name ?: "",
                                        userCode = patient?.userCode ?: ""
                                    )
                                    onPatientSelected(patient?.userCode ?: "")
                                },
                            )
                        }
                    }else{
                        var patient: PatientPagingItemUIState? = null
                        for (i in 0 until patients.itemCount) {
                            if (patients[i]?.userCode == userCode) {
                                patient = patients[i]
                            }
                        }
                        if (patient != null) {
                            item {
                                CardMedicalInspection(
                                    name = patient?.name ?: "",
                                    userCode = patient?.userCode ?: "",
                                    thumb = "",
                                    selectedState = stateSelected == patient?.userCode,
                                    onClick = {
                                        stateSelected = it
                                        statePhysicalExamination = true
                                        selectedPatient = PatientPagingItemUIState(
                                            name = patient?.name ?: "",
                                            userCode = patient?.userCode ?: ""
                                        )
                                        onPatientSelected(patient?.userCode ?: "")
                                    },
                                )
                            }
                        }
                    }
                },
                verticalArrangement = Arrangement.spacedBy(10.dp.from(ctx))
            )
        }
        Spacer(modifier = Modifier.width(26.dp.from(ctx)))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp.from(ctx)),
                modifier = Modifier
                    .padding(bottom = 10.dp.from(ctx))
                    .fillMaxHeight()
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.corporate_physical_exam),
                            style = MaterialTheme.typography.body1.copy(
                                color = Heading,
                                fontSize = 22.sp.from(ctx),
                                fontWeight = FontWeight(700)
                            ),
                        )
                        Text(
                            text = "・",
                            style = MaterialTheme.typography.body1.copy(
                                color = Heading,
                                fontSize = 16.sp.from(ctx),
                                fontWeight = FontWeight(400)
                            ),
                        )
                        Text(
                            text = selectedPatient.name,
                            style = MaterialTheme.typography.body1.copy(
                                color = Heading,
                                fontSize = 16.sp.from(ctx),
                                fontWeight = FontWeight(400)
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Button(
                            modifier = Modifier.widthIn(min = 80.dp.from(ctx)),
                            onClick = { onClickSyncToCloud() }
                        ) {
                            Icon(
                                Octicons.Sync24,
                                "",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp.from(ctx))
                            )
                            Spacer(modifier = Modifier.width(5.dp.from(ctx)))
                            Text(
                                text = stringResource(id = R.string.corporate_sync_cloud),
                                style = MaterialTheme.typography.body1.copy(
                                    color = Color.White,
                                    fontSize = 12.sp.from(ctx),
                                    fontWeight = FontWeight(400)
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
                if (statePhysicalExamination) {
                    gridItems(
                        count = featureList.size,
                        columnCount = 3,
                        horizontalArrangement = Arrangement.spacedBy(10.dp.from(ctx)),
                    ) { index ->
                        val feature = featureList[index]
                        CardPhysicalExamination(
                            icon = feature.icon,
                            namePhysicalExamination = feature.nameFeature,
                            value = feature.value1 ?: "",
                            value2 = feature.value2 ?: "",
                            typePhysicalExamination = feature.typePhysicalExamination,
                        ) {
                            feature.onClick(selectedPatient.userCode)
                        }
                    }
                } else {
                    item {
                        Spacer(modifier = Modifier.height(50.dp.from(ctx)))
                        CardEmptyState(
                            drawable = R.drawable.ic_empty_state,
                            header = stringResource(
                                id = R.string.greet_nurse,
                                formatArgs = arrayOf("Sus")
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
    val icon : Int,
    val nameFeature : String,
    val unit : String? = null,
    val typePhysicalExamination: TypePhysicalExamination,
    val value1 : String? = null,
    val value2 : String? = null,
    val onClick: (String) -> Unit,
)