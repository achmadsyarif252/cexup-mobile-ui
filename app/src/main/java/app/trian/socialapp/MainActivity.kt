package app.trian.socialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.cexup.ui.consumer.screen.ScreenOnBoarding
import com.cexup.ui.consumer.screen.auth.ScreenSignUp
import com.cexup.ui.consumer.screen.clinic_reservation.*
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.corporate.component.SidebarMenuModel
import com.cexup.ui.corporate.component.SidebarMenuType
import com.cexup.ui.corporate.screen.BaseScreen
import com.cexup.ui.corporate.screen.ScreenRegisterPatient
import com.cexup.ui.corporate.screen.ScreenTemperature
import com.cexup.ui.corporate.theme.CexupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ConsumerTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
////                    ScreenListClinicReservation(
////                        hospitalState = ClinicHospitalUIState(
////                        loading = true,
////                        error = false,
////                        data = listOf()),
////                        onSearchHospital = {})
//                    ScreenDetailOrderClinicReservation(
//                        onBackPressed = {},
//                        orderState = DetailOrderUIState(
//                            orderStatus = "Waiting Meeting",
//                            orderId = "#CXP-MT61b6f3fc2d7fd",
//                            orderDueDate = "20 Januari 2022",
//                            orderDueTime = "20 Hours",
//                            orderNote = "Test Note",
//                            loading = false,
//                            doctorName = "Fajar Achmad",
//                            doctorSpecialist = "Cocoklogi",
//                            doctorExperience = "5 Years",
//                            doctorHospital = "RS. Pesbuk",
//                            patientName = "Iqbal no Jutsu",
//                            patientGender = "Male",
//                            patientAge = "24 Years",
//
//                        )
//                    )
//
//                }
//            }
            CexupTheme {
//                ScreenTemperature(
//                    resultAnalytic = com.cexup.ui.R.string.consultation,
//                    colorAnalytic = com.cexup.ui.R.color.primary_day2,
//                    value = 66f,
//                    deviceStatus = false,
//                    listEntryTemperature = Pair(listOf(), listOf()),
//                    onSave = {},
//                    onButtonBackPressed = {}
//                )
                BaseScreen(
                    currentRoute = "Home",
                    onSearchPatient ={} ,
                    onPatientDetail = {},
                    onCheckUp = {},
                    onAddPatient = { /*TODO*/ },
                    onProfile = { /*TODO*/ },
                    onLogout = { /*TODO*/ },
                    onNavigate = {},
                    listMenuSidebar = listOf(SidebarMenuModel(com.cexup.ui.R.string.corporate_menu_account,
                        com.cexup.ui.R.drawable.ic_home_unselected, com.cexup.ui.R.drawable.ic_home_selected,"Home",SidebarMenuType.Link))
                ) {
                    ScreenRegisterPatient(onRegisterPatient = {})
                }
            }
        }
    }
}
