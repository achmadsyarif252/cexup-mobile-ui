package com.cexup.ui.corporate.screen

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.TabFeatures
import com.cexup.ui.corporate.component.TabParameter
import com.cexup.ui.corporate.theme.GreyBorder
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.coloredShadow
import compose.icons.Octicons
import compose.icons.octicons.DotFill24

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun ScreenDetailUsg(
    onBackPressed: () -> Unit,
    imagesReport: List<Uri>,
    patientName: String,
    patientUserCode: String,
) {
    val listOfTabFeature = listOf(TabParameter.Details, TabParameter.Pictures)
    var tabParameter by remember {
        mutableStateOf(TabParameter.Details)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 43.dp, vertical = 34.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Octicons.DotFill24, "", tint = MaterialTheme.colors.primary)
                    Text(text = patientUserCode, color = MaterialTheme.colors.primary)
                }
                Column(
                    modifier = Modifier.width(258.dp)
                ) {
                    TabFeatures(
                        selectedTabIndex = tabParameter.ordinal,
                        onSelectedTab = { tabParameter = it },
                        tabs = listOfTabFeature
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(id = R.drawable.ic_download),
                        "",
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Icon(
                        painterResource(id = R.drawable.ic_bx_printer),
                        "",
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    Button(
                        onClick = {
                            onBackPressed()
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                        modifier = Modifier.width(90.dp)
                    ) {
                        Text(
                            text = "Back",
                            style = TextStyle(
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    ) {
        when (tabParameter) {
            TabParameter.Details -> {
                ScreenPatientDetailUsg()
            }
            TabParameter.Pictures -> {
                ScreenPictureUsg(
                    imagesList = imagesReport.toList(),
                    patientName = patientName,
                )
            }
            else -> {}
        }
    }

}

@Composable
fun ScreenPatientDetailUsg(
    modifier: Modifier = Modifier,
) {
    var nameState by remember { mutableStateOf("") }
    var birthOfDateState by remember { mutableStateOf("") }
    var sexState by remember { mutableStateOf("") }
    var addressState by remember { mutableStateOf("") }
    var ageState by remember { mutableStateOf("") }
    var phoneState by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .padding(start = 43.dp, end = 43.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Name")
                OutlinedTextField(
                    value = nameState,
                    onValueChange = {
                        nameState = it
                    },
                    placeholder = { Text(text = "Sri Astuti") },
                    singleLine = true,
                    modifier = modifier
                        .width(448.76.dp)
                        .navigationBarsPadding()
                        .imePadding(),
                    shape = RoundedCornerShape(40.dp),

                    )
            }
            Column {
                Text(text = "Birthday")
                OutlinedTextField(
                    value = birthOfDateState,
                    onValueChange = {
                        birthOfDateState = it
                    },
                    placeholder = { Text(text = "30/10/1981") },
                    singleLine = true,
                    modifier = modifier
                        .width(448.76.dp)
                        .navigationBarsPadding()
                        .imePadding(),
                    shape = RoundedCornerShape(40.dp),
                )
            }
        }
        Row(
            modifier = modifier
                .padding(start = 43.dp, end = 43.dp, top = 30.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Sex")
                OutlinedTextField(
                    value = sexState,
                    onValueChange = {
                        sexState = it
                    },
                    placeholder = { Text(text = "Sri Astuti") },
                    singleLine = true,
                    modifier = modifier
                        .width(448.76.dp)
                        .navigationBarsPadding()
                        .imePadding(),
                    shape = RoundedCornerShape(40.dp),

                    )
            }
            Column {
                Text(text = "Address")
                OutlinedTextField(
                    value = addressState,
                    onValueChange = {
                        addressState = it
                    },
                    placeholder = { Text(text = "Sri Astuti") },
                    singleLine = true,
                    modifier = modifier
                        .width(448.76.dp)
                        .navigationBarsPadding()
                        .imePadding(),
                    shape = RoundedCornerShape(40.dp),

                    )
            }
        }
        Row(
            modifier = modifier
                .padding(start = 43.dp, end = 43.dp, top = 30.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Age")
                OutlinedTextField(
                    value = ageState,
                    onValueChange = {
                        ageState = it
                    },
                    placeholder = { Text(text = "Sri Astuti") },
                    singleLine = true,
                    modifier = modifier
                        .width(448.76.dp)
                        .navigationBarsPadding()
                        .imePadding(),
                    shape = RoundedCornerShape(40.dp),

                    )
            }
            Column {
                Text(text = "Phone")
                OutlinedTextField(
                    value = phoneState,
                    onValueChange = {
                        phoneState = it
                    },
                    placeholder = { Text(text = "088212381378") },
                    singleLine = true,
                    modifier = modifier
                        .width(448.76.dp)
                        .navigationBarsPadding()
                        .imePadding(),
                    shape = RoundedCornerShape(40.dp),
                )
            }
        }
        Spacer(modifier = modifier.height(22.dp))
        Box(
            modifier = modifier
                .align(Alignment.End)
                .padding(end = 43.dp)
        ) {
            Text(
                text = "Date 14/10/2021 10:12",
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.primary,
                    fontSize = 16.sp
                )
            )
        }
        Spacer(modifier = modifier.height(25.dp))
        Button(
            onClick = { },
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            modifier = modifier
                .width(448.76.dp)
                .height(59.13.dp)
                .coloredShadow(GreyBorder)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Save New Changes",
                style = TextStyle(
                    color = Color.White
                )
            )
        }

    }
}

@Composable
fun ScreenPictureUsg(
    modifier: Modifier = Modifier,
    imagesList: List<Uri>,
    patientName: String,
) {
    var bigImage by remember { mutableStateOf<Uri>(Uri.parse("")) }

    Row(
        modifier
            .fillMaxSize(),
    ) {
        LazyColumn(modifier = modifier.padding(start = 50.dp)) {
            items(imagesList) { image ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    elevation = 0.dp,
                    modifier = modifier
                        .width(271.dp)
                        .height(153.dp)
                        .padding(bottom = 10.dp)
                        .clickable {
                            bigImage = image
                        }
                ) {
                    image.getBitmap(LocalContext.current.contentResolver)?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "",
                            modifier = modifier
                                .clip(shape = RoundedCornerShape(10.dp))
                                .width(616.dp)
                                .height(350.dp)
                        )
                    }
                }
            }
        }
        Column(modifier = modifier.padding(horizontal = 33.dp)) {
            bigImage.getBitmap(LocalContext.current.contentResolver)?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "",
                    modifier = modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(616.dp)
                        .height(350.dp)
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    patientName,
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.primary,
                        fontSize = 22.sp,
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bx_mail_send),
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary,
                        modifier = modifier.width(30.dp)
                    )
                    Spacer(modifier = modifier.width(33.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bx_share_alt),
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary,
                        modifier = modifier.width(30.dp)
                    )
                }
            }
        }
    }
}

fun Uri.getBitmap(c: ContentResolver): Bitmap? {
    return try {
        val input = c.openInputStream(this)
        BitmapFactory.decodeStream(input)
    } catch (e: Exception) {
        null
    }
}
