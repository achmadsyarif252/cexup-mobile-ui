package com.cexup.ui.consumer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.theme.BlueUnselectedFillter
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleSelectable


@Composable
fun ChipFilter(
    modifier: Modifier = Modifier,
    text: String,
    marginStart: Dp = 0.dp,
    marginEnd: Dp = 0.dp,
    selected: Boolean = false,
    verticalPadding: Dp = 5.dp,
    onClick: () -> Unit = {}
) {
    val ctx = LocalContext.current


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = marginStart.from(ctx),
                end = marginEnd.from(ctx),
                top = 8.dp.from(ctx),
                bottom = 8.dp.from(ctx)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = modifier
                .noRippleSelectable(
                    selected = selected,
                    onClick = onClick
                )
                .background(
                    color = if (selected) Color.White else BlueUnselectedFillter,
                    shape = RoundedCornerShape(10.dp.from(ctx))
                )
                .border(
                    width = 1.dp,
                    color = if (selected) MaterialTheme.colors.onPrimary else Color.Transparent,
                    shape = RoundedCornerShape(10.dp.from(ctx))
                )

                .padding(
                    horizontal = 12.dp.from(ctx),
                    vertical = verticalPadding.from(ctx)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1.copy(
                    color = if (selected) MaterialTheme.colors.onPrimary else Color.Gray,
                    fontSize = 12.sp.from(ctx),
                    fontWeight = FontWeight(500),
                    lineHeight = 12.sp.from(ctx)
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewChipFilter() {
    ConsumerTheme {
        ChipFilter(text = "Tes aja")
    }
}