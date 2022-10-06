package com.cexup.ui.consumer.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.utils.mediaquery.from
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

//get 30 days for reschedule
fun getDays(initialDate: LocalDate): List<LocalDate> {
    val days = mutableListOf<LocalDate>()
    for (i in 0..30) {
        days += initialDate.plusDays(i.toLong())
    }
    return days
}

@Composable
fun ScrollableDatePicker(
    initialDate:LocalDate=LocalDate.now(),
    selectedDate:LocalDate=LocalDate.now(),
    modifier:Modifier=Modifier,
    onItemClicked:(LocalDate)->Unit={}
) {
    val ctx = LocalContext.current
    val days = getDays(initialDate)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.text_available_scroll_date_picker),
            style = MaterialTheme.typography.h1.copy(
                fontSize = 16.sp.from(ctx),
                lineHeight = 24.sp.from(ctx),
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = modifier.padding(vertical = 16.dp)
        )
        Text(
            text = initialDate.month.getDisplayName(
                TextStyle.SHORT,
                Locale.ENGLISH
            ), style = MaterialTheme.typography.body1.copy(
                fontSize = 12.sp.from(ctx),
                lineHeight = 18.sp.from(ctx),
                letterSpacing = 0.25.sp.from(ctx),
                fontWeight = FontWeight.Normal,
            ),
            modifier = modifier.padding(
                vertical = 16.dp.from(ctx)
            )
        )
    }
    LazyRow(content = {
        items(days) { day ->
            ChipDateAvailableDoctor(
                day = day,
                isSelected = day == selectedDate
            ) {
                onItemClicked(day)
            }

        }
    })
}

@Preview
@Composable
fun PreviewScrollDatePicker() {
    ConsumerTheme {
        ScrollableDatePicker()
    }
}
