package app.trian.socialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.cexup.ui.corporate.component.SidebarMenuModel
import com.cexup.ui.corporate.component.SidebarMenuType
import com.cexup.ui.corporate.screen.*
import com.cexup.ui.corporate.theme.CexupTheme

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
                    ScreenPatients(
                        patientsUIState = ScreenPatientsUIState(
                            data = DataScreenPatients(
                                totalPatients = 150, currentPage = 2, listDataPatients = listOf(
                                    DataPatients(
                                        patientsName = "Muhammad Sumbul",
                                        patientsAge = 12,
                                        patientsDiseases = "Penyakitan",
                                        patientsPhone = "0828282",
                                        patientsThumb = "",
                                        patientsUserCode = "wewew"
                                    ),
                                    DataPatients(
                                        patientsName = "Muhammad Sumbul",
                                        patientsAge = 12,
                                        patientsDiseases = "Penyakitan",
                                        patientsPhone = "0828282",
                                        patientsThumb = "",
                                        patientsUserCode = "wewew"
                                    ),
                                    DataPatients(
                                        patientsName = "Muhammad Sumbul",
                                        patientsAge = 12,
                                        patientsDiseases = "Penyakitan",
                                        patientsPhone = "0828282",
                                        patientsThumb = "",
                                        patientsUserCode = "wewew"
                                    ),
                                    DataPatients(
                                        patientsName = "Muhammad Sumbul",
                                        patientsAge = 12,
                                        patientsDiseases = "Penyakitan",
                                        patientsPhone = "0828282",
                                        patientsThumb = "",
                                        patientsUserCode = "wewew"
                                    ),
                                    DataPatients(
                                        patientsName = "Muhammad Sumbul",
                                        patientsAge = 12,
                                        patientsDiseases = "Penyakitan",
                                        patientsPhone = "0828282",
                                        patientsThumb = "",
                                        patientsUserCode = "wewew"
                                    )
                                )
                            )
                        )
                    )
                }
            }
        }
    }
}
