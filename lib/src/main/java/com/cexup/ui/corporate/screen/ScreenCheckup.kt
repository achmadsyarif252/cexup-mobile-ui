package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.gridItems
import compose.icons.Octicons
import compose.icons.octicons.Sync24

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScreenCheckup(
    featureList: List<PhysicalExamination>,
    onClickSyncToCloud: () -> Unit,
    patients: List<Pair<String, String>>,
    onPatientSelected: (String) -> Unit,
) {
    val listState = rememberLazyListState()
    var stateSelected by remember { mutableStateOf("") }
    var statePhysicalExamination by remember { mutableStateOf(false) }
    var selectedPatient by remember { mutableStateOf(Pair(first = "", second = "")) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 10.dp)) {
            Text(
                text = stringResource(id = R.string.corporate_waiting_checkup),
                style = MaterialTheme.typography.body1.copy(
                    color = Heading,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700)
                ),
            )
            Spacer(modifier = Modifier.height(13.dp))
            LazyColumn(
                state = listState,
                content = {
                    itemsIndexed(patients) { _, patient ->
                        CardMedicalInspection(
                            name = patient.first,
                            userCode = patient.second,
                            thumb = "",
                            selectedState = stateSelected == patient.second,
                            onClick = {
                                stateSelected = it
                                statePhysicalExamination = true
                                selectedPatient = patient
                                onPatientSelected(patient.second)
                            },
                        )
                    }
                },
                verticalArrangement = Arrangement.spacedBy(10.dp)
            )
        }
        Spacer(modifier = Modifier.width(26.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxHeight()
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.corporate_physical_exam),
                                style = MaterialTheme.typography.body1.copy(
                                    color = Heading,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight(700)
                                ),
                            )
                            Text(
                                text = "・",
                                style = MaterialTheme.typography.body1.copy(
                                    color = Heading,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(400)
                                ),
                            )
                            Text(
                                text = selectedPatient.first,
                                style = MaterialTheme.typography.body1.copy(
                                    color = Heading,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(400)
                                ),
                            )
                        }
                        Button(
                            onClick = { onClickSyncToCloud() }
                        ) {
                            Icon(
                                Octicons.Sync24,
                                "",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = stringResource(id = R.string.corporate_sync_cloud),
                                style = MaterialTheme.typography.body1.copy(
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(400)
                                ),
                            )
                        }
                    }
                }
                if (statePhysicalExamination) {
                    gridItems(
                        count = featureList.size,
                        columnCount = 3,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) { index ->
                        val feature = featureList[index]
                        CardPhysicalExamination(
                            icon = feature.icon,
                            namePhysicalExamination = feature.nameFeature,
                            value = feature.value1 ?: "",
                            value2 = feature.value2 ?: "",
                            typePhysicalExamination = feature.typePhysicalExamination,
                        ) {
                            feature.onClick(selectedPatient.second)
                        }
                    }
                } else {
                    item {
                        Spacer(modifier = Modifier.height(50.dp))
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