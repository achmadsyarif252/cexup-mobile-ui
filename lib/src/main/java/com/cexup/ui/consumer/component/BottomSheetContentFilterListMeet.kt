package com.cexup.ui.consumer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleSelectable
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun BottomSheetContentFilterListMeet(
    modifier: Modifier = Modifier,
    nameBottomSheet : String = "",
    listContent : List<String> = listOf(),
    onClosedSheet : () -> Unit ={},
){
    val ctx = LocalConfiguration.current

    val screenWidth = LocalContext.current
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    

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
                modifier = Modifier
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
                    text = nameBottomSheet.capitalizeWords(),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp.from(ctx),
                        fontWeight = FontWeight(600),
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.3.sp.from(ctx)
                    )
                )
                Text(
                    text = stringResource(id = R.string.reset),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 12.sp.from(ctx),
                        fontWeight = FontWeight(400),
                        lineHeight = 20.sp.from(ctx),
                        letterSpacing = 0.5.sp.from(ctx),
                        color = MaterialTheme.colors.onPrimary
                    ),
                    modifier = modifier.clickable {  }
                )
            }
            Spacer(modifier = Modifier.height(24.dp.from(ctx)))
            InputSearch(
                hint = stringResource(id = R.string.search)+" "+nameBottomSheet.capitalizeWords(),
                trilling = true,
                widthSearch = screenWidth,
                heightSearch = 40.dp.from(ctx),
                iconSize = 16.dp.from(ctx),
                fontHintSize = 14.sp.from(ctx),
                )
            Spacer(modifier = Modifier.height(24.dp.from(ctx)))
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
            ) {
                listContent.forEachIndexed { index, s ->
                    ContentBottomSheetSortMeetDoctor(
                        nameContent = listContent[index],
                        modifier = modifier.noRippleSelectable(
                            selected = stateSelected == index,
                            onClick = {
                                stateSelected = index
                                onClosedSheet()
                            }
                        ),
                        selectState = stateSelected == index
                    )
                }
            }

        }


    }
}

//