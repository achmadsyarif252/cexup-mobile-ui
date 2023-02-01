@file:OptIn(ExperimentalMaterialApi::class)

package com.cexup.ui.corporate.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.R
import com.cexup.ui.corporate.screen.DataUSG
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from

@Composable
fun CardPatientUSG(
    modifier: Modifier = Modifier,
    patientThumb: String = "",
    patientName: String = "",
    patientGender: String = "",
    patientAge: Int = 17,
    gestationalAge: String = "",
    patientEws: String = "Normal",
    onSeeProfileClicked: () -> Unit = {},
    onUSGExaminationClicked: () -> Unit = {},
){
    val ctx = LocalContext.current
    Card(
        modifier = Modifier
            .widthIn(max = 479.dp.from(ctx)),
        elevation = 2.dp.from(ctx),
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(
                    vertical = 12.dp.from(ctx),
                    horizontal = 16.dp.from(ctx)
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
            ) {
                Image(
                    painter = rememberImagePainter(data = patientThumb, builder = {
                        crossfade(true)
                        placeholder(R.drawable.dummy_user_profile)
                        error(R.drawable.dummy_user_profile)
                    }), contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .clip(RoundedCornerShape(8.dp.from(ctx)))
                        .coloredShadow(MaterialTheme.colors.primary)
                        .size(97.dp.from(ctx))
                        .align(Alignment.CenterVertically)
                )
                Column {
                    Text(
                        text = patientName.ifEmpty { "-" },
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp.from(ctx),
                            lineHeight = 32.sp.from(ctx),
                            color = Natural
                        )
                    )
                    Text(
                        text =
                        if (patientGender.isNotEmpty()&& patientAge != 0 || patientAge != null)
                            stringResource(id = R.string.gender_years_old, patientGender, patientAge)
                        else
                            "-",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            color = Natural80.copy(alpha = 0.8f)
                        )
                    )
                    Text(
                        text = gestationalAge.ifEmpty { "-" },
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            color = Natural80.copy(alpha = 0.8f)
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp.from(ctx)))
                        .background(BlueUSG, RoundedCornerShape(10.dp.from(ctx)))
                        .width(85.dp.from(ctx))
                        .height(36.dp.from(ctx))
                ) {
                    Text(
                        text = patientEws.ifEmpty { "-" },
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.3f.sp.from(ctx),
                        color = BlueJade
                    )
                }
            }
            Divider(thickness = 1.dp.from(ctx), color = GrayDivider)
            Row {
                CompositionLocalProvider(
                    LocalMinimumTouchTargetEnforcement provides false,
                ) {
                    OutlinedButton(
                        onClick = { onSeeProfileClicked() },
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp.from(ctx)))
                            .weight(0.5f),
                        border = BorderStroke(1.5f.dp.from(ctx), BlueJade)
                    ) {
                        Text(
                            text = stringResource(id = R.string.see_profile),
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp.from(ctx),
                                lineHeight = 24.sp.from(ctx),
                                color = BlueJade
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp.from(ctx)))
                CompositionLocalProvider(
                        LocalMinimumTouchTargetEnforcement provides false,
                ) {
                    Button(
                        onClick = { onUSGExaminationClicked() },
                        modifier = Modifier.weight(0.5f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = BlueJade,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.usg_examination),
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp.from(ctx),
                                lineHeight = 24.sp.from(ctx),
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardStatusPatientUSG(
    date: String = "30 Apr - 11:00",
    bloodPressureValue: String = "120/80",
    heartRateValue:String = "105",
    temperatureValue: String = "37,6",
    weightValue: String = "75",
    isFirstData: Boolean = false,
    isLastData:Boolean = true,
    onCalendarClicked: () -> Unit = {},
    onPreviousHistoryClicked: () -> Unit = {},
    onNextHistoryClicked: () -> Unit = {},
    onDetailsClicked: () -> Unit = {},
    onFullHistoryClicked: () -> Unit = {},
){
    val interactionSource = remember { MutableInteractionSource() }
    val ctx = LocalContext.current
    Card(
        modifier = Modifier.widthIn(max = 479.dp.from(ctx)),
        elevation = 2.dp.from(ctx),
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(
                    vertical = 12.dp.from(ctx),
                    horizontal = 16.dp.from(ctx)
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.last_checkup) +":",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 28.sp.from(ctx),
                        color = Natural
                    ),
                )
                Image(
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null) {
                          onCalendarClicked()
                    },
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = ""
                )
                Text(
                    text = stringResource(id = R.string.wib,date.ifEmpty { "-" }),
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontSize = 14.sp.from(ctx),
                        lineHeight = 22.sp.from(ctx),
                        color = Natural
                    ),
                )
                Spacer(modifier = Modifier.weight(1f))
                CompositionLocalProvider(
                    LocalMinimumTouchTargetEnforcement provides false,
                ) {
                    IconButton(
                        onClick = { onPreviousHistoryClicked() },
                        enabled = !isLastData
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_calender),
                            contentDescription = "Previous Date",
                            tint = if (!isLastData)
                                BlueJade
                            else
                                GrayDivider
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp.from(ctx)))
                CompositionLocalProvider(
                    LocalMinimumTouchTargetEnforcement provides false,
                ) {
                    IconButton(
                        onClick = { onNextHistoryClicked() },
                        enabled = !isFirstData
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_next_calender),
                            contentDescription = "Previous Date",
                            tint = if (!isFirstData)
                                BlueJade
                            else
                                GrayDivider
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlueUSG, RoundedCornerShape(4.dp.from(ctx)))
                    .padding(8.dp.from(ctx)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.blood_pressure),
                        fontSize = 12.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = Natural80.copy(alpha = 0.8f)
                    )
                    Text(
                        text = bloodPressureValue,
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp.from(ctx),
                            lineHeight = 32.sp.from(ctx),
                            color = Natural
                        ),
                    )
                }
                Column {
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_heart_rate),
                        fontSize = 12.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = Natural80.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "$heartRateValue bpm",
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp.from(ctx),
                            lineHeight = 32.sp.from(ctx),
                            color = Natural
                        ),
                    )
                }
                Column {
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_temperature),
                        fontSize = 12.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = Natural80.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "$temperatureValue C",
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp.from(ctx),
                            lineHeight = 32.sp.from(ctx),
                            color = Natural
                        ),
                    )
                }
                Column {
                    Text(
                        text = stringResource(id = R.string.corporate_measurement_weight),
                        fontSize = 12.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = Natural80.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "$weightValue kg",
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp.from(ctx),
                            lineHeight = 32.sp.from(ctx),
                            color = Natural
                        ),
                    )
                }
            }
            Divider(thickness = 1.dp.from(ctx), color = GrayDivider)
            Row {
                CompositionLocalProvider(
                    LocalMinimumTouchTargetEnforcement provides false,
                ) {
                    TextButton(
                        contentPadding = PaddingValues(),
                        modifier = Modifier.weight(0.5f),
                        onClick = { onDetailsClicked() }
                    ) {
                        Text(
                            text = stringResource(id = R.string.details),
                            style = MaterialTheme.typography.h2.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp.from(ctx),
                                lineHeight = 28.sp.from(ctx),
                                letterSpacing = -0.25f.sp.from(ctx),
                                color = BlueJade
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp.from(ctx)))
                CompositionLocalProvider(
                    LocalMinimumTouchTargetEnforcement provides false,
                ) {
                    TextButton(
                        contentPadding = PaddingValues(),
                        modifier = Modifier.weight(0.5f),
                        onClick = { onFullHistoryClicked() }
                    ) {
                        Text(
                            text = stringResource(id = R.string.full_history),
                            style = MaterialTheme.typography.h2.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 18.sp.from(ctx),
                                lineHeight = 28.sp.from(ctx),
                                letterSpacing = -0.25f.sp.from(ctx),
                                color = BlueJade
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardListDataUSG(
    listHistoryExaminationUSG: List<DataUSG> = listOf(),
    onThreeDotClicked: (id: Long) -> Unit = {},
    onFolderClicked: (id: Long) -> Unit = {},
    onDownloadClicked: (id: Long) -> Unit = {},
){
    val ctx = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 2.dp.from(ctx),
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Column(
            modifier = Modifier.padding(16.dp.from(ctx)),
            verticalArrangement = Arrangement.SpaceBetween,
        ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = 16.dp.from(ctx),
                                topEnd = 16.dp.from(ctx)
                            )
                        )
                        .background(BlueUSG)
                        .padding(vertical = 16.dp.from(ctx)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(60.dp.from(ctx)),
                        text = stringResource(id = R.string.no),
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            color = Natural80.copy(alpha = 0.8f)
                        ),
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier.width(258.33f.dp.from(ctx)),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.date_examination),
                            style = MaterialTheme.typography.subtitle1.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp.from(ctx),
                                lineHeight = 28.sp.from(ctx),
                                color = Natural80.copy(alpha = 0.8f)
                            )
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = "Sort Date Examination",
                            modifier = Modifier.clickable {
                                /*TODO*/
                            }
                        )
                    }
                    Row(
                        modifier = Modifier.width(258.33f.dp.from(ctx)),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.gestational_age),
                            style = MaterialTheme.typography.subtitle1.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp.from(ctx),
                                lineHeight = 28.sp.from(ctx),
                                color = Natural80.copy(alpha = 0.8f)
                            )
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = "Sort Gestational Age",
                            modifier = Modifier.clickable {
                                /*TODO*/
                            }
                        )
                    }
                    Text(
                        modifier = Modifier.width(258.33f.dp.from(ctx)),
                        text = stringResource(id = R.string.action),
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            color = Natural80.copy(alpha = 0.8f)
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            listHistoryExaminationUSG.forEachIndexed { index, item ->
                val nomor = index + 1
                Row(
                    modifier = Modifier
                        .padding(vertical = 16.dp.from(ctx))
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(60.dp.from(ctx)),
                        text = "$nomor",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            color = Natural80.copy(alpha = 0.8f)
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.width(258.33f.dp.from(ctx)),
                        text = item.date?:"",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            color = Natural80.copy(alpha = 0.8f)
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.width(258.33f.dp.from(ctx)),
                        text = item.gestationalAge?:"",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            color = Natural80.copy(alpha = 0.8f)
                        ),
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier
                            .width(258.33f.dp.from(ctx))
                            .padding(horizontal = 50.17f.dp.from(ctx)),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            modifier = Modifier.clickable { onDownloadClicked(item.idData?: 0L) },
                            painter = painterResource(id = R.drawable.ic_download),
                            contentDescription = "USG Download File $nomor",
                            tint = BlueJade
                        )
                        Icon(
                            modifier = Modifier.clickable { onFolderClicked(item.idData?: 0L) },
                            painter = painterResource(id = R.drawable.ic_folder),
                            contentDescription = "USG Download File $nomor",
                            tint = BlueJade
                        )
                        Icon(
                            modifier = Modifier.clickable { onThreeDotClicked(item.idData?: 0L) },
                            painter = painterResource(id = R.drawable.ic_threedot_with_circle),
                            contentDescription = "USG Download File $nomor",
                            tint = BlueJade
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Tablet", device = Devices.TABLET)
@Composable
fun PreviewCardUSG(){
    CexupTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            CardPatientUSG()
            CardStatusPatientUSG()
            CardListDataUSG(
                listHistoryExaminationUSG = listOf(
                    DataUSG(
                        date = "22/03/2020 12:34 AM",
                        gestationalAge = "Week 13 - 1st trimester",
                        idData = 1,
                    )
                )
            )
        }
    }
}