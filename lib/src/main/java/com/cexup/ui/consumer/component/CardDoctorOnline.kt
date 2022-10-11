package com.cexup.ui.consumer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.GreenStatusOnline
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.cexup.ui.R
import com.cexup.ui.utils.capitalizeWords


@Composable
fun CardDoctorOnline(
    modifier: Modifier = Modifier,
    doctorThumb: String = "",
    doctorName: String = "",
    speciality: String = "",
    price: String = "",
    hospital: String = "",
    onClick: () -> Unit
) {
    val ctx = LocalContext.current


    Card(
        modifier = modifier
            .coloredShadow(
                color = Color.Black.copy(0.05f),
                alpha = 0.05f,
                offsetY = 0.dp,
                shadowRadius = 20.dp.from(ctx),
                borderRadius = 10.dp.from(ctx)
            )
            .fillMaxWidth()
            .height(128.dp.from(ctx))
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(12.dp.from(ctx)),
        elevation = 0.3.dp.from(ctx),

        )
    {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp.from(ctx),
                    vertical = 8.dp.from(ctx)
                ),
        ) {
            Image(
                painter = rememberImagePainter(
                    data = doctorThumb, 
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.dummy_doctor)
                        error(R.drawable.dummy_doctor)
                    }
                ),
                contentDescription = "",
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp.from(ctx)))
                    .width(115.dp.from(ctx))
                    .height(112.dp.from(ctx))
                    .coloredShadow(MaterialTheme.colors.primary)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(16.dp.from(ctx)))
            Column(
                modifier = modifier
                    .fillMaxHeight(),
            ) {
                Column {
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = doctorName.capitalizeWords(),
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colors.onBackground,
                                fontSize = 16.sp.from(ctx),
                                lineHeight = 24.sp.from(ctx)
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_bookmark),
                            contentDescription = doctorThumb,
                            modifier = modifier
                                .width(10.dp.from(ctx))
                                .height(13.dp.from(ctx))
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp.from(ctx)))
                    Text(
                        text = speciality.capitalizeWords(),
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.onPrimary,
                            fontSize = 12.sp.from(ctx),
                            lineHeight = 18.sp.from(ctx),
                            letterSpacing = 0.25.sp.from(ctx)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(12.dp.from(ctx)))
                    Text(
                        text = hospital.capitalizeWords(),
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.onBackground,
                            fontSize = 12.sp.from(ctx),
                            lineHeight = 18.sp.from(ctx),
                            letterSpacing = 0.25.sp.from(ctx)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = modifier
                                    .size(6.dp.from(ctx))
                                    .background(
                                        color = GreenStatusOnline,
                                        shape = CircleShape
                                    )
                            ) {

                            }
                            Spacer(modifier = Modifier.width(5.dp.from(ctx)))
                            Text(
                                text = "Online",
                                style = MaterialTheme.typography.subtitle1.copy(
                                    color = GreenStatusOnline,
                                    fontSize = 8.sp.from(ctx),
                                    lineHeight = 12.sp.from(ctx),
                                    letterSpacing = 0.8.sp.from(ctx)
                                )
                            )

                        }
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.ic_bag_health),
                                contentDescription = "",
                                modifier = modifier.size(12.dp.from(ctx))
                            )
                            Spacer(modifier = Modifier.width(5.dp.from(ctx)))
                            Text(
                                text = "5 Years",
                                style = MaterialTheme.typography.subtitle2.copy(
                                    color = MaterialTheme.colors.onSecondary,
                                    fontSize = 10.sp.from(ctx),
                                    lineHeight = 14.sp.from(ctx),
                                    letterSpacing = 0.5.sp.from(ctx)
                                )
                            )
                        }

                    }

                    Column(
                        modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Rp. 1000",
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 8.sp.from(ctx),
                                fontWeight = FontWeight.Normal,
                                lineHeight = 12.sp.from(ctx),
                                letterSpacing = 1.sp.from(ctx),
                                color = MaterialTheme.colors.onSecondary,
                            ),
                            textDecoration = TextDecoration.LineThrough
                        )
                        Text(
                            text = price,
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.onPrimary,
                                fontSize = 14.sp.from(ctx),
                                lineHeight = 20.sp.from(ctx),
                                letterSpacing = 0.1.sp.from(ctx),
                            )
                        )
                    }

                }


            }

        }

    }

}

@Composable
fun CardDoctorOnlineShimmer(
    modifier: Modifier = Modifier,
) {
    val ctx = LocalContext.current


    val screenWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density


    Card(
        modifier = modifier
            .coloredShadow(
                color = Color.Black.copy(0.05f),
                alpha = 0.05f,
                offsetY = 0.dp,
                shadowRadius = 20.dp.from(ctx),
                borderRadius = 10.dp.from(ctx)
            )
            .fillMaxWidth()
            .height(128.dp.from(ctx)),
        shape = RoundedCornerShape(12.dp.from(ctx)),
        elevation = 0.3.dp.from(ctx),

        )
    {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp.from(ctx), vertical = 8.dp.from(ctx)),
        ) {
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp.from(ctx)))
                    .height(112.dp.from(ctx))
                    .width(115.dp.from(ctx))
                    .coloredShadow(MaterialTheme.colors.primary)
                    .fillMaxHeight()
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    ),
            ) {

            }
            Spacer(modifier = Modifier.width(16.dp.from(ctx)))
            Column(
                modifier = modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {

                    Column(
                        modifier = modifier
                            .width(screenWidth / 2 - 10.dp)
                            .height(20.dp.from(ctx))
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                                color = Color.LightGray,
                                shape = RoundedCornerShape(6.dp.from(ctx))
                            )
                    ) {

                    }

                    Spacer(modifier = modifier.height(8.dp.from(ctx)))
                    Column(
                        modifier = modifier
                            .width(screenWidth / 3)
                            .height(20.dp.from(ctx))
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                                color = Color.LightGray
                            )
                    ) {

                    }

                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = modifier
                            .width(screenWidth / 4)
                            .height(20.dp.from(ctx))
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                                color = Color.LightGray,
                                shape = RoundedCornerShape(6.dp.from(ctx))
                            )
                    ) {

                    }



                    Column(
                        modifier
                            .width(screenWidth / 4)
                            .height(20.dp.from(ctx))
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                                color = Color.LightGray,
                                shape = RoundedCornerShape(6.dp.from(ctx))
                            ),
                        horizontalAlignment = Alignment.End
                    ) {

                    }

                }


            }

        }

    }

}

@Preview
@Composable
fun PreviewDoctorOnline() {
    ConsumerTheme {
        Column {
            CardDoctorOnline(
                doctorName = "dr. Trian damai",
                speciality = "Umum",
                price = "2.000.000",
                onClick = {

                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            CardDoctorOnlineShimmer()
        }

    }

}
