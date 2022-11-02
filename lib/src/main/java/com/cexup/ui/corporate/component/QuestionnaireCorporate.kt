package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.fonts
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import compose.icons.Octicons
import compose.icons.octicons.Note24

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
    var answer: List<Answer>
)

data class Answer(
    var key: Int,
    var text: String,
)

data class Alternatives(
    var key: Int,
    var text: String,
)

enum class TypeQuestionnaireUI {
    Radio,Checkbox,Essay,Date,Image
}

fun Alternatives.toAnswer(): Answer {
    return Answer(
        key, text
    )
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
fun QuestionnaireCorporate(
    modifier: Modifier = Modifier,
    loading: Boolean,
    error: Boolean,
    questionnaire: List<QuestionnaireResponse>,
    onSubmit: () -> Unit,
    onChange: (parentIndex: Int, questionIndex: Int, alternativeIndex: Int, answer: Alternatives) -> Unit,
    onEssayValue: (parentIndex: Int, questionnaireIndex: Int, answer: String) -> Unit,
    onCheckBoxValue: (parentIndex: Int, questionnaireIndex: Int, answer: List<Alternatives>) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
//    val ctx = LocalContext.current

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
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Submit",
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
                            text = "Questionnaire",
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

        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                if (!loading && !error) {
                    if (questionnaire.isEmpty()) {
                        Column(modifier = modifier.fillMaxWidth()) {
                            Text(text = "No Data Questionnaire")
                        }
                    } else {
                        Column {
                            ComponentQuestionnaireCorporate(
                                questionnaireResponses = questionnaire,
                                onChange = { parentIndex, questionnaireIndex, alternativeIndex, alternative ->
                                    onChange(
                                        parentIndex,
                                        questionnaireIndex,
                                        alternativeIndex,
                                        alternative
                                    )
                                },
                                onEssayValue = { parentIndex, questionnaireIndex, answer ->
                                    onEssayValue(
                                        parentIndex, questionnaireIndex, answer
                                    )
                                },
                                onCheckBoxValue = { parentIndex, questionnaireIndex, answer ->
                                    onCheckBoxValue(
                                        parentIndex, questionnaireIndex, answer
                                    )
                                }
                            )
                        }
                    }
                }
                if (loading) {
                    QuestionnaireShimmer()
                }
                if (error) {
                    Column(modifier = modifier.fillMaxWidth()) {
                        Text(text = "No Data Questionnaire")
                    }
                }
            }
        }
    )
}

@Composable
fun QuestionnaireShimmer(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier
                    .width(20.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    )
            ) {

            }
            Spacer(modifier = modifier.width(10.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    )
            ) {

            }
        }
        Spacer(modifier = modifier.height(20.dp))
        for (i in 0..3) {
            Card(modifier = modifier.fillMaxWidth()) {
                Column(modifier = modifier.padding(10.dp)) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer(),
                                color = Color.LightGray,
                            )
                    ) {

                    }
                    Spacer(modifier = modifier.height(10.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = modifier
                                .width(20.dp)
                                .height(20.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = Color.LightGray,
                                )
                        ) {

                        }
                        Spacer(modifier = modifier.width(10.dp))
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = Color.LightGray,
                                )
                        ) {

                        }
                    }
                    Spacer(modifier = modifier.height(5.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = modifier
                                .width(20.dp)
                                .height(20.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = Color.LightGray,
                                )
                        ) {

                        }
                        Spacer(modifier = modifier.width(10.dp))
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .placeholder(
                                    visible = true,
                                    highlight = PlaceholderHighlight.shimmer(),
                                    color = Color.LightGray,
                                )
                        ) {

                        }
                    }
                }
            }
            Spacer(modifier = modifier.height(10.dp))
        }
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    )
            ) {

            }
            Spacer(modifier = modifier.height(10.dp))
            Column(
                modifier = modifier
                    .width(50.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color.LightGray,
                    )
            ) {

            }
        }
    }
}

@Composable
fun ComponentQuestionnaireCorporate(
    modifier: Modifier = Modifier,
    questionnaireResponses: List<QuestionnaireResponse>,
    onChange: (parentIndex: Int, questionnaireIndex: Int, alternativeIndex: Int, answer: Alternatives) -> Unit,
    onEssayValue: (parentIndex: Int, questionnaireIndex: Int, answer: String) -> Unit,
    onCheckBoxValue: (parentIndex: Int, questionnaireIndex: Int, answer: List<Alternatives>) -> Unit,
) {
    questionnaireResponses.forEachIndexed { parentIndex, questionnaireResponse ->
        Text(
            text = "${parentIndex + 1}. ${questionnaireResponse.name}",
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            modifier = modifier.padding(horizontal = 10.dp)
        )
        questionnaireResponse.questionnaire.forEachIndexed { indexQuestionnaire, dataQuestionnaire ->
            val type = dataQuestionnaire.type.toTypeQuestionnaireUI()
            var selectedRadio by remember { mutableStateOf("") }
            val listAnswer = mutableListOf<Alternatives>()
            Card(modifier = modifier.padding(10.dp)) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(text = dataQuestionnaire.question)
                    Spacer(modifier = modifier.height(10.dp))
                    when (type) {
                        TypeQuestionnaireUI.Checkbox -> {
                            dataQuestionnaire.alternatives.forEachIndexed { _, alternative ->
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
                                            } catch (e: Exception) {
                                                e.printStackTrace()
                                            }
                                            listAnswer.sortedBy { alternative ->
                                                alternative.key
                                            }
                                            onCheckBoxValue(
                                                parentIndex,
                                                indexQuestionnaire,
                                                listAnswer
                                            )
                                        },
                                        enabled = true,
                                        colors = CheckboxDefaults.colors(MaterialTheme.colors.primary)
                                    )
                                    Text(
                                        text = alternative.text,
                                        modifier = modifier.padding(start = 8.dp)
                                    )
                                }
                            }
                        }
                        TypeQuestionnaireUI.Radio -> {
                            dataQuestionnaire.alternatives.forEachIndexed { indexOfAlternative, alternative ->
                                val alterText = alternative.text
                                Row(
                                    modifier = modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = (alterText == selectedRadio),
                                        onClick = {
                                            selectedRadio = alterText
                                            onChange(
                                                parentIndex,
                                                indexQuestionnaire,
                                                indexOfAlternative,
                                                alternative
                                            )
                                        },
                                        enabled = true,
                                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary),
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
    }
}