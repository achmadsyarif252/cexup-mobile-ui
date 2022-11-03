package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.component.CardPatientInformation
import com.cexup.ui.corporate.theme.Heading

data class Registration(
    var name: String,
    var no_type: String,
    var home_address: String,
    var phone_number: String,
    var place_of_birth: String,
    var date_of_birth: String,
    var gender: String,
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenRegisterPatient(
    onRegisterPatient: (patient: Registration) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = "Add New Patient",
            style = MaterialTheme.typography.body1.copy(
                color = Heading,
                fontSize = 22.sp,
                fontWeight = FontWeight(700)
            ),
            modifier = Modifier.padding(start = 20.dp)
        )
        CardPatientInformation(
            onSubmitRegisterPatient = {
                    name, no_type, home_address,
                    phone_number, place_of_birth,
                    date_of_birth, gender ->
                onRegisterPatient(
                    Registration(
                        name, no_type, home_address,
                        phone_number, place_of_birth,
                        date_of_birth, gender
                    )
                )
            }
        )
    }
}