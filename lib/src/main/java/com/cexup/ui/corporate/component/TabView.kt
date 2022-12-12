package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.BackgroundLight
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.mediaquery.from
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

data class TabContentRow(
    var header: String,
    var content: @Composable () -> Unit,
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabView(
    tabContents: List<TabContentRow>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    colorUnderline: Color = SecondaryCorporate,
) {
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .clip(RoundedCornerShape(10.dp.from(ctx)))
                    .width(80.dp.from(ctx)),
                color = colorUnderline,
                height = 3.dp.from(ctx),
            )
        },
//            edgePadding = 0.dp.from(ctx),
    ) {
        tabContents.forEachIndexed { index, tab ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                modifier = Modifier.padding(0.dp.from(ctx)),
                text = {
                    Text(
                        text = tab.header,
                        color = when (pagerState.currentPage) {
                            index -> Heading
                            else -> inactive
                        },
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight(500),
                            textAlign = TextAlign.Start
                        ),
                    )
                },
                icon = {},
            )
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(
    tabContents: List<TabContentRow>,
    pagerState: PagerState,
) {
    HorizontalPager(
        state = pagerState,
        verticalAlignment = Alignment.Top,
        count = tabContents.size
    ) { page ->
        tabContents[page].content.invoke()
    }
}

@Composable
fun TabFeatures(
    selectedTabIndex: Int,
    onSelectedTab: (TabParameter) -> Unit,
    tabs: List<TabParameter>
) {
    val ctx = LocalContext.current
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .clip(RoundedCornerShape(7.dp.from(ctx)))
            .height(29.dp.from(ctx)),
        indicator = {},
        backgroundColor = BackgroundLight
    ) {
        tabs.forEachIndexed { index, tabParameter ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onSelectedTab(tabParameter) },
                text = {
                    Text(
                        text = tabParameter.parameter,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 12.sp.from(ctx),
                            color = if (selectedTabIndex == index) BackgroundLight else inactive
                        ),
                    )
                },
                modifier = Modifier.background(
                    if (selectedTabIndex == index) Heading else BackgroundLight
                ),
            )
        }
    }
}

enum class TabParameter(val parameter: String) {
    Heart("Heart"),
    Lungs("Lungs"),
    Abdomen("Abdomen"),
    Day("Day"),
    Week("Week"),
    Month("Month"),
    Details("Details"),
    Pictures("Pictures")
}
