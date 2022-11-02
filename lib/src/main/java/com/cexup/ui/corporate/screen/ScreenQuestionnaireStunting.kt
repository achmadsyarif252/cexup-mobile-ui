package com.cexup.ui.corporate.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cexup.ui.corporate.component.*

@Composable
fun ScreenQuestionnaireStunting(
    modifier: Modifier = Modifier,
    loading: Boolean,
    error: Boolean,
    questionnaire: List<QuestionnaireResponse>,
    onSubmit: () -> Unit,
    onChange: (
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
    QuestionnaireCorporate(
        loading = loading,
        error = error,
        questionnaire = questionnaire,
        onSubmit = onSubmit,
        onChange = onChange,
        onEssayValue = onEssayValue,
        onCheckBoxValue = onCheckBoxValue
    )
}