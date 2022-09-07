package com.cexup.ui.consumer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.SecondaryBackGround
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.mediaquery.from

@Composable
fun ChipHospital(
    modifier: Modifier=Modifier,
    hospitalName:String=""
) {
    val ctx = LocalContext.current
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    10.dp.from(ctx)
                )
            )
            .background(SecondaryBackGround)
    ) {
        Row(
            modifier = modifier.padding(
                8.dp.from(ctx)
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(id = R.drawable.ic_pin_health),
                "",
                modifier = modifier.height(
                    11.dp.from(ctx)
                ),
                tint = Color.Unspecified
            )
            Spacer(
                modifier = modifier.width(
                    5.dp.from(ctx)
                )
            )
            Text(
                text = hospitalName.capitalizeWords(),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp.from(ctx),
                    lineHeight = 18.sp.from(ctx),
                    letterSpacing = 0.25.sp.from(ctx)
                ),
            )
        }
    }
}

@Preview
@Composable
fun PreviewChipHospital() {
    ConsumerTheme {
        ChipHospital()
    }
}