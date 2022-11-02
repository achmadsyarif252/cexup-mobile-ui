package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.fontsCorp
import com.cexup.ui.R

@Composable
fun DialogQrCode(
    show:Boolean= false,
    onDismiss : ()-> Unit
){
    if(show){
        Dialog(onDismissRequest = { onDismiss()}) {
            LayoutDialogQrCode(
                onDismiss = {
                    onDismiss()
                }
            )
        }

    }
}

@Composable
fun LayoutDialogQrCode(
    onDismiss : ()-> Unit
) {
    Column{
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .height(435.dp)
                .width(357.dp)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.padding(top = 20.dp)) {
                    Text(
                        text = "Scan Barcode",
                        color = Heading,
                        fontSize = 22.sp,
                        fontFamily = fontsCorp,
                        fontWeight = FontWeight(600)
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))

                Image(
                    painter = painterResource(id = R.drawable.dummy_qr_code),
                    contentDescription = "Qr Code", modifier = Modifier.size(220.dp)
                )

                Spacer(modifier = Modifier.height(35.dp))

                Button(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(Heading),
                    modifier = Modifier
                        .height(40.dp)
                        .width(288.dp),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 11.dp)

                ) {
                    Text(
                        text = "Back",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 16.sp,
                            letterSpacing = 1.sp,
                            color = Color.White
                        ),
                    )
                }
            }


        }
    }
}