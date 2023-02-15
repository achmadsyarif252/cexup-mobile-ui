package com.cexup.ui.consumer.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.Heading
import com.cexup.ui.utils.mediaquery.from
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipDateAvailableDoctor(
    modifier: Modifier = Modifier,
    day: LocalDate,
    isSelected: Boolean,
    onClicked:()->Unit
) {
    val ctx = LocalConfiguration.current

    Card(
        shape = RoundedCornerShape(11.dp.from(ctx)),
        elevation = 0.dp,
        backgroundColor = if (isSelected) Heading  else Color.White,
        onClick = onClicked,
        modifier = modifier.padding(end = 12.dp.from(ctx))
    ) {
        Column(
            modifier = modifier.padding(horizontal = 16.dp.from(ctx), vertical = 6.dp.from(ctx)),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = day.dayOfMonth.toString(),
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected) Color.White else Color.Black,
                    fontSize = 18.sp.from(ctx),
                    lineHeight = 28.sp.from(ctx),
                    letterSpacing = (-0.25).sp.from(ctx)
                )
            )
            Text(
                text = day.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Normal,
                    color = if (isSelected) Color.White else Color.Black,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 24.sp.from(ctx),
                    letterSpacing = 0.1.sp.from(ctx)
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewChipTimeAvailabilityDoctor() {
    ConsumerTheme {
        ChipDateAvailableDoctor(day = LocalDate.now(), isSelected = false) {

        }
    }
}