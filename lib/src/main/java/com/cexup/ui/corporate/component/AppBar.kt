package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.screen.SearchPatientUIState
import com.cexup.ui.corporate.theme.BlueDarkJade
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.BlueLightJade
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    goToAddPatient: () -> Unit = {},
    goToPatient: (userCode: String) -> Unit = {},
    goToProfile: () -> Unit = {},
    checkUpPatient: (userCode: String) -> Unit = {},
    onSearchPatient: suspend (name: String) -> SearchPatientUIState = { _ ->
        SearchPatientUIState()
    },
    onLogoClicked: () -> Unit = {},
) {
    val ctx = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 35.dp.from(ctx)),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_corporate),
            contentDescription = "",
            modifier = modifier
                .height(44.83.dp.from(ctx))
                .width(114.03.dp.from(ctx))
                .clickable {
                    onLogoClicked()
                }
        )
        Spacer(modifier = modifier.width(64.45.dp.from(ctx)))
        Box {
            SearchPatient(
                onSearchPatient = { onSearchPatient(it) },
                onPatientDetail = { goToPatient(it) },
                onCheckUp = { checkUpPatient(it) }
            )
        }

        Spacer(modifier = Modifier.width(31.42.dp.from(ctx)))

        Button(
            onClick = { goToAddPatient() },
            shape = RoundedCornerShape(25.dp.from(ctx)),
            colors = ButtonDefaults.buttonColors(Heading),
            elevation = ButtonDefaults.elevation(2.dp.from(ctx)),
            modifier = modifier
                .width(167.64.dp.from(ctx))
                .height(51.66.dp.from(ctx))
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
            contentPadding = PaddingValues(0.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_plus_white),
                contentDescription = "",
                modifier = modifier
                    .size(19.49.dp.from(ctx))
            )
            Spacer(modifier = Modifier.width(6.dp.from(ctx)))
            Text(
                text = stringResource(R.string.corporate_add_patient),
                fontSize = 16.sp.from(ctx),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(600)
                ),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
        Spacer(modifier = Modifier.width(30.29.dp.from(ctx)))
        CardNotificationBar()
        Spacer(modifier = Modifier.width(30.29.dp.from(ctx)))
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
                modifier = Modifier.align(Alignment.Center),
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