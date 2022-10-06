package com.cexup.ui.consumer.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun BottomSheetSortMeetDoctor(
    modifier: Modifier = Modifier,
    closed : () -> Unit
) {
    val ctx = LocalContext.current

    val screenWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density

    val listContent = listOf("Closest Distance", "A-Z", "Z-A")
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
            Text(
                text = "Sort",
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 14.sp.from(ctx),
                    fontWeight = FontWeight(600),
                    lineHeight = 20.sp.from(ctx),
                    letterSpacing = 0.3.sp.from(ctx)
                )
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
                            }
                        ),
                        selectState = stateSelected == index,
                    )
                }
            }

        }


    }
}

@Composable
fun ContentBottomSheetSortMeetDoctor(
    modifier: Modifier = Modifier,
    nameContent: String = "",
    selectState: Boolean = false,
) {
    val ctx = LocalContext.current


    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = nameContent.capitalizeWords(),
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 16.sp.from(ctx),
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp.from(ctx),
                    letterSpacing = 0.sp,
                    color = if (selectState) MaterialTheme.colors.onPrimary else inactive
                )
            )
            if (selectState) {
                Image(
                    painter = painterResource(id = R.drawable.ic_checklist_bottomsheet),
                    contentDescription = "",
                    modifier = modifier.size(19.dp.from(ctx))
                )
            }

        }
        Spacer(modifier = Modifier.height(8.dp.from(ctx)))
        Divider(
            color = GreyDividerBottom
        )
    }
}