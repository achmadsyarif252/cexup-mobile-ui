package com.cexup.ui.corporate.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.corporate.screen.SearchPatientUIState
import com.cexup.ui.corporate.theme.BlueDarkJade
import com.cexup.ui.corporate.theme.BlueLightJade
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleClick
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    valueTextSearch: String = "",
    onValueChange: (value: String) -> Unit = {},
    goToProfile: () -> Unit = {},
    isSearch: Boolean = false,
    onSearchClicked: () -> Unit = {},
    onLogoClicked: () -> Unit = {},
    onBackIconClicked: () -> Unit = {},
) {
    val ctx = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp.from(ctx))
            .padding(horizontal = 28.dp.from(ctx)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSearch){
            IconButton(
                onClick = { onBackIconClicked() },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "arrow back search",
                    modifier = Modifier.size(24.dp.from(ctx))
                )
            }
            Spacer(modifier = Modifier.width(28.dp.from(ctx)))
            SearchPatientNew(
                valueTextSearch = valueTextSearch,
                onValueChange = {
                    onValueChange(it)
                }
            )
        }
        else{
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "menu",
                tint = MaterialThemeCexup.colors.color.text.textMain,
                modifier = Modifier.noRippleClick {
                    onLogoClicked()
                }
            )
            Spacer(modifier = modifier.width(24.dp.from(ctx)))
            Image(
                painter = painterResource(id = R.drawable.logo_corporate),
                contentDescription = "",
                modifier = modifier
                    .width(122.dp.from(ctx))
                    .height(34.16f.dp.from(ctx))
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search",
                    modifier = Modifier
                        .size(32.dp.from(ctx)),
                    tint = MaterialThemeCexup.colors.color.text.textMain
                )
            }
            Spacer(modifier = Modifier.width(24.dp.from(ctx)))
            CardNotificationBar()
            Spacer(modifier = Modifier.width(24.dp.from(ctx)))
            Box(
                modifier = Modifier
                    .coloredShadow(MaterialTheme.colors.primary)
                    .clip(CircleShape)
                    .size(51.66.dp.from(ctx))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                BlueDarkJade,
                                BlueLightJade
                            )
                        )
                    )
                    .clickable {
                        goToProfile()
                    },
            ) {
                CoilImage(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(21.33f.dp.from(ctx)),
                    imageModel = painterResource(id = R.drawable.ic_profile_dummy),
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    // shows an image with a circular revealed animation.
                    circularReveal = CircularReveal(duration = 250),
                    // shows a placeholder ImageBitmap when loading.
                    placeHolder = painterResource(id = R.drawable.ic_profile_dummy),
                    // shows an error ImageBitmap when the request failed.
                    error = painterResource(id = R.drawable.ic_profile_dummy)
                )
            }

        }
    }
}