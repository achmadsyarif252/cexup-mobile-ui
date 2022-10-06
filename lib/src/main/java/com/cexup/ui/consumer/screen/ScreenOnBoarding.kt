package com.cexup.ui.consumer.screen

import android.content.Context
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.consumer.component.ButtonTextPrimary
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.utils.mediaquery.from
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

/**
 * OnBoard Page
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 */
data class OnBoardingModelUIState(
    var text:Int,
    val description:Int,
    val image:Int
)


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScreenOnBoarding(
    modifier: Modifier = Modifier,
    data:List<OnBoardingModelUIState> = listOf(
        OnBoardingModelUIState(
            text = R.string.text_onboarding1,
            description = R.string.description_onboarding1,
            image = R.drawable.image_onboard1
        ),
        OnBoardingModelUIState(
            text = R.string.text_onboarding2,
            description = R.string.description_onboarding2,
            image = R.drawable.image_onboard2
        ),
        OnBoardingModelUIState(
            text = R.string.text_onboarding3,
            description = R.string.description_onboarding3,
            image = R.drawable.image_onboard3
        )
    ),
    toSignIn:()->Unit={}
) {
    val state = rememberPagerState()
    val ctx = LocalContext.current


    Box(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = state,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            count = data.size
        ) {
            OnBoardingItem(
                text= data[state.currentPage].text,
                description=data[state.currentPage].description,
                image=data[state.currentPage].image,
                ctx=ctx
            )
        }
        Column(
            modifier = modifier
                .padding(
                    start = 16.dp.from(ctx),
                    end = 16.dp.from(ctx),
                    bottom = 16.dp.from(ctx)

                )
                .align(Alignment.BottomCenter)
                .fillMaxHeight(fraction = 0.2f),
            verticalArrangement = Arrangement.Bottom
        ) {
            BottomSection(
                size = data.size,
                index = state.currentPage,
                ctx=ctx
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.Center
            ) {
                if (state.currentPage == data.lastIndex) {
                    ButtonTextPrimary(
                        enabled = true,
                        onClick = toSignIn,
                        text = stringResource(R.string.button_get_started)
                    )
                } else {
                    Text(
                        text = stringResource(R.string.text_swipe_left),
                        style = MaterialTheme.typography.h1.copy(
                            color = MaterialTheme.colors.onSecondary,
                            fontWeight = FontWeight.Medium
                        ),
                    )

                }

            }

        }

    }

}


@Composable
fun OnBoardingItem(
    modifier: Modifier = Modifier,
    text:Int,
    description:Int,
    image:Int,
    ctx:Context
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(24.dp.from(ctx))
        ) {
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.h4.copy(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.SemiBold
                ),
            )
            Spacer(modifier = modifier.height(19.dp.from(ctx)))
            Text(
                text = stringResource(id = description),
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSecondary,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                ),
            )
        }
        Spacer(modifier = Modifier.height(64.65.dp.from(ctx)))
        Image(
            painter = painterResource(id = image),
            contentDescription = "image $description",
            modifier = modifier
                .width(
                    263.dp.from(ctx)
                )
                .height(
                    263.dp.from(ctx)
                )
        )
    }
}

@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    size: Int,
    index: Int,
    ctx:Context
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(16.dp.from(ctx)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
        ) {
            repeat(size) {
                Spacer(modifier = Modifier.width(12.dp.from(ctx)))
                val width = animateDpAsState(
                    targetValue = if (it == index) 24.dp.from(ctx) else 12.dp.from(ctx),
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                )
                Box(
                    modifier = modifier
                        .height(4.dp.from(ctx))
                        .width(width.value)
                        .clip(RoundedCornerShape(3.dp.from(ctx)))
                        .background(
                            color =
                            if (it == index) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground.copy(
                                alpha = 0.5f
                            )
                        )
                ) {

                }

            }
        }
    }
}






@ExperimentalPagerApi
@Preview
@Composable
fun PreviewPageOnboard() {
    ConsumerTheme {
        ScreenOnBoarding()
    }
}


