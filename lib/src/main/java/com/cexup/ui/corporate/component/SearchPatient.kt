package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.cexup.ui.R
import com.cexup.ui.component.common.TextFieldCexup
import com.cexup.ui.corporate.screen.SearchPatientUIState
import com.cexup.ui.corporate.theme.FoundationBorderline
import com.cexup.ui.corporate.theme.FoundationInactive
import com.cexup.ui.corporate.theme.GreyBlackStetoscope
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.mediaquery.from
import kotlinx.coroutines.launch

@Composable
fun SearchPatient(
    onSearchPatient: suspend (keywords: String) -> SearchPatientUIState = { _ ->
        SearchPatientUIState()
    },
    onPatientDetail: (userCode: String) -> Unit = {},
    onCheckUp: (userCode: String) -> Unit = {},
) {
    val ctx = LocalContext.current
    val state = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    var list by remember { mutableStateOf<List<Pair<String, String>>>(listOf()) }
    val expanded = remember { mutableStateOf(false) }
    Box {
        if (expanded.value) {
            Popup(
                offset = IntOffset(
                    0,
                    ((ctx.resources.displayMetrics.heightPixels / LocalDensity.current.density) * 0.086f).toInt()
                ),
                onDismissRequest = { expanded.value = false }
            ) {
                Box {
                    Card(
                        shape = RoundedCornerShape(
                            bottomStart = 25.dp.from(ctx),
                            bottomEnd = 25.dp.from(ctx)
                        ),
                        modifier = Modifier
                            .heightIn(max = (ctx.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density) / 2.59f)
                            .width(543.dp.from(ctx)),
                        elevation = 2.dp.from(ctx)
                    ) {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))) {
                            items(list.size) {
                                val patientName = list[it].first
                                val userCode = list[it].second
                                Row(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = 16.dp.from(ctx),
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Divider(
                                        modifier = Modifier
                                            .width(19.dp.from(ctx))
                                            .height(6.dp.from(ctx))
                                            .background(inactive)
                                    )
                                    Spacer(modifier = Modifier.width(18.dp.from(ctx)))
                                    Text(
                                        modifier = Modifier.widthIn(max = 175.dp.from(ctx)),
                                        text = patientName,
                                        style = MaterialTheme.typography.body1.copy(
                                            fontSize = 16.sp.from(ctx),
                                            fontWeight = FontWeight(400),
                                            color = GreyBlackStetoscope
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = stringResource(id = R.string.detail),
                                        style = MaterialTheme.typography.body1.copy(
                                            fontSize = 16.sp.from(ctx),
                                            fontWeight = FontWeight(600),
                                            color = MaterialTheme.colors.secondaryVariant,
                                            textAlign = TextAlign.Center
                                        ),
                                        modifier = Modifier
                                            .width(74.dp.from(ctx))
                                            .align(CenterVertically)
                                            .clickable {
                                                onPatientDetail(userCode)
                                            }
                                    )
                                    Text(
                                        text = stringResource(id = R.string.check_up),
                                        style = MaterialTheme.typography.body1.copy(
                                            fontSize = 16.sp.from(ctx),
                                            fontWeight = FontWeight(600),
                                            color = MaterialTheme.colors.secondaryVariant,
                                            textAlign = TextAlign.Center
                                        ),
                                        modifier = Modifier
                                            .width(102.dp.from(ctx))
                                            .align(CenterVertically)
                                            .clickable {
                                                onCheckUp(userCode)
                                            }
                                    )

                                }
                            }
                        }
                    }
                }
            }
        }
        Card(
            shape = RoundedCornerShape(8.dp.from(ctx)),
            modifier = Modifier.height(56.dp.from(ctx))
        ) {
            TextFieldCexup(
                value = state.value,
                onValueChange = { value ->
                    scope.launch {
                        if (!value.isEmpty()) {
                            state.value = value
                            expanded.value = true
                            val searchResult = onSearchPatient(value)
                            list = searchResult.data
                        } else {
                            state.value = value
                            expanded.value = false
                        }
                    }
                },
                modifier = Modifier.width(543.dp.from(ctx)),
                textStyle = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp.from(ctx),
                    lineHeight = 22.sp.from(ctx),
                    color = Color.Black
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_patients),
                        style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 22.sp.from(ctx),
                            color = FoundationInactive
                        ),
                    )
                },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "search",
                        modifier = Modifier
                            .size(20.dp.from(ctx)),
                    )
                },
                trailingIcon = {
                    if (!state.value.isEmpty()) {
                        IconButton(
                            onClick = {
                                state.value = ""
                                expanded.value = false
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
                    backgroundColor = FoundationBorderline,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )
        }

    }
}