package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import coil.compose.rememberImagePainter
import com.cexup.ui.corporate.theme.Heading
import compose.icons.Octicons
import compose.icons.octicons.KebabHorizontal16
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow

@Composable
fun CardPatientList(
    modifier: Modifier = Modifier,
    listUserCode: List<String> = listOf(),
    listName: List<String> = listOf(),
    listCurrentDisease: List<String> = listOf(),
    onClicked: (userCode: String) -> Unit
) {
//    val columnWeight = .9f
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
            .padding(
                start = 23.dp,
                end = 23.dp,
                top = 23.dp,
                bottom = 5.dp
            ),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(108.dp)
            ) {
                Text(
                    text = "Patient Name",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(137.dp)
            ) {
                Text(
                    text = "Patient ID",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(121.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Diseases",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    fontWeight = FontWeight(400)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(130.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Status",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    fontWeight = FontWeight(700)
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            content = {
                items(listUserCode.size) { index ->
                    PatientRow(
                        userCode = listUserCode[index],
                        name = listName[index],
                        currentDisease = listCurrentDisease[index],
                        status = "Old Patient",
                        onClicked = {
                            onClicked(listUserCode[index])
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun PatientRow(
    modifier: Modifier = Modifier,
    userCode: String = "",
    name: String = "",
    currentDisease: String = "",
    status: String = "",
    onClicked: () -> Unit = {},
) {
    val expanded = remember {
        mutableStateOf(false)
    }
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color(0xFFE7E7E7),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClicked() }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(137.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = "",
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.dummy_profile)
                            error(R.drawable.dummy_profile)
                        }
                    ),
                    contentDescription = "",
                    modifier = modifier
                        .clip(CircleShape)
                        .coloredShadow(MaterialTheme.colors.primary)
                        .width(28.84.dp)
                        .height(28.84.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier.width(10.dp))
                Text(
                    text = name.capitalizeWords(),
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    fontWeight = FontWeight(400)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(108.dp)
            ) {
                Text(
                    text = "ID $userCode",
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    fontWeight = FontWeight(400)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(121.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                Text(
                    text = (currentDisease).ifBlank { "Empty disease" },
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    fontWeight = FontWeight(400)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(130.dp)

            ) {
                Box(
                    modifier = modifier
                        .background(
                            color = if (expanded.value) Heading else Color.White,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(vertical = 2.dp, horizontal = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = status.capitalizeWords(),
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.body1,
                        color = if (expanded.value) Color.White else Color.Black,
                        fontWeight = FontWeight(700),
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))
                Box(
                    modifier = modifier.wrapContentSize(Alignment.TopEnd)
                ) {
                    Icon(
                        Octicons.KebabHorizontal16,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = modifier.clickable {
                            expanded.value = true
                        }
                    )
                    if (expanded.value) {
                        Popup(
                            offset = IntOffset(0, 10),
                            onDismissRequest = { expanded.value = false }
                        ) {
                            Card(
                                elevation = 1.dp,
                                shape = RoundedCornerShape(10.dp),
                                modifier = modifier.width(98.09.dp)
                            ) {
                                Column(
                                    modifier = modifier.padding(
                                        vertical = 6.dp,
                                        horizontal = 7.21.dp
                                    ),
                                ) {
                                    Text(
                                        text = "Profile",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = inactive,
                                            fontWeight = FontWeight(600),
                                            fontSize = 12.sp
                                        ),
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "Checkup",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = inactive,
                                            fontWeight = FontWeight(600),
                                            fontSize = 12.sp
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "Summary",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = inactive,
                                            fontWeight = FontWeight(600),
                                            fontSize = 12.sp
                                        )
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