package com.cexup.ui.consumer.screen.clinic_reservation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.consumer.component.CardDetailDoctor
import com.cexup.ui.consumer.component.CardPatientDetail
import com.cexup.ui.consumer.theme.*
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft24

data class DetailOrderUIState(
    var loading: Boolean=true,
    var error: Boolean = false,
    var errorMessage: String="",

    var doctorSpecialist: String="",
    var doctorExperience: String="",
    var doctorImage: String="",
    var doctorName: String="",
    var doctorSlug: String="",
    var doctorHospital: String="",

    var orderId: String="",
    var orderStatus: String="",
    var orderNote: String="",
    var orderDueDate:String = "",
    var orderDueTime:String ="",

    var canStartMeeting: Boolean = false,
    var hasSoap: Boolean=false,

    var patientName: String = "",
    var patientGender: String = "",
    var patientAge: String = "",


    )

@Composable
fun ScreenDetailOrderClinicReservation(
    modifier: Modifier = Modifier,
    orderState: DetailOrderUIState = DetailOrderUIState(),
    onBackPressed: () -> Unit
){
    val ctx = LocalContext.current
    val socrollState = rememberScrollState()
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
                        text = stringResource(id = R.string.detail_order),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h2.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialTheme.colors.onBackground
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
                    .coloredShadow(
                        color = Color.Black.copy(0.05f),
                        alpha = 0.12f,
                        offsetY = -2.dp,
                        shadowRadius = 20.dp.from(ctx),
                        borderRadius = 10.dp.from(ctx)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topEnd = 12.dp.from(ctx),
                            topStart = 12.dp.from(ctx)
                        )
                    )
                    .height(133.dp.from(ctx))
                    .padding(16.dp.from(ctx))
                ,

                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    enabled = orderState.canStartMeeting,
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(10.dp.from(ctx)),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        disabledBackgroundColor = BackgroundButton,
                    ),
                    contentPadding = PaddingValues(vertical = 14.dp.from(ctx), horizontal = 15.dp.from(ctx)),
                    modifier = modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = orderState.orderStatus,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight.Medium,
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.sp,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                }
                TextButton(
                    onClick = {/*TODO*/},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.sp,
                            color = RedLight
                        ),
                        maxLines = 1,
                    )
                }
            }


        },
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(socrollState)
                .padding(horizontal = 16.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.doctor_information),
                    style = MaterialTheme.typography.body2.copy(
                        fontSize = 14.sp.from(ctx),
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.1.sp.from(ctx),
                        color = MaterialTheme.colors.onBackground
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp.from(ctx)))
                if (!orderState.error && !orderState.loading) {
                    CardDetailDoctor(
                        doctorName = orderState.doctorName,
                        speciality = orderState.doctorSpecialist,
                        hospital = orderState.doctorHospital,
                        doctorExperience = orderState.doctorExperience
                    )
                }
                if (orderState.loading) {
                    CircularProgressIndicator()
                }
                if (orderState.error) {
                    Text(text = "Cannot retrieve information!")
                }
            }

            Column {
                Text(
                    text = stringResource(id = R.string.order_information),
                    style = MaterialTheme.typography.body2.copy(
                        fontSize = 14.sp.from(ctx),
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.1.sp.from(ctx),
                        color = MaterialTheme.colors.onBackground
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp.from(ctx)))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = modifier
                            .size(36.dp.from(ctx))
                            .background(
                                color = SecondaryBackGround,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_card),
                            contentDescription = "",
                            modifier = modifier.size(16.67.dp.from(ctx))
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp.from(ctx)))
                    Column {
                        Text(
                            text = stringResource(id = R.string.order_id),
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 10.sp.from(ctx),
                                fontWeight = FontWeight.Normal,
                                lineHeight = 16.sp.from(ctx),
                                letterSpacing = 0.8.sp.from(ctx),
                                color = MaterialTheme.colors.onSecondary
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = orderState.orderId,
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 14.sp.from(ctx),
                                    fontWeight = FontWeight.Normal,
                                    lineHeight = 20.sp.from(ctx),
                                    letterSpacing = 0.1.sp.from(ctx),
                                    color = BlueJade
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Row(
                                modifier = modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_copy),
                                    contentDescription = "",
                                    modifier = modifier
                                        .size(20.dp.from(ctx))
                                        .clickable { }
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp.from(ctx)))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = modifier
                            .size(36.dp.from(ctx))
                            .background(
                                color = SecondaryBackGround,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_history),
                            contentDescription = "",
                            modifier = modifier.size(16.67.dp.from(ctx))
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp.from(ctx)))
                    Column {
                        Text(
                            text = stringResource(id = R.string.status_order),
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 10.sp.from(ctx),
                                fontWeight = FontWeight.Normal,
                                lineHeight = 16.sp.from(ctx),
                                letterSpacing = 0.5.sp.from(ctx),
                                color = MaterialTheme.colors.onSecondary
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = orderState.orderStatus,
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 14.sp.from(ctx),
                                    fontWeight = FontWeight.Medium,
                                    lineHeight = 20.sp.from(ctx),
                                    letterSpacing = 0.1.sp.from(ctx),
                                    color = BlueJade
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp.from(ctx)))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = modifier
                            .size(36.dp.from(ctx))
                            .background(
                                color = SecondaryBackGround,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_history),
                            contentDescription = "",
                            modifier = modifier.size(16.67.dp.from(ctx))
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp.from(ctx)))
                    Column {
                        Text(
                            text = stringResource(id = R.string.due_date),
                            style = MaterialTheme.typography.subtitle2.copy(
                                fontSize = 10.sp.from(ctx),
                                fontWeight = FontWeight(400),
                                lineHeight = 16.sp.from(ctx),
                                letterSpacing = 0.8.sp.from(ctx),
                                color = MaterialTheme.colors.onSecondary
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "${orderState.orderDueDate} ${orderState.orderDueTime}",
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 14.sp.from(ctx),
                                    fontWeight = FontWeight.Medium,
                                    lineHeight = 20.sp.from(ctx),
                                    letterSpacing = 0.1.sp.from(ctx),
                                    color = BlueJade
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            Column {
                Text(
                    text = stringResource(id = R.string.patient_details),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp.from(ctx),
                        fontWeight = FontWeight.Medium,
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.1.sp.from(ctx),
                        color = MaterialTheme.colors.onBackground
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp.from(ctx)))
                CardPatientDetail(
                    namePatient = orderState.patientName,
                    age = orderState.patientAge,
                    gender = orderState.patientGender,
                    simptons = orderState.orderNote,
                )
            }

            Column {
                Text(
                    text = stringResource(id = R.string.payment_detail),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 21.sp,
                        letterSpacing = 0.3.sp,
                        color = MaterialTheme.colors.onBackground
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.consultation),
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp.from(ctx),
                            fontWeight = FontWeight.Normal,
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.1.sp.from(ctx),
                            color = MaterialTheme.colors.onSecondary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Rp. 5000",
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp.from(ctx),
                            fontWeight = FontWeight.Normal,
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.1.sp.from(ctx),
                            color = MaterialTheme.colors.onSecondary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.discount),
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp.from(ctx),
                            fontWeight = FontWeight.Normal,
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.1.sp.from(ctx),
                            color = MaterialTheme.colors.onSecondary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "-",
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp.from(ctx),
                            fontWeight = FontWeight.Normal,
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.1.sp.from(ctx),
                            color = MaterialTheme.colors.onSecondary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.total),
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp.from(ctx),
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.1.sp.from(ctx),
                            color = MaterialTheme.colors.onSecondary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Rp. 5000",
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp.from(ctx),
                            fontWeight = FontWeight.Normal,
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.1.sp.from(ctx),
                            color = MaterialTheme.colors.onSecondary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }

            if (orderState.hasSoap) {
//                SectionSoap()
            }
            Spacer(modifier = Modifier.height(149.dp.from(ctx)))

        }

    }
}

@Preview
@Composable
fun PreviewScreenDetailOrder(){
    ConsumerTheme {
        ScreenDetailOrderClinicReservation(
            onBackPressed = {},
            orderState = DetailOrderUIState(
                orderStatus = "Waiting Meeting",
                orderId = "#CXP-MT61b6f3fc2d7fd",
                orderDueDate = "20 Januari 2022",
                orderDueTime = "20 Hours",
                orderNote = "Test Note",

            )

        )
    }
}