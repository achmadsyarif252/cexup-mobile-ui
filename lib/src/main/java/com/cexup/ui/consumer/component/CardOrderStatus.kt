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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.utils.mediaquery.from
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import kotlin.math.pow
import kotlin.math.sqrt
import com.cexup.ui.R
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.GreenStatusOnline
import com.cexup.ui.consumer.theme.RedLight

@Composable
fun CardOrderStatus(
modifier: Modifier=Modifier,
orderSuccess:Boolean=true,
isLoading:Boolean=false,
message:String=""
) {
    val ctx = LocalContext.current
    val currentHeight = ctx.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density
    val screenWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density

    val screenHeight = ctx
        .resources
        .displayMetrics.heightPixels.dp /
            LocalDensity.current.density

    val a = screenHeight.value.toDouble().pow(2.0)
    val b = screenWidth.value.toDouble().pow(2.0)
    val currentDiagonal = sqrt(a + b).dp
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(170.dp.from(ctx)),
        shape = RoundedCornerShape(16.dp.from(ctx)),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onPrimary,
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id =if(orderSuccess) R.drawable.bg_card_order_status_success else R.drawable.bg_card_order_status_failed),
                    contentDescription = "",
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }

            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp.from(ctx), top = 16.dp.from(ctx))
            ) {
                Box(
                    modifier = modifier
                        .size(49.43.dp.from(ctx))
                        .background(
                            color = if (orderSuccess) GreenStatusOnline else RedLight,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .placeholder(
                            visible = isLoading,
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight.shimmer()
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = if(orderSuccess) R.drawable.ic_checkmark else R.drawable.ic_crossmark),
                        contentDescription = "",
                        modifier = modifier.size(
                            (if(orderSuccess) 44.93.dp else 20.19.dp).from(ctx)
                        ),
                        tint = Color.White
                    )
                }

                Spacer(modifier = modifier.height(18.dp.from(ctx)))
                Column {
                    Text(
                        text = if(orderSuccess) "Success" else "Failed",
                        style = MaterialTheme.typography.h3.copy(
                            fontSize = 22.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            letterSpacing = (-0.5).sp.from(ctx)
                        ),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = modifier.placeholder(
                            visible = isLoading,
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight.shimmer(),
                            shape = RoundedCornerShape(4.dp.from(ctx))
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp.from(ctx)))
                    Text(
                        text = if(orderSuccess) "Your order has been placed!" else message.ifBlank { "Your order failed!" },
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp.from(ctx),
                            lineHeight = 16.sp.from(ctx),
                            letterSpacing = 0.5.sp.from(ctx)
                        ),
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = modifier.placeholder(
                            visible = isLoading,
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight.shimmer(),
                            shape = RoundedCornerShape(4.dp.from(ctx))

                        )
                    )
                }

            }
        }

    }
}

@Preview
@Composable
fun PreviewCardStatusOrder() {
    ConsumerTheme {
        Column {
            CardOrderStatus(orderSuccess = true)
            Spacer(modifier = Modifier.height(10.dp))
            CardOrderStatus(orderSuccess = true, isLoading = true)
            Spacer(modifier = Modifier.height(10.dp))
            CardOrderStatus(orderSuccess = false)
        }
    }
}