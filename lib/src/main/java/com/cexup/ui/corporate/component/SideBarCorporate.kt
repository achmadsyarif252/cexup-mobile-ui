package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.inactive
import com.cexup.ui.utils.noRippleClick

enum class SidebarMenuType {
    Button,
    Link
}

data class SidebarMenuModel(
    var name:Int,
    var image:Int,
    var selectedImage:Int,
    var path:String,
    var type: SidebarMenuType,
)

@Composable
fun SideBarCorporate(
    modifier: Modifier = Modifier,
    onNavigate: (route: String) -> Unit,
    onLogout:()->Unit,
    selectedRoute: String,
    listMenuSidebarCorporate: List<SidebarMenuModel>
) {
    val scrollState = rememberScrollState()
    var logoutState by remember {
        mutableStateOf(false)
    }
    var scope = rememberCoroutineScope()

    fun selectedMenu(path:String):Boolean{
        if (selectedRoute == path) return true
        return false
    }

    DialogLogOutConfirmation(
        show = logoutState,
        onDismiss = {
            logoutState = false
        },
        onLogout = {
            logoutState=false
            onLogout()
        }
    )

    Row(
        modifier = modifier
            .verticalScroll(state = scrollState)
    ) {
        Column(
            modifier = modifier
                .width(176.dp)
                .wrapContentHeight()
        ) {
            listMenuSidebarCorporate.forEach {
                val path = it.path
                MenuItem(
                    menuModel = it,
                    selected = selectedMenu(path),
                    onNavigate = { onNavigate(path) },
                    onClick = { logoutState = true }
                )
                Spacer(modifier = Modifier.height(38.dp))
            }
        }
    }

}

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    menuModel: SidebarMenuModel,
    selected:Boolean=false,
    onNavigate: (route: String) -> Unit = {},
    onClick:()->Unit={},
) {
    Column(
        modifier = modifier
            .noRippleClick {
                when(menuModel.type){
                    SidebarMenuType.Button -> {
                        onClick()
                    }
                    SidebarMenuType.Link -> {
                        onNavigate(menuModel.path)
                    }
                }

            }
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = modifier.height(33.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = if (selected) painterResource(id = menuModel.selectedImage) else painterResource(id = menuModel.image) ,
                    contentDescription = "image drop down",
                    modifier = modifier.size(29.24.dp)
                )
                Spacer(modifier = Modifier.width(14.62.dp))
                Text(
                    text = stringResource(id = menuModel.name),
                    color = if(selected) Heading else inactive,
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600)
                    ),
                    modifier = modifier.align(Alignment.CenterVertically)
                )
            }


        }

    }


}