package com.cexup.ui.corporate.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.GreyBorder
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.inactive
import compose.icons.Octicons
import compose.icons.octicons.Check16
import compose.icons.octicons.X16

enum class SwitchParameter(val parameter: String = "") {
    ON("ON"),
    OFF("OFF"),
}

enum class EnableParameter(val parameter: ImageVector) {
    Enable(Octicons.Check16),
    Disable(Octicons.X16),
}

@Composable
fun SwitchOnOff(
    selectedTabIndex: Int,
    onSelectedTab : (SwitchParameter) -> Unit,
    listOfValueTab: List<SwitchParameter>,
    onEnable : () -> Unit ={}

){

    var backgroundColor by remember {
        mutableStateOf(inactive)
    }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .height(26.33.dp)
            .width(100.dp)
            .border(
                width = 1.dp,
                color = GreyBorder,
                shape = RoundedCornerShape(3.dp)
            ),
        indicator = {},
        backgroundColor = Color.Transparent
    ) {
        listOfValueTab.forEachIndexed {
                index, tabParameter ->
            backgroundColor = if (tabParameter.name == SwitchParameter.ON.name) {
                Heading
            } else {
                inactive
            }
            Tab(
                selected = index == selectedTabIndex,
                onClick = {
                    onSelectedTab(tabParameter)
                    onEnable()
                },
                text = {
                    Text(
                        text = tabParameter.name,
                        style = MaterialTheme.typography.body1.copy(
                            fontSize = 9.sp,
                            color = Color.White
                        ),
                    )
                },
                modifier = Modifier.background(
                    if(selectedTabIndex == index) Color.White else backgroundColor
                ),
            )
        }

    }
}

@Composable
fun SwitchEnable(
    selectedTabIndex: Int,
    onSelectedTab : (EnableParameter) -> Unit,
    listOfValueTab: List<EnableParameter>,
    onEnable : () -> Unit ={}

){
    var backgroundColor by remember {
        mutableStateOf(inactive)
    }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .height(26.33.dp)
            .width(100.dp)
            .border(
                width = 1.dp,
                color = GreyBorder,
                shape = RoundedCornerShape(3.dp)
            ),
        indicator = {},
        backgroundColor = Color.Transparent
    ) {
        listOfValueTab.forEachIndexed {
                index, tabParameter ->
            backgroundColor = if (tabParameter.name == EnableParameter.Enable.name) {
                Heading
            } else {
                inactive
            }
            Tab(
                selected = index == selectedTabIndex,
                onClick = {
                    onSelectedTab(tabParameter)
                    onEnable()
                },
                icon = {
                    Icon(
                        tabParameter.parameter,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(10.dp)
                    )
                },
                modifier = Modifier.background(
                    if(selectedTabIndex == index) Color.White else backgroundColor
                ),
            )
        }
    }

}