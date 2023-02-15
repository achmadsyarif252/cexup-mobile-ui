package com.cexup.ui.corporate.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.component.common.TextFieldCexup
import com.cexup.ui.corporate.component.CardPatientInFeature
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from

data class ScreenSOAPUIState(
    val patientName: String? = "",
    val patientThumb: String? = "",
    var patientUserCode: String? = "",
    val message: String = "",
    val error: Boolean = false,
    val loading: Boolean = true,
)

@Composable
fun ScreenSOAP(
    soapUIState: ScreenSOAPUIState,
    onSaveClicked: (
        subjective: String,
        objective: String,
        assessment: String,
        planning: String,
    ) -> Unit,
    onButtonBackPressed: () -> Unit,
) {
    val ctx = LocalConfiguration.current
    var textSubjective by remember { mutableStateOf("") }
    var textObjective by remember { mutableStateOf("") }
    var textAssessment by remember { mutableStateOf("") }
    var textPlanning by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(30.dp.from(ctx)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardPatientInFeature(
                thumb = soapUIState.patientThumb ?: "",
                name = soapUIState.patientName ?: "",
                id = soapUIState.patientUserCode ?: "",
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    onButtonBackPressed()
                },
                modifier = Modifier
                    .width(89.dp.from(ctx))
                    .height(35.dp.from(ctx)),
                colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                shape = RoundedCornerShape(10.dp.from(ctx)),
                contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.back),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp.from(ctx),
                        letterSpacing = 1.sp.from(ctx),
                        color = Color.White
                    )
                )
            }
        }
        Row {
            //Subjective
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp.from(ctx)),
                verticalArrangement = Arrangement.spacedBy(15.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.subjective_soap),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 18.sp.from(ctx),
                    letterSpacing = -0.08f.sp.from(ctx)
                )
                Card(
                    modifier = Modifier
                        .heightIn(min = 128.dp.from(ctx)),
                    shape = RoundedCornerShape(16.dp.from(ctx)),
                    elevation = 2.dp
                ) {
                    TextFieldCexup(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp.from(ctx)),
                        value = textSubjective,
                        onValueChange = {
                            textSubjective = it
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            errorIndicatorColor = Color.White,
                            disabledIndicatorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        ),
                        textStyle = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 18.sp.from(ctx),
                        )
                    )
                }
            }
            //Objective
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp.from(ctx)),
                verticalArrangement = Arrangement.spacedBy(15.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.objective_soap),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 18.sp.from(ctx),
                    letterSpacing = -0.08f.sp.from(ctx)
                )
                Card(
                    modifier = Modifier
                        .heightIn(min = 128.dp.from(ctx)),
                    shape = RoundedCornerShape(16.dp.from(ctx)),
                    elevation = 2.dp
                ) {
                    TextFieldCexup(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp.from(ctx)),
                        value = textObjective,
                        onValueChange = {
                            textObjective = it
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            errorIndicatorColor = Color.White,
                            disabledIndicatorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        ),
                        textStyle = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 18.sp.from(ctx),
                        )
                    )
                }
            }
        }

        Row {
            //Assessement
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp.from(ctx)),
                verticalArrangement = Arrangement.spacedBy(15.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.assessment_soap),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 18.sp.from(ctx),
                    letterSpacing = -0.08f.sp.from(ctx)
                )
                Card(
                    modifier = Modifier
                        .heightIn(min = 128.dp.from(ctx)),
                    shape = RoundedCornerShape(16.dp.from(ctx)),
                    elevation = 2.dp
                ) {
                    TextFieldCexup(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp.from(ctx)),
                        value = textAssessment,
                        onValueChange = {
                            textAssessment = it
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            errorIndicatorColor = Color.White,
                            disabledIndicatorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        ),
                        textStyle = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 18.sp.from(ctx),
                        )
                    )
                }
            }
            //Planning
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp.from(ctx)),
                verticalArrangement = Arrangement.spacedBy(15.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.planning_soap),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 18.sp.from(ctx),
                    letterSpacing = -0.08f.sp.from(ctx)
                )
                Card(
                    modifier = Modifier
                        .heightIn(min = 128.dp.from(ctx)),
                    shape = RoundedCornerShape(16.dp.from(ctx)),
                    elevation = 2.dp
                ) {
                    TextFieldCexup(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp.from(ctx)),
                        value = textPlanning,
                        onValueChange = {
                            textPlanning = it
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            errorIndicatorColor = Color.White,
                            disabledIndicatorColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedIndicatorColor = Color.White
                        ),
                        textStyle = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 18.sp.from(ctx),
                        )
                    )
                }
            }
        }

        Row {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    textSubjective = ""
                    textObjective = ""
                    textAssessment = ""
                    textPlanning = ""
                },
                modifier = Modifier.width(143.dp.from(ctx)),
                colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                shape = RoundedCornerShape(10.dp.from(ctx)),
//                contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 22.sp.from(ctx),
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(12.dp.from(ctx)))
            Button(
                onClick = {
                    onSaveClicked(
                        textSubjective,
                        textObjective,
                        textAssessment,
                        textPlanning,
                    )
                },
                modifier = Modifier.width(143.dp.from(ctx)),
                colors = ButtonDefaults.buttonColors(backgroundColor = BlueJade),
                shape = RoundedCornerShape(10.dp.from(ctx)),
//                contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 22.sp.from(ctx),
                    color = Color.White
                )
            }
        }
    }
}