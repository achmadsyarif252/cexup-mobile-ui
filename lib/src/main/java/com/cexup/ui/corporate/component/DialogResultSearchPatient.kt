package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.fontsCorp
import com.cexup.ui.R

@Composable
fun DialogResultSearchPatient() {
    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(25.dp)
            )
            .height(155.dp)
            .width(418.dp)
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(start= 22.dp, top = 19.dp, end = 20.dp)) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_blue_jade_rectangle),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = "John Stones",
                    fontSize = 16.sp,
                    fontFamily = fontsCorp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(57.dp))
                Text(text = "Detail",
                    fontSize = 16.sp,
                    fontFamily = fontsCorp,
                    fontWeight = FontWeight.Normal,
                    color = BlueJade
                )
                Spacer(modifier = Modifier.width(46.dp))
                Text(text = "Check Up",
                    fontSize = 16.sp,
                    fontFamily = fontsCorp,
                    fontWeight = FontWeight.Normal,
                    color = BlueJade

                )

            }





        }
    }
}