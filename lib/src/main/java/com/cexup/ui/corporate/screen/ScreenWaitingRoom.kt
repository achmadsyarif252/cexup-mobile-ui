package com.cexup.ui.corporate.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from

@Composable
fun ScreenWaitingRoom(
    onButtonBackPressed: () -> Unit,
) {
    val ctx = LocalConfiguration.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp.from(ctx))
            .padding(bottom = 28.dp.from(ctx)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.doctor_information),
                style = MaterialThemeCexup.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialThemeCexup.colors.color.text.textMain
            )
            Button(
                onClick = { onButtonBackPressed() },
                contentPadding = PaddingValues(
                    vertical = 8.dp.from(ctx),
                    horizontal = 12.dp.from(ctx)
                ),
                colors = ButtonDefaults.buttonColors(MaterialThemeCexup.colors.palette.tertiary.redTertiary6),
                shape = RoundedCornerShape(4.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.back),
                    style = MaterialThemeCexup.typography.textButton2.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialThemeCexup.colors.palette.neutral.neutral1
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp.from(ctx)))
        Image(
            painter = painterResource(id = R.drawable.bg_wait_moment),
            contentDescription = "Background Wait meeting"
        )
        Spacer(modifier = Modifier.height(36.dp.from(ctx)))
        Text(
            text = stringResource(id = R.string.wait_a_moment),
            style = MaterialThemeCexup.typography.h4.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialThemeCexup.colors.color.primary.primaryMain
        )
        Text(
            text = stringResource(id = R.string.please_wait_a_moment_desc),
            style = MaterialThemeCexup.typography.hh1,
            color = MaterialThemeCexup.colors.palette.grey.grey6
        )
        Spacer(modifier = Modifier.height(65.dp.from(ctx)))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
            contentPadding = PaddingValues(vertical = 16.dp.from(ctx)),
            shape = RoundedCornerShape(4.dp.from(ctx)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialThemeCexup.colors.color.primary.primaryMain,
                disabledBackgroundColor = MaterialThemeCexup.colors.color.primary.primaryMain.copy(
                    alpha = 0.5f
                )
            ),
            enabled = false
        ) {
            Text(
                text = stringResource(id = R.string.join_meeting),
                style = MaterialThemeCexup.typography.textButton1.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialThemeCexup.colors.palette.neutral.neutral1
            )
        }
    }
}