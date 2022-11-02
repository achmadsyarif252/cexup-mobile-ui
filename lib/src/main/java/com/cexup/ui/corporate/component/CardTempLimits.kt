package com.cexup.ui.corporate.component

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.component.picker.CustomNumberPicker
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.Heading

@Composable
fun CardTempLimits() {
    Column {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .width(477.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .background(Color.White)
        )
        {
            Column {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 23.dp)
                ) {
                    Text(
                        text = "Temp Limits",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 22.sp,
                            letterSpacing = 1.sp,
                            color = Heading
                        )
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    Row {
                        Text(
                            text = "Upper",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Heading
                            )
                        )
                        Spacer(modifier = Modifier.width(159.dp))
                        Text(
                            text = "Lower",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Heading
                            )
                        )

                    }


                }
                //kolom isi
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.padding(start = 50.dp, end = 50.dp)) {
                    CustomNumberPicker(
                        min = 0,
                        max = 2,
                        items = arrayOf("a", "b","c"),
                        onValueChange = { old, new -> })
                    Spacer(modifier = Modifier.width(39.dp))
                    CustomNumberPicker(
                        min = 0,
                        max = 2,
                        items = arrayOf("a", "b","c"),
                        onValueChange = { old, new -> })
                    //space
                    Spacer(modifier = Modifier.width(51.dp))
                    CustomNumberPicker(
                        min = 0,
                        max = 2,
                        items = arrayOf("a", "b","c"),
                        onValueChange = { old, new -> })
                    Spacer(modifier = Modifier.width(39.dp))
                    CustomNumberPicker(
                        min = 0,
                        max = 2,
                        items = arrayOf("a", "b","c"),
                        onValueChange = { old, new -> })

                }
                //kolom
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.padding(start=64.dp,end = 64.dp)) {
                    Button(
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(BlueJade),
                        modifier = Modifier
                            .height(33.dp)
                            .width(146.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 11.dp)
                    )
                    {
                        Text(
                            text = "Default",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ))
                    }
                    //spacer
                    Spacer(modifier = Modifier.width(57.dp))
                    //button
                    Button(
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(BlueJade),
                        modifier = Modifier
                            .height(33.dp)
                            .width(146.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 11.dp)
                    )
                    {
                        Text(
                            text = "Save",
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ))
                    }
                }


            }
        }
    }
}