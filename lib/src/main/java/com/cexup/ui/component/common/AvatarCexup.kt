package com.cexup.ui.component.common

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.BlueDarkJade
import com.cexup.ui.corporate.theme.BlueLightJade
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun AvatarCexup(
    isWithStatusOnline: Boolean = false,
    sizeAvatar: Dp = 38.dp.from(LocalContext.current),
    sizeDummy: Dp = 17.dp.from(LocalContext.current),
    sizeCircleOnline: Dp = 12.dp.from(LocalContext.current)
){
    val ctx = LocalContext.current
    Box {
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
            CoilImage(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(sizeDummy),
                imageModel = painterResource(id = R.drawable.ic_profile_dummy),
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                // shows an image with a circular revealed animation.
                circularReveal = CircularReveal(duration = 250),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = painterResource(id = R.drawable.ic_profile_dummy),
                // shows an error ImageBitmap when the request failed.
                error = painterResource(id = R.drawable.ic_profile_dummy)
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