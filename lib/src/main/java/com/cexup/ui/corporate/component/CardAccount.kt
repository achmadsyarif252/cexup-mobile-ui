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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.cexup.ui.utils.mediaquery.from

@ExperimentalPagerApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun CardAccount(
    modifier: Modifier = Modifier,
    name: String = stringResource(id = R.string.nurse_name),
    email: String = stringResource(id = R.string.nurse_email),
) {
    val ctx = LocalContext.current
    val tabs = listOf(
        TabContentRow(header = stringResource(id = R.string.my_profile)) {
            EditProfile(
                name = name,
                email = email,
            )
        },
        TabContentRow(header = stringResource(id = R.string.activity_log)) {
            LogActivityTab()
        }
    )
    val pagerState = rememberPagerState()
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
            .padding(end = 30.dp.from(ctx), start = 30.dp.from(ctx), bottom = 30.dp.from(ctx))
            .coloredShadow(
                color = Color.Black.copy(0.25f),
                offsetY = 4.dp.from(ctx),
                borderRadius = 27.dp.from(ctx),
                shadowRadius = 2.dp.from(ctx)
            ),
        elevation = 1.dp.from(ctx),
        shape = RoundedCornerShape(27.dp.from(ctx))
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
                            .height(109.dp.from(ctx))
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(27.dp.from(ctx))),
                    )
                }
                Column {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(
                            modifier = modifier
                                .padding(top = 17.03.dp.from(ctx), end = 17.88.dp.from(ctx))
                                .clip(CircleShape)
                                .background(Heading)
                                .width(28.dp.from(ctx))
                                .height(28.dp.from(ctx)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Octicons.Pencil24, "",
                                tint = Color.White,
                                modifier = modifier.padding(5.dp.from(ctx))
                            )
                        }
                    }
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp.from(ctx)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Box(
                                modifier = modifier
                                    .width(143.07.dp.from(ctx))
                                    .height(143.07.dp.from(ctx))
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
                                        .width(143.07.dp.from(ctx))
                                        .height(143.07.dp.from(ctx))
                                )
                                Row(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(
                                            bottom = 11.dp.from(ctx),
                                            end = 4.26.dp.from(ctx)
                                        ),
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Box(
                                        modifier = modifier
                                            .clip(CircleShape)
                                            .background(Heading)
                                            .width(28.dp.from(ctx))
                                            .height(28.dp.from(ctx)),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Icon(
                                            Octicons.Pencil24, "",
                                            tint = Color.White,
                                            modifier = modifier.padding(5.dp.from(ctx))
                                        )
                                    }
                                }

                            }
                            Column(
                                modifier =
                                modifier
                                    .padding(start = 15.dp.from(ctx), bottom = 5.dp.from(ctx))
                                    .align(Alignment.Bottom)
                            ) {
                                Text(
                                    name,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = Heading,
                                        fontSize = 18.sp.from(ctx),
                                        fontWeight = FontWeight.Bold,
                                    )
                                )
                                Text(
                                    stringResource(id = R.string.nurse),
                                    style = MaterialTheme.typography.body1.copy(
                                        color = ColorGray,
                                        fontSize = 16.sp.from(ctx),
                                    )
                                )
                            }
                        }
                        SwipeCall(
                            session = stringResource(id = R.string.session_active),
                            doctor_name = "Dr. Kholid",
                            profile = ""
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp.from(ctx)))
            Box {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp.from(ctx))
                ) {
                    TabView(tabContents = tabs, pagerState = pagerState)
                    TabContent(tabContents = tabs, pagerState = pagerState)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(end = 30.dp.from(ctx), bottom = 93.dp.from(ctx)),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    FloatingActionButton(
                        onClick = { qrCodeState = true },
                        shape = CircleShape,
                        backgroundColor = Heading,
                        contentColor = Color.White,
                        modifier = modifier.size(40.88.dp.from(ctx))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_qrcode_button),
                            contentDescription = "qr",
                            modifier = modifier.size(22.dp.from(ctx))
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
    name: String = "Nurse name",
    email: String = "Nurse email",
    aboutMeText: String = "",
) {
    val ctx = LocalContext.current
    var editPasswordState by remember { mutableStateOf(false) }
    val jobTitleState by remember { mutableStateOf("Nurse") }
    val emailState by remember { mutableStateOf(email) }
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
    val dot = listOf(
        Octicons.DotFill16,
        Octicons.DotFill16,
        Octicons.DotFill16,
        Octicons.DotFill16,
    )


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp.from(ctx), bottom = 20.dp.from(ctx))
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .width(320.dp.from(ctx))
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.about_me),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp.from(ctx),
                        fontWeight = FontWeight(600),
                        color = GreyBlack
                    ),
                )
                Text(
                    text = stringResource(id = R.string.edit),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp.from(ctx),
                        fontWeight = FontWeight(500),
                        color = inactive
                    ),
                    modifier = Modifier.noRippleClick {
                        editAboutMeState = true
                    }
                )
            }
            Spacer(modifier = Modifier.height(5.dp.from(ctx)))
            Text(
                text = aboutMeText,
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 16.sp.from(ctx),
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
                        .size(21.dp.from(ctx))
                        .noRippleClick {
                            editPasswordState = true
                        }
                )
                Spacer(modifier = Modifier.width(5.dp.from(ctx)))
                Text(
                    text = stringResource(id = R.string.edit_password),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp.from(ctx),
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
        Spacer(modifier = Modifier.width(45.dp.from(ctx)))
        Column(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val widthRowText = 75.dp.from(ctx)
            Row(
                Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = modifier.width(widthRowText)
                ) {
                    Text(
                        text = stringResource(id = R.string.first_name),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight(500),
                            color = GreyBlack
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.width(30.dp.from(ctx)))
                Text(
                    text = name.split(" ")[0],
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp.from(ctx),
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
                        text = stringResource(id = R.string.last_name),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight(500),
                            color = GreyBlack
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(30.dp.from(ctx)))
                Text(
                    text = if (name.split(" ").size > 1) name.split(" ")[1] else "",
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp.from(ctx),
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
                        text = stringResource(id = R.string.job_tittle),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight(500),
                            color = GreyBlack
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(30.dp.from(ctx)))
                Text(
                    text = jobTitleState,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp.from(ctx),
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
                        text = stringResource(id = R.string.email),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight(500),
                            color = GreyBlack
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(30.dp.from(ctx)))
                Text(
                    text = emailState,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 16.sp.from(ctx),
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
                        text = stringResource(id = R.string.password),
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight(500),
                            color = GreyBlack
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.width(30.dp.from(ctx)))
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
fun LogActivityTab() {
    LazyColumn(content = {
        gridItems(count = 5, columnCount = 2) {
            CardDoctorNotification(
//                thumb = "",
                doctor_name = "Dr. Linggassari",
                status = "asked you to join in a video call",
                time = "2 Minutes ago"
            )
        }
    })
}