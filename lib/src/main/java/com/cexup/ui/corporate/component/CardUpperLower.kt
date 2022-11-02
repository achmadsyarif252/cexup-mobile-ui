package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.Octicons
import compose.icons.octicons.ChevronRight16
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.GreyBlackStetoscope
import com.cexup.ui.corporate.theme.GreyBorder
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.inactive

@Composable
fun CardUpperLower(
    modifier: Modifier = Modifier,
    nameParameter : String = "",
    modeSwitch : ModeSwitch,
    listSwitch : List<SwitchParameter>,
    startPosition : SwitchParameter = SwitchParameter.OFF,
    upperValue : String = "",
    lowerValue : String = "",
    listenable : List<EnableParameter> = listOf(),
    startEnable : EnableParameter = EnableParameter.Disable,
    onUpperLowerConfig: () -> Unit = {},
    limitType: LimitType? = null,
    onSwitch: (limitType: LimitType) -> Unit = {},
){
    var tabParameter by remember {
        mutableStateOf(startPosition)
    }
    var enableParameter by remember {
        mutableStateOf(startEnable)
    }
    var stateEnabled by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .border(
                color = GreyBorder,
                shape = RoundedCornerShape(5.dp),
                width = 1.dp
            )
            .padding(horizontal = 18.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when(modeSwitch){
            ModeSwitch.NORMAL -> {
                Row {
                    Text(
                        text = nameParameter,
                        style = MaterialTheme.typography.body1.copy(
                            color = GreyBlackStetoscope,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                }
            }
            ModeSwitch.UPPERLOWER -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.clickable {
                        onUpperLowerConfig()
                    }
                ) {
                    Text(
                        text = "Upper:$upperValue",
                        style = MaterialTheme.typography.body1.copy(
                            color = GreyBlackStetoscope,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "Lower:$lowerValue",
                        style = MaterialTheme.typography.body1.copy(
                            color = GreyBlackStetoscope,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Icon(
                        Octicons.ChevronRight16,
                        contentDescription = "",
                        tint = MaterialTheme.colors.primaryVariant,
                        modifier = modifier.size(18.dp),
                    )
                }
            }
            ModeSwitch.INTERVAL -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = nameParameter,
                        style = MaterialTheme.typography.body1.copy(
                            color = GreyBlackStetoscope,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = if (stateEnabled) "Enabled" else "Disabled",
                        style = MaterialTheme.typography.body1.copy(
                            color = if (stateEnabled) Heading else GreyBlackStetoscope,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400)
                        )
                    )
                }
            }
        }

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            when(modeSwitch){
                ModeSwitch.INTERVAL ->{
                    SwitchEnable(
                        selectedTabIndex =enableParameter.ordinal,
                        onSelectedTab ={enableParameter = it},
                        listOfValueTab =listenable,
                        onEnable = {
                            stateEnabled = !stateEnabled
                        }
                    )
                } else ->{
                SwitchOnOff(
                    selectedTabIndex = tabParameter.ordinal,
                    onSelectedTab ={tabParameter = it},
                    listOfValueTab = listSwitch,
                    onEnable = {
                        if (limitType != null) {
                            onSwitch(limitType)
                        }
                    }
                )
            }
            }

        }
    }
}

@Composable
fun CardUnitTemperature(
    modifier: Modifier = Modifier,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .border(
                color = GreyBorder,
                shape = RoundedCornerShape(5.dp),
                width = 1.dp
            )
            .padding(horizontal = 18.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val listOfPatient: List<Int> = listOf(R.drawable.ic_farentheit, R.drawable.ic_celcius)
        var selectedUnit by remember { mutableStateOf(0) }
        var colorUnit by remember { mutableStateOf(false) }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Unit",
                style = MaterialTheme.typography.body1.copy(
                    color = GreyBlackStetoscope,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400)
                )
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                listOfPatient.forEachIndexed {
                        index, icon ->
                    Row(
                        modifier = modifier
                            .width(34.dp)
                            .height(24.dp)
                            .padding(horizontal = 5.dp)
                            .background(
                                color = if (selectedUnit == index) Heading else inactive,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .selectable(
                                selected = index == selectedUnit,
                                onClick = {
                                    selectedUnit = index
                                }
                            )
                        ,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(id = icon),
                            contentDescription ="",
                            modifier = modifier.size(13.dp)
                        )
                    }
                }
            }
        }
    }

}