package com.cexup.ui.corporate.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.BlackShadow
import com.cexup.ui.corporate.theme.GreyBorder
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.coloredShadow

@Composable
fun CardDoctors(
    modifier: Modifier = Modifier,
    doctorName : String = "",
    specialistName : String = "",
    thumb : String = "",
    onClickInstagram: () -> Unit = {},
    onClickWhatsapp: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
) {
    Column(
        modifier = modifier.padding(vertical = 12.dp)
    ) {
        Card(
            modifier = Modifier
                .width(166.dp)
                .height(201.dp)
                .coloredShadow(
                    alpha = 0.06f,
                    color = BlackShadow.copy(alpha = 0.08f),
                    shadowRadius = 5.dp,
                    offsetY = 12.dp
                ),
            shape = RoundedCornerShape(10.dp),
            elevation = 0.dp


        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CoilImage(
                        modifier = modifier
                            .clip(CircleShape)
                            .coloredShadow(MaterialTheme.colors.primary)
                            .width(66.dp)
                            .height(66.dp)
                            .align(Alignment.CenterVertically),
                        imageModel = ImageBitmap.imageResource(R.drawable.dummy_profile),
                        // Crop, Fit, Inside, FillHeight, FillWidth, None
                        contentScale = ContentScale.Crop,
                        // shows an image with a circular revealed animation.
                        circularReveal = CircularReveal(duration = 250),
                        // shows a placeholder ImageBitmap when loading.
                        placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
                        // shows an error ImageBitmap when the request failed.
                        error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = doctorName,
                        style = MaterialTheme.typography.body1.copy(
                            color = Heading,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                    Text(
                        text = specialistName,
                        style = MaterialTheme.typography.body1.copy(
                            color = Heading,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        Modifier
                            .size(27.dp)
                            .clip(CircleShape)
                            .border(
                                BorderStroke(1.dp,
                                GreyBorder
                            ), shape = CircleShape
                            )
                            .background(Color.Transparent)
                            .clickable {
                                onClickInstagram()
                            }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_instagram),
                                contentDescription = "Icon Instagram",
                                tint = Heading,
                                modifier = Modifier.size(14.dp)
                            )
                        }

                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        Modifier
                            .size(27.dp)
                            .clip(CircleShape)
                            .border(BorderStroke(1.dp,
                                GreyBorder
                            ), shape = CircleShape
                            )
                            .background(Color.Transparent)
                            .clickable {
                                onClickWhatsapp()
                            }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_whatsapp),
                                contentDescription = "Icon Whatsapp",
                                tint = Heading,
                                modifier = Modifier.size(14.dp)
                            )
                        }

                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        Modifier
                            .size(27.dp)
                            .clip(CircleShape)
                            .border(BorderStroke(1.dp,GreyBorder), shape = CircleShape)
                            .background(Color.Transparent)
                            .clickable {
                                onClickFacebook()
                            }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_facebook),
                                contentDescription = "Icon Facebook",
                                tint = Heading,
                                modifier = Modifier.size(14.dp)
                            )
                        }

                    }


                }

            }


        }
    }
}