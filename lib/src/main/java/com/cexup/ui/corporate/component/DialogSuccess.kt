package com.cexup.ui.corporate.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.R

@Composable
fun DialogSuccessCorporate(
    modifier: Modifier = Modifier,
    show:Boolean= false,
    onNavigate: (routes: String) -> Unit={},
    onDismiss : ()-> Unit
) {
    if(show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            LayoutDialogSuccess(
//                onNavigate = {
//                    onNavigate(it)
//                }
            ) {
                onDismiss()
            }
        }
    }

}

@Composable
fun LayoutDialogSuccess(
    modifier: Modifier = Modifier,
//    onNavigate : (routes : String) -> Unit ={},
    onDismiss : ()-> Unit,
){
    Column {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .height(203.dp)

        ) {

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .height(164.dp)
                    .width(311.83.dp)
                    .align(Alignment.BottomCenter)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Success",
                        fontFamily = fontsCorp, fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = GrayFont
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .width(114.dp)
                            .height(38.dp),
                        colors = ButtonDefaults.buttonColors(Grayblue)
                    ) {
                        Text(
                            text = "OK",
                            fontSize = 12.sp,
                            color = GrayFont
                        )
                    }
//                    Spacer(modifier = Modifier.width(19.dp))
//
//                    Button(
//                        onClick = {
//                            onNavigate(Routes.Corporate.Dashboard.PATIENT_LIST)
//                        },
//                        Modifier
//                            .clip(RoundedCornerShape(5.dp))
//                            .wrapContentWidth()
//                            .height(38.dp),
//                        colors = ButtonDefaults.buttonColors(GreenJade)
//                    ) {
//                        Text(
//                            text = "List Patients",
//                            fontSize = 12.sp,
//                            color = Color.White
//                        )
//                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .align(Alignment.TopCenter)


            ) {
                Box(
                    modifier = Modifier
                        .size(86.dp)
                        .clip(CircleShape)
                        .border(BorderStroke(5.dp, GreenBorder), shape = CircleShape)
                        .background(GreenJade)
                )
                {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.ic_success),
                            contentDescription = "Success", modifier = Modifier.size(
                                width = 44.01.dp,
                                height =
                                34.38.dp
                            )

                        )
                    }
                }


            }


        }


    }
}