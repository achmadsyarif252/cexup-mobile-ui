package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.component.common.AvatarCexup
import com.cexup.ui.component.common.TextFieldCexup
import com.cexup.ui.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleClick

data class DataConsultationSchedule(
    val date: String,
    val dayName: String,
    val listSession: List<DataConsultationSession>
)

data class DataConsultationSession(
    val sessionName: String,
    val listSession: List<String>,
)

@Composable
fun CardDoctorInformationOrderDoctor(
    doctorName: String,
    doctorSpeciality: String,
    doctorPrice: String,
) {
    val ctx = LocalConfiguration.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
    ) {
        AvatarCexup(
            sizeDummy = 48.dp.from(ctx),
            sizeAvatar = 96.dp.from(ctx),
            thumb = ""
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp.from(ctx))
        ) {
            Text(
                text = doctorName,
                style = MaterialThemeCexup.typography.hh1.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialThemeCexup.colors.color.text.textMain
            )
            Text(
                text = doctorSpeciality,
                style = MaterialThemeCexup.typography.hh3,
                color = MaterialThemeCexup.colors.color.text.textSecondary
            )
            Text(
                text = doctorPrice,
                style = MaterialThemeCexup.typography.hh2.copy(fontWeight = FontWeight.Medium),
                color = MaterialThemeCexup.colors.color.primary.primaryMain
            )
        }
    }
}

@Composable
fun CardDetailDoctorInformationOrderDoctor(
    doctorNoType: String,
    doctorHospital: String,
    doctorExperience: Int,
    doctorRating: String,
    doctorCountRating: Int,
) {
    val ctx = LocalConfiguration.current
    Card(
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(6.dp.from(ctx))
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(vertical = 20.dp.from(ctx), horizontal = 12.dp.from(ctx))
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx))) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shield_checklist),
                        contentDescription = "Icon Shield Check",
                        tint = MaterialThemeCexup.colors.color.primary.primaryMain
                    )
                    Text(
                        text = "STR-$doctorNoType",
                        style = MaterialThemeCexup.typography.hh3,
                        color = MaterialThemeCexup.colors.color.text.textMain
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx))) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pin_health),
                        contentDescription = "Icon Pin Health",
                        tint = MaterialThemeCexup.colors.palette.tertiary.redTertiary6
                    )
                    Text(
                        text = doctorHospital,
                        style = MaterialThemeCexup.typography.hh3,
                        color = MaterialThemeCexup.colors.color.text.textMain
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.5f.dp.from(ctx)),
                color = MaterialThemeCexup.colors.color.borderline.borderline3,
            )
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.experience),
                    style = MaterialThemeCexup.typography.hh3.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = MaterialThemeCexup.colors.color.primary.primaryMain
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp.from(ctx)),
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_bag_health),
                        contentDescription = "Icon Bag Health",
                        tint = MaterialThemeCexup.colors.palette.neutral.neutral8
                    )
                    Text(
                        text = stringResource(
                            id = R.string.count_years_exp,
                            doctorExperience
                        ),
                        style = MaterialThemeCexup.typography.hh4,
                        color = MaterialThemeCexup.colors.color.text.textSecondary
                    )
                    Spacer(modifier = Modifier.width(4.dp.from(ctx)))
                    Icon(
                        modifier = Modifier
                            .size(16.dp.from(ctx))
                            .offset(y = 2.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Icon Star Rating",
                        tint = MaterialThemeCexup.colors.color.warning.warningMain
                    )
                    Text(
                        text = doctorRating,
                        style = MaterialThemeCexup.typography.hh4.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = MaterialThemeCexup.colors.color.text.textMain
                    )
                    Text(
                        text = "(${doctorCountRating})",
                        style = MaterialThemeCexup.typography.hh4,
                        color = MaterialThemeCexup.colors.color.text.textInactive
                    )
                }
            }
        }
    }
}

@Composable
fun CardNextScheduleTeleconsultation(
    nextConsultationDate: String,
    nextConsultationTime: String,
    onRemindeMePressed: () -> Unit,
) {
    val ctx = LocalConfiguration.current
    Column(verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))) {
        Text(
            text = stringResource(id = R.string.next_schedule_teleconsultation),
            style = MaterialThemeCexup.typography.hh2.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = MaterialThemeCexup.colors.color.text.textMain
        )
        Row(
            modifier = Modifier
                .background(
                    MaterialThemeCexup.colors.color.primary.primarySurface,
                    RoundedCornerShape(6.dp.from(ctx))
                )
                .padding(16.dp.from(ctx)),
            horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = nextConsultationDate,
                    style = MaterialThemeCexup.typography.hh3,
                    color = MaterialThemeCexup.colors.color.text.textMain
                )
                Text(
                    text = nextConsultationTime,
                    style = MaterialThemeCexup.typography.hh2.copy(
                        fontWeight = FontWeight.Medium,
                        letterSpacing = -(0.25f).sp.from(ctx)
                    ),
                    color = MaterialThemeCexup.colors.color.text.textMain
                )
            }
            OutlinedButton(
                modifier = Modifier.border(
                    1.dp.from(ctx), MaterialThemeCexup.colors.color.primary.primaryMain,
                    RoundedCornerShape(4.dp.from(ctx))
                ),
                onClick = { onRemindeMePressed() },
                contentPadding = PaddingValues(
                    horizontal = 16.dp.from(ctx),
                    vertical = 12.dp.from(ctx)
                ),
                shape = RoundedCornerShape(4.dp.from(ctx))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "Icon Remind Me",
                    tint = MaterialThemeCexup.colors.color.primary.primaryMain,
                    modifier = Modifier
                        .size(16.dp.from(ctx))
                        .offset(y = -(2).dp.from(ctx))
                )
                Spacer(modifier = Modifier.width(4.dp.from(ctx)))
                Text(
                    text = stringResource(id = R.string.reminder_me),
                    style = MaterialThemeCexup.typography.textButton1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialThemeCexup.colors.color.primary.primaryMain
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CardPatientComplaint(
    valueComplain: String,
    onValueChangeComplain: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val ctx = LocalConfiguration.current
    Column(verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))) {
        Text(
            text = stringResource(id = R.string.patient_complaints),
            style = MaterialThemeCexup.typography.hh2.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialThemeCexup.colors.color.text.textMain
        )
        TextFieldCexup(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp.from(ctx),
                    MaterialThemeCexup.colors.color.borderline.borderline3,
                    RoundedCornerShape(6.dp.from(ctx))
                )
                .heightIn(min = 92.dp.from(ctx)),
            value = valueComplain,
            onValueChange = onValueChangeComplain,
            shape = RoundedCornerShape(6.dp.from(ctx)),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.input_patient_complaints_here),
                    style = MaterialThemeCexup.typography.hh2,
                    color = MaterialThemeCexup.colors.color.text.textInactive
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified,
                backgroundColor = Color.Transparent,
                textColor = MaterialThemeCexup.colors.color.text.textMain
            ),
            innerPaddingValue = PaddingValues(
                horizontal = 12.dp.from(ctx),
                vertical = 16.dp.from(ctx)
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            textStyle = MaterialThemeCexup.typography.hh2
        )
    }
}

