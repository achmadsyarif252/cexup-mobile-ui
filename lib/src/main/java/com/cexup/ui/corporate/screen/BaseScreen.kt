package com.cexup.ui.corporate.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.cexup.ui.corporate.component.AppBar
import com.cexup.ui.corporate.component.ContentSearch
import com.cexup.ui.corporate.component.NavigationDrawerCorporate
import com.cexup.ui.corporate.component.SidebarMenuModel
import com.cexup.ui.utils.mediaquery.from
import kotlinx.coroutines.launch

data class SearchPatientUIState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val errorMessage: String = "",
    val data: List<Pair<String, String>> = listOf(),
    val dataHistory: List<Pair<String, String>> = listOf(),
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun BaseScreen(
    currentRoute: String,
    onEnterSearchPressed: (value: String) -> Unit = {},
    onSearchPatient: suspend (String) -> SearchPatientUIState,
    onPatientDetail: (String) -> Unit,
    onCheckUp: (String) -> Unit,
    onProfile: () -> Unit,
    onLogout: () -> Unit,
    onNavigate: (String) -> Unit,
    listMenuSidebar: List<SidebarMenuModel>,
    content: @Composable () -> Unit,
) {
    val ctx = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var height by remember { mutableStateOf(0f) }
    val width = with(LocalDensity.current) { 291.dp.from(ctx).toPx() }
    var isSearch by remember { mutableStateOf(false) }
    var valueTextSearch by remember { mutableStateOf("") }
    var iconSearch by remember{ mutableStateOf(false) }
    var list by remember { mutableStateOf<List<Pair<String, String>>>(listOf()) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned {
                height = it.size.height.toFloat()
            },
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                valueTextSearch = valueTextSearch,
                isSearch = isSearch,
                onValueChange = {
                    if (!it.isEmpty()) {
                        scope.launch {
                            valueTextSearch = it
                            val searchResult = onSearchPatient(it)
                            list = searchResult.data
                            iconSearch = true
                        }
                    }else{
                        scope.launch {
                            valueTextSearch = it
                            val searchResult = onSearchPatient("")
                            list = searchResult.dataHistory
                            iconSearch = false
                        }
                    }
                },
                goToProfile = { onProfile() },
                onLogoClicked = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                onSearchClicked = {
                    isSearch = true
                },
                onBackIconClicked = {
                    valueTextSearch = ""
                    isSearch = false
                },
                onEnterPressed = onEnterSearchPressed
            )
        },
        drawerGesturesEnabled = !isSearch,
        drawerContent = {
            NavigationDrawerCorporate(
                selectedRoute = currentRoute,
                onLogout = { onLogout() },
                listMenuSidebarCorporate = listMenuSidebar,
                widthSize = width,
                onNavigate = { onNavigate(it) },
                onCloseDrawer = {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        },
        drawerElevation = 0.dp,
        drawerShape = customShape(
            width = width,
            height = height
        )
    ) {
        if (isSearch) {
            BackHandler() {
                valueTextSearch = ""
                isSearch = false
            }
        }
        Row(
            modifier = Modifier
                .padding(it)
//                .padding(start = 30.dp.from(ctx), end = 30.dp.from(ctx), top = 30.dp.from(ctx))
        ) {
            if (isSearch) {
                ContentSearch(
                    list = list,
                    onUpArrowClicked = {
                        scope.launch {
                            valueTextSearch = it
                            val searchResult = onSearchPatient(it)
                            list = searchResult.data
                        }
                    },
                    onSearchClicked = {
                        onPatientDetail(it)
                    },
                    onCheckupClicked = {
                        onCheckUp(it)
                    },
                    isIconSearch = iconSearch
                )
            } else {
                content.invoke()
            }
        }
    }
}

fun customShape(
    width: Float,
    height: Float,
) = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rounded(
            RoundRect(
                0f,
                0f,
                width /* width */,
                height /* height */,
                topRightCornerRadius = CornerRadius(16f, 16f),
                bottomRightCornerRadius = CornerRadius(16f, 16f)
            )
        )
    }
}