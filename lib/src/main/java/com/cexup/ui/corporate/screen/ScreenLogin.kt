package com.cexup.ui.corporate.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.Octicons
import compose.icons.octicons.Eye24
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.BackgroundLight
import com.cexup.ui.corporate.theme.ColorGray
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.noRippleClick

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenLogin(
    shouldShowLoading: Boolean,
    onForgetPassword: () -> Unit,
    onLogin: (username: String, password: String, isRemember: Boolean) -> Unit,
) {
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

    Scaffold {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLight)
                .verticalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner_corporate),
                contentDescription = "banner",
                modifier = Modifier
                    .width(420.dp)
                    .fillMaxHeight()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp, start = 28.dp, end = 48.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_corporate),
                    contentDescription = "logo",
                    modifier = Modifier
                        .width(117.dp)
                        .height(46.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Login",
                    fontSize = 22.sp,
                    fontWeight = FontWeight(700),
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Login to your account",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                )
                Text(
                    text = "Thank you for get back to CeXup, let's access to help people to be happy.",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(700),
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Email",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                )
                Spacer(modifier = Modifier.height(5.41.dp))
                TextField(
                    value = userName,
                    onValueChange = { value ->
                        userName = value
                    },
                    placeholder = { Text(text = "example@mail.com") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.59.dp)
                        .background(BackgroundLight)
                        .coloredShadow(
                            color = Color.Black.copy(0.25f),
                            offsetY = 4.dp,
                            borderRadius = 5.dp,
                            shadowRadius = 2.dp
                    ),
                    shape = RoundedCornerShape(5.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BackgroundLight,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Password",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    style = MaterialTheme.typography.body1,
                    color = Heading,
                )
                Spacer(modifier = Modifier.height(5.41.dp))
                TextField(
                    value = userPassword,
                    onValueChange = { value ->
                        userPassword = value
                    },
                    placeholder = { Text(text = "Password") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.59.dp)
                        .background(BackgroundLight)
                        .coloredShadow(
                            color = Color.Black.copy(0.25f),
                            offsetY = 4.dp,
                            borderRadius = 5.dp,
                            shadowRadius = 2.dp
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
                    shape = RoundedCornerShape(5.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BackgroundLight,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 21.dp, start = 5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .noRippleClick {
                                rememberMe = !rememberMe
                            }
                            .height(23.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { checked ->
                                rememberMe = checked
                            },
                            modifier = Modifier
                                .size(12.dp)
                                .clip(shape = RoundedCornerShape(6.dp)),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Heading,
                                uncheckedColor = Heading
                            )
                        )
                        Spacer(modifier = Modifier.width(13.dp))
                        Text(
                            text = "Remember me",
                            fontWeight = FontWeight(400),
                            modifier = Modifier
                                .drawWithContent {
                                    if (readyToDraw) {
                                        drawContent()
                                    }
                                }
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.body1,
                            fontSize = 12.sp,
                            softWrap = true,
                            onTextLayout = { textLayoutResult ->
                                if (textLayoutResult.didOverflowWidth) {
                                    scaledTextStyle =
                                        scaledTextStyle.copy(fontSize = scaledTextStyle.fontSize * 0.9)
                                } else {
                                    readyToDraw = true
                                }
                            },
                            color = Heading,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(23.dp)
                            .padding(end = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Forget Password?",
                            fontWeight = FontWeight(400),
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
                            style = MaterialTheme.typography.body1,
                            fontSize = 12.sp,
                            softWrap = true,
                            onTextLayout = { textLayoutResult ->
                                if (textLayoutResult.didOverflowWidth) {
                                    scaledTextStyle =
                                        scaledTextStyle.copy(fontSize = scaledTextStyle.fontSize * 0.9)
                                } else {
                                    readyToDraw = true
                                }
                            },
                            color = Heading,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(17.37.dp))
                Button(
                    onClick = {
                        keyboardController?.hide()
                        onLogin(userName, userPassword, rememberMe)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Heading),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if (shouldShowLoading) {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    } else {
                        Text(
                            text = "Login",
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight(700),
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            ),
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}