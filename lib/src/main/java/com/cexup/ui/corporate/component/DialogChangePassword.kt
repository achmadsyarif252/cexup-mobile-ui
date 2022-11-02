package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.fontsCorp
import compose.icons.Octicons
import compose.icons.octicons.Eye24

@ExperimentalComposeUiApi
@Composable
fun DialogChangePassword(
    show: Boolean = false,
    onDismiss: () -> Unit
) {
    var oldPassword by rememberSaveable {
        mutableStateOf("")
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    var passwordVisibility by remember { mutableStateOf(false) }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column {
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(25.dp)
                        )
                        .height(255.dp)
                        .width(508.dp)
                        .background(Color.White)
                ) {
                    Column {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 23.dp)
                        ) {
                            Text(
                                text = "Change Password",
                                fontSize = 22.sp,
                                fontFamily = fontsCorp,
                                fontWeight = FontWeight.SemiBold,
                                color = BlueJade
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row {
                            Column(modifier = Modifier.padding(start = 28.dp)) {

                                FormTextField(
                                    nameTextField = "Old Password",
                                    placeholderText = "Your secret password",
                                    valueTextField = oldPassword,
                                    isPassword = passwordVisibility,
                                    onValueChange = {
                                        oldPassword = it
                                    },
                                    trailingIcon = { value ->
                                        Icon(
                                            Octicons.Eye24, "",
                                            modifier = Modifier.clickable {
                                                passwordVisibility = value
                                            })
                                    },
                                )

                            }
                            Column(modifier = Modifier.padding(start = 28.dp)) {

                                FormTextField(
                                    nameTextField = "New Password",
                                    placeholderText = "Your secret password",
                                    valueTextField = newPassword,
                                    onValueChange = {
                                        newPassword = it
                                    },
                                    isPassword = true,
                                    trailingIcon = { value ->
                                        Icon(
                                            Octicons.Eye24, "",
                                            modifier = Modifier.clickable {
                                                passwordVisibility = value
                                            })
                                    },
                                )

                            }
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            Button(
                                onClick = {  },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .width(288.dp)
                                    .height(36.dp),
                                colors = ButtonDefaults.buttonColors(BlueJade)
                            ) {
                                Text(
                                    text = "Save Changes",
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}