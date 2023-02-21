package com.cexup.ui.corporate.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.component.common.TextFieldCexup
import com.cexup.ui.theme.BackgroundLight
import com.cexup.ui.theme.ColorGray
import com.cexup.ui.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleClick
import compose.icons.Octicons
import compose.icons.octicons.Eye24

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenLogin(
    shouldShowLoading: Boolean,
    onForgetPassword: () -> Unit,
    scaffoldState: ScaffoldState,
    onLogin: (username: String, password: String, isRemember: Boolean) -> Unit,
) {
    val ctx = LocalConfiguration.current
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val keyboardController = LocalSoftwareKeyboardController.current
    var userName by remember {
        mutableStateOf("")
    }
    var userPassword by remember {
        mutableStateOf("")
    }
    var shouldShowPassword by remember {
        mutableStateOf(false)
    }
    var rememberMe by remember { mutableStateOf(false) }
    var readyToDraw by remember { mutableStateOf(false) }
    var scaledTextStyle by remember { mutableStateOf(TextStyle()) }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLight)
                .verticalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(top = 63.dp.from(ctx), start = 80.dp.from(ctx), end = 80.dp.from(ctx)),
                verticalArrangement = Arrangement.spacedBy(18.dp.from(ctx))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_corporate),
                    contentDescription = "logo",
                    modifier = Modifier
                        .width(117.dp.from(ctx))
                        .height(46.dp.from(ctx))
                )
                Spacer(modifier = Modifier.height(12.dp.from(ctx)))
                Column {
                    Text(
                        text = stringResource(id = R.string.login),
                        style = MaterialThemeCexup.typography.h6.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialThemeCexup.colors.color.text.textMain,
                    )
                    Spacer(modifier = Modifier.height(16.dp.from(ctx)))
                    Text(
                        text = stringResource(id = R.string.cexup_description),
                        style = MaterialThemeCexup.typography.hh3,
                        color = MaterialThemeCexup.colors.color.text.textSecondary,
                    )
                }
                TextFieldCexup(
                    value = userName,
                    onValueChange = { value ->
                        userName = value
                    },
                    placeholder = { Text(
                        text = stringResource(id = R.string.example_email),
                        style = MaterialThemeCexup.typography.hh4,
                        color = MaterialThemeCexup.colors.palette.neutral.neutral8
                    ) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp.from(ctx),
                            MaterialThemeCexup.colors.color.borderline.borderline3,
                            RoundedCornerShape(4.dp.from(ctx))
                        ),
                    shape = RoundedCornerShape(4.dp.from(ctx)),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black,
                        unfocusedIndicatorColor = Color.Unspecified,
                        focusedIndicatorColor = Color.Unspecified
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    innerPaddingValue = PaddingValues(16.dp.from(ctx))
                )
                TextFieldCexup(
                    value = userPassword,
                    onValueChange = { value ->
                        userPassword = value
                    },
                    placeholder = { Text(
                        text = stringResource(id = R.string.password),
                        style = MaterialThemeCexup.typography.hh4,
                        color = MaterialThemeCexup.colors.palette.neutral.neutral8
                    ) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp.from(ctx),
                            MaterialThemeCexup.colors.color.borderline.borderline3,
                            RoundedCornerShape(4.dp.from(ctx))
                        ),
                    visualTransformation = if (shouldShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { shouldShowPassword = !shouldShowPassword }) {
                            Icon(
                                imageVector = Octicons.Eye24,
                                contentDescription = "",
                                tint = if (shouldShowPassword) MaterialTheme.colors.primary else ColorGray
                            )
                        }
                    },
                    shape = RoundedCornerShape(4.dp.from(ctx)),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black,
                        unfocusedIndicatorColor = Color.Unspecified,
                        focusedIndicatorColor = Color.Unspecified
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    innerPaddingValue = PaddingValues(16.dp.from(ctx))
                )
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .noRippleClick {
                                rememberMe = !rememberMe
                            }
                            .height(23.dp.from(ctx)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { checked ->
                                rememberMe = checked
                            },
                            modifier = Modifier
                                .size(20.dp.from(ctx))
                                .clip(shape = RoundedCornerShape(2.dp.from(ctx))),
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialThemeCexup.colors.color.primary.primaryMain,
                                uncheckedColor = MaterialThemeCexup.colors.palette.neutral.neutral7
                            )
                        )
                        Spacer(modifier = Modifier.width(13.dp.from(ctx)))
                        Text(
                            text = stringResource(id = R.string.remember_me),
                            modifier = Modifier
                                .drawWithContent {
                                    if (readyToDraw) {
                                        drawContent()
                                    }
                                }
                                .align(Alignment.CenterVertically),
                            style = MaterialThemeCexup.typography.hh4,
                            softWrap = true,
                            onTextLayout = { textLayoutResult ->
                                if (textLayoutResult.didOverflowWidth) {
                                    scaledTextStyle =
                                        scaledTextStyle.copy(fontSize = scaledTextStyle.fontSize * 0.9)
                                } else {
                                    readyToDraw = true
                                }
                            },
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.forget_password),
                        modifier = Modifier
                            .drawWithContent {
                                if (readyToDraw) {
                                    drawContent()
                                }
                            }
                            .align(Alignment.CenterVertically)
                            .noRippleClick {
                                onForgetPassword()
                            },
                        style = MaterialThemeCexup.typography.hh4,
                        softWrap = true,
                        onTextLayout = { textLayoutResult ->
                            if (textLayoutResult.didOverflowWidth) {
                                scaledTextStyle =
                                    scaledTextStyle.copy(fontSize = scaledTextStyle.fontSize * 0.9)
                            } else {
                                readyToDraw = true
                            }
                        },
                        color = MaterialThemeCexup.colors.color.primary.primaryMain,
                        textAlign = TextAlign.Center,
                    )
                }
                Button(
                    onClick = {
                        keyboardController?.hide()
                        onLogin(userName, userPassword, rememberMe)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialThemeCexup.colors.color.primary.primaryMain),
                    shape = RoundedCornerShape(4.dp.from(ctx)),
                    contentPadding = PaddingValues(vertical = 12.dp.from(ctx))
                ) {
                    if (shouldShowLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(25.dp.from(ctx)),
                            strokeWidth = 3.dp.from(ctx)
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.login),
                            style = MaterialThemeCexup.typography.textButton1.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = MaterialThemeCexup.colors.palette.neutral.neutral1
                        )
                    }
                }
            }
            Image(
                modifier = Modifier
                    .weight(0.4f)
                    .height(screenHeight),
                painter = painterResource(id = R.drawable.ic_new_banner_corporate),
                contentDescription = "banner",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}