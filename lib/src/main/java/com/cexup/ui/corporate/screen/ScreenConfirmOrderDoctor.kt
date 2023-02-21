package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.CardBookingConfirmed
import com.cexup.ui.corporate.component.CardInsuranceProgramOrder
import com.cexup.ui.corporate.component.CardNeedOurHelpOrder
import com.cexup.ui.corporate.component.CardPaymentSummary
import com.cexup.ui.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from

data class ScreenConfirmOrderDoctorUIState(
    var message: String = "",
    var loading: Boolean = true,
    var error: Boolean = false,
    var data: DataConfirmOrderDoctor = DataConfirmOrderDoctor()
)

data class DataConfirmOrderDoctor(
    var patientComplain: String = "",
    var consultationSchedule: String = "",
    var listPaymentPatient: List<Pair<String,String>> = listOf(),
    var totalPayment: String = "",
)

@Composable
fun ScreenConfirmOrderDoctor(
    confirmOrderDoctorUIState: ScreenConfirmOrderDoctorUIState = ScreenConfirmOrderDoctorUIState(),
    onRescheduleClicked: () -> Unit = {},
    onButtonCallClicked: () -> Unit = {},
    onButtonNextPressed: () -> Unit = {},
    onButtonBackPressed: () -> Unit,
) {
    val ctx = LocalConfiguration.current
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
        CardBookingConfirmed(
            patientComplaint = confirmOrderDoctorUIState.data.patientComplain,
            consultationSchedule = confirmOrderDoctorUIState.data.consultationSchedule,
            onRescheduleButtonClicked = onRescheduleClicked
        )
        Row(horizontalArrangement = Arrangement.spacedBy(31.dp.from(ctx))) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
            ) {
                CardNeedOurHelpOrder(onButtonCallClicked)
                CardInsuranceProgramOrder()
            }
            Box(modifier = Modifier.weight(1f)) {
                CardPaymentSummary(
                    totalPayment = confirmOrderDoctorUIState.data.totalPayment, listPayment = confirmOrderDoctorUIState.data.listPaymentPatient
                )
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onButtonNextPressed() },
            contentPadding = PaddingValues(vertical = 16.dp.from(ctx)),
            shape = RoundedCornerShape(4.dp.from(ctx)),
            colors = ButtonDefaults.buttonColors(MaterialThemeCexup.colors.color.primary.primaryMain),
        ) {
            Text(
                text = stringResource(id = R.string.next),
                style = MaterialThemeCexup.typography.textButton1.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialThemeCexup.colors.palette.neutral.neutral1
            )
        }
    }
}