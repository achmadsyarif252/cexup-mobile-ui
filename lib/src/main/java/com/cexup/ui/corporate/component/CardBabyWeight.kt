package com.cexup.ui.corporate.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.screen.StepBabyBMI
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.utils.mediaquery.from

@ExperimentalMaterialApi
@Composable
fun CardResultBabyWeight(
    motherWeight: Double,
    babyWeight: Double,
    babyHeight: Double,
    onSeeChartClicked: () -> Unit,
) {
    val ctx = LocalContext.current
    Surface(
        shape = RoundedCornerShape(8.dp.from(ctx)),
        modifier = Modifier
            .width(619.dp.from(ctx)),
        elevation = 2.dp.from(ctx)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(16.dp.from(ctx)),
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(99.dp.from(ctx))
                    .padding(top = 16.dp.from(ctx), start = 16.dp.from(ctx), end = 16.dp.from(ctx)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
            ) {
                Column(
                    modifier = Modifier
                        .width(153.dp.from(ctx))
                        .padding(vertical = 5.5f.dp.from(ctx))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp.from(ctx)),
                            painter = painterResource(id = R.drawable.ic_weight_scale),
                            contentDescription = "Icon Weight Scales"
                        )
                        Spacer(modifier = Modifier.width(8.dp.from(ctx)))
                        Text(
                            text = if (motherWeight > 0)
                                stringResource(
                                    id = R.string.kg_with_value,
                                    motherWeight
                                )
                            else
                                "-- kg",
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp.from(ctx),
                                lineHeight = 32.sp.from(ctx),
                                color = Natural.copy(alpha = 0.9f)
                            ),
                        )
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.mother_weight),
                        fontSize = 12.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = Natural.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                }
                Divider(
                    modifier = Modifier
                        .width(1.dp.from(ctx))
                        .height(67.dp.from(ctx)), thickness = 1.dp.from(ctx)
                )
                Column(
                    modifier = Modifier
                        .width(153.dp.from(ctx))
                        .padding(vertical = 5.5f.dp.from(ctx))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp.from(ctx)),
                            painter = painterResource(id = R.drawable.ic_baby),
                            contentDescription = "Icon Weight Scales"
                        )
                        Spacer(modifier = Modifier.width(8.dp.from(ctx)))
                        Text(
                            text = if (babyWeight > 0)
                                stringResource(
                                    id = R.string.kg_with_value,
                                    babyWeight
                                )
                            else
                                "-- kg",
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp.from(ctx),
                                lineHeight = 32.sp.from(ctx),
                                color = Natural.copy(alpha = 0.9f)
                            ),
                        )
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.baby_weight),
                        fontSize = 12.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = Natural.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                }
                Divider(
                    modifier = Modifier
                        .width(1.dp.from(ctx))
                        .height(67.dp.from(ctx)), thickness = 1.dp.from(ctx)
                )
                Column(
                    modifier = Modifier
                        .width(153.dp.from(ctx))
                        .padding(vertical = 5.5f.dp.from(ctx)),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(20.dp.from(ctx)),
                            painter = painterResource(id = R.drawable.ic_baby_height),
                            contentDescription = "Icon Weight Scales"
                        )
                        Spacer(modifier = Modifier.width(8.dp.from(ctx)))
                        Text(
                            text =
                            if (babyHeight > 0)
                                stringResource(
                                    id = R.string.cm_with_value,
                                    babyHeight
                                ) else
                                "-- cm",
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp.from(ctx),
                                lineHeight = 32.sp.from(ctx),
                                color = Natural.copy(alpha = 0.9f)
                            ),
                        )
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.baby_height),
                        fontSize = 12.sp.from(ctx),
                        lineHeight = 20.sp.from(ctx),
                        color = Natural.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Divider(thickness = 1.dp, color = Natural.copy(alpha = 0.3f))
            CompositionLocalProvider(
                LocalMinimumTouchTargetEnforcement provides false,
            ) {
                OutlinedButton(
                    onClick = { onSeeChartClicked() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp.from(ctx)))
                        .fillMaxWidth(),
                    border = BorderStroke(1.5f.dp.from(ctx), BlueJade)
                ) {
                    Text(
                        text = stringResource(id = R.string.see_chart),
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 24.sp.from(ctx),
                            color = BlueJade
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardBmiBabyWeight(
    babyBmi: Double,
    bmiStatus: String,
    bmiRangeValue: String,
    onRemeasurementClicked: () -> Unit,
) {
    val ctx = LocalContext.current
    Surface(
        shape = RoundedCornerShape(8.dp.from(ctx)),
        modifier = Modifier
            .width(296.dp.from(ctx)),
        elevation = 2.dp.from(ctx)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(
                    horizontal = 12.dp.from(ctx),
                    vertical = 16.dp.from(ctx),
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp.from(ctx)))
                    .background(BlueUSG),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp.from(ctx))
                        .clip(RoundedCornerShape(8.dp.from(ctx)))
                        .background(MaterialTheme.colors.background)
                        .padding(horizontal = 16.dp.from(ctx)),
                    horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.width(119.dp.from(ctx)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (babyBmi> 0) babyBmi.toString() else "--",
                            style = MaterialTheme.typography.h5.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 24.sp.from(ctx),
                                lineHeight = 32.sp.from(ctx),
                                letterSpacing = -2.sp.from(ctx),
                                color = BlueJade
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.baby_bmi),
                            style = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp.from(ctx),
                                lineHeight = 20.sp.from(ctx),
                                color = Natural.copy(alpha = 0.7f)
                            )
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .width(1.dp.from(ctx))
                            .padding(top = 8.dp.from(ctx), bottom = 16.dp.from(ctx))
                            .height(67.dp.from(ctx)),
                        thickness = 1.dp.from(ctx),
                        color = Natural.copy(alpha = 0.3f)
                    )
                    Column(
                        modifier = Modifier.width(73.dp.from(ctx)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = bmiStatus,
                            style = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp.from(ctx),
                                lineHeight = 20.sp.from(ctx),
                                color = if (bmiStatus == "Normal") GreenNormalBabyBMI else RedTextGlucose
                            )
                        )
                        Text(
                            text = bmiRangeValue,
                            fontSize = 12.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                            color = Natural.copy(alpha = 0.7f)
                        )
                    }
                }
            }
            Divider(thickness = 1.dp, color = Natural.copy(alpha = 0.3f))
            CompositionLocalProvider(
                LocalMinimumTouchTargetEnforcement provides false,
            ) {
                TextButton(
                    contentPadding = PaddingValues(),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onRemeasurementClicked() }
                ) {
                    Text(
                        text = stringResource(id = R.string.measurement),
                        style = MaterialTheme.typography.h2.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            letterSpacing = -0.25f.sp.from(ctx),
                            color = BlueJade
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CardStepBabyWeight(
    numberStep: Int,
    resourceImage: Int,
    textTitle: String,
    textDetails: String,
    stateStep: StepBabyBMI,
    isEnabled: Boolean,
    onCardStepClicked: (numberStep: Int) -> Unit,
) {
    val ctx = LocalContext.current
    Surface(
        modifier = Modifier
            .width(297.dp.from(ctx)),
        elevation =
        if (stateStep == StepBabyBMI.POST_MEASUREMENT)
            2.dp.from(ctx)
        else
            0.dp,
        shape = RoundedCornerShape(16.dp.from(ctx))
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp.from(ctx)))
                .background(
                    when (stateStep) {
                        StepBabyBMI.PRE_MEASUREMENT -> {
                            GrayTextField
                        }
                        StepBabyBMI.MEASURING -> {
                            BlueUSG
                        }
                        StepBabyBMI.POST_MEASUREMENT -> {
                            MaterialTheme.colors.background
                        }
                    }
                )
                .clickable(enabled = isEnabled) {
                    onCardStepClicked(numberStep)
                }
                .border(
                    width = if (stateStep == StepBabyBMI.MEASURING) 2.dp.from(ctx) else 0.dp,
                    color = if (stateStep == StepBabyBMI.MEASURING) BlueJade else Color.Transparent,
                    shape = RoundedCornerShape(16.dp.from(ctx))
                )
                .height(190.dp.from(ctx))
                .fillMaxWidth()
                .padding(16.dp.from(ctx)),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(40.dp.from(ctx))
                    .clip(CircleShape)
                    .background(
                        if (stateStep == StepBabyBMI.MEASURING || stateStep == StepBabyBMI.POST_MEASUREMENT) {
                            GreenNormalBabyBMI
                        } else {
                            if (numberStep == 2) {
                                BlueJade
                            } else {
                                Color.Transparent
                            }
                        }
                    )
                    .border(
                        1.dp.from(ctx), color =
                        if (stateStep == StepBabyBMI.MEASURING || stateStep == StepBabyBMI.POST_MEASUREMENT) {
                            GreenNormalBabyBMI
                        } else {
                            if (numberStep == 2) {
                                Color.Transparent
                            } else {
                                BlueJade
                            }
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (stateStep == StepBabyBMI.MEASURING || stateStep == StepBabyBMI.POST_MEASUREMENT) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_checkmark),
                        contentDescription = ""
                    )
                } else {
                    Text(
                        text = numberStep.toString(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        color = if (numberStep == 2) Color.White else BlueStepBabyBMI
                    )
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp.from(ctx)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(64.dp.from(ctx)),
                    painter = painterResource(id = resourceImage),
                    contentDescription = textTitle
                )
                Spacer(modifier = Modifier.height(8.dp.from(ctx)))
                Text(
                    text = textTitle,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 28.sp.from(ctx),
                        color = Natural
                    )
                )
                Text(
                    text = textDetails,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontSize = 14.sp.from(ctx),
                        lineHeight = 22.sp.from(ctx),
                        color = Natural.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                )
            }

        }
    }
}

@Composable
fun CardChartWHOBaby() {

}

@OptIn(ExperimentalMaterialApi::class)
@Preview(device = Devices.TABLET)
@Composable
fun PreviewCardBabyWeight() {
    CexupTheme {
        Column {
            CardResultBabyWeight(
                motherWeight = 0.0,
                babyWeight = 0.0,
                babyHeight = 0.0,
                onSeeChartClicked = {}
            )
            CardBmiBabyWeight(
                babyBmi = 33.0,
                bmiStatus = "Normal",
                bmiRangeValue = "27-30 BMI",
                onRemeasurementClicked = {}
            )
            CardStepBabyWeight(
                numberStep = 1,
                resourceImage = R.drawable.ic_baby_bmi_3,
                textTitle = "pepe",
                textDetails = "cek bebi cek",
                stateStep = StepBabyBMI.PRE_MEASUREMENT,
                onCardStepClicked = {},
                isEnabled = true
            )
        }
    }
}