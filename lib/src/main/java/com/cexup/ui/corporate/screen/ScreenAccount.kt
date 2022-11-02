package com.cexup.ui.corporate.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.cexup.ui.corporate.component.CardAccount
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalComposeUiApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun ScreenAccount(
    modifier: Modifier = Modifier,
) {
    CardAccount()
}