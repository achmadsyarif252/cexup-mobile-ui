package com.cexup.ui.corporate.screen

import androidx.compose.runtime.Composable

=
=======
data class SearchPatientUIState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val errorMessage: String = "",
    val data: List<Pair<String, String>> = listOf(),
)

@Composable
fun BaseScreen(

) {

}