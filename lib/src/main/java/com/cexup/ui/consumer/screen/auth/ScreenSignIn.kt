package com.cexup.ui.consumer.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.component.form.OutlineTextFieldWithErrorView
import com.cexup.ui.consumer.theme.*
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleClick
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft16
import compose.icons.octicons.Eye24
import compose.icons.octicons.EyeClosed24

@Composable
fun ScreenSignIn(
    modifier: Modifier = Modifier,
    goToRegister: () -> Unit,
    gotToForgetPassword: () -> Unit,
    onLogin: (username: String, password: String) -> Unit,
    onLoginGoogle: () -> Unit
) {
    val ctx = LocalContext.current
    var userName by rememberSaveable { 
        mutableStateOf("") 
    }
    var usernameTextError by rememberSaveable { 
        mutableStateOf(false) 
    }
    var userPassword by rememberSaveable { 
        mutableStateOf("") 
    }
    var passwordTextError by remember { 
        mutableStateOf(false) 
    }
    var shouldShowPassword by rememberSaveable { 
        mutableStateOf(true) 
    }
    var rememberMe by rememberSaveable { 
        mutableStateOf(false) 
    }
    Scaffold(
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(
                        vertical = 16.dp.from(ctx),
                        horizontal = 16.dp.from(ctx)
                    )
            ) {
                Icon(
                    Octicons.ChevronLeft16,
                    contentDescription = "Back arrow",
                    modifier = modifier
                        .size(24.dp.from(ctx))
                        .clickable { },
                    tint = ArrowBackAuth
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .background(Color.White)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 16.dp.from(ctx),
                    top = 35.dp.from(ctx),
                    end = 16.dp.from(ctx)
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Sign In",
                        style = MaterialTheme.typography.h4.copy(
                            fontSize = 28.sp.from(ctx),
                            lineHeight = 32.sp.from(ctx),
                            letterSpacing = -1.sp.from(ctx),
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp.from(ctx)))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Don't have an account? ",
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onSecondary,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.1.sp.from(ctx),
                                lineHeight = 20.sp.from(ctx)
                            )
                        )
                        Text(text = "Sign up now",
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onPrimary,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.1.sp.from(ctx),
                                lineHeight = 20.sp.from(ctx)
                            ), modifier = Modifier.clickable { goToRegister() })
                    }
                    Spacer(modifier = Modifier.height(50.dp.from(ctx)))
                    OutlineTextFieldWithErrorView(
                        value = userName,
                        onValueChange = { userName = it },
                        label = {
                            Text(
                                text = "Email or Phone number",
                                style = MaterialTheme.typography.body2.copy(
                                    letterSpacing = 0.1.sp.from(ctx),
                                    fontSize = 14.sp.from(ctx),
                                    lineHeight = 20.sp.from(ctx),
                                    color = MaterialTheme.colors.onSecondary
                                )
                            )
                        },
                        isError = usernameTextError,
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(),
                        errorMsg = "Enter Email or Phone number",
                        shape = RoundedCornerShape(6.dp.from(ctx)),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.onSecondary,
                            cursorColor = MaterialTheme.colors.onBackground
                        ),
                        maxLines = 1,
                        textStyle = TextStyle(
                            fontFamily = fonts,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 0.1.sp.from(ctx),
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                            color = MaterialTheme.colors.onBackground
                        )
                    )
                    Spacer(modifier = Modifier.height(30.dp.from(ctx)))
                    OutlineTextFieldWithErrorView(
                        value = userPassword,
                        onValueChange = { userPassword = it },
                        placeholder = {
                            Text(
                                text = "Enter Password",
                                style = MaterialTheme.typography.h1.copy(
                                    letterSpacing = 0.1.sp.from(ctx),
                                    fontSize = 14.sp.from(ctx),
                                    lineHeight = 20.sp.from(ctx),
                                    color = Darkgrayishblue
                                )
                            )
                        },
                        isError = passwordTextError,
                        textStyle = TextStyle(
                            fontFamily = fonts,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 0.1.sp.from(ctx),
                            fontSize = 14.sp.from(ctx),
                            lineHeight = 20.sp.from(ctx),
                            color = MaterialTheme.colors.onBackground
                        ),
                        shape = RoundedCornerShape(6.dp.from(ctx)),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.onSecondary,
                            cursorColor = MaterialTheme.colors.onBackground
                        ),
                        modifier = modifier.fillMaxWidth(),
                        visualTransformation = if (shouldShowPassword) PasswordVisualTransformation() else VisualTransformation.None,
                        trailingIcon = {
                            IconButton(onClick = { shouldShowPassword = !shouldShowPassword }) {
                                Icon(
                                    if (shouldShowPassword) Octicons.Eye24 else Octicons.EyeClosed24,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(26.dp.from(ctx)))
                    Row(
                        modifier = Modifier.noRippleClick { rememberMe = !rememberMe },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { rememberMe = it },
                            colors = CheckboxDefaults.colors(Heading),
                            modifier = Modifier
                                .size(16.dp.from(ctx))

                        )

                        Spacer(modifier = Modifier.width(8.dp.from(ctx)))

                        Text(
                            text = "Remember me",
                            style = MaterialTheme.typography.body1.copy(
                                fontSize = 12.sp.from(ctx),
                                fontWeight = FontWeight.Medium,
                                lineHeight = 14.sp.from(ctx),
                                letterSpacing = 0.25.sp.from(ctx),
                                color = MaterialTheme.colors.onSecondary
                            )
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Forgot Password?",
                                style = MaterialTheme.typography.body1.copy(
                                    fontSize = 12.sp.from(ctx),
                                    fontWeight = FontWeight.Medium,
                                    lineHeight = 14.sp.from(ctx),
                                    letterSpacing = 0.25.sp.from(ctx),
                                    color = MaterialTheme.colors.onPrimary
                                ), modifier = Modifier.clickable {
                                    gotToForgetPassword()
                                }
                            )
                        }


                    }

                    Spacer(modifier = Modifier.height(28.dp.from(ctx)))

                    Button(
                        onClick = {
                            onLogin(
                                userName, userPassword
                            )
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onPrimary),
                        shape = RoundedCornerShape(8.dp.from(ctx)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp.from(ctx)))
                            .height(48.dp.from(ctx))
                    ) {
                        Text(
                            text = "Sign In",
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 16.sp.from(ctx),
                                lineHeight = 20.sp.from(ctx),
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                        )

                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewScreenSignIn() {
    ConsumerTheme {
        ScreenSignIn(
            gotToForgetPassword = {},
            goToRegister = {},
            onLoginGoogle = {},
            onLogin = { _, _ ->
            },
        )
    }
}