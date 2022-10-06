package com.cexup.ui.consumer.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.Heading
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.mediaquery.from

@Composable
fun InputGender(
    selected:String?=null,
    items:List<String> = listOf(),
    onSelected:(String)->Unit={},
    modifier:Modifier=Modifier,
) {
    val ctx = LocalContext.current

    Column {
        Text(
            text = "Gender",
            style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.onBackground,
                fontSize = 16.sp.from(ctx),
                lineHeight = 24.sp.from(ctx)
            )
        )
        Spacer(modifier = modifier.height(16.dp.from(ctx)))
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, value ->
                Row(
                    modifier = modifier
                        .selectable(
                            selected = if (selected != null) value == selected else false,
                            onClick = {
                                onSelected(value)
                            }
                        )
                        .padding(end = 10.dp.from(ctx)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = if (selected != null) value == selected else false,
                        onClick = {  onSelected(value) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Heading,
                            disabledColor = Color.Gray,
                            unselectedColor = Heading,
                        )
                    )
                    Text(
                        text = value.capitalizeWords(),
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                            letterSpacing = 0.1.sp.from(ctx)
                        )
                    )
                }
                if (index == 0) {
                    Spacer(modifier = modifier.width(90.dp.from(ctx)))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGenderInput() {
    ConsumerTheme {
        InputGender(
            items = listOf("Laki-Laki","Perempuan")
        )
    }
}