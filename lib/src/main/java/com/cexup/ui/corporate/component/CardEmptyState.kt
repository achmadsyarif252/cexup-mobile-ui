package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.theme.BlueJade
import com.cexup.ui.theme.GrayFont
import com.cexup.ui.theme.fontsCorp
import com.cexup.ui.utils.mediaquery.from

@Composable
fun CardEmptyState(
    drawable: Int,
    header: String = "",
    body: String = "",
) {
    val ctx = LocalConfiguration.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier.size(height = 204.dp.from(ctx), width = 271.dp.from(ctx))
            )
            Text(
                text = header,
                fontSize = 16.sp.from(ctx),
                fontFamily = fontsCorp,
                fontWeight = FontWeight.SemiBold,
                color = BlueJade
            )
            Text(
                modifier = Modifier.widthIn(
                    min = 0.dp.from(ctx),
                    max = 303.dp.from(ctx)
                ),
                text = body,
                fontSize = 12.sp.from(ctx),
                fontFamily = fontsCorp,
                fontWeight = FontWeight(600),
                color = GrayFont,
                textAlign = TextAlign.Center
            )
        }
    }
}