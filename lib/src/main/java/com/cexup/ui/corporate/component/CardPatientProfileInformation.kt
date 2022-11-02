package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cexup.ui.utils.capitalizeWords

@Composable
fun CardPatientProfileInformation(
    modifier: Modifier = Modifier,
    name: String = "Unknown",
    email: String = "Unknown",
    date_of_birth: String = "Unknown",
    gender: String = "Unknown",
    status: Boolean = true,
    address: String = "Unknown",
    phone_number: String = "Unknown",
) {
    val listPatientInformation = listOf(
        "name",
        "email",
        "date of birth",
        "gender",
        "status",
        "address",
        "phone number",
    )

    val valueListPatientInformation = listOf(
        name,
        email,
        date_of_birth,
        gender,
        status,
        address,
        phone_number,
    )

    Column(
        modifier = modifier
            .padding(0.dp)
    ) {
        listPatientInformation.zip(valueListPatientInformation) { a, b ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = a.capitalizeWords())
                Text(text = b.toString())
            }
        }
    }
}