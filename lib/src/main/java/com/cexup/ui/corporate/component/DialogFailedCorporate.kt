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
fun DialogFailedCorporate(
    show: Boolean,
    onClickCancel: () -> Unit,
    onClickTryAgain: () -> Unit,
) {
    if (show) {
        Dialog(onDismissRequest = { onClickCancel() }) {
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
                                text = "Failed",
                                fontFamily = fontsCorp,
                                fontWeight = FontWeight(700),
                                fontSize = 26.sp,
                                color = inactive
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { onClickCancel() },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .width(114.dp)
                                    .height(38.dp),
                                colors = ButtonDefaults.buttonColors(BlueLight)
                            ) {
                                Text(
                                    text = "Cancel",
                                    fontSize = 12.sp,
                                    fontFamily = fontsCorp,
                                    color = inactive
                                )
                            }
                            Spacer(modifier = Modifier.width(19.dp))

                            Button(
                                onClick = { onClickTryAgain() },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .width(114.dp)
                                    .height(38.dp),
                                colors = ButtonDefaults.buttonColors(SecondaryCorporate)
                            ) {
                                Text(
                                    text = "Try again",
                                    fontSize = 12.sp,
                                    fontFamily = fontsCorp,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                            .align(Alignment.TopCenter)
                    ) {
                        Box(
                            Modifier
                                .size(86.dp)
                                .clip(CircleShape)
                                .border(
                                    BorderStroke(
                                        3.dp,
                                        RedCircle
                                    ),
                                    shape = CircleShape
                                )
                                .background(SecondaryCorporate)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_cancel),
                                    contentDescription = "Cancel", modifier = Modifier
                                        .size(40.dp)
                                )
                            }
                        }


                    }


                }


            }
        }
    }
}