package com.cexup.ui.corporate.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.CardBmiBabyWeight
import com.cexup.ui.corporate.component.CardPatientInFeature
import com.cexup.ui.corporate.component.CardResultBabyWeight
import com.cexup.ui.corporate.component.CardStepBabyWeight
import com.cexup.ui.corporate.theme.Natural90
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from

data class ScreenBabyWeightUIState(
    val patientName: String? = "",
    val patientThumb: String? = "",
    val patientUserCode: String? = "",
    val data: DataBabyWeight,
    val message: String = "",
    val error: Boolean = false,
    val loading: Boolean = true,
)

data class DataBabyWeight(
    val idData: Long? = 0,
    val motherWeight: Int? = 0,
    val stateStepMotherWeight: StepBabyBMI? = StepBabyBMI.PRE_MEASUREMENT,
    val babyWeight: Int? = 0,
    val stateStepBabyWeight: StepBabyBMI? = StepBabyBMI.PRE_MEASUREMENT,
    val babyHeight: Int? = 0,
    val stateStepBabyHeight: StepBabyBMI? = StepBabyBMI.PRE_MEASUREMENT,
    val babyBMI: Int? = 0,
    val statusBabyBMI: String? = "Normal",
    val statusRangeBabyBMI: String? = "≥ 18.5 – < 25"
)

enum class StepBabyBMI {
    PRE_MEASUREMENT, MEASURING, POST_MEASUREMENT
}

data class ResourceStepBabyBMI(
    val resourceImage:Int,
    val titleText: String,
    val descriptionText: String,
)

@Composable
fun ScreenBabyWeight(
    babyWeightUIState: ScreenBabyWeightUIState,
    onMotherWeightMeasurement: () -> Unit,
    onMotherAndBabyWeightMeasurement: () -> Unit,
    onBabyHeightMeasurement: () -> Unit,
    onSeeChartClicked: () -> Unit,
    onRemeasurementClicked: () -> Unit,
    onSync: () -> Unit = {},
    onButtonBackPressed: () -> Unit,
) {
    val ctx = LocalContext.current
    val dataStep = listOf(
        ResourceStepBabyBMI(
            resourceImage = R.drawable.ic_baby_bmi_1,
            titleText = stringResource(id = R.string.mother_weight),
            descriptionText = stringResource(id = R.string.step_1_baby_weight)
        ),
        ResourceStepBabyBMI(
            resourceImage = R.drawable.ic_baby_bmi_2,
            titleText = stringResource(id = R.string.mother_and_baby_weight),
            descriptionText = stringResource(id = R.string.step_2_baby_weight)
        ),
        ResourceStepBabyBMI(
            resourceImage = R.drawable.ic_baby_bmi_3,
            titleText = stringResource(id = R.string.baby_height),
            descriptionText = stringResource(id = R.string.step_3_baby_weight)
        ),
    )

    Column (
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
            .padding(32.dp.from(ctx)),
        verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardPatientInFeature(
                thumb = babyWeightUIState.patientThumb ?: "",
                name = babyWeightUIState.patientName ?: "",
                id = babyWeightUIState.patientUserCode ?: "-"
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { onSync() },
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_sync
                    ),
                    contentDescription = "",
                )
            }
            Spacer(modifier = Modifier.width(24.dp.from(ctx)))
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
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.bmi_for_baby),
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp.from(ctx),
                lineHeight = 32.sp.from(ctx),
                letterSpacing = (-2).sp.from(ctx),
                color = Natural90.copy(alpha = 0.9f),
                textAlign = TextAlign.Center
            )
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardResultBabyWeight(
                motherWeight = babyWeightUIState.data.motherWeight ?: 0,
                babyWeight = babyWeightUIState.data.babyWeight ?: 0,
                babyHeight = babyWeightUIState.data.babyHeight ?: 0,
                onSeeChartClicked = {
                    onSeeChartClicked()
                }
            )
            CardBmiBabyWeight(
                babyBmi = babyWeightUIState.data.babyBMI ?: 0,
                bmiStatus = babyWeightUIState.data.statusBabyBMI ?: "",
                bmiRangeValue = babyWeightUIState.data.statusRangeBabyBMI ?: "",
                onRemeasurementClicked = {onRemeasurementClicked()}
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dataStep.forEachIndexed {index, resourceStepBabyBMI ->
                CardStepBabyWeight(
                    numberStep = index+1,
                    resourceImage = resourceStepBabyBMI.resourceImage,
                    textTitle = resourceStepBabyBMI.titleText,
                    textDetails = resourceStepBabyBMI.descriptionText,
                    stateStep = when(index){
                        0 -> babyWeightUIState.data.stateStepMotherWeight ?: StepBabyBMI.PRE_MEASUREMENT
                        1 -> babyWeightUIState.data.stateStepBabyWeight ?: StepBabyBMI.PRE_MEASUREMENT
                        2 -> babyWeightUIState.data.stateStepBabyHeight ?: StepBabyBMI.PRE_MEASUREMENT
                        else -> {StepBabyBMI.PRE_MEASUREMENT}
                    },
                    onCardStepClicked = {
                        when(it){
                            1 -> {
                                onMotherWeightMeasurement()
                            }
                            2 -> {
                                onMotherAndBabyWeightMeasurement()
                            }
                            3 -> {
                                onBabyHeightMeasurement()
                            }
                        }
                    },
                    isEnabled = when(index){
                        0 -> true
                        1 -> babyWeightUIState.data.stateStepMotherWeight == StepBabyBMI.POST_MEASUREMENT
                        2 -> babyWeightUIState.data.stateStepMotherWeight == StepBabyBMI.POST_MEASUREMENT && babyWeightUIState.data.stateStepBabyWeight == StepBabyBMI.POST_MEASUREMENT
                        else -> {false}
                    }
                )
            }
        }
    }
}