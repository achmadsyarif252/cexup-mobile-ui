package com.cexup.ui.consumer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.Search16

@Composable
fun InputSearch(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
    heightSearch: Dp = 25.dp,
    widthSearch: Dp = 149.dp,
    verticalPadding: Dp = 2.dp,
    horizontalPadding: Dp = 20.dp,
    fontHintSize: TextUnit = 12.sp,
    iconSize: Dp = 21.dp,
    trilling: Boolean = false
) {
    val ctx = LocalContext.current


    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = modifier
                .width(widthSearch.from(ctx))
                .height(heightSearch.from(ctx)),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                    onSearch(it)
                },
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(
                    Color.Black,
                    fontSize = fontHintSize
                ),
                modifier = Modifier
                    .width(widthSearch)
                    .height(heightSearch)
                    .shadow(5.dp.from(ctx), RoundedCornerShape(10.dp.from(ctx)))
                    .background(Color.White, RoundedCornerShape(10.dp.from(ctx)))
                    .padding(horizontal = horizontalPadding, vertical = verticalPadding)
                    .onFocusChanged {
                        when {
                            it.isFocused -> {
                                isHintDisplayed = false
                            }
                        }
                    }
                    .align(Alignment.Center)
            )
            if (isHintDisplayed) {
                Row(
                    modifier = modifier
                        .fillMaxHeight()
                        .padding(
                            horizontal = 20.dp.from(ctx)
                        ),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = hint,
                        modifier = Modifier.padding(vertical = verticalPadding),
                        style = MaterialTheme.typography.body2.copy(
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onSecondary,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                        )
                    )
                }
            }
            if (trilling) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding, vertical = verticalPadding),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Octicons.Search16,
                        contentDescription = "",
                        modifier = modifier
                            .size(iconSize.from(ctx)),
                        tint = MaterialTheme.colors.onSecondary
                    )}
            }
        }
    }


}