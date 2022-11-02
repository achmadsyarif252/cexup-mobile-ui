package com.cexup.ui.corporate.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.cexup.ui.R
import com.cexup.ui.component.chart.PieChart
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.utils.gridItems

@Composable
fun ScreenDashboard(
    onClickPatientList: () -> Unit,
    onClickDoctorList: () -> Unit,
) {
    val listPatientDiseases by remember {
        mutableStateOf("Diabetes")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)

    ) {

        Row {

            LazyColumn(modifier = Modifier
                .width(369.25.dp)
                .fillMaxHeight()
            ) {
                item {
                    Text(
                        text = "Welcome",
                        style = MaterialTheme.typography.body1.copy(
                            color = Heading,
                            fontSize = 22.sp,
                            fontWeight = FontWeight(700)
                        ),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CardCount(
                            imageId = R.drawable.image_patient_dashboard,
                            title = "Today's Patient",
                            countValue = 124,
                            onNavigate = {
                                onClickPatientList()
                            }
                        )
                        Spacer(modifier = Modifier.width(32.dp))
                        CardCount(
                            imageId = R.drawable.image_patient_dashboard,
                            title = "Our Doctors",
                            countValue = 19,
                            onNavigate = {
                                onClickDoctorList()
                            }
                        )
                        Spacer(modifier = Modifier.width(17.dp))
                        Image(painter = painterResource(id = R.drawable.right_grey),
                            contentDescription = "right",
                        )


                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    CardPatientHistory(
                        xValueFormatter = listOf(
                            "day 1",
                            "day 2",
                            "day 3",
                            "day 4",
                            "day 5",
                            "day 6",
                            "day 7"
                        ),
                        data = listOf(
                            Entry(0f, 2f),
                            Entry(1f, 2f),
                            Entry(3f, 6f),
                            Entry(4f, 7f),
                            Entry(4f, 8f),
                            Entry(6f, 9f),
                            Entry(7f, 7f),
                            Entry(8f, 1f),
                            Entry(9f, 3f),
                            Entry(10f, 2f),

                            ),
                        data2 = listOf(
                            Entry(0f, 2f),
                            Entry(1f, 2f),
                            Entry(2f, 2f),
                            Entry(3f, 2f),
                            Entry(4f, 2f),
                            Entry(5f, 2f),
                            Entry(6f, 2f),
                            Entry(7f, 2f),
                            Entry(8f, 2f),
                            Entry(9f, 2f),

                            ),
                        maxAxis = 10f,
                        minAxis = 0f,
                        name = "Patient History",
                        label1 = LabelAndColorChart(
                            label = "New Patient",
                            colorLine = 0x18A0FB,
                            coloGradient = R.drawable.texture_fill_chart_new_patient
                        ),
                        label2 = LabelAndColorChart(
                            label = "Old Patient",
                            colorLine = 0xF16A51,
                            coloGradient = R.drawable.texture_fill_chart_old_patient
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    CardPatientTodayChart(
                        name ="Total Patient Per day",
                        data = listOf(
                            BarEntry(1f, 10f),
                            BarEntry(3f, 50f),
                            BarEntry(5f, 45f),
                        ),
                        data2 = listOf(
                            BarEntry(2f, 21f),
                            BarEntry(4f, 31f),
                            BarEntry(6f, 15f),

                            ),
                        maxAxis = 50f,
                        minAxis = 0f,
                        xValueFormatter = listOf(
                            "day 1",
                            "day 2",
                            "day 3",
                            "day 4",
                            "day 5",
                            "day 6",
                            "day 7"
                        ),
                    )


                }


            }
            Spacer(modifier = Modifier.width(30.dp))

            LazyColumn(modifier = Modifier
                .width(341.dp)
                .fillMaxHeight(),
            ) {
                item {
                    Text(
                        text = "Top 10 Diseases",
                        style = MaterialTheme.typography.body1.copy(
                            color = Heading,
                            fontSize = 22.sp,
                            fontWeight = FontWeight(700)
                        ),
                    )
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 47.dp, bottom = 37.42.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PieChart(
                            modifier = Modifier
                                .width(160.dp)
                                .height(165.dp),
                            progress = listOf(
                                40f,
                                30f,
                                40f,
                                17f,
                                10f,
                                40f,
                                30f,
                                40f,
                                17f,
                                10f,
                            ),
                            colors = listOf(
                                MaterialTheme.colors.secondary,
                                PrimaryCorporate,
                                Color(0xFF8b0a50),
                                Color.Yellow,
                                Color.Magenta,
                                Color.Green,
                                Color.Blue,
                                Color.Cyan,
                                Color.Red,
                                OrangePrimary
                            ),
                            isDonut = true,
                            percentColor = Color.Black
                        )
                    }

                }

                gridItems(
                    count = 10,
                    columnCount = 2,

                    ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(9.05.dp)
                                .background(Secondary),

                            ) {

                        }
                        Spacer(modifier = Modifier.width(5.66.dp))
                        Text(
                            text = "Stroke",
                            style = MaterialTheme.typography.body1.copy(
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                        Spacer(modifier = Modifier.width(25.dp))
                        Text(
                            text = "40%",
                            style = MaterialTheme.typography.body1.copy(
                                fontSize = 11.sp,
                                fontWeight = FontWeight(500),
                                color = inactive
                            )
                        )
                    }
                }
                item{
                    Spacer(modifier = Modifier.height(37.33.dp))
                    Text(
                        text = "List Patients $listPatientDiseases",
                        style = MaterialTheme.typography.body1.copy(
                            color = Heading,
                            fontSize = 22.sp,
                            fontWeight = FontWeight(700)
                        ),
                    )
                }

                items(count = 2){
                    CardRecentPatient()
                }
            }
        }



    }
}