package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.component.CardMedicalInspection
import com.cexup.ui.corporate.component.CardReportDocument
import com.cexup.ui.corporate.theme.Heading

@ExperimentalMaterialApi
@Composable
fun ScreenReport(
    modifier: Modifier = Modifier,
    namePatient : String,
    userCode : String,
    thumb : String = "",
){
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)) {
        Text(
            text = "New Check Up",
            style = MaterialTheme.typography.body1.copy(
                color = Heading,
                fontSize = 22.sp,
                fontWeight = FontWeight(700)
            ),
        )
        LazyRow(content = {
            items(count = 4){
                CardMedicalInspection(
                    name = namePatient,
                    userCode = userCode,
                    thumb = "",
                    onClick = {

                    }
                )
            }
        },
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        )
        Spacer(modifier = Modifier.height(26.dp))
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            LazyColumn(
                modifier = modifier.fillMaxHeight()
            ) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Check Up Result",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontSize = 22.sp,
                                fontWeight = FontWeight(700)
                            ),
                        )
                        Text(
                            text = "・",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.primaryVariant,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400)
                            ),
                        )
                        Text(
                            text = namePatient,
                            style = MaterialTheme.typography.body1.copy(
                                color = Heading,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400)
                            ),
                        )
                    }
                    Spacer(modifier = Modifier.height(17.dp))
                }
                item {
                    Text(
                        text = "Health Reports Document",
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.primaryVariant,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600)
                        ),
                    )
                }
                item {
                    Row(
                        modifier = modifier.width(290.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "This Month",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.secondaryVariant,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500)
                            ),
                        )
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.secondaryVariant,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500)
                            ),
                        )
                    }
                }
                items(count= 6){
                    CardReportDocument(nameFile = "Health Rate Report.Doc", fileSize = "" )
                }
            }
        }
    }
}