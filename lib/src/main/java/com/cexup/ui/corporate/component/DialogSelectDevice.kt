package com.cexup.ui.corporate.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.fontsCorp
import com.cexup.ui.R

data class DevicesModel(
    var name:String,
    var mac:String,
    var rssi:Int
)

@Composable
fun DialogSelectDevice(
    show: Boolean,
    deviceList: List<DevicesModel>,
    onConnectSelectedDevice: (selectedDeviceAddress:String)->Unit,
    onBack: () -> Unit,
) {
    if (show) {
        Dialog(
            onDismissRequest = { onBack() },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            )
        ) {
            ScreenDialogSelectDevice(
                deviceList = deviceList,
                onSelectedDevice = {
                    onConnectSelectedDevice(it)
                },
                onBackPressed = {
                    onBack()
                }
            )
        }
    }


}

@Composable
fun ScreenDialogSelectDevice(
    deviceList: List<DevicesModel>,
    onSelectedDevice:(deviceName:String)->Unit,
    onBackPressed:()->Unit,
) {

    Column {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .height(435.dp)
                .width(357.dp)
                .background(Color.White)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 23.dp)
            ) {
                Text(
                    text = "Select Device",
                    fontSize = 22.sp,
                    fontFamily = fontsCorp,
                    fontWeight = FontWeight.SemiBold,
                    color = BlueJade
                )
                Spacer(modifier = Modifier.height(24.dp))
                LazyColumn(
                    modifier = Modifier
                        .width(300.dp)
                        .height(250.dp),
                    content = {
                        items(deviceList) { device ->
                            DeviceRow(
                                device = device,
                                onSelectDevice = { deviceAddress ->
                                    onSelectedDevice(deviceAddress)
                                }
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Scanning...",
                    fontSize = 16.sp,
                    fontFamily = fontsCorp,
                    fontWeight = FontWeight(600),
                    color = BlueJade

                )
                Spacer(modifier = Modifier.height(5.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()


                ) {
                    Button(
                        onClick = {
                            onBackPressed()
                        },
                        colors = ButtonDefaults.buttonColors(BlueJade),
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp)
                            .width(288.dp)

                    ) {
                        Text(
                            text = "Back",
                            fontSize = 16.sp,
                            fontFamily = fontsCorp,
                            fontWeight = FontWeight(600),
                            color = Color.White

                        )
                    }

                }
            }

        }
    }
}

@Composable
fun DeviceRow(
    device: DevicesModel,
    onSelectDevice: (deviceAddress:String)->Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(
                BorderStroke(1.dp, Color.Black.copy(0.5f)
            ), shape = RoundedCornerShape(10.dp)
            )
            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
            .clickable {
                onSelectDevice(device.mac)
            },
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_blue_jade_rectangle),
                contentDescription = "",
                modifier = Modifier.size(15.dp)

            )
            Spacer(modifier = Modifier.width(35.dp))
            Text(
                text = device.name,
                fontSize = 16.sp,
                fontFamily = fontsCorp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "Connect",
            fontSize = 16.sp,
            fontFamily = fontsCorp,
            fontWeight = FontWeight.Normal,
            color = BlueJade
        )
    }
}