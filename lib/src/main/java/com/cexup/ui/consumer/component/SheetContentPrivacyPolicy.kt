package com.cexup.ui.consumer.component

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import compose.icons.Octicons
import compose.icons.octicons.Circle24

@Composable
fun SheetContentPrivacyPolicy(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    onAgree:()->Unit={}
){
    var scaledTextStyle by remember { mutableStateOf(textStyle) }
    var readyToDraw by remember { mutableStateOf(false) }
    var agreeWithTermCondition by remember { mutableStateOf(false) }


    Card(
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier =
        modifier.padding(
            top=10.dp,
            bottom=10.dp,
            start = 16.dp,
            end=16.dp
        )) {
            Text(
                text = "Syarat & Ketentuan Penggunaan Cexup",
                modifier = modifier
                    .drawWithContent {
                        if(readyToDraw){
                            drawContent()
                        }
                    },
                style = scaledTextStyle.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                softWrap = true,
                onTextLayout = {
                        textLayoutResult ->
                    if(textLayoutResult.didOverflowWidth){
                        scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                    }else{
                        readyToDraw = true
                    }
                }
            )
            Divider(modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp))
            Text(
                text = "Cexup telah memperbarui Syarat & Ketentuan Penggunaan",
                modifier = modifier
                    .drawWithContent {
                        if(readyToDraw){ drawContent()
                        }
                    },
                style = scaledTextStyle.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                softWrap = true,
                onTextLayout = {
                        textLayoutResult ->
                    if(textLayoutResult.didOverflowWidth){
                        scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                    }else{
                        readyToDraw = true
                    }
                }
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "Syarat & Ketentuan Penggunaan merupakan ketentuan yang harus dibaca, dipahami, dan disetejui oleh Pengguna sebelum mengakses atau menggunakan aplikasi Cexup. Lihat Syarat & Ketentuan Penggunaan Cexup di sini :",
                modifier = modifier
                    .drawWithContent {
                        if(readyToDraw){ drawContent()
                        }
                    },
                style = scaledTextStyle,
                softWrap = true,
                onTextLayout = {
                        textLayoutResult ->
                    if(textLayoutResult.didOverflowWidth){
                        scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                    }else{
                        readyToDraw = true
                    }
                }
            )
            Spacer(modifier = modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    Octicons.Circle24,
                    contentDescription = "",
                    modifier = modifier.width(10.dp)
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = "Syarat dan Ketentuan Penggunaan",
                    modifier = modifier
                        .drawWithContent {
                            if (readyToDraw) {
                                drawContent()
                            }
                        }
                        .clickable {

                        },
                    style = scaledTextStyle.copy(color = MaterialTheme.colors.primary),
                    softWrap = true,
                    onTextLayout = {
                            textLayoutResult ->
                        if(textLayoutResult.didOverflowWidth){
                            scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                        }else{
                            readyToDraw = true
                        }
                    }
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Row(
                modifier=modifier
                    .clickable {
                        agreeWithTermCondition = !agreeWithTermCondition
                    },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = agreeWithTermCondition,
                    onCheckedChange = {
                        agreeWithTermCondition = it
                    }
                )
                Text(
                    text = "Saya setuju dengan Syarat dan Ketentuan Penggunaan.",
                    modifier = modifier
                        .drawWithContent {
                            if(readyToDraw){ drawContent()
                            }
                        },
                    style = scaledTextStyle,
                    softWrap = true,
                    onTextLayout = {
                            textLayoutResult ->
                        if(textLayoutResult.didOverflowWidth){
                            scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                        }else{
                            readyToDraw = true
                        }
                    }
                )
            }
            Spacer(modifier = modifier.height(15.dp))
            Button(
                enabled = agreeWithTermCondition,
                onClick ={

                },
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Simpan",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    ),
                    modifier = modifier.padding(10.dp),
                )
            }
        }
    }
}