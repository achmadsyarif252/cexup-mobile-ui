package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.corporate.theme.BackgroundLight
import com.cexup.ui.corporate.theme.BlueButtonLogout
import com.cexup.ui.corporate.theme.GreyBlackStetoscope
import com.cexup.ui.corporate.theme.SecondaryCorporate


@Composable
fun DialogLogOutConfirmation(
    show:Boolean= false,
    onDismiss : ()-> Unit,
    onLogout: () -> Unit
){
    if(show){
        Dialog(onDismissRequest = { onDismiss()}) {
            LayoutDialogLogout(onDismiss = {
                onDismiss()
            }) {
                onLogout()
            }
        }
    }

}

@Composable
fun LayoutDialogLogout(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onLogout:()->Unit,
){
    Surface(
        modifier = modifier
            .padding(5.dp)
            .height(184.dp)
            .width(416.dp),
        shape = RoundedCornerShape(25.dp),
        color = BackgroundLight
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 31.dp, vertical = 23.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Logout?",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(500),
                fontSize = 22.sp,
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Left,
                color = BlueButtonLogout

            )
            Text(
                text = "Are you sure want to logout?",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(400),
                fontSize = 22.sp,
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Left,
                color = GreyBlackStetoscope

            )
            Spacer(modifier = Modifier.height(21.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = {onDismiss() },
                    modifier = modifier
                        .width(149.dp)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = SecondaryCorporate,
                        contentColor = Color.White,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 41.38.dp,vertical = 12.69.dp)
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.body1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600)
                    )
                }
                Spacer(modifier = modifier.width(8.dp))
                Button(
                    onClick = {
                              onLogout()
                    }, modifier = modifier
                        .width(149.dp)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = BlueButtonLogout,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 41.38.dp,vertical = 12.69.dp)
                ) {
                    Text(
                        text = "Logout",
                        style = MaterialTheme.typography.body1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600)

                    )
                }

            }

        }

    }
}