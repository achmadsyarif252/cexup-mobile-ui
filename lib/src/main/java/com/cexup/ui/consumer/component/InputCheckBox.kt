package com.cexup.ui.consumer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.Heading
import com.cexup.ui.utils.mediaquery.from

sealed class CheckboxStyle(var text:String){
    data class Text(var label:String):CheckboxStyle(label)
    data class Button(var label:String):CheckboxStyle(label)
}
@Composable
fun InputCheckBox(
    checked:Boolean=false,
    onCheckedChange:(Boolean)->Unit={},
    label:String="",
    modifier: Modifier=Modifier
){
    val ctx = LocalConfiguration.current
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
            text = label,
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

@Composable
fun InputCheckBox(
    checked:Boolean=false,
    labels:List<CheckboxStyle> = listOf(),
    onCheckedChange:(Boolean)->Unit={},
    onTextClick:(Int)->Unit={},
    modifier: Modifier=Modifier
){
    val ctx = LocalConfiguration.current
    val annotates = buildAnnotatedString {
        labels.forEachIndexed {
            index,data->
            when(data){
                is CheckboxStyle.Button -> {
                    append(" ")
                    pushStringAnnotation(
                        tag = "text_${index}",
                        annotation = "text_${index}"
                    )
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                        )
                    ){
                        append(data.text)
                    }
                    pop()
                }
                is CheckboxStyle.Text ->{
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.onBackground,
                        )
                    ){
                        append(data.text)
                    }
                }
            }
        }
    }
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

        ClickableText(
            text = annotates,
            style = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.onSecondary
            ),
            onClick = {
                offset->
                labels.forEachIndexed { index, checkbox ->
                    annotates.getStringAnnotations(
                        tag ="text_${index}",
                        start = offset,
                        end=offset
                    )
                        .firstOrNull()?.let {
                            onTextClick(index)
                        }
                }

            }
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
            InputCheckBox(
                label = "Remember me"
            )

            Spacer(modifier = Modifier.height(20.dp))
            InputCheckBox(
                labels = listOf(
                    CheckboxStyle.Text("Tes"),
                    CheckboxStyle.Button("Ini Button"),
                    CheckboxStyle.Text("Tes 1"),
                    CheckboxStyle.Button("Ini Button 1")
                ),
                onTextClick = {}
            )
        }

    }
}