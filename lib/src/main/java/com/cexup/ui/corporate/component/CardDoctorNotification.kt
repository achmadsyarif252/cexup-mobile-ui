package com.cexup.ui.corporate.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.utils.coloredShadow
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.cexup.ui.R
import com.cexup.ui.theme.Heading
import com.cexup.ui.theme.inactive
import com.cexup.ui.utils.mediaquery.from

@Composable
fun CardDoctorNotification(
//    thumb: String,
    doctor_name: String,
    status: String,
    time: String,
    modifier: Modifier = Modifier
) {
    val ctx = LocalConfiguration.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp.from(ctx))
            .clickable {

            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(
            modifier = modifier
                .clip(CircleShape)
                .coloredShadow(MaterialTheme.colors.primary)
                .width(35.dp.from(ctx))
                .height(35.dp.from(ctx)),
            imageModel = ImageBitmap.imageResource(R.drawable.dummy_profile_small),
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            // shows an image with a circular revealed animation.
            circularReveal = CircularReveal(duration = 250),
            // shows a placeholder ImageBitmap when loading.
            placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile_small),
            // shows an error ImageBitmap when the request failed.
            error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
        )
        Spacer(modifier = Modifier.width(13.89.dp.from(ctx)))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = doctor_name,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight(500),
                    color = Heading,
                    fontSize = 12.sp.from(ctx),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(5.dp.from(ctx)))
                Text(
                    text = status,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight(300),
                    color = inactive,
                    fontSize = 12.sp.from(ctx),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(18.11.dp.from(ctx)))
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "",
                    tint = inactive,
                    modifier = modifier.size(12.dp.from(ctx))
                )

            }
            Text(
                text = time,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight(400),
                color = inactive,
                fontSize = 8.sp.from(ctx),
                overflow = TextOverflow.Ellipsis
            )

        }

    }
}