package com.cexup.ui.corporate.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.Octicons
import compose.icons.octicons.DotFill24
import com.cexup.ui.R
import com.cexup.ui.component.chart.BaseChartView
import com.cexup.ui.corporate.component.CardPatientInFeature
import com.cexup.ui.corporate.component.DialogInputManualWaist
import com.cexup.ui.corporate.theme.GreyBorder
import com.cexup.ui.corporate.theme.PrimaryCorporate
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.capitalizeWords
import com.github.mikephil.charting.data.Entry

@Composable
fun ScreenWaist(
    modifier: Modifier = Modifier,
    receivedDistance: Float = 0F,
    chartListData: Pair<List<Entry>, List<Entry>> = Pair(listOf(), listOf()),
    onTypePress: (type: String) -> Unit = {},
    onSave: (distance: Float) -> Unit = {},
    onSaveManualInput:(distance: Float) -> Unit,
//    maxDistance: Float,
    patientName: String = "Patient name",
    onBackPressed:() -> Unit = {},
){
    val listOfTypeWaist = listOf(
        "neck", "waist", "shoulder", "hip",
        "arm", "thigh", "chest", "calf"
    )
    var selectedTypeWaist by remember { mutableStateOf(0) }
    var titleOfChart by remember { mutableStateOf("neck") }
    var positionBodyIndex by remember { mutableStateOf(0) }

    var showDialogManualInput by remember { mutableStateOf(false) }

    DialogInputManualWaist(
        textWaist = titleOfChart.capitalizeWords(),
        show = showDialogManualInput,
        onCancel = { showDialogManualInput = false},
        onSave = { value ->
            onSaveManualInput(value)
            showDialogManualInput = false
        }
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CardPatientInFeature(
                thumb = "",
                name = patientName.capitalizeWords(),
                id = 0,
            )
            Button(
                onClick = {onBackPressed() },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                modifier = modifier.width(90.dp)
            ) {
                Text(
                    text = "Back",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.padding(start = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = modifier.width(170.dp)) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_man_anatomy),
                            contentDescription = ""
                        )
                    }
                    when (selectedTypeWaist) {
                        0 -> LineWaist(
                            horizontalArrangement = Arrangement.End,
                            height = 16.dp,
                            valueWaist = receivedDistance
                        )
                        1 -> LineWaist(
                            horizontalArrangement = Arrangement.End,
                            height = 120.dp,
                            valueWaist = receivedDistance
                        )
                        2 -> LineWaist(
                            horizontalArrangement = Arrangement.Start,
                            height = 35.dp,
                            valueWaist = receivedDistance
                        )
                        3 -> LineWaist(
                            horizontalArrangement = Arrangement.Start,
                            height = 159.dp,
                            valueWaist = receivedDistance
                        )
                        4 -> LineWaist(
                            horizontalArrangement = Arrangement.End,
                            height = 133.dp,
                            valueWaist = receivedDistance
                        )
                        5 -> LineWaist(
                            horizontalArrangement = Arrangement.End,
                            height = 242.dp,
                            valueWaist = receivedDistance
                        )
                        6 -> LineWaist(
                            horizontalArrangement = Arrangement.End,
                            height = 100.dp,
                            valueWaist = receivedDistance
                        )
                        7 -> LineWaist(
                            horizontalArrangement = Arrangement.End,
                            height = 100.dp,
                            valueWaist = receivedDistance
                        )
                    }
                }
                Row {
                    Button(
                        onClick = { showDialogManualInput = true },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                        modifier = modifier.width(90.dp)
                    ) {
                        Text(
                            text = "Input",
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.W600
                            )
                        )
                    }
                    Spacer(modifier = modifier.width(15.dp))
                    Button(
                        onClick = {
                            onSave(receivedDistance)
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                        modifier = modifier.width(90.dp)
                    ) {
                        Text(
                            text = "Save",
                            style = TextStyle(
                                color = Color.White
                            )
                        )
                    }
                }
            }
            Spacer(modifier = modifier.width(50.dp))
            LazyColumn(content = {
                items(listOfTypeWaist.size) { index ->
                    Button(
                        onClick = {
                            selectedTypeWaist = index
                            positionBodyIndex = index
                            titleOfChart = listOfTypeWaist[index]
                            onTypePress(listOfTypeWaist[index])
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =
                                if (selectedTypeWaist == index) MaterialTheme.colors.primary
                                else Color.White
                        ),
                        modifier = modifier
                            .width(90.dp)
                            .selectable(selectedTypeWaist == index, onClick = {
                                selectedTypeWaist = index
                                positionBodyIndex = index
                                titleOfChart = listOfTypeWaist[index]
                            }),
                        border = BorderStroke(
                            1.dp,
                            if (selectedTypeWaist == index) MaterialTheme.colors.primary
                            else inactive
                        )
                    ) {
                        Text(
                            text = listOfTypeWaist[index].capitalizeWords(),
                            style = TextStyle(
                                color =
                                    if (selectedTypeWaist == index) Color.White
                                    else inactive
                            )
                        )
                    }
                }
            })
            Column(modifier = modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Measuring Circumference",
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.primary,
                            fontSize = 22.sp
                        )
                    )
                    Spacer(modifier = modifier.width(10.dp))
                    Icon(
                        Octicons.DotFill24,
                        "",
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = modifier.width(10.dp))
                    Text(
                        text = titleOfChart.capitalizeWords(),
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.primary,
                            fontSize = 16.sp
                        )
                    )
                }
                Spacer(modifier = modifier.height(10.dp))
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, GreyBorder)
                ) {
                    Box(modifier = modifier.padding(20.dp)) {
                        BaseChartView(
                            data = chartListData.second,
                            name = listOf(),
                            description = "",
                            maxAxis = 40f,
                            minAxis = 0f,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LineWaist(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal,
    height: Dp,
    valueWaist: Float,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = height),
        horizontalArrangement = horizontalArrangement
    ) {
        Column(modifier = modifier.width(70.dp)) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = horizontalArrangement
            ) {
                Text(
                    text = valueWaist.toString(),
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.primary,
                        fontSize = 16.sp
                    ),
                )
            }
            Canvas(modifier = modifier,
                onDraw = {
                    drawRect(
                        color = PrimaryCorporate,
                        size = Size(
                            width = 8.dp.toPx(),
                            height = 2.dp.toPx()
                        ),
                        topLeft = Offset(
                            x = 0.dp.toPx(),
                            y = 0.dp.toPx()
                        )
                    )
                    drawRect(
                        color = PrimaryCorporate,
                        size = Size(
                            width = 8.dp.toPx(),
                            height = 2.dp.toPx()
                        ),
                        topLeft = Offset(
                            x = 15.dp.toPx(),
                            y = 0.dp.toPx()
                        )
                    )
                    drawRect(
                        color = PrimaryCorporate,
                        size = Size(
                            width = 8.dp.toPx(),
                            height = 2.dp.toPx()
                        ),
                        topLeft = Offset(
                            x = 30.dp.toPx(),
                            y = 0.dp.toPx()
                        )
                    )
                    drawRect(
                        color = PrimaryCorporate,
                        size = Size(
                            width = 8.dp.toPx(),
                            height = 2.dp.toPx()
                        ),
                        topLeft = Offset(
                            x = 45.dp.toPx(),
                            y = 0.dp.toPx()
                        )
                    )
                    drawRect(
                        color = PrimaryCorporate,
                        size = Size(
                            width = 8.dp.toPx(),
                            height = 2.dp.toPx()
                        ),
                        topLeft = Offset(
                            x = 60.dp.toPx(),
                            y = 0.dp.toPx()
                        )
                    )
                }
            )
        }
    }
}