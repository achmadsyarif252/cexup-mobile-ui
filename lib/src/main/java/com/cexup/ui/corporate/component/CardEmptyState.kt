package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.GrayFont
import com.cexup.ui.corporate.theme.fontsCorp

@Composable
fun CardEmptyState(
    drawable: Int,
    header: String = "",
    body: String = "",
) {
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
                modifier = Modifier.size(height = 204.dp, width = 271.dp)
            )
            Text(
                text = header,
                fontSize = 16.sp,
                fontFamily = fontsCorp,
                fontWeight = FontWeight.SemiBold,
                color = BlueJade
            )
            Text(
                modifier = Modifier.widthIn(
                    min = 0.dp,
                    max = 303.dp
                ),
                text = body,
                fontSize = 12.sp,
                fontFamily = fontsCorp,
                fontWeight = FontWeight(600),
                color = GrayFont,
                textAlign = TextAlign.Center
            )
        }
    }
}