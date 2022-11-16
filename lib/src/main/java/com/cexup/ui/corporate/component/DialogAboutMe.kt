package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.fontsCorp
import com.cexup.ui.utils.mediaquery.from

@Composable
fun DialogAboutMe(
    show: Boolean = false,
    onDismiss: () -> Unit
) {
    var text by remember {
        mutableStateOf("Lorem ipsum dolor sit amet, consectetur adipiscing " +
                "elit. Aliquet fermentum neque...")
    }

    if (show) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            LayoutDialogAboutMe(
                texAboutMe = text,
                onValueChange = {
                    text = it
                },
                onSaveClicked = {
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun LayoutDialogAboutMe(
    texAboutMe: String,
    onValueChange: (String) -> Unit,
    onSaveClicked: () -> Unit,
) {
    val ctx = LocalContext.current
    Column {
        Box(
            modifier = Modifier
                .height(265.dp.from(ctx))
                .width(459.dp.from(ctx))
                .clip(
                    RoundedCornerShape(20.dp.from(ctx))
                )
                .background(Color.White)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 23.dp.from(ctx))
            ) {
                Text(
                    text = stringResource(id = R.string.edit_about_me),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 22.sp.from(ctx),
                        letterSpacing = 1.sp.from(ctx),
                        color = Heading
                    ),
                )
                Spacer(modifier = Modifier.height(9.dp.from(ctx)))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 42.dp.from(ctx), end = 42.dp.from(ctx))
                        .wrapContentHeight()


                ) {
                    OutlinedTextField(
                        value = texAboutMe,
                        onValueChange = {
                            onValueChange(it)
                        },
                        textStyle = TextStyle(
                            fontSize = 12.sp.from(ctx),
                            fontFamily = fontsCorp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Justify
                        ),
                        modifier = Modifier
                            .width(375.dp.from(ctx))
                            .height(111.dp.from(ctx)),
                        shape = RoundedCornerShape(10.dp.from(ctx))
                    )
                }
                Spacer(modifier = Modifier.height(13.dp.from(ctx)))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Button(
                        onClick = { onSaveClicked() },
                        colors = ButtonDefaults.buttonColors(BlueJade),
                        modifier = Modifier
                            .height(36.dp.from(ctx))
                            .width(288.dp.from(ctx))
                            .clip(RoundedCornerShape(10.dp.from(ctx))),
                        shape = RoundedCornerShape(10.dp.from(ctx)),
                        contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))


                    ) {
                        Text(
                            text = stringResource(id = R.string.save_changes),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp.from(ctx),
                                letterSpacing = 1.sp.from(ctx),
                                color = Color.White
                            ),

                            )
                    }

                }


            }

        }

    }

}