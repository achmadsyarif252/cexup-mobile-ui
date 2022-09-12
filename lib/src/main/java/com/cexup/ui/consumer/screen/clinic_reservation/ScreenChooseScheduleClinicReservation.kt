package com.cexup.ui.consumer.screen.clinic_reservation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.component.*
import com.cexup.ui.consumer.theme.BlueJade
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.utils.gridItems
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft24
import java.time.LocalDate

data class DetailDoctorClinicReservationUIState(
    var loading: Boolean = true,
    var error: Boolean = false,
    var errorMessage: String = "",

    var doctorName: String = "",
    var doctorSpecialist: String = "",
    var doctorStr: String = "",
    var doctorHospital: String = "",
    var doctorUsername: String = "", //removed soon https://github.com/Cexup-Team/cexup-api/issues/171
    var doctorPriceFormatted: String = "",
    var doctorPrice: Long = 0,
    var hospitalId: String = "",
    var doctorSpecialityId: String = "",
    var doctorImage: String = ""
)

data class ScheduleClinicReservationUIState(
    var loading: Boolean = true,
    var error: Boolean = false,
    var errorMessage: String = "",
    var data: List<Pair<String, String>> = listOf()
)



@Composable
fun ScreenChooseScheduleClinicReservation(
    modifier: Modifier = Modifier,
    detailDoctorState: DetailDoctorClinicReservationUIState = DetailDoctorClinicReservationUIState(),
    scheduleState: ScheduleClinicReservationUIState = ScheduleClinicReservationUIState(),
    onBackPressed: () -> Unit,
    getAvailableTimeDoctor: (LocalDate) -> Unit,
    goNext: (time: Pair<String, String>, date: LocalDate, note: String, price: Long, doctorSpecialityId: String) -> Unit,
) {
    var noteState by remember { mutableStateOf("") }
    val today = LocalDate.now()
    val days by remember { mutableStateOf(getDays(today)) }
    var selectedDate by remember { mutableStateOf(today) }
    var selectedTime by remember { mutableStateOf(Pair("", "")) }

    var selectedTimeHasData by remember { mutableStateOf(false) }
    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            Octicons.ChevronLeft24,
                            contentDescription = "",
                            tint = Color.Gray,
                        )
                    }
                },
                title = {
                    Text(
                        text = "Choose Schedule Doctor",
                        style = MaterialTheme.typography.h2.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                            fontSize = 18.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            letterSpacing = (-0.25).sp.from(ctx)
                        ),
                        textAlign = TextAlign.Center
                    )
                },
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background
            )
        },
        bottomBar = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp.from(ctx)),
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    enabled = selectedTimeHasData,
                    onClick = {

                        goNext(
                            selectedTime,
                            selectedDate,
                            noteState,
                            detailDoctorState.doctorPrice,
                            detailDoctorState.doctorSpecialityId
                        )
                    },
                    colors = ButtonDefaults.buttonColors(BlueJade),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp.from(ctx))
                        .height(48.dp.from(ctx))
                        .clip(
                            RoundedCornerShape(8.dp.from(ctx))
                        )
                ) {
                    Text(
                        text = "Next",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            lineHeight = 20.sp.from(ctx),
                            fontSize = 16.sp.from(ctx),
                            color = Color.White
                        ),
                    )

                }
            }
        },
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(it),
            contentPadding = PaddingValues(16.dp.from(ctx)),
            content = {

                if (!detailDoctorState.loading && !detailDoctorState.error) {
                    item {
                        CardDoctorInformation(
                            doctorImage = detailDoctorState.doctorImage,
                            doctorExperience = "5 years",
                            doctorSpecialist = detailDoctorState.doctorSpecialist,
                            doctorName = detailDoctorState.doctorName,
                        )
                        Spacer(
                            modifier = modifier.height(
                                20.dp.from(ctx)
                            )
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier
                                .padding(top = 10.dp.from(ctx))
                        ) {
                            ChipStr(str = detailDoctorState.doctorStr)
                            Spacer(
                                modifier = modifier.width(
                                    11.dp.from(ctx)
                                )
                            )
                            ChipHospital(hospitalName = detailDoctorState.doctorHospital)

                        }
                    }
                }
                if (detailDoctorState.loading) {
                    item {
                        CardDoctorInformationShimmer()
                    }
                }

                item {
                    Text(
                        text = "My Simptons",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),

                            ),
                        modifier = modifier.padding(
                            vertical = 16.dp.from(ctx)
                        )
                    )
                    OutlinedInput(
                        value = noteState,
                        onChange = {
                            noteState = it
                        },
                        placeholder = "Chest pain"
                    )
                }
                item {
                    ScrollDatePicker(
                        selectedDate = selectedDate,
                        onItemClicked = {
                            selectedDate=it
                            getAvailableTimeDoctor(it)
                        }
                    )
                }
                item {
                    Text(
                        text = "Select Time",
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = modifier.padding(vertical = 16.dp)
                    )
                }
                if (!scheduleState.loading && !scheduleState.error) {
                    gridItems(
                        scheduleState.data.size,
                        columnCount = 3,
                        itemContent = { index ->
                            val data = scheduleState.data[index]
                            Row {
                                ChipTimeAvailableDoctor(
                                    time = data.second,
                                    onSelected = data.second == selectedTime.second,
                                    onClick = {
                                        selectedTime = data
                                        selectedTimeHasData = true
                                    }
                                )
                                Spacer(
                                    modifier = modifier.width(
                                        16.dp.from(ctx)
                                    )
                                )
                            }
                        })
                }
                if (scheduleState.loading) {
                    gridItems(count = 5, columnCount = 3) {
                        ChipTimeAvailableShimmer()
                    }
                }
            })

    }
}

@Preview
@Composable
fun PreviewDetailDoctorCall() {
    ConsumerTheme {
        ScreenChooseScheduleClinicReservation(
            detailDoctorState = DetailDoctorClinicReservationUIState(
                loading = true,
                error = false
            ),
            scheduleState = ScheduleClinicReservationUIState(
                loading = true,
                error = false,
                data = listOf(Pair("Sa", "00:00"))
            ),
            onBackPressed = { /*TODO*/ },
            getAvailableTimeDoctor = {},
            goNext = { _, _, _, _, _ -> }
        )
    }

}