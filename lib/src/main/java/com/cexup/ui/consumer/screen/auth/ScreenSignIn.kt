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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.component.form.OutlinedInput
import com.cexup.ui.consumer.component.ButtonPrimary
import com.cexup.ui.consumer.component.CheckboxInput
import com.cexup.ui.consumer.theme.*
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleClick
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft16
import compose.icons.octicons.Eye24
import compose.icons.octicons.EyeClosed24

const val TAG_BODY_SCREEN_SIGNIN ="scaffold_sign_in"
const val TAG_BTN_SIGN_IN = "btn_signin"
const val TAG_TITLE = "title_signin"
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

    var userPassword by rememberSaveable { 
        mutableStateOf("") 
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
        },
        modifier = modifier
            .background(MaterialTheme.colors.background),
        backgroundColor = MaterialTheme.colors.background

    ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(
                            it
                        )
                        .padding(
                            start = 16.dp.from(ctx),
                            top = 35.dp.from(ctx),
                            end = 16.dp.from(ctx)
                        )
                        .testTag(TAG_BODY_SCREEN_SIGNIN)
                ) {
                    Text(
                        text = stringResource(R.string.header_screen_signin),
                        style = MaterialTheme.typography.h4.copy(
                            fontSize = 28.sp.from(ctx),
                            lineHeight = 32.sp.from(ctx),
                            letterSpacing = -1.sp.from(ctx),
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.onPrimary
                        ),
                        modifier = modifier.testTag(TAG_TITLE)
                    )

                    Spacer(modifier = Modifier.height(16.dp.from(ctx)))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.dont_have_account),
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onSecondary,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.1.sp.from(ctx),
                                lineHeight = 20.sp.from(ctx)
                            )
                        )
                        Text(text = stringResource(R.string.signup_now),
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onPrimary,
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.1.sp.from(ctx),
                                lineHeight = 20.sp.from(ctx)
                            ), modifier = Modifier.clickable { goToRegister() })
                    }
                    Spacer(modifier = Modifier.height(50.dp.from(ctx)))
                    OutlinedInput(
                        value = userName,
                        onChange = {
                            userName = it
                        },
                        placeholder = "Enter Email or Phone number",
                        label = "Email or Phone number",
                        errorMessage = if(userPassword.isBlank()) "Username or Phone cannot empty!" else null,
                        visualTransformation =  VisualTransformation.None,
                        shape = RoundedCornerShape(6.dp.from(ctx))
                    )
                    Spacer(modifier = Modifier.height(30.dp.from(ctx)))
                    OutlinedInput(
                        value = userPassword,
                        onChange = {
                            userPassword = it
                        },
                        placeholder = "Enter Password",
                        label = "Password",
                        errorMessage = if(userPassword.isBlank()) "Password cannot empty!" else null,
                        trailingIcon = {
                            IconButton(onClick = { shouldShowPassword = !shouldShowPassword }) {
                                Icon(
                                    if (shouldShowPassword) Octicons.Eye24 else Octicons.EyeClosed24,
                                    contentDescription = ""
                                )
                            }
                        },
                        visualTransformation = if (shouldShowPassword) PasswordVisualTransformation() else VisualTransformation.None,
                        shape = RoundedCornerShape(6.dp.from(ctx))
                    )

                    Spacer(modifier = Modifier.height(26.dp.from(ctx)))
                    Row(
                        modifier = Modifier
                            .noRippleClick { rememberMe = !rememberMe }
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CheckboxInput(
                            checked = rememberMe,
                            onCheckedChange = {
                                rememberMe = it
                            },
                            text = "Remember me"
                        )

                        Text(
                            text = "Forgot Password?",
                            style = MaterialTheme.typography.body1.copy(
                                fontSize = 12.sp.from(ctx),
                                fontWeight = FontWeight.Medium,
                                lineHeight = 14.sp.from(ctx),
                                letterSpacing = 0.25.sp.from(ctx),
                                color = MaterialTheme.colors.onPrimary
                            ),
                            modifier = Modifier.clickable {
                                    gotToForgetPassword()
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp.from(ctx)))

                    ButtonPrimary(
                        text = "Sign In",
                        onClick = {
                            onLogin(
                                userName,
                                userPassword
                            )
                        }
                    )
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