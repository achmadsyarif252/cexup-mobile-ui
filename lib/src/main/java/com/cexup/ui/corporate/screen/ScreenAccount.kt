package com.cexup.ui.corporate.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.cexup.ui.corporate.component.CardAccount
import com.google.accompanist.pager.ExperimentalPagerApi

data class NurseData(
    var name: String = "Nurse name",
    var email: String = "Nurse email",
)

@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalComposeUiApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun ScreenAccount(
    nurseData: NurseData = NurseData(),
) {
    CardAccount(
        name = nurseData.name,
        email = nurseData.email,
    )
}