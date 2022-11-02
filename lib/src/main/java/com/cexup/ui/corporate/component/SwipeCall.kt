package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.Heading
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import compose.icons.Octicons
import compose.icons.octicons.DeviceCameraVideo16
import kotlin.math.roundToInt
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.AlternativeInactive
import com.cexup.ui.corporate.theme.BlueButtonLogout
import com.cexup.ui.utils.coloredShadow

@ExperimentalMaterialApi
@Composable
fun SwipeCall(
    session: String,
    doctor_name: String,
    profile: String,
    modifier: Modifier = Modifier
) {

    val square = 50.dp
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { square.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)

    Card(
        shape = RoundedCornerShape(19.dp),
        modifier = modifier
            .height(91.dp)
            .width(163.dp),
        elevation = 16.dp,
        backgroundColor = Heading
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = session,
                fontWeight = FontWeight(600),
                fontSize = 12.sp,
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
            Text(
                text = "join video call with",
                fontWeight = FontWeight(400),
                fontSize = 8.sp,
                style = MaterialTheme.typography.body1,
                color = Color.White,
            )
            Text(
                text = doctor_name,
                fontWeight = FontWeight(400),
                fontSize = 8.sp,
                style = MaterialTheme.typography.body1,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier
                        .height(31.dp)
                        .width(2.dp)
                        .background(Color.White, shape = RoundedCornerShape(19.dp))
                ) {

                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = modifier
                        .height(28.dp)
                        .width(127.dp)
                        .background(BlueButtonLogout,
                            shape = RoundedCornerShape(13.dp)
                        )
                        .swipeable(
                            state = swipeableState,
                            anchors = anchors,
                            thresholds = { _, _ ->
                                FractionalThreshold(0.3f)
                            },
                            orientation = Orientation.Horizontal
                        ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(2.dp)
                    ) {
                        CoilImage(
                            modifier = modifier
                                .clip(CircleShape)
                                .size(21.66.dp)
                                .coloredShadow(MaterialTheme.colors.primary),
                            imageModel = profile,
                            // Crop, Fit, Inside, FillHeight, FillWidth, None
                            contentScale = ContentScale.Crop,
                            // shows an image with a circular revealed animation.
                            circularReveal = CircularReveal(duration = 250),
                            // shows a placeholder ImageBitmap when loading.
                            placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
                            // shows an error ImageBitmap when the request failed.
                            error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            Icons.Filled.ArrowBackIos,
                            contentDescription = "",
                            tint = AlternativeInactive,
                            modifier = modifier
                                .height(12.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Icon(
                            Icons.Filled.ArrowBackIos,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = modifier
                                .size(12.dp)
                                .align(Alignment.CenterVertically)
                        )

                    }
                    Box(
                        modifier = modifier
                            .offset {
                                IntOffset(
                                    swipeableState.offset.value.roundToInt(), 0
                                )
                            }
                            .height(28.dp)
                            .width(77.dp)
                            .background(BlueButtonLogout,
                                shape = RoundedCornerShape(13.dp)
                            )
                            .border(1.dp, color = Color.White, shape = RoundedCornerShape(13.dp))

                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Icon(
                                Octicons.DeviceCameraVideo16,
                                contentDescription = " Video",
                                tint = Color.White,
                                modifier = modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Join",
                                fontWeight = FontWeight(600),
                                fontSize = 12.sp,
                                style = MaterialTheme.typography.body1,
                                color = Color.White
                            )

                        }
                    }

                }
            }

        }

    }

}
