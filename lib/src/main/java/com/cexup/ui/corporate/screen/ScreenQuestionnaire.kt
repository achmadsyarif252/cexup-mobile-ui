package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.component.*
import compose.icons.Octicons
import compose.icons.octicons.Note24
import com.cexup.ui.R

data class QuestionnaireResponse(
    var id: String,
    var slug: String,
    var name: String,
    var questionnaire: MutableList<Questionnaire>,
    var created_at: String,
    var updated_at: String
)

data class Questionnaire(
    var type: String,
    var question: String,
    val alternatives: List<Alternatives>,
    var answer: List<Alternatives>
)

data class Alternatives(
    var key: Int,
    var text: String,
)

enum class QuestionnaireDataState {
    LOADING, SUCCESS, FAILED,
}

fun String.toTypeQuestionnaireUI(): TypeQuestionnaireUI {
    return when(this) {
        "radio" -> TypeQuestionnaireUI.Radio
        "checkbox" -> TypeQuestionnaireUI.Checkbox
        "essay" -> TypeQuestionnaireUI.Essay
        "date" -> TypeQuestionnaireUI.Date
        "image" -> TypeQuestionnaireUI.Image
        else -> TypeQuestionnaireUI.Checkbox
    }
}

@Composable
fun ScreenQuestionnaire(
    modifier: Modifier = Modifier,
    questionnaireDataState: QuestionnaireDataState,
    questionnaireResponses: List<QuestionnaireResponse>,
    onSubmit: () -> Unit,
    onRadioValue: (
        parentIndex: Int,
        questionIndex: Int,
        alternativeIndex: Int,
        answer: Alternatives
    ) -> Unit,
    onEssayValue: (
        parentIndex: Int,
        questionnaireIndex: Int,
        answer: String
    ) -> Unit,
    onCheckBoxValue: (
        parentIndex: Int,
        questionnaireIndex: Int,
        answer: List<Alternatives>
    ) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Column {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            onSubmit()
                        },
                        modifier = modifier
                            .padding(vertical = 3.dp, horizontal = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.submit),
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ),
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Octicons.Note24, "")
                        Spacer(modifier = modifier.width(5.dp))
                        Text(
                            text = stringResource(id = R.string.questionnaire),
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.1.sp
                            )
                        )
                    }
                    Box {

                    }
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        scaffoldState = scaffoldState,
        bottomBar = {

        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            when(questionnaireDataState) {
                QuestionnaireDataState.LOADING -> {
                    QuestionnaireShimmer()
                }
                QuestionnaireDataState.FAILED -> {
                    Column(modifier = modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.no_data))
                    }
                }
                QuestionnaireDataState.SUCCESS -> {
                    if (questionnaireResponses.isEmpty()) {
                        Column(modifier = modifier.fillMaxWidth()) {
                            Text(text = "No Data Questionnaire")
                        }
                    } else {
                        Column {
                            questionnaireResponses.forEachIndexed {
                                    parentIndex, questionnaireResponse ->
                                Text(
                                    text = "${parentIndex + 1}. ${questionnaireResponse.name}",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    ),
                                    modifier = modifier.padding(horizontal = 10.dp)
                                )
                                questionnaireResponse.questionnaire.forEachIndexed {
                                        indexQuestionnaire, dataQuestionnaire ->
                                    val type = dataQuestionnaire.type.toTypeQuestionnaireUI()
                                    var selectedRadio by remember { mutableStateOf("") }
                                    val listAnswer = mutableListOf<Pair<Int, String>>()
                                    CardQuestionnaireCorporate(
                                        question = dataQuestionnaire.question,
                                        selectedRadio = selectedRadio,
                                        indexQuestionnaire = indexQuestionnaire,
                                        parentIndex = parentIndex,
                                        alternatives = dataQuestionnaire.alternatives.map {
                                                alternative -> Pair(alternative.key, alternative.text)
                                        },
                                        listAnswer = listAnswer,
                                        typeQuestionnaireUI = type,
                                        onRadioValue = {
                                                parentIndex, questionnaireIndex, alternativeIndex,
                                                alternative, radioSelected ->
                                            onRadioValue(
                                                parentIndex,
                                                questionnaireIndex,
                                                alternativeIndex,
                                                Alternatives(alternative.first, alternative.second)
                                            )
                                            selectedRadio = radioSelected
                                        },
                                        onEssayValue = { parentIndex, questionnaireIndex, answer ->
                                            onEssayValue(parentIndex, questionnaireIndex, answer)
                                        },
                                        onCheckBoxValue = { parentIndex, questionnaireIndex ->
                                            listAnswer.sortBy { pair -> pair.first }
                                            onCheckBoxValue(
                                                parentIndex,
                                                questionnaireIndex,
                                                listAnswer.map { pair ->
                                                    Alternatives(pair.first, pair.second)
                                                }
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}