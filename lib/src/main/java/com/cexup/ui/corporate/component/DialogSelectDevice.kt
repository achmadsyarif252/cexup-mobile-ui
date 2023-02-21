package com.cexup.ui.corporate.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.fontsCorp
import com.cexup.ui.R
import com.cexup.ui.utils.mediaquery.from

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DialogSelectDevice(
    show: Boolean,
    deviceList: List<Pair<String, String>>, // name, mac address
    onSelectedDevice:(deviceName:String)->Unit,
    onBackPressed:()->Unit,
) {
    val ctx = LocalConfiguration.current
    if (show) {
        Dialog(
            onDismissRequest = { onBackPressed() },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            )
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(20.dp.from(ctx))
                        )
                        .height(435.dp.from(ctx))
                        .width(357.dp.from(ctx))
                        .background(Color.White)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 23.dp.from(ctx))
                    ) {
                        Text(
                            text = "Select Device",
                            fontSize = 22.sp.from(ctx),
                            lineHeight = 33.sp.from(ctx),
                            fontWeight = FontWeight.SemiBold,
                            color = BlueJade
                        )
                        Spacer(modifier = Modifier.height(24.dp.from(ctx)))
                        LazyColumn(
                            modifier = Modifier
                                .width(300.dp.from(ctx))
                                .height(250.dp.from(ctx)),
                            content = {
                                items(deviceList) { device ->
                                    DeviceRow(
                                        name = device.first,
                                        mac = device.second,
                                        onSelectDevice = { deviceAddress ->
                                            onSelectedDevice(deviceAddress)
                                        }
                                    )
                                }
                            },
                            verticalArrangement = Arrangement.spacedBy(8.dp.from(ctx))
                        )
                        Spacer(modifier = Modifier.height(5.dp.from(ctx)))
                        Text(
                            text = "Scanning...",
                            fontSize = 16.sp.from(ctx),
                            lineHeight = 22.sp.from(ctx),
                            fontFamily = fontsCorp,
                            fontWeight = FontWeight(600),
                            color = BlueJade
                        )
                        Spacer(modifier = Modifier.height(5.dp.from(ctx)))
                            Button(
                                onClick = {
                                    onBackPressed()
                                },
                                colors = ButtonDefaults.buttonColors(BlueJade),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp.from(ctx)))
                                    .padding(horizontal = 35.dp.from(ctx))
                                    .height(36.dp.from(ctx))
                                    .fillMaxWidth()
                                    .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
                                contentPadding = PaddingValues(0.dp),
                            ) {
                                Text(
                                    text = "Back",
                                    fontSize = 16.sp.from(ctx),
                                    lineHeight = 22.sp.from(ctx),
                                    fontWeight = FontWeight(600),
                                    color = Color.White

                                )
                            }

                    }
                }
            }
        }
    }
}

@Composable
fun DeviceRow(
    name: String = "",
    mac: String = "",
    onSelectDevice: (deviceAddress:String)->Unit,
) {
    val ctx = LocalConfiguration.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp.from(ctx)))
            .border(
                BorderStroke(1.dp.from(ctx), Color.Black.copy(0.5f)),
                shape = RoundedCornerShape(10.dp.from(ctx))
            )
            .clickable {
                onSelectDevice(mac)
            }
            .padding(10.dp.from(ctx)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp.from(ctx))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_blue_jade_rectangle),
            contentDescription = "",
            modifier = Modifier.size(15.dp.from(ctx))
        )
        Text(
            modifier = Modifier.weight(1f),
            text = name,
            fontSize = 16.sp.from(ctx),
            lineHeight = 24.sp.from(ctx),
            fontFamily = fontsCorp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Text(
            modifier = Modifier.widthIn(min = 72.dp.from(ctx)),
            text = "Connect",
            fontSize = 16.sp.from(ctx),
            fontFamily = fontsCorp,
            fontWeight = FontWeight.Normal,
            color = BlueJade
        )
    }
}