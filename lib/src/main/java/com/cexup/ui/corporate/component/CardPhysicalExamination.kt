package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.utils.mediaquery.from

enum class TypePhysicalExamination {
    OneValue,
    BloodPressure,
    NoValue,
}

@Composable
fun CardPhysicalExamination(
    modifier: Modifier = Modifier,
    icon: Int,
    namePhysicalExamination: String,
    value: String,
    value2: String = "",
    unit: String = " ",
    typePhysicalExamination: TypePhysicalExamination,
    onClick: () -> Unit,
) {
    val ctx = LocalContext.current
    Card(
        shape = RoundedCornerShape(10.dp.from(ctx)),
        modifier = modifier
            .clip(RoundedCornerShape(10.dp.from(ctx)))
            .clickable {
            onClick()
        }

    ) {
        when (typePhysicalExamination) {
            TypePhysicalExamination.OneValue -> {
                Column(
                    modifier = modifier
                        .width(150.dp.from(ctx))
                        .height(180.dp.from(ctx))
                        .background(
                            color = MaterialTheme.colors.primaryVariant
                        )
                        .padding(horizontal = 13.dp.from(ctx), vertical = 9.dp.from(ctx))
                ) {
                    Image(
                        painter = painterResource(
                            id = icon
                        ),
                        contentDescription = "",
                        modifier = modifier.size(21.43.dp.from(ctx)),
                    )
                    Column(
                        modifier = modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = namePhysicalExamination,
                            fontSize = 16.sp.from(ctx),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(400),
                                color = Color.White
                            ),
                        )
                        Text(
                            text = "$value $unit",
                            fontSize = 22.sp.from(ctx),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(500),
                                color = Color.White
                            ),
                        )
                    }

                }
            }
            TypePhysicalExamination.BloodPressure -> {
                Column(
                    modifier = modifier
                        .width(150.dp.from(ctx))
                        .height(180.dp.from(ctx))
                        .background(
                            color = MaterialTheme.colors.primaryVariant
                        )
                        .padding(horizontal = 13.dp.from(ctx), vertical = 9.dp.from(ctx))
                ) {
                    Image(
                        painter = painterResource(
                            id = icon
                        ),
                        contentDescription = "",
                        modifier = modifier.size(21.43.dp.from(ctx))
                    )
                    Column(
                        modifier = modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = namePhysicalExamination,
                            fontSize = 16.sp.from(ctx),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(400),
                                color = Color.White
                            ),
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$value/$value2",
                                fontSize = 22.sp.from(ctx),
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight(500),
                                    color = Color.White
                                ),
                            )
                            Spacer(modifier = Modifier.width(5.dp.from(ctx)))
                            Text(
                                text = unit,
                                fontSize = 12.sp.from(ctx),
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight(300),
                                    color = Color.White
                                ),
                            )
                        }

                    }

                }
            }

            TypePhysicalExamination.NoValue -> {
                Column(
                    modifier = modifier
                        .width(150.dp.from(ctx))
                        .height(180.dp.from(ctx))
                        .background(
                            color = MaterialTheme.colors.primaryVariant
                        )
                        .padding(horizontal = 13.dp.from(ctx), vertical = 9.dp.from(ctx))
                ) {
                    Image(
                        painter = painterResource(
                            id = icon
                        ),
                        contentDescription = "",
                        modifier = modifier.size(21.43.dp.from(ctx))
                    )
                    Column(
                        modifier = modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = namePhysicalExamination,
                            fontSize = 16.sp.from(ctx),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(400),
                                color = Color.White
                            ),
                        )
                    }

                }
            }

        }

    }

}