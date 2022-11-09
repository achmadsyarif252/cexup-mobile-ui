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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.cexup.ui.utils.mediaquery.from

data class DoctorDataUIState(
    var title: String = "",
    var specialty: String = "",
)

enum class DoctorState {
    LOADING, SUCCESS, FAILED
}

@Composable
fun ScreenDoctors(
    modifier: Modifier = Modifier,
    listDoctor: List<DoctorDataUIState>,
    doctorState: DoctorState = DoctorState.LOADING,
    onBack: () -> Unit = {},
) {
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp.from(ctx))
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.doctor_list),
                style = MaterialTheme.typography.body1.copy(
                    color = Heading,
                    fontSize = 22.sp.from(ctx),
                    fontWeight = FontWeight(700)
                ),
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp.from(ctx)),
            modifier = modifier.padding(vertical = 10.dp.from(ctx))
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
                                modifier = modifier.height(165.dp.from(ctx))
                            )
                            Spacer(modifier = modifier.height(16.dp.from(ctx)))
                            Text(
                                text = "No List Doctors",
                                style = MaterialTheme.typography.body1.copy(
                                    fontSize = 22.sp.from(ctx),
                                    fontWeight = FontWeight(600),
                                    color = PrimaryCorporate
                                )
                            )
                            Spacer(modifier = modifier.height(10.dp.from(ctx)))
                            Text(
                                text = stringResource(id = R.string.no_list_doctor2),
                                style = MaterialTheme.typography.body1.copy(
                                    fontSize = 12.sp.from(ctx),
                                    fontWeight = FontWeight(600),
                                    color = PrimaryCorporate
                                )
                            )
                            Spacer(modifier = modifier.height(20.dp.from(ctx)))

                            Button(
                                onClick = {
                                    onBack()
                                },
                                shape = RoundedCornerShape(10.dp.from(ctx)),
                                colors = ButtonDefaults.buttonColors(PrimaryCorporate),
                                elevation = ButtonDefaults.elevation(2.dp.from(ctx)),
                                modifier = modifier.coloredShadow(
                                    alpha = 0.06f,
                                    color = BlackShadow.copy(alpha = 0.08f),
                                    shadowRadius = 5.dp.from(ctx),
                                    offsetY = 10.dp.from(ctx)
                                )
                            ) {
                                Text(
                                    text = stringResource(id = R.string.go_back),
                                    style = MaterialTheme.typography.body1.copy(
                                        fontSize = 16.sp.from(ctx),
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
                        horizontalArrangement = Arrangement.spacedBy(22.dp.from(ctx)),
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