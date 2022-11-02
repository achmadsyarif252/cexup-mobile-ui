package com.cexup.ui.corporate.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.GreyBlack
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.noRippleClick
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import compose.icons.Octicons
import compose.icons.octicons.DotFill16
import compose.icons.octicons.Pencil24
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.ColorGray
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.gridItems

data class NurseData(
    var name: String = "Nurse name",
    var email: String = "Nurse email",
)

@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun CardAccount(
    modifier: Modifier = Modifier,
    nurse: NurseData = NurseData()
) {
    val tabs = listOf(
        TabContentRow(header = "My Profile") {
            EditProfile()
        },
        TabContentRow(header = "Log Activity") {
            LogActivityTab()
        }
    )
    val pagerState = rememberPagerState()

    val nurseDisplayName = nurse.name
    var qrCodeState by remember {
        mutableStateOf(false)
    }
    DialogQrCode(
        show = qrCodeState,
        onDismiss = {
            qrCodeState = false
        }
    )

    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(end = 30.dp, start = 30.dp, bottom = 30.dp)
            .coloredShadow(
                color = Color.Black.copy(0.25f),
                offsetY = 4.dp,
                borderRadius = 27.dp,
                shadowRadius = 2.dp
            ),
        elevation = 1.dp,
        shape = RoundedCornerShape(27.dp)
    ) {
        Column {
            Box(
                modifier = modifier
                    .fillMaxWidth(),
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    CoilImage(
                        imageModel = ImageBitmap.imageResource(id = R.drawable.bg_rs),
                        // Crop, Fit, Inside, FillHeight, FillWidth, None
                        contentScale = ContentScale.Crop,
                        // shows an image with a circular revealed animation.
                        circularReveal = CircularReveal(duration = 250),
                        // shows a placeholder ImageBitmap when loading.
                        placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
                        // shows an error ImageBitmap when the request failed.
                        error = ImageBitmap.imageResource(R.drawable.dummy_doctor),

                        modifier = Modifier
                            .height(109.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(27.dp)),
                    )
                }
                Column {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(
                            modifier = modifier
                                .padding(top = 17.03.dp, end = 17.88.dp)
                                .clip(CircleShape)
                                .background(Heading)
                                .width(28.dp)
                                .height(28.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Octicons.Pencil24, "",
                                tint = Color.White,
                                modifier = modifier.padding(5.dp)
                            )
                        }
                    }
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Box(
                                modifier = modifier
                                    .width(143.07.dp)
                                    .height(143.07.dp)
                            ) {
                                CoilImage(
                                    imageModel = ImageBitmap.imageResource(R.drawable.dummy_profile),
                                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                                    contentScale = ContentScale.Crop,
                                    // shows an image with a circular revealed animation.
                                    circularReveal = CircularReveal(duration = 250),
                                    // shows a placeholder ImageBitmap when loading.
                                    placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
                                    // shows an error ImageBitmap when the request failed.
                                    error = ImageBitmap.imageResource(R.drawable.dummy_doctor),
                                    modifier = modifier
                                        .clip(CircleShape)
                                        .width(143.07.dp)
                                        .height(143.07.dp)
                                )
                                Row(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(
                                            bottom = 11.dp,
                                            end = 4.26.dp
                                        ),
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Box(
                                        modifier = modifier
                                            .clip(CircleShape)
                                            .background(Heading)
                                            .width(28.dp)
                                            .height(28.dp),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Icon(
                                            Octicons.Pencil24, "",
                                            tint = Color.White,
                                            modifier = modifier.padding(5.dp)
                                        )
                                    }
                                }

                            }
                            Column(
                                modifier =
                                modifier
                                    .padding(start = 15.dp, bottom = 5.dp)
                                    .align(Alignment.Bottom)
                            ) {
                                Text(
                                    nurseDisplayName,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = Heading,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                                Text(
                                    "Nurse",
                                    style = MaterialTheme.typography.body1.copy(
                                        color = ColorGray,
                                        fontSize = 16.sp,
                                    )
                                )
                            }
                        }
                        SwipeCall(
                            session = "Session Active", doctor_name = "Dr. Kholid",
                            profile = ""
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Box {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                ) {
                    TabView(tabContents = tabs, pagerState = pagerState)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(end = 30.dp, bottom = 93.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    FloatingActionButton(
                        onClick = { qrCodeState = true },
                        shape = CircleShape,
                        backgroundColor = Heading,
                        contentColor = Color.White,
                        modifier = modifier.size(40.88.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_qrcode_button),
                            contentDescription = "qr",
                            modifier = modifier.size(22.dp)
                        )
                    }
                }
            }

        }
    }
}


@ExperimentalComposeUiApi
@Composable
fun EditProfile(
    modifier: Modifier = Modifier,
    nurse: NurseData = NurseData(),
    aboutMeText: String = "",
) {

    var editPasswordState by remember { mutableStateOf(false) }
    val jobTitleState by remember { mutableStateOf("Nurse") }
    val emailState by remember { mutableStateOf(nurse.email) }
    var editAboutMeState by remember {
        mutableStateOf(false)
    }
    DialogAboutMe(
        show = editAboutMeState,
        onDismiss = {
            editAboutMeState = false
        }
    )

    DialogChangePassword(
        show = editPasswordState,
        onDismiss = {
            editPasswordState = false
        }
    )
    val password by remember { mutableStateOf(nurse.name) }
    var dot = listOf(
        Octicons.DotFill16,
        Octicons.DotFill16,
        Octicons.DotFill16,
        Octicons.DotFill16,
    )


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 20.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .width(320.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "About Me",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = GreyBlack
                    ),
                )
                Text(
                    text = "Edit",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = inactive
                    ),
                    modifier = Modifier.noRippleClick {
                        editAboutMeState = true
                    }
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = aboutMeText,
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = GreyBlack,
                    textAlign = TextAlign.Justify
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_edit_password),
                    contentDescription = "edit password",
                    modifier = modifier
                        .size(21.dp)
                        .noRippleClick {
                            editPasswordState = true
                        }
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Edit Password",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = inactive
                    ),
                    modifier = Modifier
                        .noRippleClick {
                            editPasswordState = true
                        }
                )

            }
        }
        Spacer(modifier = Modifier.width(45.dp))
        Column(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            var widthRowText = 75.dp
            Row(
                Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = modifier.width(widthRowText)
                ) {
                    Text(
                        text = "First Name",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = GreyBlack
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = nurse.name.split(" ")[0],
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = GreyBlack
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = modifier.width(widthRowText)
                ) {
                    Text(
                        text = "Last Name",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = GreyBlack
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = nurse.name.split(" ")[1],
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = GreyBlack
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = modifier.width(widthRowText)
                ) {
                    Text(
                        text = "Job Tittle",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = GreyBlack
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = jobTitleState,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = GreyBlack
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = modifier.width(widthRowText)
                ) {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = GreyBlack
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = emailState,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = GreyBlack
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = modifier.width(widthRowText)
                ) {
                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = GreyBlack
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                dot.forEachIndexed { index, imageVector ->
                    Icon(
                        imageVector,
                        contentDescription = "",
                        tint = inactive
                    )
                }

            }
        }
    }
}

@Composable
fun LogActivityTab(
    modifier: Modifier = Modifier
) {
    LazyColumn(content = {
        gridItems(count = 5, columnCount = 2) {
            CardDoctorNotification(
//                tumb = "",
                doctor_name = "Dr. Linggassari",
                status = "asked you to join in a video call",
                time = "2 Minutes ago"
            )
        }
    })
}