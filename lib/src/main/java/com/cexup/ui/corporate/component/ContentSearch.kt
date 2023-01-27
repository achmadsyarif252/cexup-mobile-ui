package com.cexup.ui.corporate.component

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.corporate.screen.SearchPatientUIState
import com.cexup.ui.corporate.theme.BlueDarkJade
import com.cexup.ui.corporate.theme.BlueLightJade
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleClick

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentSearch(
    list: List<Pair<String, String>>,
    onUpArrowClicked: (name: String) -> Unit = {},
    onSearchClicked: (userCode: String) -> Unit = {},
    onCheckupClicked: (userCode: String) -> Unit = {},
) {
    val ctx = LocalContext.current
    var a = LocalConfiguration.current.screenHeightDp

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx)),
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 10.dp.from(ctx))
    ) {
        list.forEach {
            var tempListNamePatient = it.first.split(" ")
            var namePatientFirstWord: String = ""
            run lit@{
                tempListNamePatient.forEachIndexed { index, it ->
                    namePatientFirstWord += it.first().toString().uppercase()
                    if (index == 1) {
                        return@lit
                    }
                }
            }
            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(MaterialThemeCexup.elevation.skim))
                    .clickable {
                        onSearchClicked(it.second)
                    }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp.from(ctx)),
                    painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = "Icon History",
                    tint = MaterialThemeCexup.colors.color.text.textMain
                )
                Spacer(modifier = Modifier.width(40.dp.from(ctx)))
                Text(
                    text = it.first,
                    style = MaterialThemeCexup.typography.hh2.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialThemeCexup.colors.color.text.textMain
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.clickable {
                        onCheckupClicked(it.second)
                    }
                        .padding(vertical = 8.dp.from(ctx)),
                    text = stringResource(id = R.string.check_up),
                    style = MaterialThemeCexup.typography.textButton2.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialThemeCexup.colors.color.primary.primaryMain
                    )
                )
                Spacer(modifier = Modifier.width(24.dp.from(ctx)))
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp.from(ctx))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    BlueDarkJade,
                                    BlueLightJade
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = namePatientFirstWord,
                        style = MaterialThemeCexup.typography.h6.copy(
                            color = Color.White
                        )
                    )
                }
                Spacer(modifier = Modifier.width(10.dp.from(ctx)))
                Box(
                    contentAlignment = Center,
                    modifier = Modifier
                        .size(48.dp.from(ctx))
                        .noRippleClick {
                            onUpArrowClicked(it.first)
                        }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp.from(ctx)),
                        painter = painterResource(id = R.drawable.ic_arrow_up),
                        contentDescription = "Icon Arrow Up",
                        tint = MaterialThemeCexup.colors.color.text.textMain
                    )
                }
            }
        }
    }
}