package com.cexup.ui.consumer.component

import androidx.compose.foundation.Image
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
import com.cexup.ui.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.consumer.theme.BlueJade
import com.cexup.ui.consumer.theme.GreyPlaceHolder
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from

@Composable
fun CardPatientDetail(
    modifier: Modifier = Modifier,
    namePatient: String = "Empty name",
    age: String = "Empty age",
    gender: String = "Empty gender",
    simptons: String = "Empty simptons",
    thumb: String = "",
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
            .height(81.dp.from(ctx)),
        shape = RoundedCornerShape(12.dp.from(ctx)),
        elevation = 0.2.dp.from(ctx)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp.from(ctx)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = thumb, builder = {
                        crossfade(true)
                        placeholder(R.drawable.dummy_doctor)
                        error(R.drawable.dummy_doctor)
                    }),
                contentDescription = "",
                modifier = modifier
                    .clip(CircleShape)
                    .size(49.dp.from(ctx))
                    .coloredShadow(MaterialTheme.colors.primary),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(16.dp.from(ctx)))

            Column {
                Text(
                    text = namePatient.capitalizeWords(),
                    style = MaterialTheme.typography.body2.copy(
                        fontSize = 14.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.1.sp.from(ctx),
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colors.onBackground
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$age years old, $gender",
                    style = MaterialTheme.typography.subtitle2.copy(
                        fontSize = 10.sp.from(ctx),
                        lineHeight = 14.sp.from(ctx),
                        letterSpacing = 0.5.sp.from(ctx),
                        color = GreyPlaceHolder
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.symptoms),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontSize = 8.sp.from(ctx),
                        lineHeight = 12.sp.from(ctx),
                        letterSpacing = 0.8.sp.from(ctx),
                        color = GreyPlaceHolder
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = simptons,
                    style = MaterialTheme.typography.body2.copy(
                        fontSize = 14.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        fontWeight = FontWeight.Medium,
                        color = BlueJade
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }

        }

    }
}