package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.component.common.AvatarCexup
import com.cexup.ui.corporate.screen.DataDiagnostic
import com.cexup.ui.corporate.screen.DataDoctorDashboard
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.utils.mediaquery.from
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CardNewPatients(
    valueNewPatients: Int = 0,
    valuePercentRatioPatientBeforeAndToday: String = "-21%",
    valueLastUpdated: String = "19 Jan"
) {
    val ctx = LocalConfiguration.current
    Card(
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(295.5f.dp.from(ctx))
                .padding(16.dp.from(ctx)),

            ) {
            Box(
                modifier = Modifier
                    .size(60.dp.from(ctx))
                    .clip(CircleShape)
                    .background(MaterialThemeCexup.colors.palette.tertiary.redTertiary6),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.size(30.dp.from(ctx)),
                    painter = painterResource(id = R.drawable.ic_patients_corporate),
                    contentDescription = "New Patient icon",
                    tint = MaterialThemeCexup.colors.palette.neutral.neutral1
                )
            }
            Spacer(modifier = Modifier.width(12.dp.from(ctx)))
            Column {
                Text(
                    text = stringResource(id = R.string.new_patients),
                    style = MaterialThemeCexup.typography.hh2.copy(
                        color = MaterialThemeCexup.colors.color.text.textMain,
                        fontWeight = FontWeight.Medium
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = valueNewPatients.toString(),
                        style = MaterialThemeCexup.typography.h6.copy(
                            color = MaterialThemeCexup.colors.color.text.textMain,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Box(
                        modifier = Modifier
                            .width(36.dp.from(ctx))
                            .clip(RoundedCornerShape(8.dp.from(ctx)))
                            .background(
                                if (valuePercentRatioPatientBeforeAndToday.isEmpty()) {
                                    Color.Transparent
                                } else {
                                    if (valuePercentRatioPatientBeforeAndToday.first() == '-')
                                        MaterialThemeCexup.colors.color.danger.dangerSurface
                                    else
                                        MaterialThemeCexup.colors.color.success.successSurface
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = valuePercentRatioPatientBeforeAndToday,
                            style = MaterialThemeCexup.typography.hh5.copy(
                                color =
                                if (valuePercentRatioPatientBeforeAndToday.isEmpty()) {
                                    Color.Transparent
                                } else {
                                    if (valuePercentRatioPatientBeforeAndToday.first() == '-')
                                        MaterialThemeCexup.colors.color.danger.dangerHover
                                    else
                                        MaterialThemeCexup.colors.color.success.successMain
                                },
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.last_updated_at, valueLastUpdated),
                    style = MaterialThemeCexup.typography.hh4.copy(
                        color = MaterialThemeCexup.colors.color.text.textSecondary,
                    )
                )
            }
        }
    }
}

@Composable
fun CardDoctorActive(
    valueDoctorsActive: Int = 0,
    valuePercentRatioDoctorsBeforeAndToday: String = "+5%",
    valueLastUpdated: String = "19 Jan"
) {
    val ctx = LocalConfiguration.current
    Card(
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(295.5f.dp.from(ctx))
                .padding(16.dp.from(ctx)),

            ) {
            Box(
                modifier = Modifier
                    .size(60.dp.from(ctx))
                    .clip(CircleShape)
                    .background(BlueDashboardNew),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.size(30.dp.from(ctx)),
                    painter = painterResource(id = R.drawable.ic_doctor),
                    contentDescription = "Doctors Active icon",
                    tint = MaterialThemeCexup.colors.palette.neutral.neutral1
                )
            }
            Spacer(modifier = Modifier.width(12.dp.from(ctx)))
            Column {
                Text(
                    text = stringResource(id = R.string.doctors_active),
                    style = MaterialThemeCexup.typography.hh2.copy(
                        color = MaterialThemeCexup.colors.color.text.textMain,
                        fontWeight = FontWeight.Medium
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = valueDoctorsActive.toString(),
                        style = MaterialThemeCexup.typography.h6.copy(
                            color = MaterialThemeCexup.colors.color.text.textMain,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Box(
                        modifier = Modifier
                            .width(36.dp.from(ctx))
                            .clip(RoundedCornerShape(8.dp.from(ctx)))
                            .background(
                                if (valuePercentRatioDoctorsBeforeAndToday.isEmpty()) {
                                    Color.Transparent
                                } else {
                                    if (valuePercentRatioDoctorsBeforeAndToday.first() == '-')
                                        MaterialThemeCexup.colors.color.danger.dangerSurface
                                    else
                                        MaterialThemeCexup.colors.color.success.successSurface
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = valuePercentRatioDoctorsBeforeAndToday,
                            style = MaterialThemeCexup.typography.hh5.copy(
                                color =
                                if (valuePercentRatioDoctorsBeforeAndToday.isEmpty()) {
                                    Color.Transparent
                                } else {
                                    if (valuePercentRatioDoctorsBeforeAndToday.first() == '-')
                                        MaterialThemeCexup.colors.color.danger.dangerHover
                                    else
                                        MaterialThemeCexup.colors.color.success.successMain
                                },
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.last_updated_at, valueLastUpdated),
                    style = MaterialThemeCexup.typography.hh4.copy(
                        color = MaterialThemeCexup.colors.color.text.textSecondary,
                    )
                )
            }
        }
    }
}

@Composable
fun CardPatientsDiagnostic(
    listDiagnostic: List<DataDiagnostic> = listOf()
) {
    val ctx = LocalConfiguration.current
    Card(
        elevation = MaterialThemeCexup.elevation.skim,
        modifier = Modifier.width(296.dp.from(ctx)),
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Column(
            modifier = Modifier.padding(16.dp.from(ctx)),
        ) {
            Text(
                text = stringResource(id = R.string.patients_diagnostic),
                style = MaterialThemeCexup.typography.hh2.copy(
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(8.dp.from(ctx)))
            Divider(
                thickness = 1.dp.from(ctx),
                modifier = Modifier.fillMaxWidth(),
                color = MaterialThemeCexup.colors.color.borderline.borderline1
            )
            if (listDiagnostic.isEmpty()){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp.from(ctx)),
                    contentAlignment = Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.no_data),
                        style = MaterialThemeCexup.typography.hh3.copy(
                            color = MaterialThemeCexup.colors.color.text.textMain,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }else {
                listDiagnostic.forEach {
                    Spacer(modifier = Modifier.height(16.dp.from(ctx)))
                    Row {
                        Text(
                            text = it.initialValue.toInt().toString(),
                            style = MaterialThemeCexup.typography.hh4.copy(
                                color = MaterialThemeCexup.colors.color.text.textMain,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Text(
                            text = " dari ",
                            style = MaterialThemeCexup.typography.hh4.copy(
                                color = MaterialThemeCexup.colors.color.text.textMain,
                            )
                        )
                        Text(
                            text = it.maxValue.toInt().toString(),
                            style = MaterialThemeCexup.typography.hh4.copy(
                                color = MaterialThemeCexup.colors.color.text.textMain,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = it.diagnosticName,
                            style = MaterialThemeCexup.typography.hh2.copy(
                                color = MaterialThemeCexup.colors.color.text.textMain,
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp.from(ctx)))
                    ProgressBarDiagnostic(
                        initialValue = it.initialValue,
                        maxValue = it.maxValue,
                        colorProgressBar = it.colorProgressBar
                    )
                }
            }

        }
    }
}

@Composable
fun CardLastConsultationDoctor(
    doctorName: String = "Doctor Name",
    doctorSpeciality: String = "Ilmu Kesehatan Anak",
    dateLastConsultation: String = "Kamis, 6 Maret 2022 14:30 - 15.00 WIB"
) {
    val ctx = LocalConfiguration.current
    Card(
        modifier = Modifier.width(296.dp.from(ctx)),
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {

        Column(
            modifier = Modifier.padding(12.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(8.dp.from(ctx))
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 4.dp.from(ctx)),
                text = stringResource(id = R.string.last_consultation_doctor),
                style = MaterialThemeCexup.typography.hh2.copy(
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 4.dp.from(ctx))
                    .fillMaxWidth(),
                thickness = 1.dp.from(ctx),
                color = MaterialThemeCexup.colors.color.borderline.borderline1
            )
            if (doctorName.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp.from(ctx)),
                    contentAlignment = Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.no_consultation_doctor),
                        style = MaterialThemeCexup.typography.hh3.copy(
                            color = MaterialThemeCexup.colors.color.text.textMain,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            } else {
                Row(
                    modifier = Modifier.padding(horizontal = 4.dp.from(ctx)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box {

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(38.dp.from(ctx))
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            BlueDarkJade,
                                            BlueLightJade
                                        )
                                    )
                                ),
                        ) {

                            CoilImage(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(17.dp.from(ctx)),
                                imageModel = painterResource(id = R.drawable.ic_profile_dummy),
                                // Crop, Fit, Inside, FillHeight, FillWidth, None
                                contentScale = ContentScale.Crop,
                                // shows an image with a circular revealed animation.
                                circularReveal = CircularReveal(duration = 250),
                                // shows a placeholder ImageBitmap when loading.
                                placeHolder = painterResource(id = R.drawable.ic_profile_dummy),
                                // shows an error ImageBitmap when the request failed.
                                error = painterResource(id = R.drawable.ic_profile_dummy)
                            )

                        }
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(12.dp.from(ctx))
                                .background(
                                    MaterialThemeCexup.colors.color.success.successMain,
                                    CircleShape
                                )
                                .border(
                                    2.dp.from(ctx),
                                    MaterialThemeCexup.colors.palette.neutral.neutral1,
                                    CircleShape
                                ),

                            )
                    }
                    Spacer(modifier = Modifier.width(12.dp.from(ctx)))
                    Column {
                        Text(
                            text = doctorName,
                            style = MaterialThemeCexup.typography.hh4.copy(
                                color = MaterialThemeCexup.colors.color.text.textMain,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Text(
                            text = doctorSpeciality,
                            style = MaterialThemeCexup.typography.hh5.copy(
                                color = MaterialThemeCexup.colors.color.text.textSecondary,
                            )
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .background(
                            MaterialThemeCexup.colors.color.primary.primarySurface,
                            RoundedCornerShape(4.dp.from(ctx))
                        )
                        .padding(horizontal = 13.5f.dp.from(ctx), vertical = 9.5f.dp.from(ctx)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp.from(ctx))
                ) {
                    Icon(
                        modifier = Modifier.size(13.33f.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_clock),
                        contentDescription = "icon clock",
                        tint = MaterialThemeCexup.colors.color.primary.primaryMain
                    )
                    Text(
                        text = dateLastConsultation,
                        style = MaterialThemeCexup.typography.hh5.copy(
                            color = MaterialThemeCexup.colors.color.primary.primaryMain,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }

        }
    }
}

@Composable
fun CardActiveDoctorsList(
    sizeData: Int = 6,
    listDoctor: List<DataDoctorDashboard> = listOf(),
    currentPage: Int = 1,
    onBackPressed: () -> Unit,
    onNextPressed: () -> Unit,
) {
    val ctx = LocalConfiguration.current
    Card(
        modifier = Modifier.width(295.dp.from(ctx)),
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Column(
            modifier = Modifier.padding(16.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(8.dp.from(ctx))
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 4.dp.from(ctx)),
                text = stringResource(id = R.string.active_doctors_list),
                style = MaterialThemeCexup.typography.hh2.copy(
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 4.dp.from(ctx))
                    .fillMaxWidth(),
                thickness = 1.dp.from(ctx),
                color = MaterialThemeCexup.colors.color.borderline.borderline1
            )
            if (listDoctor.isEmpty()) {
                Spacer(modifier = Modifier.height(8.dp.from(ctx)))
                Box(
                    modifier = Modifier
                        .size(250.dp.from(ctx)),
                    contentAlignment = Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.no_doctor_available),
                        style = MaterialThemeCexup.typography.hh3.copy(
                            color = MaterialThemeCexup.colors.color.text.textMain,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            } else {
                listDoctor.forEach {
                    Spacer(modifier = Modifier.height(8.dp.from(ctx)))
                    Row(
                        modifier = Modifier.padding(horizontal = 4.dp.from(ctx)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AvatarCexup(isWithStatusOnline = true)
                        Spacer(modifier = Modifier.width(12.dp.from(ctx)))
                        Column {
                            Text(
                                text = it.doctorName,
                                style = MaterialThemeCexup.typography.hh4.copy(
                                    color = MaterialThemeCexup.colors.color.text.textMain,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Text(
                                text = it.doctorSpeciality,
                                style = MaterialThemeCexup.typography.hh5.copy(
                                    color = MaterialThemeCexup.colors.color.text.textSecondary,
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp.from(ctx)))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(18.dp.from(ctx)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(24.dp.from(ctx))
                            .clickable(enabled = currentPage > 1) {
                                onBackPressed()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp.from(ctx)))
                                .rotate(180f)
                                .width(6.dp.from(ctx))
                                .height(10.dp.from(ctx)),
                            painter = painterResource(id = R.drawable.ic_arrow_bottomsheet),
                            contentDescription = "arrow back pager list doctor",
                            tint = if (currentPage > 1) MaterialThemeCexup.colors.color.primary.primaryMain else MaterialThemeCexup.colors.color.text.textInactive
                        )
                    }
                    Text(
                        text = currentPage.toString(),
                        style = MaterialThemeCexup.typography.hh3.copy(
                            color = MaterialThemeCexup.colors.color.text.textSecondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp.from(ctx)))
                            .size(24.dp.from(ctx))
                            .clickable(enabled = currentPage.toFloat() <= sizeData.toFloat() / 3f) {
                                onNextPressed()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .width(6.dp.from(ctx))
                                .height(10.dp.from(ctx)),
                            painter = painterResource(id = R.drawable.ic_arrow_bottomsheet),
                            contentDescription = "arrow next pager list doctor",
                            tint = if (currentPage.toFloat() <= sizeData.toFloat() / 3f) MaterialThemeCexup.colors.color.text.textInactive else MaterialThemeCexup.colors.color.primary.primaryMain
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardChartPatients(
    totalPatients: Int = 0,
    dataPieChart: List<PieChartData> = listOf()
) {
    val ctx = LocalConfiguration.current
    Card(
        modifier = Modifier.width(296.dp.from(ctx)),
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(8.dp.from(ctx))
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp.from(ctx)),
                text = stringResource(id = R.string.corporate_menu_patients),
                style = MaterialThemeCexup.typography.hh2.copy(
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Divider(
                modifier = Modifier
                    .padding(horizontal = 20.dp.from(ctx))
                    .fillMaxWidth(),
                thickness = 1.dp.from(ctx),
                color = MaterialThemeCexup.colors.color.borderline.borderline1
            )
            Column(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .size(260.dp.from(ctx))
            ) {
                if (dataPieChart.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp.from(ctx))
                            .size(260.dp.from(ctx)),
                        contentAlignment = Center
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.no_data),
                            style = MaterialThemeCexup.typography.hh3.copy(
                                color = MaterialThemeCexup.colors.color.text.textMain,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                } else {
                    PieChartDashboard(
                        totalPatients = totalPatients,
                        dataPieChart = dataPieChart
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCardDashboard() {
    CexupTheme {
        Column {
            CardNewPatients(

            )
        }
    }
}