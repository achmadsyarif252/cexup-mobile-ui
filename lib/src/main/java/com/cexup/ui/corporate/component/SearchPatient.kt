package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.cexup.ui.corporate.screen.SearchPatientUIState
import com.cexup.ui.corporate.theme.GreyBlackStetoscope
import com.cexup.ui.corporate.theme.inactive
import compose.icons.Octicons
import compose.icons.octicons.Search16

@Composable
fun SearchPatient(
    data: List<Pair<String, String>>,
    onSearchPatient: (keywords: String) -> Unit = {},
    onPatientDetail: (userCode: String) -> Unit = {},
    onCheckUp: (userCode: String) -> Unit = {},
) {
    val state = remember {
        mutableStateOf(TextFieldValue(""))
    }
    Box {
        val expanded = remember {
            mutableStateOf(false)
        }

        if (expanded.value) {
            Popup(
                offset = IntOffset(0, 50),
                onDismissRequest = { expanded.value = false }
            ) {
                Box {
                    Card(
                        shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp),
                        modifier = Modifier
                            .width(421.66.dp)
                            .padding(10.dp),
                        elevation = 2.dp
                    ) {
                        LazyColumn {
                            items(data.size) {
                                val patientName = data[it].first
                                val userCode = data[it].second
                                Row(
                                    modifier = Modifier
                                        .clickable { }
                                        .background(Color.White)
                                        .height(57.dp)
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .width(19.dp)
                                                .height(6.dp)
                                                .background(inactive)
                                        ) {

                                        }
                                        Spacer(modifier = Modifier.width(18.dp))
                                        Text(
                                            text = patientName,
                                            style = MaterialTheme.typography.body1.copy(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight(400),
                                                color = GreyBlackStetoscope
                                            )
                                        )
                                    }

                                    Row {
                                        Text(
                                            text = "Detail",
                                            style = MaterialTheme.typography.body1.copy(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight(600),
                                                color = MaterialTheme.colors.secondaryVariant
                                            ),
                                            modifier = Modifier.clickable {
                                                onPatientDetail(userCode)
                                            }
                                        )
                                    }
                                    Row {
                                        Text(
                                            text = "Check Up",
                                            style = MaterialTheme.typography.body1.copy(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight(600),
                                                color = MaterialTheme.colors.secondaryVariant
                                            ),
                                            modifier = Modifier.clickable {
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
        }
        Card(
            shape = RoundedCornerShape(25.dp),
            elevation = 2.dp,
            modifier = Modifier.height(56.dp)
        ) {
            TextField(
                value = state.value,
                onValueChange = { value ->
                    if (value != TextFieldValue("")) {
                        state.value = value
                        expanded.value = true
                        onSearchPatient(value.text)
                    } else {
                        expanded.value = false
                    }
                },
                modifier = Modifier
                    .width(421.66.dp),
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                placeholder = {
                    Text(
                        text = "Search patients",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colors.secondaryVariant
                        ),
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Octicons.Search16,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = inactive
                    )
                },
                trailingIcon = {
                    if (state.value != TextFieldValue("")) {
                        IconButton(
                            onClick = {
                                state.value = TextFieldValue("")
                                expanded.value = false
                            }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(24.dp),
                                tint = inactive
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = Color.Black,
                    leadingIconColor = Color.White,
                    trailingIconColor = Color.White,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )
        }

    }
}