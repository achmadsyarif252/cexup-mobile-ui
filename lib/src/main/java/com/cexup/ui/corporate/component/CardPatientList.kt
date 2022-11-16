package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.KebabHorizontal16


@Composable
fun PatientRow(
    modifier: Modifier = Modifier,
    userCode: String = "",
    name: String = "",
    currentDisease: String = "",
    status: String = "",
    onClicked: () -> Unit = {},
) {
    var expanded = remember {
        mutableStateOf(false)
    }
    val items = listOf("Profile", "Checkup", "Summary")
    val ctx = LocalContext.current
    Card(
        elevation = 0.dp.from(ctx),
        shape = RoundedCornerShape(10.dp.from(ctx)),
        modifier = modifier
            .clip(RoundedCornerShape(10.dp.from(ctx)))
            .border(
                width = 1.dp.from(ctx),
                color = Color(0xFFE7E7E7),
                shape = RoundedCornerShape(10.dp.from(ctx))
            )
            .clickable { onClicked() }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp.from(ctx), vertical = 8.dp.from(ctx)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(137.dp.from(ctx))
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = "",
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.dummy_profile_small)
                            error(R.drawable.dummy_profile_small)
                        }
                    ),
                    contentDescription = "",
                    modifier = modifier
                        .clip(CircleShape)
                        .coloredShadow(MaterialTheme.colors.primary)
                        .width(28.84.dp.from(ctx))
                        .height(28.84.dp.from(ctx)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier.width(10.dp.from(ctx)))
                Text(
                    text = name.capitalizeWords(),
                    fontSize = 12.sp.from(ctx),
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    fontWeight = FontWeight(400),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(108.dp.from(ctx))
            ) {
                Text(
                    text = "ID $userCode",
                    fontSize = 12.sp.from(ctx),
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    fontWeight = FontWeight(400)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(121.dp.from(ctx)),
                horizontalArrangement = Arrangement.Center

            ) {
                Text(
                    text = (currentDisease).ifBlank { "Empty disease" },
                    fontSize = 12.sp.from(ctx),
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                    fontWeight = FontWeight(400)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.width(130.dp.from(ctx))

            ) {
                Box(
                    modifier = modifier
                        .padding(vertical = 2.dp.from(ctx), horizontal = 10.dp.from(ctx)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = status.capitalizeWords(),
                        fontSize = 12.sp.from(ctx),
                        style = MaterialTheme.typography.body1,
                        color = Color.Black,
                        fontWeight = FontWeight(700),
                    )
                }
                Spacer(modifier = Modifier.width(15.dp.from(ctx)))
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

                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                        modifier = modifier.width(98.09.dp.from(ctx))
                    ) {
                        items.forEachIndexed { index, s ->
                            DropdownMenuItem(
                                modifier = Modifier
                                    .heightIn(max = 28.dp.from(ctx)),
                                onClick = {
                                },

                                ) {
                                Text(
                                    text = s,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = inactive,
                                        fontWeight = FontWeight(600),
                                        fontSize = 12.sp.from(ctx)
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