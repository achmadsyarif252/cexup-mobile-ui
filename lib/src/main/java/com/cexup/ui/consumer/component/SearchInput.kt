package com.cexup.ui.consumer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.consumer.theme.GreyOutline
import com.cexup.ui.consumer.theme.GreyPlaceHolder
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.Search16

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
    heightSearch: Dp = 25.dp,
    widthSearch: Dp = 149.dp,
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(widthSearch)
                .height(heightSearch + 15.dp.from(ctx)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                cursorColor = Color.Black,
                focusedIndicatorColor = GreyOutline,
                unfocusedIndicatorColor = GreyOutline
            ),
            shape = RoundedCornerShape(10.dp.from(ctx)),
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            textStyle = TextStyle(
                fontSize = fontHintSize
            ),
            maxLines = 1,
            placeholder = {
                if (isHintDisplayed) {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.body2.copy(
                            textAlign = TextAlign.Center,
                            color = GreyPlaceHolder,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                        )
                    )
                }
            },
            trailingIcon = {
                if (trilling)
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        modifier = Modifier.size(iconSize),
                        contentDescription = "")
            },

            )


    }


}