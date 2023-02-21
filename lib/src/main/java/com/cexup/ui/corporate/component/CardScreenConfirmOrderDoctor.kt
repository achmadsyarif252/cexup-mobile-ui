package com.cexup.ui.corporate.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from

@Composable
fun CardBookingConfirmed(
    patientComplaint: String,
    consultationSchedule: String,
    onRescheduleButtonClicked: () -> Unit,
) {
    val ctx = LocalConfiguration.current
    Card(
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(6.dp.from(ctx))
    ) {
        Column(
            modifier = Modifier.padding(16.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp.from(ctx))
                        .clip(CircleShape)
                        .background(MaterialThemeCexup.colors.color.success.successMain),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_checkmark),
                        contentDescription = "Icon Checklist",
                        tint = Color.White
                    )
                }
                Text(
                    text = stringResource(id = R.string.booking_confirmed),
                    style = MaterialThemeCexup.typography.hh1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialThemeCexup.colors.color.success.successMain
                )
            }
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp.from(ctx),
                color = MaterialThemeCexup.colors.color.borderline.borderline3,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp.from(ctx)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp.from(ctx))
                        .clip(CircleShape)
                        .background(MaterialThemeCexup.colors.palette.grey.grey6)
                )
                Text(
                    text = patientComplaint,
                    style = MaterialThemeCexup.typography.hh2,
                    color = MaterialThemeCexup.colors.color.text.textMain
                )
            }
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp.from(ctx),
                color = MaterialThemeCexup.colors.color.borderline.borderline3,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.consultation_schedule_will_take_place_on),
                        style = MaterialThemeCexup.typography.hh4,
                        color = MaterialThemeCexup.colors.color.text.textSecondary
                    )
                    Text(
                        text = consultationSchedule,
                        style = MaterialThemeCexup.typography.hh3.copy(fontWeight = FontWeight.Medium),
                        color = MaterialThemeCexup.colors.color.text.textMain
                    )
                }
                OutlinedButton(
                    onClick = { onRescheduleButtonClicked() },
                    contentPadding = PaddingValues(
                        vertical = 12.dp.from(ctx),
                        horizontal = 16.dp.from(ctx)
                    ),
                    shape = RoundedCornerShape(4.dp.from(ctx)),
                    border = BorderStroke(
                        1.dp.from(ctx),
                        MaterialThemeCexup.colors.color.primary.primaryMain
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.re_schedule),
                        style = MaterialThemeCexup.typography.textButton1.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialThemeCexup.colors.color.primary.primaryMain
                    )
                }
            }
        }
    }
}

@Composable
fun CardNeedOurHelpOrder(
    onIconCallClicked: () -> Unit,
) {
    val ctx = LocalConfiguration.current
    Card(
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(6.dp.from(ctx))
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp.from(ctx),
                vertical = 18.25f.dp.from(ctx)
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.need_our_help),
                    style = MaterialThemeCexup.typography.hh3.copy(fontWeight = FontWeight.Medium),
                    color = MaterialThemeCexup.colors.color.text.textMain
                )
                Text(
                    text = stringResource(id = R.string.call_us_in_case_you_face_any_issue),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
            }
            IconButton(onClick = { onIconCallClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_call),
                    contentDescription = "Icon Call",
                    tint = MaterialThemeCexup.colors.color.text.textSecondary
                )
            }
        }
    }
}

@Composable
fun CardInsuranceProgramOrder() {
    val ctx = LocalConfiguration.current
    Card(
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(6.dp.from(ctx))
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp.from(ctx),
                vertical = 19.25f.dp.from(ctx)
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(40.dp.from(ctx)),
                painter = painterResource(id = R.drawable.ic_shield_checklist_fill),
                contentDescription = "Icon Shield Checklist",
                tint = MaterialThemeCexup.colors.palette.tertiary.redTertiary6
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.your_request_is_eligible_for_rsui_insurance_program),
                style = MaterialThemeCexup.typography.hh3.copy(fontWeight = FontWeight.Medium),
                color = MaterialThemeCexup.colors.palette.tertiary.redTertiary6
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_information),
                contentDescription = "Icon Information",
                tint = MaterialThemeCexup.colors.color.text.textSecondary
            )
        }
    }
}

@Composable
fun CardPaymentSummary(
    listPayment: List<Pair<String, String>> = listOf(),
    totalPayment: String
) {
    val ctx = LocalConfiguration.current
    Card(
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(6.dp.from(ctx))
    ) {
        Column(
            modifier = Modifier.padding(16.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
        ) {
            Text(
                text = stringResource(id = R.string.payment_summary),
                style = MaterialThemeCexup.typography.hh1.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialThemeCexup.colors.color.text.textMain
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp.from(ctx),
                color = MaterialThemeCexup.colors.color.borderline.borderline3,
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp.from(ctx))) {
                listPayment.forEach {
                    Row {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = it.first,
                            style = MaterialThemeCexup.typography.hh4,
                            color = MaterialThemeCexup.colors.color.text.textMain
                        )
                        Text(
                            text = it.second,
                            style = MaterialThemeCexup.typography.hh4,
                            color = MaterialThemeCexup.colors.color.text.textMain
                        )
                    }
                }
            }
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp.from(ctx),
                color = MaterialThemeCexup.colors.color.borderline.borderline3,
            )
            Row {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.total),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
                Text(
                    text = totalPayment,
                    style = MaterialThemeCexup.typography.hh3.copy(fontWeight = FontWeight.Medium),
                    color = MaterialThemeCexup.colors.color.text.textMain
                )
            }
        }
    }
}