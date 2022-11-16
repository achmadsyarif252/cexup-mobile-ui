package com.cexup.ui.corporate.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cexup.ui.corporate.component.AppBar
import com.cexup.ui.corporate.component.SideBarCorporate
import com.cexup.ui.corporate.component.SidebarMenuModel
import com.cexup.ui.utils.mediaquery.from

data class SearchPatientUIState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val errorMessage: String = "",
    val data: List<Pair<String, String>> = listOf(),
)

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun BaseScreen(
    currentRoute: String,
    onSearchPatient: suspend (String) -> SearchPatientUIState,
    onPatientDetail: (String) -> Unit,
    onCheckUp: (String) -> Unit,
    onAddPatient: () -> Unit,
    onProfile: () -> Unit,
    onLogout: () -> Unit,
    onNavigate: (String) -> Unit,
    listMenuSidebar: List<SidebarMenuModel>,
    content: @Composable () -> Unit,
) {
    val ctx = LocalContext.current
    Scaffold(
        topBar = {
            AppBar(
                goToAddPatient = { onAddPatient() },
                goToPatient = { onPatientDetail(it) },
                goToProfile = { onProfile() },
                onSearchPatient = { onSearchPatient(it) },
                checkUpPatient = { onCheckUp(it) },
            )
        }
    ) {
        Row(
            modifier = Modifier
                .padding(start = 30.dp.from(ctx), end = 30.dp.from(ctx), top = 30.dp.from(ctx))
        ) {
            SideBarCorporate(
                selectedRoute = currentRoute,
                onLogout = { onLogout() },
                listMenuSidebarCorporate = listMenuSidebar,
                onNavigate = { onNavigate(it) }
            )
            content.invoke()
        }
    }
}