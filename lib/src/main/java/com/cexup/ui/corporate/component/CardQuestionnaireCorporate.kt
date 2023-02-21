package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.theme.fonts


enum class TypeQuestionnaireUI {
    Radio,Checkbox,Essay,Date,Image
}

@Composable
fun CardQuestionnaireCorporate(
    modifier: Modifier = Modifier,
    question: String,
    alternatives: List<Pair<Int, String>>, // key, text
    typeQuestionnaireUI: TypeQuestionnaireUI,
    listAnswer: MutableList<Pair<Int, String>>, // key, text
    parentIndex: Int,
    indexQuestionnaire: Int,
    selectedRadio: String,
    onRadioValue: (
        parentIndex: Int,
        questionnaireIndex: Int,
        alternativeIndex: Int,
        answer: Pair<Int, String>, // key, text
        selectedRadio: String,
    ) -> Unit,
    onEssayValue: (
        parentIndex: Int,
        questionnaireIndex: Int,
        answer: String
    ) -> Unit,
    onCheckBoxValue: (
        parentIndex: Int,
        questionnaireIndex: Int,
    ) -> Unit,
) {
    Card(modifier = modifier.padding(10.dp)) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = question)
            Spacer(modifier = modifier.height(10.dp))
            when (typeQuestionnaireUI) {
                TypeQuestionnaireUI.Checkbox -> {
                    alternatives.forEachIndexed { _, alternative ->
                        var selectedCheckBox by remember { mutableStateOf(false) }
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selectedCheckBox,
                                onCheckedChange = {
                                    try {
                                        if (it) {
                                            selectedCheckBox = true
                                            listAnswer.add(alternative)
                                        }
                                        else {
                                            listAnswer.remove(alternative)
                                            selectedCheckBox = false
                                        }
                                    } catch (e: Exception) { e.printStackTrace() }
                                    onCheckBoxValue(
                                        parentIndex,
                                        indexQuestionnaire,
                                    )
                                },
                                enabled = true,
                                colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
                            )
                            Text(
                                text = alternative.second,
                                modifier = modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
                TypeQuestionnaireUI.Radio -> {
                    alternatives.forEachIndexed { indexOfAlternative, alternative ->
                        val alterText = alternative.second
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (alterText == selectedRadio),
                                onClick = {
                                    onRadioValue(
                                        parentIndex,
                                        indexQuestionnaire,
                                        indexOfAlternative,
                                        alternative,
                                        alterText,
                                    )
                                },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colors.primary
                                ),
                            )
                            Text(
                                text = alterText,
                                modifier = modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
                TypeQuestionnaireUI.Essay -> {
                    var essay by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = essay,
                        onValueChange = {
                            essay = it
                            onEssayValue(
                                parentIndex,
                                indexQuestionnaire,
                                it
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Essay",
                                style = MaterialTheme.typography.body2.copy(
                                    color = MaterialTheme.colors.onSecondary
                                )
                            )
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontFamily = fonts,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400)
                        ),
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                            .navigationBarsPadding().imePadding(),
                        readOnly = false,
                        enabled = true,
                    )
                }
                TypeQuestionnaireUI.Date -> {

                }
                TypeQuestionnaireUI.Image -> {

                }
            }

        }
    }


}