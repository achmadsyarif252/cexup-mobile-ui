package com.cexup.ui.corporate.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.CardDoctors
import com.cexup.ui.theme.MaterialThemeCexup
import com.cexup.ui.theme.PrimaryCorporate
import com.cexup.ui.utils.gridItems
import com.cexup.ui.utils.mediaquery.from

data class DataDoctorActive(
    var userCode: String = "",
    var title: String = "",
    var thumb: String = "",
    var specialty: String = "",
    var experienced: Int = 1,
    var rating: String = "4.70",
    var countRating: Int = 0
)

data class ScreenActiveDoctorsUIState(
    var message: String = "",
    var loading: Boolean = true,
    var error: Boolean = false,
    var data: List<DataDoctorActive> = listOf()
)

@Composable
fun ScreenDoctors(
    modifier: Modifier = Modifier,
    activeDoctorsUIState: ScreenActiveDoctorsUIState = ScreenActiveDoctorsUIState(),
    onButtonBackPressed: () -> Unit = {},
) {
    val ctx = LocalConfiguration.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
                .padding(horizontal = 28.dp.from(ctx)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.doctors_active),
                style = MaterialThemeCexup.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialThemeCexup.colors.color.text.textMain
            )
            Button(
                onClick = { onButtonBackPressed() },
                contentPadding = PaddingValues(vertical = 8.dp.from(ctx), horizontal = 12.dp.from(ctx)),
                colors = ButtonDefaults.buttonColors(MaterialThemeCexup.colors.palette.tertiary.redTertiary6),
                shape = RoundedCornerShape(4.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.back),
                    style = MaterialThemeCexup.typography.textButton2.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialThemeCexup.colors.palette.neutral.neutral1
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx)),
            contentPadding = PaddingValues(horizontal = 28.dp.from(ctx), vertical = 10.dp.from(ctx))
        ) {
            if (!activeDoctorsUIState.error && !activeDoctorsUIState.loading){
                gridItems(
                        data = activeDoctorsUIState.data,
                        columnCount = 3,
                        horizontalArrangement = Arrangement.spacedBy(24.dp.from(ctx)),
                    ) { data ->
                        CardDoctors(
                            dataDoctor = data,
                        )
                    }
            }else if (activeDoctorsUIState.loading){
                item {
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }else{
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
                    }
                }
            }
//            when (doctorState) {
//                DoctorState.LOADING -> {
//                    item {
//                        Box(
//                            modifier = modifier.fillMaxSize(),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            CircularProgressIndicator()
//                        }
//                    }
//                }
//                DoctorState.FAILED -> {
//                    item {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center,
//                            modifier = modifier.fillMaxWidth()
//                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.ic_empty_doctor),
//                                contentDescription = "",
//                                modifier = modifier.height(165.dp.from(ctx))
//                            )
//                            Spacer(modifier = modifier.height(16.dp.from(ctx)))
//                            Text(
//                                text = "No List Doctors",
//                                style = MaterialTheme.typography.body1.copy(
//                                    fontSize = 22.sp.from(ctx),
//                                    fontWeight = FontWeight(600),
//                                    color = PrimaryCorporate
//                                )
//                            )
//                            Spacer(modifier = modifier.height(10.dp.from(ctx)))
//                            Text(
//                                text = stringResource(id = R.string.no_list_doctor2),
//                                style = MaterialTheme.typography.body1.copy(
//                                    fontSize = 12.sp.from(ctx),
//                                    fontWeight = FontWeight(600),
//                                    color = PrimaryCorporate
//                                )
//                            )
//                            Spacer(modifier = modifier.height(20.dp.from(ctx)))
//
////                            Button(
////                                onClick = {
////                                    onBack()
////                                },
////                                shape = RoundedCornerShape(10.dp.from(ctx)),
////                                colors = ButtonDefaults.buttonColors(PrimaryCorporate),
////                                elevation = ButtonDefaults.elevation(2.dp.from(ctx)),
////                                modifier = modifier.coloredShadow(
////                                    alpha = 0.06f,
////                                    color = BlackShadow.copy(alpha = 0.08f),
////                                    shadowRadius = 5.dp.from(ctx),
////                                    offsetY = 10.dp.from(ctx)
////                                )
////                            ) {
////                                Text(
////                                    text = stringResource(id = R.string.go_back),
////                                    style = MaterialTheme.typography.body1.copy(
////                                        fontSize = 16.sp.from(ctx),
////                                        fontWeight = FontWeight(600),
////                                        color = Color.White
////                                    )
////                                )
////                            }
//
//                        }
//                    }
//                }
//                DoctorState.SUCCESS -> {
//                    gridItems(
//                        data = listDoctor,
//                        columnCount = 4,
//                        horizontalArrangement = Arrangement.spacedBy(24.dp.from(ctx)),
//                    ) { data ->
//                        CardDoctors(
//                            doctorName = data.title.capitalizeWords(),
//                            specialistName = data.specialty.capitalizeWords()
//                        )
//                    }
//                }
//            }
        }

    }
}