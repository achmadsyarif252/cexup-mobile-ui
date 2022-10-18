package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cexup.ui.R

@Composable
fun CardNotificationBar(
    modifier: Modifier = Modifier,
){
    val expanded = remember {
        mutableStateOf(false)
    }
    val patientName = listOf("Dr. Andy","Dr. Anita", "Dr. Bagas")
    Box(
        modifier = modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(
            onClick = {
                expanded.value = true
            },
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "notification",
                modifier = modifier
                    .size(32.16.dp)
                    .align(alignment = Alignment.Center)

            )
        }
        DropdownMenu(
            modifier = modifier.width(350.dp),
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            patientName.forEachIndexed {
                    index, _ ->
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    CardDoctorNotification(
//                        thumb = "",
                        doctor_name =patientName[index],
                        status ="asked you to join in a group",
                        time = "2 minutes ago"
                    )
                }

            }

        }

    }


}