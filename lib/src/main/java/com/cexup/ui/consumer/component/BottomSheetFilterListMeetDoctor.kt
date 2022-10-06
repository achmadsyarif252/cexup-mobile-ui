package com.cexup.ui.consumer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.consumer.theme.GreyDividerBottom
import com.cexup.ui.consumer.theme.inactive
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleSelectable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun BottomSheetFilterListMeetDoctor(
    modifier: Modifier = Modifier,
    listContent : List<String>,
    onCloseSheet : () -> Unit ={},
    onClick : (filtername : String) -> Unit = {}
){
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()

    var stateSelected by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .coloredShadow(color = Color.Gray, alpha = 0.2f)
            .clip(
                RoundedCornerShape(
                    topStart = 30.dp.from(ctx),
                    topEnd = 30.dp.from(ctx)
                )
            )
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(
                    topStart = 30.dp.from(ctx),
                    topEnd = 30.dp.from(ctx)
                )
            )
            .padding(bottom = 24.dp.from(ctx))
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp.from(ctx),
                    top = 30.dp.from(ctx),
                    end = 16.dp.from(ctx),
                    bottom = 30.dp.from(ctx)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_rectangle_bottomsheet),
                contentDescription = "",
                tint = Color.LightGray,
                modifier = androidx.compose.ui.Modifier
                    .width(36.dp.from(ctx))
                    .height(5.dp.from(ctx))
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)

            )

        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp.from(ctx)),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Filter",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp.from(ctx),
                        fontWeight = FontWeight(600),
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.3.sp.from(ctx)
                    )
                )
                Text(
                    text = "Reset",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 12.sp.from(ctx),
                        fontWeight = FontWeight(400),
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.5.sp.from(ctx),
                        color = MaterialTheme.colors.onPrimary
                    ),
                    modifier = modifier.clickable { /*TODO*/ }
                )
            }
            Spacer(modifier = Modifier.height(24.dp.from(ctx)))
            listContent.forEachIndexed{
                    index, s ->
                ContentBottomSheetFilterMeetDoctor(
                    nameContent = listContent[index],
                    modifier = modifier.selectable(
                        selected = stateSelected == index,
                        onClick = {
                            stateSelected = index
                            scope.launch {
                                onCloseSheet()
                                delay(200)
                                onClick(listContent[index])
                            }


                        }
                    ),
                )
            }
        }
    }
}

@Composable
fun ContentBottomSheetFilterMeetDoctor(
    modifier: Modifier = Modifier,
    nameContent: String = "",
) {
    val ctx = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .then(Modifier.padding(top = 16.dp.from(ctx), bottom = 8.dp.from(ctx))),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = nameContent.capitalizeWords(),
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 16.sp.from(ctx),
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp.from(ctx),
                    letterSpacing = 0.sp,
                    color = MaterialTheme.colors.onSecondary
                )
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_bottomsheet),
                contentDescription = "",
                modifier = modifier.size(19.dp.from(ctx))
            )

        }
        Divider(
            color = GreyDividerBottom
        )
    }
}
