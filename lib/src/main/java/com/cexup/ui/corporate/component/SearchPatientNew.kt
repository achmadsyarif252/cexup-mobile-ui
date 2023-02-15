package com.cexup.ui.corporate.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.component.common.TextFieldCexup
import com.cexup.ui.corporate.theme.FoundationBorderline
import com.cexup.ui.corporate.theme.FoundationInactive
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchPatientNew(
    valueTextSearch:String = "",
    onEnterPressed: (value: String) -> Unit,
    onValueChange: (value: String) -> Unit,
){
    val ctx = LocalConfiguration.current

    val keyboardController = LocalSoftwareKeyboardController.current

    var valueText by remember {
        mutableStateOf("")
    }
    Card(
        shape = RoundedCornerShape(8.dp.from(ctx)),
        modifier = Modifier.height(56.dp.from(ctx))
    ) {
        TextFieldCexup(
            value = valueTextSearch,
            onValueChange = {
                valueText = it
                onValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = MaterialThemeCexup.typography.hh3.copy(
                color = MaterialThemeCexup.colors.color.text.textMain
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_patients),
                    style = MaterialThemeCexup.typography.hh3.copy(
                        color = MaterialThemeCexup.colors.color.text.textInactive
                    ),
                )
            },
            trailingIcon = {
                if (!valueTextSearch.isEmpty()) {
                    IconButton(
                        onClick = {
                            onValueChange("")
                        }) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp.from(ctx))
                                .size(24.dp.from(ctx)),
                            tint = FoundationInactive
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(8.dp.from(ctx)),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                cursorColor = Color.Black,
                backgroundColor = MaterialThemeCexup.colors.color.borderline.borderline2,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                onEnterPressed(valueText)
                keyboardController?.hide()
            }),
        )
    }
}