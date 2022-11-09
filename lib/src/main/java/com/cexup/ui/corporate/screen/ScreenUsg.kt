package com.cexup.ui.corporate.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.component.ListUsgResult
import compose.icons.Octicons
import com.cexup.ui.corporate.component.TabFeatures
import com.cexup.ui.corporate.component.TabParameter
import com.cexup.ui.corporate.theme.GreyBorder
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import compose.icons.octicons.ChevronLeft24
import compose.icons.octicons.ChevronRight24

data class ScreenUsgData(
    var listIdUsg : List<Int> = listOf(),
    var patientName: String = "",
    var patientUserCode: String = "",
)

@Composable
fun ScreenUsg(
    modifier: Modifier = Modifier,
    onViewResult: () -> Unit = {},
    onBackPressed: () -> Unit = {},
    onDownload: () -> Unit = {},
    onOpenHealson: () -> Unit = {},
    screenUsgData: ScreenUsgData = ScreenUsgData(),
    onGetListUsgRemote: (nextPage: Int) -> Unit = {},
) {
    val listOfTab = listOf(TabParameter.Day, TabParameter.Week, TabParameter.Month)
    var tabParameter by remember {
        mutableStateOf(TabParameter.Day)
    }
    var searchByNameOrId by remember { mutableStateOf("") }
    var countTable by remember { mutableStateOf("10") }
    val textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expand by remember { mutableStateOf(false) }
    val icon = if (expand) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    val listCountTable = listOf("1", "10", "50", "100")

    val pageNow by remember {
        mutableStateOf(5)
    }
    val totalPage by remember {
        mutableStateOf(263)
    }
//    val numberPage by remember {
//        mutableStateOf(listOf(1, 2, 3, 4))
//    }

    val ctx = LocalContext.current

    var nextPage by remember { mutableStateOf(0) }

    fun onNext() {
        if (screenUsgData.listIdUsg.isEmpty()) {
            Toast.makeText(ctx, "Latest page!", Toast.LENGTH_LONG).show()
        } else {
            nextPage = nextPage.plus(1)
            onGetListUsgRemote(nextPage)
        }
    }

    fun onPrev() {
        if (nextPage > 0) {
            nextPage = nextPage.minus(1)
            onGetListUsgRemote(nextPage)
        } else {
            Toast.makeText(ctx, "First page!", Toast.LENGTH_LONG).show()
        }
    }


    Scaffold(
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp.from(ctx), horizontal = 33.dp.from(ctx)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SHOW",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp.from(ctx),
                        fontSize = 12.sp.from(ctx)
                    )
                )
                Spacer(modifier = modifier.width(8.dp.from(ctx)))
                TextField(
                    value = countTable,
                    onValueChange = {
                        countTable = it
                    },
                    trailingIcon = {
                        Icon(icon, contentDescription = "")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    placeholder = { Text(text = countTable) },
                    enabled = false,
                    readOnly = true,
                    modifier = modifier
                        .width(86.dp.from(ctx))
                        .border(
                            width = 1.dp.from(ctx),
                            color = GreyBorder,
                            shape = RoundedCornerShape(10.dp.from(ctx))
                        )
                        .clickable { expand = !expand }
                )
                DropdownMenu(
                    expanded = expand,
                    onDismissRequest = { expand = false },
                    modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                ) {
                    listCountTable.forEach { label ->
                        DropdownMenuItem(onClick = {
                            countTable = label
                            expand = false
                        }) {
                            Text(text = label, color = Color.Black)
                        }
                    }
                }
                Spacer(modifier = modifier.width(12.dp.from(ctx)))
                TextField(
                    value = searchByNameOrId,
                    onValueChange = { searchByNameOrId = it },
                    placeholder = { Text(text = "Search by name or id patients") },
                    modifier = modifier
                        .navigationBarsPadding().imePadding()
                        .border(
                            width = 1.dp.from(ctx),
                            color = GreyBorder,
                            shape = RoundedCornerShape(10.dp.from(ctx))
                        )
                        .width(375.dp.from(ctx)),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
                )
                Spacer(modifier = modifier.width(12.dp.from(ctx)))
                Column(
                    modifier = modifier.width(291.3.dp.from(ctx))
                ) {
                    TabFeatures(
                        selectedTabIndex = tabParameter.ordinal,
                        onSelectedTab = { value ->
                            tabParameter = value
                        },
                        tabs = listOfTab,
                    )
                }
                Spacer(modifier = modifier.width(27.dp.from(ctx)))
                Button(
                    onClick = {
                        onOpenHealson()
                    },
                    shape = RoundedCornerShape(10.dp.from(ctx)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                    modifier = modifier.width(90.dp.from(ctx))
                ) {
                    Text(
                        text = "Periksa",
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }
        }
    ) {
        it.toString()
        Column(
            modifier = modifier
                .padding(start = 33.dp.from(ctx), end = 33.dp.from(ctx), bottom = 25.dp.from(ctx))
                .border(
                    1.dp.from(ctx),
                    GreyBorder, RoundedCornerShape(15.dp.from(ctx))
                )
        ) {
            LazyColumn(
                content = {
                    item {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 7.dp.from(ctx), horizontal = 11.dp.from(ctx)
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Name")
                            Spacer(modifier = Modifier.width(250.dp.from(ctx)))
                            Text("File")
                            Spacer(modifier = Modifier.width(100.dp.from(ctx)))
                            Text("ID")
                            Spacer(modifier = Modifier.width(200.dp.from(ctx)))
                            Text("Action")
                        }
                    }
                    items(screenUsgData.listIdUsg) {
                        Box(
                            modifier = modifier.padding(
                                vertical = 7.dp.from(ctx), horizontal = 11.dp.from(ctx)
                            )
                        ) {
                            ListUsgResult(
                                patientName = screenUsgData.patientName,
                                patientId = screenUsgData.patientUserCode,
                                onClickViewResult = { onViewResult() },
                                onClickDownload = { onDownload() },
                                onClickKebabIcon = {  }
                            )
                        }
                    }
                    if (screenUsgData.listIdUsg.isEmpty()) {
                        item {
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(10.dp.from(ctx)),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "no data",
                                    style = MaterialTheme.typography.body1.copy(
                                        fontSize = 16.sp.from(ctx),
                                        fontWeight = FontWeight(400)
                                    ),
                                )
                            }
                        }
                    }
                    item {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(10.dp.from(ctx)),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "showing $pageNow from $totalPage data",
                                style = MaterialTheme.typography.body1.copy(
                                    fontSize = 16.sp.from(ctx),
                                    fontWeight = FontWeight(400)
                                ),
                            )
                            Row(
                                modifier = modifier
                                    .width(318.dp.from(ctx))
                                    .height(35.dp.from(ctx))
                                    .padding(horizontal = 9.dp.from(ctx))
                                    .coloredShadow(
                                        color = Color.Black.copy(0.25f),
                                        offsetY = 4.dp.from(ctx),
                                        borderRadius = 10.dp.from(ctx),
                                        shadowRadius = 2.dp.from(ctx)
                                    )
                                    .background(
                                        MaterialTheme.colors.background,
                                        RoundedCornerShape(10.dp.from(ctx))
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Octicons.ChevronLeft24,
                                    contentDescription = "left",
                                    modifier = modifier.size(16.dp.from(ctx)).clickable {
                                        onPrev()
                                    },
                                    tint = Color.Black
                                )
    //                            LazyRow(
    //                                modifier.fillMaxHeight(),
    //                                verticalAlignment = Alignment.CenterVertically,
    //                                horizontalArrangement = Arrangement.sp.from(ctx)acedBy(33.dp.from(ctx)),
    //                                content = {
    //                                    items(count = 4) { index ->
    //                                        Text(
    //                                            text = numberPage[index].toString(),
    //                                            style = MaterialTheme.typography.body1.copy(
    //                                                fontSize = 16.sp.from(ctx),
    //                                                fontWeight = FontWeight(400)
    //                                            ),
    //                                        )
    //                                    }
    //                                }
    //                            )

                                Icon(
                                    Octicons.ChevronRight24,
                                    contentDescription = "right",
                                    modifier = modifier.size(16.dp.from(ctx)).clickable {
                                        onNext()
                                    },
                                    tint = Color.Black
                                )

                            }
                        }
                    }
                }
            )
        }
    }
}