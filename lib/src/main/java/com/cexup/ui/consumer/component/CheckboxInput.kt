package com.cexup.ui.consumer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.Heading
import com.cexup.ui.utils.mediaquery.from

@Composable
fun CheckboxInput(
    checked:Boolean=false,
    onCheckedChange:(Boolean)->Unit={},
    text:String="",
    modifier: Modifier=Modifier
){
    val ctx = LocalContext.current
    Row(
        modifier = modifier.padding(
            vertical = 4.dp.from(ctx),
            horizontal = 4.dp.from(ctx)
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(Heading),
            modifier = Modifier
                .size(16.dp.from(ctx))

        )

        Spacer(modifier = Modifier.width(8.dp.from(ctx)))

        Text(
            text = text,
            style = MaterialTheme.typography.body1.copy(
                fontSize = 12.sp.from(ctx),
                fontWeight = FontWeight.Medium,
                lineHeight = 14.sp.from(ctx),
                letterSpacing = 0.25.sp.from(ctx),
                color = MaterialTheme.colors.onSecondary
            )
        )
    }
}

@Preview
@Composable
fun PreviewCheckboxInput() {
    ConsumerTheme {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)) {
            CheckboxInput(
                text = "Remember me"
            )
        }

    }
}