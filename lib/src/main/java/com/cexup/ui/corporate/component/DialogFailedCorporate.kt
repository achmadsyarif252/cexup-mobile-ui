package com.cexup.ui.corporate.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.R
import com.cexup.ui.theme.*

data class FailedDialogData(
    val failedData: String = "",
    val message: String = "",
)

@Composable
fun DialogFailedCorporate(
    show: Boolean,
    listFailedData: List<FailedDialogData> = listOf(),
    onClickCancel: () -> Unit,
    onClickTryAgain: () -> Unit,
) {
    if (show) {
        Dialog(onDismissRequest = { onClickCancel() }) {
            Column {
                Box(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .height(height = if (listFailedData.isEmpty())225.dp else 344.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .heightIn(min = 164.dp)
                            .width(311.83.dp)
                            .align(Alignment.BottomCenter)

                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        )
                        {
                            Spacer(modifier = Modifier.height(50.dp))
                            Text(
                                text = "Failed",
                                fontFamily = fontsCorp,
                                fontWeight = FontWeight(700),
                                fontSize = 26.sp,
                                color = inactive
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 0.dp, max = 120.dp)
                                .padding(horizontal = 8.dp)
                                .border(
                                    2.dp, MaterialThemeCexup.colors.color.borderline.borderline2,
                                    RoundedCornerShape(MaterialThemeCexup.elevation.skim)
                                )
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 8.dp),
                        ) {
                            listFailedData.forEach {
                                Text(
                                    text = it.failedData,
                                    style = MaterialThemeCexup.typography.hh4,
                                    color = MaterialThemeCexup.colors.color.text.textMain
                                )
                                Text(
                                    text = it.message,
                                    style = MaterialThemeCexup.typography.hh6,
                                    color = MaterialThemeCexup.colors.color.text.textSecondary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = { onClickCancel() },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .width(114.dp)
                                    .height(38.dp),
                                colors = ButtonDefaults.buttonColors(BlueLight)
                            ) {
                                Text(
                                    text = "Cancel",
                                    fontSize = 12.sp,
                                    fontFamily = fontsCorp,
                                    color = inactive
                                )
                            }
                            Spacer(modifier = Modifier.width(19.dp))
                            Button(
                                onClick = { onClickTryAgain() },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .width(114.dp)
                                    .height(38.dp),
                                colors = ButtonDefaults.buttonColors(SecondaryCorporate)
                            ) {
                                Text(
                                    text = "Try again",
                                    fontSize = 12.sp,
                                    fontFamily = fontsCorp,
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                            .align(Alignment.TopCenter)
                    ) {
                        Box(
                            Modifier
                                .size(86.dp)
                                .clip(CircleShape)
                                .border(
                                    BorderStroke(
                                        3.dp,
                                        RedCircle
                                    ),
                                    shape = CircleShape
                                )
                                .background(SecondaryCorporate)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_cancel),
                                    contentDescription = "Cancel", modifier = Modifier
                                        .size(40.dp)
                                )
                            }
                        }


                    }


                }


            }
        }
    }
}

@Preview
@Composable
fun PreviewDialogCorporate() {
    CexupTheme {
        DialogFailedCorporate(
            show = true,
            onClickCancel = { /*TODO*/ },
            listFailedData = listOf(
                FailedDialogData(
                    failedData = "USG",
                    message = "User code not found"
                ),
                FailedDialogData(
                    failedData = "Baby BMI",
                    message = "User code not found"
                ),
                FailedDialogData(
                    failedData = "USG",
                    message = "JOSHN CINA BING CHILLING ICE CREAM SPECIAL KAMBING"
                ),
                FailedDialogData(
                    failedData = "BMI",
                    message = "JOSHN CINA BING CHILLING ICE CREAM SPECIAL KAMBING"
                ),
                FailedDialogData(
                    failedData = "USG",
                    message = "User code not found"
                ),
                FailedDialogData(
                    failedData = "USG",
                    message = "JOSHN CINA BING CHILLING ICE CREAM SPECIAL KAMBING"
                ),
                FailedDialogData(
                    failedData = "USG",
                    message = "User code not found"
                ),
                FailedDialogData(
                    failedData = "USG",
                    message = "User code not found"
                )
            ),
            onClickTryAgain = {}
        )
    }
}