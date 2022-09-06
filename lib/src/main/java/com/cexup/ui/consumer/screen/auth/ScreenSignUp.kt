package com.cexup.ui.consumer.screen.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.consumer.component.ButtonPrimary
import com.cexup.ui.consumer.component.CheckboxInput
import com.cexup.ui.consumer.component.CheckboxStyle
import com.cexup.ui.consumer.component.OutlinedInput
import com.cexup.ui.consumer.theme.ArrowBackAuth
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft16

@Composable
fun ScreenSignUp(
    modifier: Modifier = Modifier,
    toSignIn: () -> Unit,
    onSubmit: (name:String, email:String, password:String) -> Unit,
    onPrivacyPolice: () -> Unit,
    onLoginGoogle: () -> Unit,
    onBackPressed: () -> Unit,
) {

    val ctx = LocalContext.current

    var scrollState = rememberScrollState()

    var name by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    var confirmPassword by remember { mutableStateOf("") }



    var stateAgreement by remember { mutableStateOf(false) }

    var buttonState by remember { mutableStateOf(false) }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }


    fun sendRegisterData() {
        if (password != confirmPassword) {
            return
        }
       
        onSubmit(
            name,
            email,
            password
        )
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
                        .clickable {
                            onBackPressed()
                        },
                    tint = ArrowBackAuth,

                    )

            }
        },
        modifier = modifier
            .background(Color.White)

    ) {
   
        Column(
            modifier = modifier
                .padding(it)
                .padding(
                    start = 16.dp.from(ctx),
                    end = 16.dp.from(ctx),
                    top = 35.dp.from(ctx),
                    bottom = 16.dp.from(ctx)
                )
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState),
        ) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.header_screen_signup),
                        style = MaterialTheme.typography.h4.copy(
                            fontSize = 28.sp.from(ctx),
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 32.sp.from(ctx),
                            letterSpacing = -1.sp.from(ctx),
                            color = MaterialTheme.colors.onPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp.from(ctx)))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.already_have_account),
                            style = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight.Medium,
                                lineHeight = 20.sp.from(ctx),
                                letterSpacing = 0.1.sp.from(ctx),
                                color = MaterialTheme.colors.onSecondary
                            )
                        )
                        Text(
                            text = stringResource(R.string.sign_in),
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp.from(ctx),
                                fontWeight = FontWeight.Medium,
                                lineHeight = 20.sp.from(ctx),
                                letterSpacing = 0.1.sp.from(ctx),
                                color = MaterialTheme.colors.onPrimary
                            ),
                            modifier = modifier
                                .clickable {
                                    toSignIn()
                                }
                        )
                    }
                }

            }
            Spacer(modifier = modifier.height(50.dp.from(ctx)))
            Column(
                modifier = modifier
                    .fillMaxHeight(),
            ) {
                OutlinedInput(
                    value=name,
                    onChange = {
                        name=it
                    },
                    label = stringResource(R.string.label_input_fullname),
                    errorMessage = stringResource(R.string.error_message_input_fullname),
                    placeholder = stringResource(R.string.placeholder_input_fullname)

                )
                Spacer(modifier = Modifier.height(30.dp.from(ctx)))
                OutlinedInput(
                    value=email,
                    onChange = {
                        email=it
                    },
                    label = stringResource(R.string.label_input_email),
                    errorMessage = stringResource(R.string.error_message_input_email),
                    placeholder = stringResource(R.string.placeholder_input_email)
                )
                Spacer(modifier = Modifier.height(30.dp.from(ctx)))
                OutlinedInput(
                    value=password,
                    onChange = {
                        password = it
                    },
                    label = stringResource(id = R.string.label_input_password),
                    errorMessage = stringResource(id = R.string.error_message_input_password),
                    placeholder = stringResource(id = R.string.placeholder_input_passwprd),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                painter = painterResource( if(passwordVisibility) R.drawable.ic_show_password else R.drawable.ic_hide_password),
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary,
                                modifier = Modifier.size(20.dp.from(ctx)),
                            )

                        }
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(30.dp.from(ctx)))
                OutlinedInput(
                    value=password,
                    onChange = {
                        password = it
                    },
                    label = stringResource(R.string.label_input_confirm_password),
                    errorMessage = stringResource(R.string.error_message_input_confirm_password),
                    placeholder = stringResource(R.string.placeholder_input_confirm_password),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                painter = painterResource( if(passwordVisibility) R.drawable.ic_show_password else R.drawable.ic_hide_password),
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary,
                                modifier = Modifier.size(20.dp.from(ctx)),
                            )

                        }
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                )
                Spacer(modifier = Modifier.height(14.dp.from(ctx)))
                CheckboxInput(
                    checked=stateAgreement,
                    labels = listOf(
                        CheckboxStyle.Text(stringResource(R.string.message_agreement)),
                        CheckboxStyle.Button(stringResource(R.string.message_term_of_use)),
                        CheckboxStyle.Text(stringResource(R.string.message_and)),
                        CheckboxStyle.Button(stringResource(R.string.message_privacy_policy))
                    ),
                    onTextClick = {
                            position->
                        //TODO show privacy policy
                    },
                    onCheckedChange = {
                        stateAgreement=it
                    }
                )
                Spacer(modifier = Modifier.height(28.dp.from(ctx)))
                ButtonPrimary(
                    enabled = buttonState,
                    onClick = {

                    },
                    text = stringResource(R.string.button_submit_sign_up)
                )

            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenSignUp() {
    ConsumerTheme {
        ScreenSignUp(
            toSignIn = { /*TODO*/ },
            onPrivacyPolice = { /*TODO*/ },
            onLoginGoogle = { /*TODO*/ },
            onBackPressed = {},
            onSubmit = { _, _, _->}
        )
    }
}