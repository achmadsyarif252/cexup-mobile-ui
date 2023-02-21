package com.cexup.ui.consumer.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.R
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.RedLight
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun CardHospitalMeetDoctor(
    modifier: Modifier = Modifier,
    hospitalThumb: String?,
    hospitalName: String,
    hospitalSlug: String,
    hospitalAddress: String?,
    onClick: (slug: String, nameHospital: String) -> Unit = { s, s2 -> }
){
    val ctx = LocalConfiguration.current
    Card(modifier = modifier
        .coloredShadow(
            color = Color.Black.copy(0.05f),
            alpha = 0.05f,
            offsetY = 0.dp,
            shadowRadius = 20.dp.from(ctx),
            borderRadius = 10.dp.from(ctx)
        )
        .fillMaxWidth()
        .height(112.dp.from(ctx))
        .clickable {
            onClick(hospitalSlug, hospitalName)
        },
        shape = RoundedCornerShape(12.dp.from(ctx)),
        elevation = 0.3.dp.from(ctx),

        )
    {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(88.dp.from(ctx))
                .padding(12.dp.from(ctx)),
            horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Image(
                modifier = Modifier.size(88.dp.from(ctx)),
                painter = rememberImagePainter(
                    data = hospitalThumb,
                    builder = {
                        crossfade(true)
                        error(R.drawable.dummy_product)
                        placeholder(R.drawable.dummy_product)
                }),
                contentDescription = "",
            )
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp.from(ctx))
            ) {
                Row {
                    Text(
                        text = hospitalName.capitalizeWords(),
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colors.onBackground,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.1.sp.from(ctx)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = hospitalAddress?.capitalizeWords() ?: "Empty",
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onSecondary,
                        fontSize = 12.sp.from(ctx),
                        lineHeight = 18.sp.from(ctx),
                        letterSpacing = 0.25.sp.from(ctx)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row (
                modifier = Modifier.padding(start = 2.dp.from(ctx))){
                    Image(
                        modifier = Modifier.size(height = 13.dp.from(ctx), width = 11.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_location_health),
                        contentDescription = "",
                    )
                    Spacer(modifier = Modifier.width(8.dp.from(ctx)))
                    Text(
                        text = "2 miles",
                        style = MaterialTheme.typography.body1.copy(
                            color = RedLight,
                            fontSize = 12.sp.from(ctx),
                            lineHeight = 18.sp.from(ctx),
                            letterSpacing = 0.25.sp.from(ctx)
                        )
                    )

                }
            }

        }
    }
}

@Composable
fun CardHospitalMeetDoctorShimmer(
){
    val ctx = LocalConfiguration.current
    val screenWidth = LocalContext.current
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    Card(modifier = Modifier
        .coloredShadow(
            color = Color.Black.copy(0.05f),
            alpha = 0.05f,
            offsetY = 0.dp,
            shadowRadius = 20.dp.from(ctx),
            borderRadius = 10.dp.from(ctx)
        )
        .fillMaxWidth()
        .height(112.dp.from(ctx)),
        shape = RoundedCornerShape(12.dp.from(ctx)),
        elevation = 0.3.dp.from(ctx),

        )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp.from(ctx))
                .padding(12.dp.from(ctx)),
            horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp.from(ctx)))
                    .size(88.dp.from(ctx))
                    .coloredShadow(MaterialTheme.colors.primary)
                    .fillMaxHeight()
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    ),
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row (
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp.from(ctx)))
                        .width(screenWidth / 2 - 10.dp)
                        .height(20.dp.from(ctx))
                        .coloredShadow(MaterialTheme.colors.primary)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = Color.LightGray,
                        ),
                ) {

                }
                Row (
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp.from(ctx)))
                        .width(screenWidth / 1.7f)
                        .height(36.dp.from(ctx))
                        .coloredShadow(MaterialTheme.colors.primary)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = Color.LightGray,
                        ),
                ){
                }
                Row (
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp.from(ctx)))
                        .width(screenWidth / 4)
                        .height(18.dp.from(ctx))
                        .coloredShadow(MaterialTheme.colors.primary)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = Color.LightGray,
                        ),
                ){
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewCardHospital() {
    ConsumerTheme {
        Column {
            CardHospitalMeetDoctor(
                hospitalThumb = "",
                hospitalName = "Rumah Orang Sakit",
                hospitalSlug = "",
                hospitalAddress = "Jalan Puri Indah Raya S-2 Jakarta Barat Daya, Indonesia"
            )
            Spacer(modifier = Modifier.height(20.dp))
            CardHospitalMeetDoctorShimmer()
        }

    }

}