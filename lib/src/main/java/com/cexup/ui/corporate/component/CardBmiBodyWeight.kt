package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.Octicons
import compose.icons.octicons.ChevronRight16
import compose.icons.octicons.ChevronUp16
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.*

@Composable
fun CardBmiBodyWeight(
    modifier: Modifier = Modifier,
    value : String= "--",
    colorOfValue : Color = LightBlue,
    type : Int = 1,
    satuan : String ="",
    analyticName : String = "",
    analytic : String = "",
    image : Int = R.drawable.ic_spo2
) {
    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val currentHeight = LocalContext
        .current
        .resources
        .displayMetrics.heightPixels.dp /
            LocalDensity.current.density
    var stateDropDown by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier
            .border(width = 1.dp, color = GreyBorder, shape = RoundedCornerShape(10.dp))
            .width((currentWidth-98.dp)/2f)
            .height(currentHeight / 9.5f)
            .padding(horizontal = 20.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            modifier = modifier
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = modifier.size(27.dp)
            )
            Spacer(Modifier.width(24.45.dp))
            Text(
                text = analyticName,
                style = MaterialTheme.typography.body1.copy(
                    color = GreyBlackStetoscope,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400)
                )
            )

        }
        when(type){
            //type 1 : Body Weight
            1-> {
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier.fillMaxWidth().fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = value,
                        style = MaterialTheme.typography.body1.copy(
                            color = colorOfValue,
                            fontSize = 22.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Column(
                        modifier = modifier.height(33.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = satuan,
                            style = MaterialTheme.typography.body1.copy(
                                color = colorOfValue,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(600)
                            ),
                        )
                    }

                }
            }
            //type 2 : dropdown
            2 -> {
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier.fillMaxWidth().fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = value,
                        style = MaterialTheme.typography.body1.copy(
                            color = colorOfValue,
                            fontSize = 22.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Column(
                        modifier = modifier.height(33.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = satuan,
                            style = MaterialTheme.typography.body1.copy(
                                color = colorOfValue,
                                fontSize = 12.sp,
                                fontWeight = FontWeight(600)
                            ),
                        )
                    }

                    Spacer(modifier = Modifier.width(13.dp))
                    Column(
                        modifier = modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            if (stateDropDown) Octicons.ChevronUp16 else Octicons.ChevronRight16,
                            contentDescription = "Arrow",
                            tint = GreyJade,
                            modifier = modifier
                                .size(23.dp)
                                .clickable {
                                    stateDropDown = !stateDropDown
                                }
                        )
                    }

                }
            }
            //type 3 : Bodytype
            3 ->{
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier.fillMaxWidth().fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = analytic,
                        style = MaterialTheme.typography.body1.copy(
                            color = LightOrange,
                            fontSize = 18.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                }
            }
            //type 4 : BMR
            4 -> {
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier.fillMaxWidth().fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column(
                        modifier = modifier
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            Text(
                                text = value,
                                style = MaterialTheme.typography.body1.copy(
                                    color = colorOfValue,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight(600)
                                )
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Column(
                                modifier = modifier.height(33.dp),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Text(
                                    text = satuan,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = colorOfValue,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(600)
                                    )
                                )
                            }

                        }
                        Row(
                            modifier = modifier
                                .padding(horizontal = 7.dp)
                                .background(color = colorOfValue, shape = RoundedCornerShape(10.dp)),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = analytic,
                                style = MaterialTheme.typography.body1.copy(
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight(400)
                                ),
                                modifier = modifier
                                    .padding(horizontal = 7.dp, vertical = 1.dp)
                            )

                        }

                    }
                    Spacer(modifier = modifier.width(13.dp))
                    Column(
                        modifier = modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            if (stateDropDown) Octicons.ChevronUp16 else Octicons.ChevronRight16,
                            contentDescription = "Arrow",
                            tint = GreyJade,
                            modifier = modifier
                                .size(23.dp)
                                .clickable {
                                    stateDropDown = !stateDropDown
                                }
                        )
                    }



                }

            }

            //type 5 : subcutaneous
            5 -> {
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier.fillMaxWidth().fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = modifier
                                .padding(horizontal = 7.dp, vertical = 7.dp)
                                .background(color = colorOfValue, shape = RoundedCornerShape(10.dp)),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = analytic,
                                style = MaterialTheme.typography.body1.copy(
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight(400)
                                ),
                                modifier = modifier
                                    .padding(horizontal = 7.dp, vertical = 1.dp)
                            )

                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Row {
                            Text(
                                text = value,
                                style = MaterialTheme.typography.body1.copy(
                                    color = colorOfValue,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight(600)
                                )
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Column(
                                modifier = modifier.height(33.dp),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Text(
                                    text = satuan,
                                    style = MaterialTheme.typography.body1.copy(
                                        color = colorOfValue,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight(600)
                                    )
                                )
                            }


                        }
                        Spacer(modifier = modifier.width(13.dp))
                        Column(
                            modifier = modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                if (stateDropDown) Octicons.ChevronUp16 else Octicons.ChevronRight16,
                                contentDescription = "Arrow",
                                tint = GreyJade,
                                modifier = modifier
                                    .size(23.dp)
                                    .clickable {
                                        stateDropDown = !stateDropDown
                                    }
                            )
                        }

                    }
                }
            }
        }

    }

}