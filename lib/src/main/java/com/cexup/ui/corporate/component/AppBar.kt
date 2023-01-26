package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.cexup.ui.utils.noRippleClick
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
            .padding(top = 32.dp.from(ctx))
            .padding(horizontal = 28.dp.from(ctx)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "menu",
            tint = BlueJade,
            modifier = Modifier.noRippleClick {
                onLogoClicked()
            }
        )
        Spacer(modifier = modifier.width(40.dp.from(ctx)))
        SearchPatient(
            onSearchPatient = { onSearchPatient(it) },
            onPatientDetail = { goToPatient(it) },
            onCheckUp = { checkUpPatient(it) }
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { goToAddPatient() },
            shape = RoundedCornerShape(4.dp.from(ctx)),
            colors = ButtonDefaults.buttonColors(BlueDarkJade),
            elevation = ButtonDefaults.elevation(2.dp.from(ctx)),
            modifier = modifier
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        ) {
            Text(
                text = stringResource(R.string.corporate_add_patient),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 20.sp.from(ctx),
                    letterSpacing = 0.14f.sp.from(ctx)
                ),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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
                modifier = Modifier.align(Alignment.Center)
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