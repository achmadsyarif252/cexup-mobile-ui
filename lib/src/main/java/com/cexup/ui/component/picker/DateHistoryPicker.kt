package com.cexup.ui.component.picker

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.utils.mediaquery.from
import kotlin.math.pow
import kotlin.math.sqrt


@Composable
fun DateHistoryPicker(
    modifier: Modifier = Modifier,
    date:String="Mon,Sep 14",
    onClickCalender: () -> Unit,
    onNext:()->Unit,
    onPrev:()->Unit
){
    val ctx = LocalConfiguration.current

    //calender
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp.from(ctx), vertical = 16.dp.from(ctx)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconToggleButton(checked = false, onCheckedChange = {
            onPrev()
        }) {
            Icon(
                Icons.Filled.ArrowBackIos,
                contentDescription = stringResource(R.string.text_previous_date),
                tint = MaterialTheme.colors.onPrimary,
            )
        }
        Text(
            text = date,
            modifier = modifier
                .clickable { onClickCalender() },
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
            fontSize = 18.sp.from(ctx),
            fontWeight = FontWeight.Bold
        )
        IconToggleButton(checked = false, onCheckedChange = {
            onNext()
        }) {
            Icon(
                Icons.Filled.ArrowForwardIos,
                contentDescription = stringResource(R.string.text_next_date),
                tint = MaterialTheme.colors.onPrimary,
            )
        }

    }
}

@Preview(
    name = "preview DateHistoryPicker dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "preview DateHistoryPicker light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewDateHistoryPicker() {
    MaterialTheme {
        DateHistoryPicker(onClickCalender = { /*TODO*/ }, onNext = { /*TODO*/ }) {

        }
    }
}
