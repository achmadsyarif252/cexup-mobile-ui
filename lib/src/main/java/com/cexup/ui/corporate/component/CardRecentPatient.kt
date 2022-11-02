package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.utils.coloredShadow
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.inactive

@Composable
fun CardRecentPatient (
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .width(326.49.dp)
            .height(103.dp)
            .padding(6.dp),
    ){
        CoilImage(
            modifier = modifier
                .clip(RoundedCornerShape(4.dp))
                .coloredShadow(MaterialTheme.colors.primary)
                .width(85.dp)
                .height(85.dp),
            imageModel = ImageBitmap.imageResource(R.drawable.dummy_profile),
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            // shows an image with a circular revealed animation.
            circularReveal = CircularReveal(duration = 250),
            // shows a placeholder ImageBitmap when loading.
            placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
            // shows an error ImageBitmap when the request failed.
            error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
        )
        Spacer(modifier = modifier.width(24.dp))
        Column {
            Row {
                Text(
                    text = "ID ",
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1,
                    color = inactive
                )
                Text(
                    text = "1001001010" ,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1,
                    color = inactive
                )
            }
            Text(
                text = "Corry Angela",
                fontSize = 15.sp,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(700),
                color = Heading
            )

            Row(
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = "Age ",
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.body1,
                        color = inactive
                    )
                    Text(
                        text = "25" ,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.body1,
                        color = inactive
                    )
                }
                Text(
                    text = "Check up",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight(400),
                    color = Heading,
                    modifier = modifier.align(Alignment.Bottom)
                )

            }

        }
    }

}