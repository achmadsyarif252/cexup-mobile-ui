package com.cexup.ui.component.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.cexup.ui.R
import com.cexup.ui.theme.BlueDarkJade
import com.cexup.ui.theme.BlueLightJade
import com.cexup.ui.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from

@Composable
fun AvatarCexup(
    thumb: String = "",
    isWithStatusOnline: Boolean = false,
    sizeAvatar: Dp = 38.dp.from(LocalConfiguration.current),
    sizeDummy: Dp = 17.dp.from(LocalConfiguration.current),
    sizeCircleOnline: Dp = 12.dp.from(LocalConfiguration.current)
){
    val ctx = LocalConfiguration.current
    Box {
        if (thumb.isEmpty()){
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(sizeAvatar)
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
                        .size(sizeDummy),
                    painter = painterResource(id = R.drawable.ic_profile_dummy),
                    contentDescription = "PlaceHolder Avatar",
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    // shows an image with a circular revealed animation.
//                    circularReveal = CircularReveal(duration = 250),
//                    // shows a placeholder ImageBitmap when loading.
//                    placeHolder = painterResource(id = R.drawable.ic_profile_dummy),
//                    // shows an error ImageBitmap when the request failed.
//                    error = painterResource(id = R.drawable.ic_profile_dummy)
                )
            }
        }else {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(sizeAvatar)
                    .clip(CircleShape),
                painter = rememberImagePainter(
                    data = thumb,
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.ic_profile_dummy)
                        error(R.drawable.ic_profile_dummy)
                    }),
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )
        }
        if (isWithStatusOnline){
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(sizeCircleOnline)
                    .background(
                        MaterialThemeCexup.colors.color.success.successMain,
                        CircleShape
                    )
                    .border(
                        2.dp.from(ctx),
                        MaterialThemeCexup.colors.palette.neutral.neutral1,
                        CircleShape
                    ),

            )
        }
    }
}