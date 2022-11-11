package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.GreyBorder
import com.cexup.ui.utils.coloredShadow
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import compose.icons.Octicons
import compose.icons.octicons.KebabHorizontal24
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.Heading

@Composable
fun ListUsgResult(
    modifier: Modifier = Modifier,
    patientName: String,
    patientId: String,
    onClickViewResult: () -> Unit,
    onClickKebabIcon: () -> Unit,
    onClickDownload: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, GreyBorder, RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier.padding(horizontal = 11.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CoilImage(
                    modifier = modifier
                        .clip(RoundedCornerShape(5.dp))
                        .coloredShadow(MaterialTheme.colors.primary)
                        .width(28.84.dp)
                        .height(28.84.dp),
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
                Spacer(modifier.width(17.dp))
                Text(
                    text = patientName,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    fontWeight = FontWeight(400),
                    modifier = modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.width(151.dp))
            Icon(
                modifier = modifier.clickable {
                    onClickDownload()
                },
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = "download",
                tint = MaterialTheme.colors.primaryVariant
            )
            Spacer(modifier = Modifier.width(30.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_bx_printer),
                contentDescription = "print",
                tint = MaterialTheme.colors.primaryVariant
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = "ID $patientId",
                fontSize = 16.sp,
                style = MaterialTheme.typography.body1,
                color = Heading,
                fontWeight = FontWeight(600),
                modifier = modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(120.dp))
            Text(text = "View Result",
                fontSize = 12.sp,
                style = MaterialTheme.typography.body1,
                color = if (true) Color.White else Color.Black,
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .background(
                        color = if (true) MaterialTheme.colors.primaryVariant else Color.White,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .width(97.dp)
                    .height(22.dp)
                    .padding(3.dp)
                    .clickable {
                        onClickViewResult()
                    }

            )
            Spacer(modifier = Modifier.width(37.dp))
            IconButton(onClick = { onClickKebabIcon() }) {
                Icon(
                    Octicons.KebabHorizontal24,
                    contentDescription = " Dot",
                )
            }
        }
    }

}