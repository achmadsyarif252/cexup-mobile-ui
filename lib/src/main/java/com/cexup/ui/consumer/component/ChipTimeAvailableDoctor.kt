package com.cexup.ui.consumer.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.GrayFont
import com.cexup.ui.consumer.theme.Heading
import com.cexup.ui.utils.mediaquery.from
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer


@Composable
fun ChipTimeAvailableDoctor(
    modifier: Modifier = Modifier,
    time:String="",
    onSelected: Boolean,
    onClick:()->Unit
) {
    val ctx = LocalContext.current

    Card(
        shape = RoundedCornerShape(10.dp.from(ctx)),
        border = BorderStroke(1.dp.from(ctx), color = if (onSelected) Heading else Color.Transparent),
        elevation = 0.dp,
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.body1.copy(
                color = if (onSelected) Heading else GrayFont,
                fontSize = 12.sp.from(ctx),
                lineHeight = 18.sp.from(ctx),
                letterSpacing = 0.25.sp.from(ctx)
            ),
            textAlign = TextAlign.Center,
            modifier = modifier.padding(horizontal = 20.dp.from(ctx), vertical = 6.dp.from(ctx))
        )
    }
}

@Composable
fun ChipTimeAvailableShimmer(
    modifier: Modifier = Modifier,
) {
    val ctx = LocalContext.current

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp.from(ctx))
            .padding(
                all = 2.dp.from(ctx)
            )
            .placeholder(
                visible = true,
                highlight = PlaceholderHighlight.shimmer(),
                color = Color.LightGray,
                shape = RoundedCornerShape(10.dp.from(ctx))
            )
    ) {
        Column(
            modifier = modifier.padding(horizontal = 20.dp.from(ctx), vertical = 6.dp.from(ctx))
        ){

        }
    }
}

@Preview
@Composable
fun PreviewChipTimeAvailability(){
    ConsumerTheme {
        Column {
            ChipTimeAvailableDoctor(
                time = "",
                onSelected = false,
                onClick = {}
            )
            ChipTimeAvailableShimmer()
        }
    }
}

