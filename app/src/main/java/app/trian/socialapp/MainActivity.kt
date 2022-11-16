package app.trian.socialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.cexup.ui.R
import com.cexup.ui.corporate.component.SidebarMenuModel
import com.cexup.ui.corporate.component.SidebarMenuType
import com.cexup.ui.corporate.component.TypePhysicalExamination
import com.cexup.ui.corporate.screen.*
import com.cexup.ui.corporate.theme.CexupTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CexupTheme {
                val navController = rememberAnimatedNavController()
                val scope = rememberCoroutineScope()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = "HOME",
                ) {
                    composable(
                        "HOME"
                    ) {
                        BaseScreen(
                            currentRoute = "HOME",
                            onSearchPatient = {
                                SearchPatientUIState(
                                    data = listOf(
                                        Pair("hwadjgdhafghdfasghdfasjytuytqyeuwque", "usercode1"),
                                        Pair("ADAM SH", "usercode2"),
                                        Pair("KAMBING BANDOT", "usercode3"),
                                        Pair("ICIKIWIR", "usercode1"),
                                        Pair("FERDI KOPLING", "usercode2"),
                                        Pair("SIPALING KAMBING", "usercode3"),
                                        Pair("MUHAMMAD SUMBUL", "usercode1"),
                                        Pair("SIGIT RENDANG", "usercode2"),
                                        Pair("ROGER SUMATRA", "usercode3"),
                                    )
                                )

                            },
                            onPatientDetail = {},
                            onCheckUp = {},
                            onAddPatient = { /*TODO*/ },
                            onProfile = { /*TODO*/ },
                            onLogout = { /*TODO*/ },
                            onNavigate = {},
                            listMenuSidebar = listOf(
                                SidebarMenuModel(
                                    name = R.string.corporate_menu_dashboard,
                                    image = R.drawable.ic_home_unselected,
                                    selectedImage = R.drawable.ic_home_selected,
                                    path = "HOME",
                                    type = SidebarMenuType.Link
                                ),
                            )
                        ) {
                            ScreenAccount()
//                            ScreenDashboard(
//                                onClickPatientList = { /*TODO*/ },
//                                onClickDoctorList = { /*TODO*/ })
//                            val cek = flowOf(PagingData.from(listOf(
//                                PatientPagingItemUIState(
//
//                                )
//                            )))
//                            ScreenCheckup(
//                                userCode = "Unknown",
//                                featureList = listOf(
//                                    PhysicalExamination(
//                                        icon = R.drawable.ic_bmi,
//                                        nameFeature = "BMI & BMR",
//                                        unit = "",
//                                        typePhysicalExamination = TypePhysicalExamination.OneValue,
//                                        value1 = "asd",
//                                        onClick = {}
//                                    )),
//                                onClickSyncToCloud = { /*TODO*/ },
//                                patients = cek.collectAsLazyPagingItems(),
//                                onPatientSelected = {}
//                            )
//                            ScreenPatients(
//                                listPatient = listOf(
//                                    PatientProfileUIState(name = "hwadjgdhafghdfasghdfasjytuytqyeuwque"),
//                                    PatientProfileUIState(name = "ADAM SH"),
//                                    PatientProfileUIState(name = "FERDI KOPLING"),
//                                    PatientProfileUIState(name = "SIGIT RENDANG"),
//                                    PatientProfileUIState(name = "ROGER SUMATRA"),
//                                ),
//                                onGetPatient = {},
//                                onClickPatient = {},
//                                next = 1
//                            )
                        }
                    }
//                    ScreenReport(namePatient = "Adam SH", userCode = "165150200111152")

                }
            }
        }
    }
}
