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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cexup.ui.R
import com.cexup.ui.consumer.component.InputOutlined
import com.cexup.ui.consumer.component.ButtonTextPrimary
import com.cexup.ui.consumer.component.InputCheckBox
import com.cexup.ui.consumer.theme.*
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleClick
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft16
import compose.icons.octicons.Eye24
import compose.icons.octicons.EyeClosed24

const val TAG_BODY_SCREEN_SIGNIN = "scaffold_sign_in"
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
    val ctx = LocalConfiguration.current
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
                    contentDescription = stringResource(R.string.content_description_btn_back),
                    modifier = modifier
                        .size(24.dp.from(ctx))
                        .clickable { },
                    tint = ArrowBackAuth
                )
            }
        },
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
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.primary
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
                        color = MaterialTheme.colors.onSecondary
                    )
                )
                Text(
                    text = stringResource(R.string.signup_now),
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.primary
                    ),
                    modifier = Modifier.clickable { goToRegister() }
                )
            }
            Spacer(modifier = Modifier.height(50.dp.from(ctx)))
            InputOutlined(
                value = userName,
                onChange = {
                    userName = it
                },
                placeholder = stringResource(R.string.placeholder_input_email_or_phone_number),
                label = stringResource(R.string.label_input_email_or_phone_number),
                errorMessage = if (userPassword.isBlank()) stringResource(R.string.error_message_input_email_or_phone) else null,
                visualTransformation = VisualTransformation.None,
                shape = RoundedCornerShape(6.dp.from(ctx))
            )
            Spacer(modifier = Modifier.height(30.dp.from(ctx)))
            InputOutlined(
                value = userPassword,
                onChange = {
                    userPassword = it
                },
                placeholder = stringResource(R.string.placeholder_input_passwprd),
                label = stringResource(R.string.label_input_password),
                errorMessage = if (userPassword.isBlank()) stringResource(R.string.error_message_input_password) else null,
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
                InputCheckBox(
                    checked = rememberMe,
                    onCheckedChange = {
                        rememberMe = it
                    },
                    label = stringResource(R.string.label_input_remember_me)
                )

                Text(
                    text = stringResource(R.string.button_forget_password),
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.primary
                    ),
                    modifier = Modifier.clickable {
                        gotToForgetPassword()
                    }
                )
            }

            Spacer(modifier = Modifier.height(28.dp.from(ctx)))

            ButtonTextPrimary(
                text = stringResource(R.string.button_submit_sign_in),
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