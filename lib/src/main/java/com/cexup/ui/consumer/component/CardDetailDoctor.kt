package com.cexup.ui.consumer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.R
import com.cexup.ui.consumer.theme.GreyPlaceHolder
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from

@Composable
fun CardDetailDoctor(
    modifier: Modifier = Modifier,
    doctorName: String = "",
    doctorThumb: String = "",
    speciality: String = "",
    hospital: String = "",
    doctorExperience: String = "",
){
    val ctx = LocalContext.current
    Card(
        modifier = modifier
            .coloredShadow(
                color = Color.Black.copy(0.05f),
                alpha = 0.05f,
                offsetY = 0.dp,
                shadowRadius = 20.dp,
                borderRadius = 10.dp
            )
            .fillMaxWidth()
            .height(136.dp.from(ctx)),
        shape = RoundedCornerShape(12.dp),
        elevation = 0.2.dp
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(21.dp.from(ctx))
        ) {
            Image(
                painter = rememberImagePainter(
                    data = doctorThumb, builder = {
                        crossfade(true)
                        placeholder(R.drawable.dummy_doctor)
                        error(R.drawable.dummy_doctor)
                    }),
                contentDescription = "",
                modifier = modifier
                    .clip(CircleShape)
                    .size(80.dp)
                    .coloredShadow(MaterialTheme.colors.primary),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = doctorName.capitalizeWords(),
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight.Medium,
                            lineHeight = 24.sp.from(ctx),
                            color = MaterialTheme.colors.onBackground
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = speciality.capitalizeWords(),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp.from(ctx),
                            lineHeight = 18.sp.from(ctx),
                            letterSpacing = 0.25f.sp.from(ctx),
                            color = GreyPlaceHolder
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = hospital.capitalizeWords(),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp.from(ctx),
                            lineHeight = 18.sp.from(ctx),
                            letterSpacing = 0.25f.sp.from(ctx),
                            color = GreyPlaceHolder
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp.from(ctx))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_bag_health),
                            contentDescription = "",
                            modifier = Modifier.size(12.dp),

                        )
                        Text(
                            text = doctorExperience.capitalizeWords(),
                            style = MaterialTheme.typography.subtitle2.copy(
                                fontSize = 10.sp.from(ctx),
                                lineHeight = 14.sp.from(ctx),
                                letterSpacing = 0.5f.sp.from(ctx),
                                color = MaterialTheme.colors.onBackground
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                }
                //TODO: remove
//                Column {
//                    Text(
//                        text = "Monday, 14 Feb 2022 09:30 - 09:45",
//                        style = MaterialTheme.typography.h1.copy(
//                            fontSize = 11.sp,
//                            fontWeight = FontWeight(400),
//                        ),
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                }


            }

        }
    }
}