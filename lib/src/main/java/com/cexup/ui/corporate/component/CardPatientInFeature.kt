package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.BlueDarkJade
import com.cexup.ui.corporate.theme.BlueLightJade
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from

@Composable
fun CardPatientInFeature(
    modifier: Modifier = Modifier,
    thumb: String,
    name: String,
    id: String,
) {
    val ctx = LocalConfiguration.current
    Row(
        modifier = modifier
            .padding(6.dp.from(ctx)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (thumb.isEmpty()) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp.from(ctx))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                BlueDarkJade,
                                BlueLightJade
                            )
                        )
                    ),
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(23f.dp.from(ctx)),
                    painter = painterResource(id = R.drawable.ic_profile_dummy),
                    contentDescription = "",
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    // shows an image with a circular revealed animation.
//                    circularReveal = CircularReveal(duration = 250),
                    // shows a placeholder ImageBitmap when loading.
//                    placeHolder = painterResource(id = R.drawable.ic_profile_dummy),
//                    // shows an error ImageBitmap when the request failed.
//                    error = painterResource(id = R.drawable.ic_profile_dummy)
                )
            }
        } else {
            Image(
                painter = rememberImagePainter(data = thumb, builder = {
                    crossfade(true)
                    placeholder(R.drawable.dummy_user_profile)
                    error(R.drawable.dummy_user_profile)
                }), contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp.from(ctx)))
                    .coloredShadow(MaterialTheme.colors.primary)
                    .size(64.dp.from(ctx))
                    .align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = modifier.width(15.dp.from(ctx)))
        Column {
            Column {
                Text(
                    text = name.capitalizeWords(),
                    fontSize = 15.sp.from(ctx),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight(700),
                    color = Heading
                )
                Text(
                    text = id,
                    fontSize = 12.sp.from(ctx),
                    style = MaterialTheme.typography.body1,
                    color = inactive
                )
            }

        }
    }
}