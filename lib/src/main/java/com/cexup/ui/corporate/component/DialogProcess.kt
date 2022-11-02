package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.Linecolor
import com.cexup.ui.corporate.theme.fontsCorp
import com.cexup.ui.corporate.theme.inactive

@Composable
fun DialogProcess() {
    Column {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .width(311.83.dp)
                .height(164.dp)
                .background(color = Color.White)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = Heading, modifier = Modifier
                        .padding(top = 21.11.dp, bottom = 18.34.dp)
                        .size(73.73.dp)
                )
                Divider(color = Linecolor, thickness = 1.dp)

                Card(
                    modifier = Modifier
                        .border(0.dp, Color.Transparent)
                        .width(311.83.dp)
                        .height(50.84.dp)
                        .clickable { /*later*/ }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally


                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            fontFamily = fontsCorp,
                            color = inactive
                        )

                    }

                }


            }


        }
    }
}