package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.OrangeCardCount
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.coloredShadow

@Composable
fun CardCount(
    imageId : Int,
    title : String,
    countValue : Int,
    modifier: Modifier = Modifier,
    onNavigate : () -> Unit
){

    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .width(153.dp)
            .height(48.dp)
            .coloredShadow(
                color = Color.Black,
                shadowRadius = 4.dp,
                offsetY = 2.dp,
            )
            .clickable {
                onNavigate()
            },
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(OrangeCardCount),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "Logo",
                    modifier = modifier
                        .size(20.dp)
                )

            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = inactive
                    )
                )
                Text(
                    text = countValue.toString(),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = Heading
                    )
                )
            }
        }


    }
}