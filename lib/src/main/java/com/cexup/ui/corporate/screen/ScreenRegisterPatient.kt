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
import com.cexup.ui.corporate.component.Registration
import com.cexup.ui.corporate.theme.Heading

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
                onRegisterPatient(it)
            }
        )
    }
}