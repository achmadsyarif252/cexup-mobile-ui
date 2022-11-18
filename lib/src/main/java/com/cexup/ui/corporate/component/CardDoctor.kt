package com.cexup.ui.corporate.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.BlackShadow
import com.cexup.ui.corporate.theme.GreyBorder
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CardDoctors(
    modifier: Modifier = Modifier,
    doctorName: String = "",
    specialistName: String = "",
    thumb: String = "",
    onClickInstagram: () -> Unit = {},
    onClickWhatsapp: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
) {
    val ctx = LocalContext.current
    Column(
        modifier = modifier.padding(vertical = 12.dp.from(ctx))
    ) {
        Card(
            modifier = Modifier
                .width(172.dp.from(ctx))
                .height(201.dp.from(ctx))
                .coloredShadow(
                    alpha = 0.06f,
                    color = BlackShadow.copy(alpha = 0.08f),
                    shadowRadius = 5.dp.from(ctx),
                    offsetY = 12.dp.from(ctx)
                ),
            shape = RoundedCornerShape(10.dp.from(ctx)),
            elevation = 0.dp.from(ctx)
        ) {
            Column (
                modifier = Modifier
                    .padding(
                        vertical = 18.dp.from(ctx),
                        horizontal = 8.dp.from(ctx)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CoilImage(
                        modifier = modifier
                            .clip(CircleShape)
                            .coloredShadow(MaterialTheme.colors.primary)
                            .width(66.dp.from(ctx))
                            .height(66.dp.from(ctx))
                            .align(Alignment.CenterVertically),
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
                }
                Spacer(modifier = Modifier.height(15.dp.from(ctx)))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = doctorName,
                        style = MaterialTheme.typography.body1.copy(
                            color = Heading,
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight(600)
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = specialistName,
                        style = MaterialTheme.typography.body1.copy(
                            color = Heading,
                            fontSize = 12.sp.from(ctx),
                            fontWeight = FontWeight(400)
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
//                Spacer(modifier = Modifier.height(6.dp.from(ctx)))
//                Row(
//                    horizontalArrangement = Arrangement.Center,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Box(
//                        Modifier
//                            .size(27.dp.from(ctx))
//                            .clip(CircleShape)
//                            .border(
//                                BorderStroke(
//                                    1.dp.from(ctx),
//                                    GreyBorder
//                                ), shape = CircleShape
//                            )
//                            .background(Color.Transparent)
//                            .clickable {
//                                onClickInstagram()
//                            },
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_instagram),
//                            contentDescription = "Icon Instagram",
//                            tint = Heading,
//                            modifier = Modifier.size(14.dp.from(ctx))
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(10.dp.from(ctx)))
//                    Box(
//                        Modifier
//                            .size(27.dp.from(ctx))
//                            .clip(CircleShape)
//                            .border(
//                                BorderStroke(
//                                    1.dp.from(ctx),
//                                    GreyBorder
//                                ), shape = CircleShape
//                            )
//                            .background(Color.Transparent)
//                            .clickable {
//                                onClickWhatsapp()
//                            },
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_whatsapp),
//                            contentDescription = "Icon Whatsapp",
//                            tint = Heading,
//                            modifier = Modifier.size(14.dp.from(ctx))
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(10.dp.from(ctx)))
//                    Box(
//                        Modifier
//                            .size(27.dp.from(ctx))
//                            .clip(CircleShape)
//                            .border(BorderStroke(1.dp.from(ctx), GreyBorder), shape = CircleShape)
//                            .background(Color.Transparent)
//                            .clickable {
//                                onClickFacebook()
//                            },
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_facebook),
//                            contentDescription = "Icon Facebook",
//                            tint = Heading,
//                            modifier = Modifier.size(14.dp.from(ctx))
//                        )
//                    }
//                }
            }
        }
    }
}