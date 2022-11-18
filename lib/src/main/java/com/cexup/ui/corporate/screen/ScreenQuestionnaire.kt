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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.component.*
import compose.icons.Octicons
import compose.icons.octicons.Note24
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from

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
    onBackPress: () -> Unit,
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
    val ctx = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Column {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp.from(ctx)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            onSubmit()
                        },
                        modifier = modifier
                            .padding(vertical = 3.dp.from(ctx), horizontal = 5.dp.from(ctx)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        ),
                        shape = RoundedCornerShape(8.dp.from(ctx))
                    ) {
                        Text(
                            text = stringResource(id = R.string.submit),
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp.from(ctx),
                                letterSpacing = 1.sp.from(ctx),
                                color = Color.White
                            ),
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Octicons.Note24, "")
                        Spacer(modifier = modifier.width(5.dp.from(ctx)))
                        Text(
                            text = stringResource(id = R.string.questionnaire),
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 15.sp.from(ctx),
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.1.sp.from(ctx)
                            )
                        )
                    }
                    Button(
                        onClick = {
                            onBackPress()
                        },
                        modifier = modifier
                            .width(89.dp.from(ctx))
                            .height(35.dp.from(ctx)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                        shape = RoundedCornerShape(10.dp.from(ctx)),
                        contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.corporate_back),
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight(600),
                                    fontSize = 14.sp.from(ctx),
                                    letterSpacing = 1.sp.from(ctx),
                                    color = Color.White
                                ),
                                modifier = modifier.padding(5.dp.from(ctx))
                            )
                        }
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
            verticalArrangement = Arrangement.spacedBy(10.dp.from(ctx)),
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
                                        fontSize = 14.sp.from(ctx)
                                    ),
                                    modifier = modifier.padding(horizontal = 10.dp.from(ctx))
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