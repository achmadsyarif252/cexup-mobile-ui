package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.inactive
import compose.icons.Octicons
import compose.icons.octicons.Search16

@Composable
fun SearchBar(
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
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Box(
        modifier = modifier.height(heightSearch),
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
            textStyle = TextStyle(Color.Black, fontSize = fontHintSize),
            modifier = Modifier
                .width(widthSearch)
                .height(heightSearch)
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
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
                        horizontal = 20.dp
                    ),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (trilling) {
                    Icon(
                        imageVector = Octicons.Search16,
                        contentDescription = "",
                        modifier = modifier
                            .size(iconSize),
                        tint = inactive
                    )
                    Spacer(modifier = modifier.width(3.dp))
                }
                Text(
                    text = hint,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = verticalPadding),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = fontHintSize,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }

}