@Composable
fun CardChooseConsultationSchedule(
    selected: DataConsultationSchedule,
    listDateConsultationSchedule: List<DataConsultationSchedule> = listOf(),
    onScheduleSelected: (DataConsultationSchedule) -> Unit,
) {
    val ctx = LocalConfiguration.current
    Column(verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))) {
        Text(
            text = stringResource(id = R.string.choose_a_consultation_schedule),
            style = MaterialThemeCexup.typography.hh2.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialThemeCexup.colors.color.text.textMain
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp.from(ctx))) {
            items(listDateConsultationSchedule) {
                Box(
                    modifier = Modifier
                        .width(121.57f.dp.from(ctx))
                        .height(97.dp.from(ctx))
                        .clip(RoundedCornerShape(6.dp.from(ctx)))
                        .selectable(
                            onClick = {
                                onScheduleSelected(it)
                            },
                            selected = selected == it,
                            role = Role.RadioButton,
                            enabled = selected != it
                        )
                        .background(if (selected == it) MaterialThemeCexup.colors.color.primary.primaryMain else Color.Unspecified)
                        .border(
                            1.dp.from(ctx),
                            if (selected == it) Color.Unspecified else MaterialThemeCexup.colors.color.borderline.borderline3,
                            RoundedCornerShape(6.dp.from(ctx))
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp.from(ctx))) {
                        Text(
                            text = it.date,
                            style = MaterialThemeCexup.typography.hh2.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = if (selected == it) Color.White else MaterialThemeCexup.colors.color.text.textMain
                        )
                        Text(
                            text = it.dayName, style = MaterialThemeCexup.typography.hh4,
                            color = if (selected == it) Color.White else MaterialThemeCexup.colors.color.text.textMain
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardSessionSchedule(
    sessionTitle: String,
    selectedSession: String,
    listTimeSession: List<String>,
    onSessionSelected: (String) -> Unit,
) {
    val ctx = LocalConfiguration.current
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))) {
        Row(
            modifier = Modifier.noRippleClick {
                expanded = !expanded
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp.from(ctx))
                    .clip(CircleShape)
                    .background(MaterialThemeCexup.colors.palette.grey.grey6)
            )
            Spacer(modifier = Modifier.width(16.dp.from(ctx)))
            Text(
                text = "$sessionTitle ",
                style = MaterialThemeCexup.typography.hh2.copy(fontWeight = FontWeight.Medium),
                color = MaterialThemeCexup.colors.color.text.textMain
            )
            Text(
                text = stringResource(id = R.string.count_session, listTimeSession.size),
                style = MaterialThemeCexup.typography.hh2.copy(fontWeight = FontWeight.Medium),
                color = MaterialThemeCexup.colors.color.text.textInactive
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.rotate(if (expanded) 270f else 90f),
                painter = painterResource(id = R.drawable.ic_arrow_corporate),
                contentDescription = "Icon Arrow Expand",
                tint = MaterialThemeCexup.colors.color.text.textSecondary
            )
        }
        if (expanded) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx))) {
                listTimeSession.forEach {
                    Box(
                        modifier = Modifier
                            .border(
                                1.dp.from(ctx),
                                if (selectedSession == it) Color.Unspecified else MaterialThemeCexup.colors.palette.grey.grey6,
                                RoundedCornerShape(6.dp.from(ctx))
                            )
                            .clip(RoundedCornerShape(6.dp.from(ctx)))
                            .noRippleClick {
                                onSessionSelected(it)
                            }
                            .background(if (selectedSession == it) MaterialThemeCexup.colors.color.primary.primaryMain else Color.Unspecified)
                            .padding(
                                horizontal = 24.dp.from(ctx),
                                vertical = 8.dp.from(ctx)
                            ),
                    ) {
                        Text(
                            text = it,
                            style = MaterialThemeCexup.typography.hh4.copy(fontWeight = FontWeight.Medium),
                            color = if (selectedSession == it) Color.White else MaterialThemeCexup.colors.color.text.textMain
                        )
                    }
                }
            }
        }
    }
}