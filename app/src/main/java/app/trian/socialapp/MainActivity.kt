package app.trian.socialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cexup.ui.corporate.component.ContentSearch
import com.cexup.ui.corporate.component.SidebarMenuModel
import com.cexup.ui.corporate.component.SidebarMenuType
import com.cexup.ui.corporate.screen.BaseScreen
import com.cexup.ui.corporate.screen.SearchPatientUIState
import com.cexup.ui.corporate.theme.CexupTheme
import com.cexup.ui.corporate.theme.MaterialThemeCexup
import com.cexup.ui.utils.mediaquery.from

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CexupTheme {
//                val navController = rememberAnimatedNavController()
//                AnimatedNavHost(
//                    navController = navController,
//                    startDestination = "HOME",
//                ) {
//
//                }
                val ctx = LocalContext.current
                BaseScreen(
                    currentRoute = "Kambing",
                    onSearchPatient = { SearchPatientUIState() },
                    onPatientDetail = {},
                    onCheckUp = {},
                    onProfile = { /*TODO*/ },
                    onLogout = { /*TODO*/ },
                    onNavigate = {},
                    listMenuSidebar = listOf(
                        SidebarMenuModel(
                            name = com.cexup.ui.R.string.age,
                            path = "Kambing",
                            sizeData = "2",
                            image = com.cexup.ui.R.drawable.ic_download,
                            selectedImage = com.cexup.ui.R.drawable.ic_right_arrow_selected,
                            type = SidebarMenuType.Link,
                        ),
                        SidebarMenuModel(
                            name = com.cexup.ui.R.string.age,
                            path = "uwu",
                            image = com.cexup.ui.R.drawable.ic_baby,
                            selectedImage = com.cexup.ui.R.drawable.ic_whatsapp,
                            type = SidebarMenuType.Link,
                        ),

                        )
                ) {
                    Card(elevation = MaterialThemeCexup.elevation.skim, shape = RoundedCornerShape(20.dp.from(ctx))){
                        Box(modifier = Modifier) {
                            Text(
                                text = "asdsda",
                                style = MaterialThemeCexup.typography.h1
                            )
                        }
                    }
                }
            }
        }
    }
}
