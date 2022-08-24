package com.cexup.ui.component.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.trian.component.R

@Composable
fun DialogStatus(
    statusOrder: Status,
    show : Boolean = false,
    textSuccess: String,
    textFailed:String,
    onClicked:()->Unit
) {
    if(show){
        Dialog(onDismissRequest = onClicked) {
            ScreenDialogStatusOrder(statusOrder = statusOrder, onClick = onClicked, textFailed = textFailed, textSuccess = textSuccess)
        }
    }
}

@Composable
fun ScreenDialogStatusOrder(
    statusOrder: Status,
    textSuccess: String,
    textFailed:String,
    onClick:()->Unit
) {
    Card(
        modifier = Modifier
            .padding(24.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Icon(
                painter = painterResource(id = when(statusOrder){
                    Status.SUCCCESS -> R.drawable.ic_groupsuccess
                    Status.FAILED -> R.drawable.ic_groupfail
                }),
                contentDescription = "",
                modifier = Modifier.size(33.dp),
                tint = when(statusOrder){
                    Status.SUCCCESS -> Color(0xFF52D16B)
                    Status.FAILED -> Color(0xFFEF716B)
                }
            )
            Text(
                text = when(statusOrder){
                    Status.SUCCCESS -> "Succes"
                    Status.FAILED -> "Failed"
                },
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = when(statusOrder){
                    Status.SUCCCESS -> textSuccess
                    Status.FAILED -> textFailed
                },
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onBackground
            )

            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = when(statusOrder){
                        Status.SUCCCESS -> Color(0xFF52D16B)
                        Status.FAILED -> Color(0xFFEF716B)
                    }
                )
            ) {
                Text(
                    text = when(statusOrder){
                        Status.SUCCCESS -> "Got it"
                        Status.FAILED -> "Try Again"
                    },
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

    }
}

@Preview
@Composable
fun PreviewDialogScreen() {

}

enum class Status{
    FAILED,
    SUCCCESS
}