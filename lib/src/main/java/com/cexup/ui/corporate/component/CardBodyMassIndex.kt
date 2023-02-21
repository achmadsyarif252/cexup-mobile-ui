package com.cexup.ui.corporate.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from

@Composable
fun CardBodyMassIndex(
    modifier: Modifier = Modifier,
    parameterName : String = "",
    parameterUnit : String = "",
    valueOfParameter : Float,
    color: Color = Heading,
    typeBodyMassIndex: TypeBodyMassIndex,
){
    val ctx = LocalConfiguration.current
    var value by remember {
        mutableStateOf("--")
    }
    value = if (valueOfParameter == 0f) "--" else valueOfParameter.toString()
    Card(
        shape = RoundedCornerShape(15.dp.from(ctx)),
        elevation = 2.dp.from(ctx),
        modifier = modifier.height(273.45.dp.from(ctx))
    ) {
        Column(
            modifier = modifier.padding(
                horizontal = 22.41.dp.from(ctx),
                vertical = 21.29.dp.from(ctx)
            )
        ) {
            Text(
                text = parameterName,
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Black,
                    fontSize = 22.sp.from(ctx),
                    fontWeight = FontWeight(700)
                ),
            )
            Spacer(modifier = Modifier.height(10.dp.from(ctx)))
            when(typeBodyMassIndex){
                TypeBodyMassIndex.BMI ->{
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier.size(78.955.dp.from(ctx)*2f)
                        ){
                            Canvas(modifier = modifier.size(78.955.dp.from(ctx)*2f)){
                                rotate(-45f){
                                    drawArc(
                                        brush = Brush.sweepGradient(
                                            colors = listOf(
                                                SecondaryCorporate,
                                                Heading,
                                            )
                                        ),
                                        startAngle = 90f,
                                        sweepAngle = 360f,
                                        useCenter = false,
                                        style = Stroke(
                                            15.dp.toPx(),
                                            cap = StrokeCap.Round
                                        )
                                    )
                                }
                            }
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = value,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = Color.Black,
                                        fontSize = 30.sp.from(ctx),
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                                Text(
                                    text = parameterUnit,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = Color.Black,
                                        fontSize = 16.sp.from(ctx),
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = modifier.align(Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
                else -> {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = modifier
                                .size(171.82.dp.from(ctx))
                                .clip(CircleShape)
                                .border(
                                    width = 3.dp.from(ctx),
                                    color = color,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = value,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = Color.Black,
                                        fontSize = 26.sp.from(ctx),
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                                Text(
                                    text = parameterUnit,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = Color.Black,
                                        fontSize = 16.sp.from(ctx),
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = modifier.align(Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class TypeBodyMassIndex {
    BMI,
    WEIGHT,
    HEIGHT,
}