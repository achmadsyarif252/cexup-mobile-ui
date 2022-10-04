package com.cexup.ui.consumer.screen.clinic_reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.consumer.component.BottomBarPayment
import com.cexup.ui.consumer.component.BottomBarPaymentShimmer
import com.cexup.ui.consumer.component.CardDoctorInformation
import com.cexup.ui.consumer.component.CardDoctorInformationShimmer
import com.cexup.ui.consumer.theme.BlueJade
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.GreyRecomen
import com.cexup.ui.consumer.theme.Heading
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.formatToRupiah
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.ChevronRight24

@Composable
fun ScreenCheckoutOrderClinincReservation(
    modifier: Modifier = Modifier,
    detailDoctorState: DoctorUIState,
    patientState: PatientUIState,
    orderState: OrderUIState,
    showBottomSheet: () -> Unit,
    onBackPressed: () -> Unit,
    onSubmit: () -> Unit
) {
    var promo by remember {
        mutableStateOf("")
    }

    val ctx = LocalContext.current
    Scaffold(
        topBar = {

        },
        bottomBar = {
            if (orderState.loading) {
                BottomBarPayment(
                    price = "Rp 10.000",
                    enabled = !orderState.error && orderState.loading,
                    onClick = {}
                )
            } else {
                BottomBarPaymentShimmer()
            }
        },
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp.from(ctx)),
            modifier = modifier.padding(it),
            content = {
                item {

                    Text(
                        text = "Doctor Information",
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.onBackground,
                        )
                    )
                    Spacer(modifier = modifier.height(12.dp.from(ctx)))

                    if (detailDoctorState.loading) {
                        CardDoctorInformationShimmer()
                    }
                    if (detailDoctorState.error) {
                        Text(text = "Cannot retrieve information!")
                    }
                    if (!detailDoctorState.loading && !detailDoctorState.error) {
                        CardDoctorInformation()
                    }


                }
                item {
                    Text(
                        text = "Detail Patient",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colors.onBackground
                        ),
                        modifier = modifier.padding(vertical = 16.dp)
                    )
                    if (patientState.error) {
                        Text(text = "Cannot retrieve information!")
                    }

                    if (patientState.loading) {
                        CircularProgressIndicator()
                    }

                    if (!patientState.error && !patientState.loading) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row {
                                Image(
                                    painterResource(id = R.drawable.ic_patient),
                                    contentDescription = "",
                                    modifier = modifier.size(20.dp)
                                )
                                Spacer(modifier = modifier.width(8.dp))
                                Text(
                                    text = patientState.name.capitalizeWords(),
                                    style = MaterialTheme.typography.body2.copy(
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colors.onBackground
                                    )
                                )
                            }

                            Text(
                                text = "${patientState.gender.capitalizeWords()}, ${
                                    patientState.dateOfBirth.replace(
                                        "-",
                                        "/"
                                    )
                                }",
                                style = MaterialTheme.typography.body1.copy(
                                    color = MaterialTheme.colors.onBackground
                                )
                            )
                        }
                    }


                    Spacer(modifier = modifier.height(24.dp))
                }
                item {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Date",
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colors.onBackground
                            )
                        )
                        Text(
                            text = "Change",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(500),
                                fontSize = 12.sp,
                                color = Heading
                            )
                        )
                    }
                    Spacer(modifier = modifier.height(16.dp))

                    if (!orderState.error && !orderState.loading) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = "",
                                tint = BlueJade,
                                modifier = modifier.size(15.dp)
                            )
                            Spacer(modifier = modifier.width(25.dp))
                            Text(
                                text = "${orderState.dueDate} ${orderState.dueTime}",
                                style = MaterialTheme.typography.body2.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = GreyRecomen
                                )
                            )
                        }
                    }
                    if (orderState.error) {
                        Text(text = "Cannot retrieve information!")
                    }


                    Spacer(modifier = modifier.height(24.dp))
                }
                item {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "My Simptons",
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colors.onBackground
                            )
                        )
                        Text(
                            text = "Change",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(500),
                                fontSize = 12.sp,
                                color = Heading
                            )
                        )
                    }
                    Spacer(modifier = modifier.height(16.dp))

                    if (!orderState.loading && !orderState.error) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = "",
                                tint = BlueJade,
                                modifier = modifier.size(15.dp)
                            )
                            Spacer(modifier = modifier.width(25.dp))
                            Text(
                                text = orderState.note,
                                style = MaterialTheme.typography.body2.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = GreyRecomen
                                )
                            )
                        }
                    }
                    if (orderState.error) {
                        Text(text = "Cannot retrieve information !")
                    }

                    Spacer(modifier = modifier.height(24.dp))
                }
                item {
                    Text(
                        text = "Voucher",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            color = GreyRecomen
                        )
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    OutlinedTextField(
                        value = promo,
                        onValueChange = { promo = it },
                        placeholder = {
                            Text(
                                text = "Promotion Code",
                                style = MaterialTheme.typography.body2.copy(
                                    color = MaterialTheme.colors.onSecondary
                                )
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Heading,
                        ),
                        textStyle = MaterialTheme.typography.body2.copy(
                            color = MaterialTheme.colors.onBackground
                        ),
                        modifier = modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(6.dp)
                    )
                    Spacer(modifier = modifier.height(24.dp))
                }
                item {
                    Text(
                        text = "Payment Method",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            color = GreyRecomen
                        )
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = 0.dp,
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                showBottomSheet()
                            }
                            .coloredShadow(
                                color = Color.Black,
                                alpha = .1f,
                                offsetY = 1.dp,
                                shadowRadius = 10.dp,
                            )
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Select your payment method",
                                style = MaterialTheme.typography.body2.copy(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Icon(Octicons.ChevronRight24, "", tint = MaterialTheme.colors.onPrimary)
                        }
                    }
                    Spacer(modifier = modifier.height(24.dp))
                }
                item {
                    Text(
                        text = "Payment Detail",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = GreyRecomen
                        )
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Consultation",
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onBackground,
                            )
                        )
                        Text(
                            text = 0.0.formatToRupiah(),
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onBackground,
                            )
                        )
                    }
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Additional Discount",
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onBackground,
                            )
                        )
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onBackground,
                            )
                        )
                    }
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total",
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Text(
                            text = 0.0.formatToRupiah(),
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                    Spacer(modifier = modifier.height(100.dp))
                }
            })
    }
}

@Preview
@Composable
fun PreviewCheckoutOrderClinicReservation() {
    ConsumerTheme {
        ScreenCheckoutOrderClinincReservation(
            showBottomSheet = { /*TODO*/ },
            onBackPressed = { /*TODO*/ },
            detailDoctorState = DoctorUIState(
                loading = true,
                error = false,
                errorMessage = "",
                doctorImage = "",
                doctorSpecialist = "",
                doctorHospital = "",
                doctorName = "",
                doctorSlug = "",
                doctorSpecialityId = ""
            ),
            orderState = OrderUIState(
                loading = true,
                error = false,
                errorMessage = "",
                dueTime = "",
                dueDate = "",
                dueTimeId = "",
                note = "",
                price = 0.0,
                dueDateTime = ""
            ),
            patientState = PatientUIState(
                loading = true,
                error = false,
                errorMessage = "Cannot find user!",
                phoneNumber = "",
                dateOfBirth = "",
                gender = "",
                name = "",
                genderTranslation = "",
                patientHasData = false
            ),
            onSubmit = {},
        )
    }
}