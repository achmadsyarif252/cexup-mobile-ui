package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.screen.SearchPatientUIState
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.corporate.theme.Heading
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    goToAddPatient: () -> Unit = {},
    goToPatient: (userCode: String) -> Unit = {},
    goToProfile: () -> Unit = {},
    checkUpPatient: (userCode: String) -> Unit = {},
    onSearchPatient: (name: String) -> Unit = {},
    searchPatientUIState: SearchPatientUIState,
) {
    Row(
    modifier = modifier
        .fillMaxWidth()
        .padding(top = 35.dp),
    horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_corporate),
            contentDescription = "",
            modifier = modifier
                .height(44.83.dp)
                .width(114.03.dp)
        )
        Spacer(modifier = modifier.width(64.45.dp))
        Box {
            SearchPatient(
                data = searchPatientUIState.data,
                onSearchPatient = { onSearchPatient(it) },
                onPatientDetail = { goToPatient(it) },
                onCheckUp = { checkUpPatient(it) }
            )
        }

        Spacer(modifier = Modifier.width(31.42.dp))

        Button(
            onClick = { goToAddPatient() },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(Heading),
            elevation = ButtonDefaults.elevation(2.dp),
            modifier = modifier
                .width(167.64.dp)
                .height(51.66.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_plus_white),
                contentDescription = "",
                modifier = modifier
                    .size(19.49.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(R.string.corporate_add_patient),
                fontSize = 16.sp,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(600)
                ),
                color = Color.White
            )

        }
        Spacer(modifier = Modifier.width(30.29.dp))
        CardNotificationBar()
        Spacer(modifier = Modifier.width(30.29.dp))
        CoilImage(
            modifier = modifier
                .clip(CircleShape)
                .coloredShadow(MaterialTheme.colors.primary)
                .size(51.66.dp)
                .clickable {
                    goToProfile()
                },
            imageModel = ImageBitmap.imageResource(R.drawable.dummy_profile),
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            // shows an image with a circular revealed animation.
            circularReveal = CircularReveal(duration = 250),
            // shows a placeholder ImageBitmap when loading.
            placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
            // shows an error ImageBitmap when the request failed.
            error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
        )
    }
}