package com.cexup.ui.corporate.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.R
import com.cexup.ui.corporate.component.CardDoctors
import com.cexup.ui.corporate.theme.BlackShadow
import com.cexup.ui.corporate.theme.PrimaryCorporate
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.gridItems

data class DoctorData(
    var title: String = "",
    var specialty: String = "",
)

enum class DoctorState {
    LOADING, SUCCESS, FAILED
}

@Composable
fun ScreenDoctors(
    modifier: Modifier = Modifier,
    listDoctor: List<DoctorData>,
    doctorState: DoctorState = DoctorState.LOADING,
    onBack: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "Doctors List",
                style = MaterialTheme.typography.body1.copy(
                    color = Heading,
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700)
                ),
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier.padding(vertical = 10.dp)
        ) {
            when (doctorState) {
                DoctorState.LOADING -> {
                    item {
                        Box(
                            modifier = modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                DoctorState.FAILED -> {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_empty_doctor),
                                contentDescription = "",
                                modifier = modifier.height(165.dp)
                            )
                            Spacer(modifier = modifier.height(16.dp))
                            Text(
                                text = "No List Doctors",
                                style = MaterialTheme.typography.body1.copy(
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight(600),
                                    color = PrimaryCorporate
                                )
                            )
                            Spacer(modifier = modifier.height(10.dp))
                            Text(
                                text = "There’re no Doctors List for you to see yet. if you\nwant to add doctor list contact super admin user",
                                style = MaterialTheme.typography.body1.copy(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(600),
                                    color = PrimaryCorporate
                                )
                            )
                            Spacer(modifier = modifier.height(20.dp))

                            Button(
                                onClick = {
                                    onBack()
                                },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(PrimaryCorporate),
                                elevation = ButtonDefaults.elevation(2.dp),
                                modifier = modifier.coloredShadow(
                                    alpha = 0.06f,
                                    color = BlackShadow.copy(alpha = 0.08f),
                                    shadowRadius = 5.dp,
                                    offsetY = 10.dp
                                )
                            ) {
                                Text(
                                    text = "Go around",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color.White
                                    )
                                )
                            }

                        }
                    }
                }
                DoctorState.SUCCESS -> {
                    gridItems(
                        data = listDoctor,
                        columnCount = 4,
                        horizontalArrangement = Arrangement.spacedBy(22.dp),
                    ) { data ->
                        CardDoctors(
                            doctorName = data.title.capitalizeWords(),
                            specialistName = data.specialty.capitalizeWords()
                        )
                    }
                }
            }
        }

    }
}