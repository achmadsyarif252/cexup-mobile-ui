@file:OptIn(ExperimentalMaterialApi::class)

package com.cexup.ui.corporate.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.CardListDataUSG
import com.cexup.ui.corporate.component.CardPatientUSG
import com.cexup.ui.corporate.component.CardStatusPatientUSG
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.CexupTheme
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from

data class ScreenUSGUIState(
    val patientName: String? = "",
    val patientThumb: String? = "",
    val patientGender: String? = "",
    val patientAge: Int? = 0,
    val patientEws: String? = "",
    val dataUSG: List<DataUSG>? = listOf(),
    val dataCheckup: List<DataCheckup>? = listOf(),
    val message: String = "",
    val error: Boolean = false,
    val loading: Boolean = true,
)

data class DataUSG(
    val idData: Long? = 0,
    val date: String? = "",
    val gestationalAge: String? = "",
)
data class DataCheckup(
    val idData: Long? = 0,
    val date: String? = "",
    val bloodPressureValue: String? = "",
    val heartRateValue: Int? = 0,
    val temperatureValue: Float? = 0f,
    val weightValue: Int? = 0,
)

@Composable
fun ScreenUsg(
    usgUIState: ScreenUSGUIState,
    onButtonBackPressed: () -> Unit,
    onSeeProfileClicked: () -> Unit,
    onUSGExaminationClicked: () -> Unit,
    onThreeDotClicked: (id: Long) -> Unit = {},
    onFolderClicked: (id: Long) -> Unit = {},
    onDownloadClicked: (id: Long) -> Unit = {},
) {
    val ctx = LocalContext.current

    var positionData by remember { mutableStateOf(0) }
    var isFirst by remember { mutableStateOf(true) }
    var isLast by remember { mutableStateOf(false) }
    when (positionData) {
        usgUIState.dataCheckup?.size ?: 1-1 -> isLast = true
        0 -> isFirst = true
        else -> {
            isLast = false
            isFirst = false
        }
    }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 32.dp.from(ctx), end = 32.dp.from(ctx), start = 32.dp.from(ctx)),
        verticalArrangement = Arrangement.spacedBy(23.dp.from(ctx))
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.usg_information),
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp.from(ctx),
                    lineHeight = 32.sp.from(ctx),
                    letterSpacing = -2.sp.from(ctx),
                    color = BlueJade
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            CompositionLocalProvider(
                LocalMinimumTouchTargetEnforcement provides false,
            ) {
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
        }
        Row {
            CardPatientUSG(
                patientThumb = usgUIState.patientThumb ?: "",
                patientGender = usgUIState.patientGender ?: "",
                patientAge = usgUIState.patientAge ?: 17 ,
                gestationalAge =
                    if (usgUIState.dataUSG?.isNotEmpty() == true)
                        usgUIState.dataUSG[0].gestationalAge?: ""
                    else
                        "-",
                patientName = usgUIState.patientName?: "",
                patientEws = usgUIState.patientEws?: "",
                onSeeProfileClicked = {
                    onSeeProfileClicked()
                },
                onUSGExaminationClicked = {
                    onUSGExaminationClicked()
                },
            )
            Spacer(modifier = Modifier.width(24.dp.from(ctx)))
            CardStatusPatientUSG(
                date =
                    if (usgUIState.dataCheckup?.isNotEmpty() == true)
                        usgUIState.dataCheckup[positionData].date ?: ""
                    else
                        "-",
                temperatureValue =
                    if (usgUIState.dataCheckup?.isNotEmpty() == true)
                        usgUIState.dataCheckup[positionData].temperatureValue.toString() ?: ""
                    else
                        "-",
                heartRateValue =
                    if (usgUIState.dataCheckup?.isNotEmpty() == true)
                        usgUIState.dataCheckup[positionData].heartRateValue.toString()?: ""
                    else
                        "-",
                bloodPressureValue =
                    if (usgUIState.dataCheckup?.isNotEmpty() == true)
                        usgUIState.dataCheckup[positionData].bloodPressureValue.toString() ?: ""
                    else
                        "-",
                weightValue =
                    if (usgUIState.dataCheckup?.isNotEmpty() == true)
                        usgUIState.dataCheckup[positionData].weightValue.toString() ?: ""
                    else
                        "-",
                isFirstData = isFirst,
                isLastData = isLast,
                onCalendarClicked = {
                    /*TODO*/
                },
                onDetailsClicked = {
                    /*TODO*/
                },
                onFullHistoryClicked = {
                    /*TODO*/
                },
                onNextHistoryClicked = {
                    positionData -= 1
                },
                onPreviousHistoryClicked = {
                    positionData += 1
                },
            )
        }
        CardListDataUSG(
            listHistoryExaminationUSG = usgUIState.dataUSG?: listOf(),
            onDownloadClicked = { onDownloadClicked(it) },
            onFolderClicked = { onFolderClicked(it) },
            onThreeDotClicked = { onThreeDotClicked(it) }
        )

        Spacer(modifier = Modifier.height(32.dp.from(ctx)))
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun PreviewScreenUSGNew() {
    CexupTheme {
        Column(modifier = Modifier.width(1200.dp)) {
            ScreenUsg(
                onButtonBackPressed = {},
                usgUIState = ScreenUSGUIState(
                    patientName = "Goto Hitori",
                    patientEws = "Normal",
                    patientAge = 17,
                    patientGender = "Female",
                    patientThumb = "",
                    loading = false,
                    error = false,
                    dataUSG = listOf(),
                    dataCheckup = listOf(),
                    message = ""
                ),
                onUSGExaminationClicked = {},
                onSeeProfileClicked = {},
            )
        }
    }
}