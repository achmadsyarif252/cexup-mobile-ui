package com.cexup.ui.consumer.screen.clinic_reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.R
import com.cexup.ui.consumer.component.*
import com.cexup.ui.consumer.theme.ConsumerTheme

data class DoctorUIState(
    var loading: Boolean,
    var error: Boolean,
    var errorMessage: String,
    var doctorName: String,
    var doctorSpecialist: String,
    var doctorSpecialityId: String,
    var doctorImage: String,
    var doctorHospital: String,
)
data class PatientUIState(
    var loading: Boolean,
    var error: Boolean,
    var errorMessage: String,
    var dateOfBirth: String,
    var phoneNumber: String,
    var gender: String,
    var genderTranslation: String,
    var patientHasData: Boolean
)

data class OrderUIState(
    var loading: Boolean,
    var error: Boolean,
    var errorMessage: String,
    var dueDate: String,
    var dueTime: String,
    var dueDateTime: String
)

@Composable
fun ScreenRegisterOrderClinicReservation(
    modifier: Modifier = Modifier,
    detailDoctorState: DoctorUIState,
    patientState: PatientUIState,
    orderState: OrderUIState,
    dateOfBirth: String,
    showQuestionnaire: () -> Unit,
    showDatePicker: () -> Unit,
    onBackPressed: () -> Unit,
    onCheckout: (dateOfBirth: String, gender: String) -> Unit,
) {

    var phonenumber by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }

    val radioOptions = listOf("male", "female")
    val ctx = LocalContext.current

    Scaffold(
        topBar = {

        },
        bottomBar = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(
                        vertical = 6.dp.from(ctx),
                        horizontal = 16.dp.from(ctx)
                    )
            ) {
                ButtonPrimary(
                    enabled = if (patientState.patientHasData) true else phonenumber.isNotBlank() && selectedGender.isNotBlank() && dateOfBirth.isNotBlank(),
                    onClick = {},
                    text = "Checkout"
                )
            }
        },
        backgroundColor=MaterialTheme.colors.background,
        modifier = modifier.padding(horizontal = 16.dp.from(ctx), vertical = 24.dp.from(ctx))
    ) {
        LazyColumn(
            contentPadding = it,
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
                    if (!detailDoctorState.loading && !detailDoctorState.error) {
                        CardDoctorInformation(

                        )
                    } else {
                        CardDoctorInformationShimmer()
                    }
                }
                item {
                    Spacer(modifier = modifier.height(30.dp.from(ctx)))
                    Text(
                        text = "Date of Birth",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.onBackground,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                        )
                    )
                    Spacer(modifier = modifier.height(16.dp.from(ctx)))
                    OutlinedInput(
                        value=dateOfBirth,
                        onChange = {},
                        trailingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = "",
                                modifier = modifier.size(20.dp.from(ctx))
                            )
                        },
                        placeholder = "Your date of birth"
                    )

                }
                item {
                    Spacer(modifier = modifier.height(24.dp.from(ctx)))
                    Text(
                        text = "Phone Number",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.onBackground,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                        )
                    )
                    Spacer(modifier = modifier.height(16.dp.from(ctx)))
                    OutlinedInput(
                        value = phonenumber,
                        onChange = {
                            phonenumber= it
                        },
                        placeholder = "Your phone number",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        )
                    )

                }
                item {
                    Spacer(modifier = modifier.height(24.dp.from(ctx)))
                    GenderInput(
                        items = listOf("Laki-Laki","Perempuan"),
                    )
                }
                item {
                    Spacer(modifier = modifier.height(24.dp.from(ctx)))
                    Text(
                        text = "Questionnaire",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.onBackground,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp.from(ctx)))
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            showQuestionnaire()
                        }
                        .padding(vertical = 10.dp.from(ctx))) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_question),
                            contentDescription = "",
                            modifier = modifier.size(23.dp.from(ctx))
                        )
                        Spacer(modifier = modifier.width(12.dp.from(ctx)))
                        Text(
                            text = "Please fill it out",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colors.onBackground,
                                fontSize = 14.sp.from(ctx),
                                lineHeight = 20.sp.from(ctx),
                                letterSpacing = 0.1.sp.from(ctx)
                            )
                        )
                    }
                }
                item { Spacer(modifier = modifier.height(70.dp.from(ctx))) }
            })
    }
}

@Preview
@Composable
fun PreviewScreenRegisterOrder() {
    ConsumerTheme {
        ScreenRegisterOrderClinicReservation(
            showQuestionnaire = { /*TODO*/ },
            onBackPressed = { /*TODO*/ },
            detailDoctorState = DoctorUIState(
                loading = false,
                error = false,
                errorMessage = "",
                doctorImage = "",
                doctorSpecialist = "",
                doctorHospital = "",
                doctorName = "",
                doctorSpecialityId = ""
            ),
            patientState = PatientUIState(
                loading = false,
                error = false,
                errorMessage = "",
                dateOfBirth = "",
                gender = "",
                phoneNumber = "",
                genderTranslation = "",
                patientHasData = false,
            ),
            orderState = OrderUIState(
                loading = false,
                errorMessage = "",
                error = false,
                dueDateTime = "",
                dueDate = "",
                dueTime = ""
            ),
            onCheckout = { _, _ -> },
            showDatePicker = {},
            dateOfBirth = ""
        )
    }
}

