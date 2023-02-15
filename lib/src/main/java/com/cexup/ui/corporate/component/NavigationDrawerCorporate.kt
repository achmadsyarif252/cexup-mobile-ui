package com.cexup.ui.corporate.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleClick

enum class SidebarMenuType {
    Button,
    Link
}

data class SidebarMenuModel(
    var name: Int,
    var image: Int,
    var selectedImage: Int,
    var path: String,
    var type: SidebarMenuType,
    var sizeData: String = ""
)

@Composable
fun NavigationDrawerCorporate(
    modifier: Modifier = Modifier,
    onNavigate: (route: String) -> Unit,
    widthSize: Float,
    onLogout: () -> Unit,
    selectedRoute: String,
    onCloseDrawer: () -> Unit,
    listMenuSidebarCorporate: List<SidebarMenuModel>
) {
    val ctx = LocalConfiguration.current
    val scrollState = rememberScrollState()
    var logoutState by remember {
        mutableStateOf(false)
    }

    fun selectedMenu(path: String): Boolean {
        if (selectedRoute == path) return true
        return false
    }

    DialogLogOutConfirmation(
        show = logoutState,
        onDismiss = {
            logoutState = false
        },
        onLogout = {
            logoutState = false
            onLogout()
        }
    )

    Column(
        modifier = modifier
            .width(291.dp.from(ctx))
            .verticalScroll(state = scrollState)
            .padding(top = 40.dp.from(ctx), start = 28.dp.from(ctx), end = 12.dp.from(ctx)),
        verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "menu",
            tint = BlueJade,
            modifier = Modifier.noRippleClick {
                onCloseDrawer()
            }
        )
        Spacer(modifier = Modifier.height(12.dp.from(ctx)))
        listMenuSidebarCorporate.forEachIndexed { index, it ->
            if (listMenuSidebarCorporate.size-1 == index) {
                Divider(color = GrayDivider, thickness = 1.dp.from(ctx))
            }
            val path = it.path
            MenuItem(
                menuModel = it,
                selected = selectedMenu(path),
                onNavigate = {
                    onCloseDrawer()
                    onNavigate(path)
                },
                onClick = {
                    logoutState = true
                },
                sizeData = it.sizeData
            )
        }
    }


}

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    menuModel: SidebarMenuModel,
    selected: Boolean = false,
    sizeData: String = "",
    onNavigate: (route: String) -> Unit = {},
    onClick: () -> Unit = {},
) {
    val ctx = LocalConfiguration.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = if (selected) 0.dp.from(ctx) else 16.dp.from(ctx))
            .clip(RoundedCornerShape(24.dp.from(ctx)))
            .background(if (selected) BlueSelectedJade else Color.Transparent)
            .clickable(enabled = !selected) {
                when (menuModel.type) {
                    SidebarMenuType.Button -> {
                        onClick()
                    }
                    SidebarMenuType.Link -> {
                        onNavigate(menuModel.path)
                    }
                }
            }
            .padding(16.dp.from(ctx)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = if (selected) painterResource(id = menuModel.selectedImage) else painterResource(
                id = menuModel.image
            ),
            contentDescription = "image drop down",
            modifier = modifier.size(24.dp.from(ctx))
        )
        Spacer(modifier = Modifier.width(12.dp.from(ctx)))
        Text(
            text = stringResource(id = menuModel.name),
            color = if (selected) BlueDarkJade else BlackJade,
            style = MaterialTheme.typography.h3.copy(
                fontSize = 16.sp.from(ctx),
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp.from(ctx)
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = sizeData,
            color = BlueDarkJade,
            style = MaterialTheme.typography.h3.copy(
                fontSize = 16.sp.from(ctx),
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp.from(ctx)
            )
        )
    }


}