package com.cexup.ui.corporate.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cexup.ui.corporate.component.CardAccount
import com.cexup.ui.utils.mediaquery.from
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
    val ctx = LocalConfiguration.current
    Column(
        Modifier.padding(
            start = 30.dp.from(ctx),
            end = 30.dp.from(ctx),
            top = 30.dp.from(ctx)
        )
    ) {

        CardAccount(
            name = nurseData.name,
            email = nurseData.email,
        )
    }
}