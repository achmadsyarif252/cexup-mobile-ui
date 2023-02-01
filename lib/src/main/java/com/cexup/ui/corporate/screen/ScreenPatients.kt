package com.cexup.ui.corporate.screen

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleClick
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage


data class ScreenPatientsUIState(
    val error: Boolean = false,
    val loading: Boolean = true,
    val message: String = "",
    val data: DataScreenPatients = DataScreenPatients()
)

data class DataScreenPatients(
    val totalPatients: Int = 0,
    val currentPage: Int = 1,
    val listDataPatients: List<DataPatients> = listOf()
)

data class DataPatients(
    val patientsName: String = "",
    val patientsThumb: String = "",
    val patientsPhone: String = "",
    val patientsAge: Int = 1,
    val patientsDiseases: String = "",
    val patientsUserCode: String = "",
)

@Composable
fun ScreenPatients(
    patientsUIState: ScreenPatientsUIState = ScreenPatientsUIState(),
    onPatientNameSort: () -> Unit = {},
    onPhoneSort: () -> Unit = {},
    onAgeSort: () -> Unit = {},
    onDiseasesSort: () -> Unit = {},
    onAddPatients: () -> Unit = {},
    onRowPatientsClicked: (userCode: String) -> Unit = {},
    onDetailsClicked: (userCode: String) -> Unit = {},
    onGetPatient:(Boolean) -> Unit = {},
) {
    val ctx = LocalContext.current

    fun onNext() {
        if (patientsUIState.data.listDataPatients.isEmpty()) {
            Toast.makeText(ctx, "Latest page!", Toast.LENGTH_LONG).show()
        } else {
            onGetPatient(true)
        }
    }

    fun onPrev() {
        if (patientsUIState.data.currentPage > 1) {
            onGetPatient(false)
        } else {
            Toast.makeText(ctx, "First page!", Toast.LENGTH_LONG).show()
        }
    }
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .padding(top = 25.dp.from(ctx),bottom = 27.dp.from(ctx))
            .padding(horizontal = 28.dp.from(ctx)),
        verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.corporate_menu_patients),
                style = MaterialThemeCexup.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.width(8.dp.from(ctx)))
            Box(
                modifier = Modifier
                    .width(29.dp.from(ctx))
                    .height(16.dp.from(ctx))
                    .clip(RoundedCornerShape(8.dp.from(ctx)))
                    .background(MaterialThemeCexup.colors.color.primary.primaryMain),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = patientsUIState.data.totalPatients.toString(),
                    style = MaterialThemeCexup.typography.hh6.copy(
                        color = MaterialThemeCexup.colors.palette.grey.grey1
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(
                    horizontal = 12.dp.from(ctx),
                    vertical = 8.dp.from(ctx)
                ),
                modifier = Modifier
                    .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
                shape = RoundedCornerShape(4.dp.from(ctx)),
                border = BorderStroke(
                    1.dp.from(ctx),
                    MaterialThemeCexup.colors.color.primary.primaryMain
                )
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp.from(ctx))) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = "Icon Filter Date",
                        tint = MaterialThemeCexup.colors.color.primary.primaryMain
                    )
                    Text(
                        text = stringResource(id = R.string.filter_date),
                        style = MaterialThemeCexup.typography.textButton2.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialThemeCexup.colors.color.primary.primaryMain
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp.from(ctx)))
            Button(
                contentPadding = PaddingValues(
                    horizontal = 12.dp.from(ctx),
                    vertical = 8.dp.from(ctx)
                ),
                modifier = Modifier
                    .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
                onClick = { onAddPatients() },
                shape = RoundedCornerShape(4.dp.from(ctx)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialThemeCexup.colors.color.primary.primaryMain,
                )
            ) {
                Text(
                    text = stringResource(id = R.string.corporate_add_patient),
                    style = MaterialThemeCexup.typography.textButton2.copy(
                        color = MaterialThemeCexup.colors.palette.neutral.neutral1,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
        Card(
            elevation = MaterialThemeCexup.elevation.skim,
            shape = RoundedCornerShape(16.dp.from(ctx))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx)),
                modifier = Modifier.padding(bottom = 16.dp.from(ctx))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = 16.dp.from(ctx),
                                topEnd = 16.dp.from(ctx)
                            )
                        )
                        .background(MaterialThemeCexup.colors.color.primary.primarySurface),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 17.5f.dp.from(ctx), vertical = 16.dp.from(ctx)),
                        text = stringResource(id = R.string.no),
                        style = MaterialThemeCexup.typography.hh2.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialThemeCexup.colors.color.text.textMain,
                            textAlign = TextAlign.Center
                        )
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
                        modifier = Modifier
                            .noRippleClick {
                                onPatientNameSort()
                            }
                            .padding(horizontal = 16.5f.dp.from(ctx), vertical = 16.dp.from(ctx)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.patient_name),
                            style = MaterialThemeCexup.typography.hh2.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialThemeCexup.colors.color.text.textMain,
                                textAlign = TextAlign.Center
                            )
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = "Icon Sort Patient Name",
                            tint = MaterialThemeCexup.colors.palette.neutral.neutral8
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
                        modifier = Modifier
                            .noRippleClick {
                                onPhoneSort()
                            }
                            .padding(horizontal = 32.12f.dp.from(ctx), vertical = 16.dp.from(ctx)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.phone),
                            style = MaterialThemeCexup.typography.hh2.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialThemeCexup.colors.color.text.textMain,
                                textAlign = TextAlign.Center
                            )
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = "Icon Sort Patient Name",
                            tint = MaterialThemeCexup.colors.palette.neutral.neutral8
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
                        modifier = Modifier
                            .noRippleClick {
                                onAgeSort()
                            }
                            .padding(horizontal = 42.63f.dp.from(ctx), vertical = 16.dp.from(ctx)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.age),
                            style = MaterialThemeCexup.typography.hh2.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialThemeCexup.colors.color.text.textMain,
                                textAlign = TextAlign.Center
                            )
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = "Icon Sort Patient Name",
                            tint = MaterialThemeCexup.colors.palette.neutral.neutral8
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
                        modifier = Modifier
                            .noRippleClick {
                                onDiseasesSort()
                            }
                            .padding(horizontal = 20.13f.dp.from(ctx), vertical = 16.dp.from(ctx)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.diseases),
                            style = MaterialThemeCexup.typography.hh2.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialThemeCexup.colors.color.text.textMain,
                                textAlign = TextAlign.Center
                            )
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = "Icon Sort Patient Name",
                            tint = MaterialThemeCexup.colors.palette.neutral.neutral8
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 38.62f.dp.from(ctx), vertical = 16.dp.from(ctx)),
                        text = stringResource(id = R.string.actions),
                        style = MaterialThemeCexup.typography.hh2.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialThemeCexup.colors.color.text.textMain,
                            textAlign = TextAlign.Center
                        )
                    )

                }
                patientsUIState.data.listDataPatients.forEachIndexed { index, dataPatients ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp.from(ctx))
                            .clickable {
                                onRowPatientsClicked(dataPatients.patientsUserCode)
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.width(60.dp.from(ctx)),
                            text = ((index + 1) + (5 * (patientsUIState.data.currentPage - 1))).toString(),
                            style = MaterialThemeCexup.typography.hh2.copy(
                                fontWeight = FontWeight.Medium,
                                color = Neutral70,
                                textAlign = TextAlign.Center
                            )
                        )
                        Row(
                            modifier = Modifier
                                .width(175.dp.from(ctx))
                                .padding(horizontal = 16.dp.from(ctx), vertical = 8.dp.from(ctx)),
                            horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx))
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp.from(ctx)))
                                    .size(32.dp.from(ctx))
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
                                        .size(18.dp.from(ctx)),
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
                            Text(
                                text = dataPatients.patientsName,
                                style = MaterialThemeCexup.typography.hh3.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = Neutral100,
                                    textAlign = TextAlign.Center,
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Text(
                            modifier = Modifier
                                .width(148.dp.from(ctx)),
                            text = dataPatients.patientsPhone,
                            style = MaterialThemeCexup.typography.hh3.copy(
                                fontWeight = FontWeight.Normal,
                                color = Neutral70,
                                textAlign = TextAlign.Center
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier
                                .width(148.dp.from(ctx)),
                            text = stringResource(
                                id = R.string.years_old,
                                dataPatients.patientsAge
                            ),
                            style = MaterialThemeCexup.typography.hh3.copy(
                                fontWeight = FontWeight.Normal,
                                color = Neutral70,
                                textAlign = TextAlign.Center
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier
                                .width(148.dp.from(ctx)),
                            text = dataPatients.patientsDiseases,
                            style = MaterialThemeCexup.typography.hh3.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = Neutral70,
                                textAlign = TextAlign.Center
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier
                                .width(148.dp.from(ctx))
                                .clickable {
                                    onDetailsClicked(dataPatients.patientsUserCode)
                                }
                                .padding(vertical = 16.dp.from(ctx)),
                            text = stringResource(id = R.string.details),
                            style = MaterialThemeCexup.typography.hh3.copy(
                                fontWeight = FontWeight.Normal,
                                color = MaterialThemeCexup.colors.color.primary.primaryMain,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp.from(ctx)),
            verticalAlignment = CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier.size(16.dp.from(ctx)),
                onClick = { onPrev() }
            ) {
                Icon(
                    modifier = Modifier
                        .rotate(180f)
                        .width(5.75f.dp.from(ctx))
                        .height(10.dp.from(ctx)),
                    painter = painterResource(id = R.drawable.ic_arrow_bottomsheet),
                    contentDescription = "Icon Back Page",
                    tint = MaterialThemeCexup.colors.color.text.textInactive
                )
            }
            Box(
                modifier = Modifier
                    .size(24.dp.from(ctx))
                    .clip(CircleShape)
                    .background(MaterialThemeCexup.colors.color.primary.primaryMain),
                contentAlignment = Center
            ) {
                Text(
                    text = patientsUIState.data.currentPage.toString(),
                    fontSize = 12.sp.from(ctx),
                    lineHeight = 14.sp.from(ctx),
                    fontWeight = FontWeight.Normal,
                    color = MaterialThemeCexup.colors.palette.neutral.neutral1
                )
            }
            IconButton(
                modifier = Modifier.size(16.dp.from(ctx)),
                onClick = { onNext() }
            ) {
                Icon(
                    modifier = Modifier
                        .width(5.75f.dp.from(ctx))
                        .height(10.dp.from(ctx)),
                    painter = painterResource(id = R.drawable.ic_arrow_bottomsheet),
                    contentDescription = "Icon Next Page",
                    tint = MaterialThemeCexup.colors.color.text.textInactive
                )
            }
        }
    }
}