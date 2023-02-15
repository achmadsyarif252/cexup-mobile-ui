package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.component.common.AvatarCexup
import com.cexup.ui.corporate.theme.BlueDarkJade
import com.cexup.ui.corporate.theme.BlueLightJade
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.mediaquery.from

//enum class TypePhysicalExamination {
//    OneValue,
//    BloodPressure,
//    NoValue,
//}

@Composable
fun CardPhysicalExamination(
    icon: Int,
    namePhysicalExamination: String,
    valueRisk: String,
    onClick: () -> Unit,
) {
    val ctx = LocalConfiguration.current
    Card(
        shape = RoundedCornerShape(16.dp.from(ctx)),
        elevation = MaterialThemeCexup.elevation.skim
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .padding(16.dp.from(ctx)),
        ) {
            Image(
                modifier = Modifier.size(40.dp.from(ctx)),
                painter = painterResource(id = icon),
                contentDescription = "Icon $namePhysicalExamination",
            )
            Spacer(modifier = Modifier.height(16.dp.from(ctx)))
            Text(
                text = namePhysicalExamination,
                style = MaterialThemeCexup.typography.hh1.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialThemeCexup.colors.color.text.textMain
            )
            Spacer(modifier = Modifier.height(4.dp.from(ctx)))
            Text(
                text = valueRisk,
                style = MaterialThemeCexup.typography.hh3,
                color = MaterialThemeCexup.colors.color.text.textSecondary
            )
        }
//        when (typePhysicalExamination) {
//            TypePhysicalExamination.OneValue -> {
//                Column(
//                    modifier = modifier
//                        .width(150.dp.from(ctx))
//                        .height(180.dp.from(ctx))
//                        .clickable {
//                            onClick()
//                        }
//                        .background(
//                            color = MaterialTheme.colors.primaryVariant
//                        )
//                        .padding(horizontal = 13.dp.from(ctx), vertical = 9.dp.from(ctx))
//                ) {
//                    Image(
//                        painter = painterResource(
//                            id = icon
//                        ),
//                        contentDescription = "",
//                        modifier = modifier.size(21.43.dp.from(ctx)),
//                    )
//                    Column(
//                        modifier = modifier.fillMaxHeight(),
//                        verticalArrangement = Arrangement.Bottom
//                    ) {
//                        Text(
//                            text = namePhysicalExamination,
//                            fontSize = 16.sp.from(ctx),
//                            style = MaterialTheme.typography.body1.copy(
//                                fontWeight = FontWeight(400),
//                                color = Color.White
//                            ),
//                        )
//                        Text(
//                            text = "$value $unit",
//                            fontSize = 22.sp.from(ctx),
//                            style = MaterialTheme.typography.body1.copy(
//                                fontWeight = FontWeight(500),
//                                color = Color.White
//                            ),
//                        )
//                    }
//
//                }
//            }
//            TypePhysicalExamination.BloodPressure -> {
//                Column(
//                    modifier = modifier
//                        .width(150.dp.from(ctx))
//                        .height(180.dp.from(ctx))
//                        .background(
//                            color = MaterialTheme.colors.primaryVariant
//                        )
//                        .padding(horizontal = 13.dp.from(ctx), vertical = 9.dp.from(ctx))
//                ) {
//                    Image(
//                        painter = painterResource(
//                            id = icon
//                        ),
//                        contentDescription = "",
//                        modifier = modifier.size(21.43.dp.from(ctx))
//                    )
//                    Column(
//                        modifier = modifier.fillMaxHeight(),
//                        verticalArrangement = Arrangement.Bottom
//                    ) {
//                        Text(
//                            text = namePhysicalExamination,
//                            fontSize = 16.sp.from(ctx),
//                            style = MaterialTheme.typography.body1.copy(
//                                fontWeight = FontWeight(400),
//                                color = Color.White
//                            ),
//                        )
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(
//                                text = "$value/$value2",
//                                fontSize = 22.sp.from(ctx),
//                                style = MaterialTheme.typography.body1.copy(
//                                    fontWeight = FontWeight(500),
//                                    color = Color.White
//                                ),
//                            )
//                            Spacer(modifier = Modifier.width(5.dp.from(ctx)))
//                            Text(
//                                text = unit,
//                                fontSize = 12.sp.from(ctx),
//                                style = MaterialTheme.typography.body1.copy(
//                                    fontWeight = FontWeight(300),
//                                    color = Color.White
//                                ),
//                            )
//                        }
//
//                    }
//
//                }
//            }
//
//            TypePhysicalExamination.NoValue -> {
//                Column(
//                    modifier = modifier
//                        .width(150.dp.from(ctx))
//                        .height(180.dp.from(ctx))
//                        .background(
//                            color = MaterialTheme.colors.primaryVariant
//                        )
//                        .padding(horizontal = 13.dp.from(ctx), vertical = 9.dp.from(ctx))
//                ) {
//                    Image(
//                        painter = painterResource(
//                            id = icon
//                        ),
//                        contentDescription = "",
//                        modifier = modifier.size(21.43.dp.from(ctx))
//                    )
//                    Column(
//                        modifier = modifier.fillMaxHeight(),
//                        verticalArrangement = Arrangement.Bottom
//                    ) {
//                        Text(
//                            text = namePhysicalExamination,
//                            fontSize = 16.sp.from(ctx),
//                            style = MaterialTheme.typography.body1.copy(
//                                fontWeight = FontWeight(400),
//                                color = Color.White
//                            ),
//                        )
//                    }
//
//                }
//            }
//
//        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CardMedicalInspection(
    modifier: Modifier = Modifier,
    name: String,
    userCode: String,
    email: String,
    thumb: String,
    selectedState: Boolean = false,
    onClick: (String) -> Unit
) {
    val ctx = LocalConfiguration.current
    var tempListNamePatient = name.split(" ")
    var namePatientFirstWord: String = ""
    run lit@{
        tempListNamePatient.forEachIndexed { index, it ->
            namePatientFirstWord += it.first().toString().uppercase()
            if (index == 1) {
                return@lit
            }
        }
    }
    Card(
        shape = RoundedCornerShape(16.dp.from(ctx)),
        elevation = MaterialThemeCexup.elevation.skim
    ) {
        Row(
            modifier = modifier
                .width(306.dp.from(ctx))
                .selectable(
                    selected = selectedState,
                    onClick = {
                        onClick(userCode)
                    }
                )
                .background(
                    color = Color.Transparent
                )
                .border(
                    width = 2.dp.from(ctx),
                    shape = RoundedCornerShape(16.dp.from(ctx)),
                    color = if (selectedState) MaterialThemeCexup.colors.color.primary.primaryMain else Color.White
                )
                .padding(16.dp.from(ctx)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
//            CoilImage(
//                modifier = modifier
//                    .clip(CircleShape)
//                    .coloredShadow(MaterialTheme.colors.primary)
//                    .width(48.dp.from(ctx))
//                    .height(48.dp.from(ctx))
//                    .clip(CircleShape)
//                    .align(Alignment.CenterVertically),
//                imageModel = ImageBitmap.imageResource(R.drawable.dummy_profile_small),
//                // Crop, Fit, Inside, FillHeight, FillWidth, None
//                contentScale = ContentScale.Crop,
//                // shows an image with a circular revealed animation.
//                circularReveal = CircularReveal(duration = 250),
//                // shows a placeholder ImageBitmap when loading.
//                placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile_small),
//                // shows an error ImageBitmap when the request failed.
//                error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
//            )
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
                    style = MaterialThemeCexup.typography.h6,
                    color = Color.White
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = name.capitalizeWords(),
                    style = MaterialThemeCexup.typography.hh3.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    fontWeight = FontWeight(500),
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = email,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Icon(
                painter = painterResource(R.drawable.ic_arrow_corporate),
                contentDescription = "Arrow",
                tint = MaterialThemeCexup.colors.color.text.textMain
            )
        }
    }
}

@Composable
fun CardOrderTeleconsultation(
    valueDoctorActive: Int = 0,
    onClickSelectDoctor: () -> Unit = {},
){
    val ctx = LocalConfiguration.current
    Card(
        shape = RoundedCornerShape(16.dp.from(ctx)),
        elevation = MaterialThemeCexup.elevation.skim
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp.from(ctx), vertical = 24.dp.from(ctx)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
        ) {
            AvatarCexup(
                isWithStatusOnline = true,
                sizeAvatar = 52.dp.from(ctx),
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.order_teleconsultation),
                    style = MaterialThemeCexup.typography.hh1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialThemeCexup.colors.color.text.textMain
                )
                Spacer(modifier = Modifier.height(4.dp.from(ctx)))
                Text(
                    text = stringResource(id = R.string.value_active_doctors,valueDoctorActive),
                    style = MaterialThemeCexup.typography.hh3,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
            }
            Button(
                onClick = { onClickSelectDoctor() },
                colors = ButtonDefaults.buttonColors(MaterialThemeCexup.colors.color.primary.primaryMain),
                contentPadding = PaddingValues(vertical = 12.dp.from(ctx), horizontal = 47.5f.dp.from(ctx)),
                shape = RoundedCornerShape(4.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.select_doctor),
                    style = MaterialThemeCexup.typography.textButton1.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialThemeCexup.colors.palette.neutral.neutral1
                )
            }

        }
    }
}