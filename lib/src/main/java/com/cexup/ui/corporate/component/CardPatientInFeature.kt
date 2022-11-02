package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow

@Composable
fun CardPatientInFeature(
    modifier: Modifier = Modifier,
    thumb: String,
    name: String,
    id: Long,
) {
    Row(
        modifier = modifier
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = thumb, builder = {
                crossfade(true)
                placeholder(R.drawable.dummy_profile)
                error(R.drawable.dummy_profile)
            }), contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .coloredShadow(MaterialTheme.colors.primary)
                .width(64.dp)
                .height(64.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = modifier.width(15.dp))
        Column {
            Column {
                Text(
                    text = name.capitalizeWords(),
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight(700),
                    color = Heading
                )
                Text(
                    text = "ID $id",
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1,
                    color = inactive
                )
            }

        }
    }
}