package com.cexup.ui.consumer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.R
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.GrayFont
import com.cexup.ui.consumer.theme.Heading
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import compose.icons.Octicons
import compose.icons.octicons.Bookmark24

@Composable
fun CardDoctorInformation(
    modifier:Modifier=Modifier,
    doctorImage:String="",
    doctorName:String="",
    doctorSpecialist:String="",
    doctorExperience:String=""
){
    val ctx = LocalContext.current
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(12.dp.from(ctx)),
        modifier = modifier
            .fillMaxWidth()
            .coloredShadow(
                color = Color.Black,
                borderRadius = 6.dp.from(ctx),
                shadowRadius = 6.dp.from(ctx),
                alpha = .1f
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(18.dp.from(ctx))
        ) {
            Image(
                painter = rememberImagePainter(
                    data = doctorImage,
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.dummy_doctor)
                        error(R.drawable.dummy_doctor)
                    }),
                contentDescription = "",
                modifier = modifier
                    .width(80.dp.from(ctx))
                    .height(80.dp.from(ctx))
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(
                modifier = modifier.width(
                    16.dp.from(ctx)
                )
            )
            Column {
                Column(
                    modifier = modifier.padding(
                        0.dp,
                        0.dp,
                        0.dp,
                        10.dp.from(ctx)
                    )
                ) {
                    Text(
                        doctorName.capitalizeWords(),
                        style = MaterialTheme.typography.h2.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = modifier.padding(0.dp)
                    )
                    Text(
                        doctorSpecialist,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colors.onSecondary,
                        ),
                        modifier = modifier.padding(0.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = modifier
                            .clip(
                                RoundedCornerShape(
                                    4.dp.from(ctx)
                                )
                            )
                            .background(Heading),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = modifier.padding(
                                horizontal = 10.dp.from(ctx),
                                vertical = 4.dp.from(ctx)
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_bag_health),
                                "",
                                modifier = modifier
                                    .width(
                                        12.dp.from(ctx)
                                    ),
                                tint = MaterialTheme.colors.onPrimary
                            )
                            Spacer(
                                modifier = modifier.width(
                                    5.dp.from(ctx)
                                )
                            )
                            Text(
                                text = doctorExperience,
                                style = MaterialTheme.typography.subtitle1.copy(
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White,
                                )
                            )
                        }
                    }
                    Spacer(
                        modifier = modifier.width(
                            10.dp.from(ctx)
                        )
                    )
                    Icon(
                        Octicons.Bookmark24,
                        "",
                        modifier = modifier.height(
                            18.dp.from(ctx)
                        ),
                        tint = GrayFont
                    )
                }
            }
        }
    }
}

@Composable
fun CardDoctorInformationShimmer(
    modifier:Modifier=Modifier,
){
    val ctx = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp.from(ctx)))
            .background(MaterialTheme.colors.background)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(18.dp.from(ctx))
        ) {
            Column(
                modifier = modifier
                    .width(80.dp.from(ctx))
                    .height(80.dp.from(ctx))
                    .clip(CircleShape)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    )
            ){}
            Spacer(
                modifier = modifier.width(
                    16.dp.from(ctx)
                )
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Column(
                    modifier = modifier.padding()
                ) {
                    Column(
                        modifier = modifier
                            .height(25.dp.from(ctx))
                            .width(60.dp.from(ctx))
                            .clip(
                                RoundedCornerShape(
                                    4.dp.from(ctx)
                                )
                            )
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                                color = Color.LightGray,
                            )
                    ){}
                    Spacer(modifier = modifier.height(8.dp))
                    Column(
                        modifier = modifier
                            .height(20.dp.from(ctx))
                            .width(70.dp.from(ctx))
                            .clip(
                                RoundedCornerShape(
                                    4.dp.from(ctx)
                                )
                            )
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                                color = Color.LightGray,
                            )
                    ){}
                }

                    Box(
                        modifier = modifier
                            .height(20.dp.from(ctx))
                            .width(40.dp)
                            .clip(
                                RoundedCornerShape(
                                    4.dp.from(ctx)
                                )
                            )
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                                color = Color.LightGray,
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                    }


            }
        }
    }
}

@Preview
@Composable
fun PreviewCardDoctorInformation() {
    ConsumerTheme {
        Column {
            CardDoctorInformation(
                doctorName = "Trian",
                doctorExperience = "6 Years",
                doctorSpecialist = "Umum",
                doctorImage = ""
            )
            Spacer(modifier = Modifier.height(20.dp))

            CardDoctorInformationShimmer()
        }
    }
}