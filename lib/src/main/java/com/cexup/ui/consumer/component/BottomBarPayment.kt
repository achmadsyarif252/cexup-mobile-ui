package com.cexup.ui.consumer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.Heading
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.formatToRupiah
import com.cexup.ui.utils.mediaquery.from
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

@Composable
fun BottomBarPayment(
    modifier:Modifier=Modifier,
    price:String="",
    enabled:Boolean=false,
    onClick:()->Unit
) {
    val ctx = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .coloredShadow(
                color = Color.Black,
                shadowRadius = 10.dp.from(ctx),
                borderRadius = 8.dp.from(ctx),
                alpha = .1f
            ),
        elevation = 0.dp,
        shape = RoundedCornerShape(topStart = 8.dp.from(ctx), topEnd = 8.dp.from(ctx)),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp.from(ctx)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Total:",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp.from(ctx),
                        lineHeight = 18.sp.from(ctx),
                        letterSpacing = 0.25.sp.from(ctx),
                        color = Color.Black
                    )
                )
                Text(
                    text = price,
                    style = MaterialTheme.typography.h2.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp.from(ctx),
                        lineHeight = 28.sp.from(ctx),
                        letterSpacing = (-0.25).sp.from(ctx),
                        color = MaterialTheme.colors.onPrimary
                    )
                )
            }
            TextButton(
                enabled =enabled,
                onClick = {
                    onClick()
                },
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp.from(ctx)))
                    .background(Heading)
            ) {
                Text(
                    text = "Pay now",
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.1.sp.from(ctx),
                        color = Color.White
                    ),
                    modifier = modifier.padding(horizontal = 10.dp.from(ctx))
                )
            }

        }
    }
}

@Composable
fun BottomBarPaymentShimmer(
    modifier: Modifier=Modifier
) {
    val ctx = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .coloredShadow(
                color = Color.Black,
                shadowRadius = 10.dp.from(ctx),
                borderRadius = 8.dp.from(ctx),
                alpha = .1f
            ),
        elevation = 0.dp,
        shape = RoundedCornerShape(topStart = 8.dp.from(ctx), topEnd = 8.dp.from(ctx)),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp.from(ctx)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Column(
                    modifier = modifier
                        .height(
                            20.dp
                        )
                        .width(70.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = Color.Gray
                        )
                ){}
                Spacer(modifier = modifier.height(8.dp))
                Column(
                    modifier = modifier
                        .height(
                            20.dp
                        )
                        .width(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = Color.Gray
                        )
                ){}
            }
            Column(
                modifier = modifier
                    .height(
                        40.dp
                    )
                    .width(90.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.Gray
                    )
            ) {

            }

        }
    }
}

@Preview
@Composable
fun PreviewBottomBarPayment() {
    ConsumerTheme {
        Column {
            BottomBarPayment() {

            }
            Spacer(modifier = Modifier.height(20.dp))
            BottomBarPaymentShimmer()
        }
    }
}