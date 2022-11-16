package com.cexup.ui.corporate.component

import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.fontsCorp
import compose.icons.Octicons
import compose.icons.octicons.Eye24
import com.cexup.ui.R
import com.cexup.ui.utils.mediaquery.from

@ExperimentalComposeUiApi
@Composable
fun DialogChangePassword(
    oldPassword:String = "",
    show: Boolean = false,
    onSaveClicked: (newPassword:String) -> Unit = {},
    onDismiss: () -> Unit
) {
    var oldPassword by rememberSaveable {
        mutableStateOf(oldPassword)
    }
    var newPassword by remember {
        mutableStateOf("")
    }

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
                                text = stringResource(id = R.string.change_password),
                                fontSize = 22.sp,
                                fontFamily = fontsCorp,
                                fontWeight = FontWeight.SemiBold,
                                color = BlueJade
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(
                            modifier = Modifier.padding(start = 28.dp),
                            horizontalArrangement = Arrangement.spacedBy(28.dp)
                        ) {
                            FormTextField(
                                nameTextField = stringResource(id = R.string.old_password),
                                placeholderText = stringResource(id = R.string.your_secret_password),
                                valueTextField = oldPassword,
                                onValueChange = {
                                    oldPassword = it
                                },
                            )
                            FormTextField(
                                nameTextField = stringResource(id = R.string.new_password),
                                placeholderText = stringResource(id = R.string.your_secret_password),
                                valueTextField = newPassword,
                                onValueChange = {
                                    newPassword = it
                                },


                            )
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    onDismiss()
                                    onSaveClicked(newPassword)
                                },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .width(288.dp)
                                    .height(36.dp),
                                colors = ButtonDefaults.buttonColors(BlueJade)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.save_changes),
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