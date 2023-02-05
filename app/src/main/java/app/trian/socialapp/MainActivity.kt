package app.trian.socialapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import app.trian.socialapp.databinding.ActivityMainBinding
import app.trian.socialapp.viewmodeltest.ViewModelTest
import com.cexup.ui.corporate.component.DialogSettingMultiparameter
import com.cexup.ui.corporate.component.LimitType
import com.cexup.ui.corporate.component.SidebarMenuModel
import com.cexup.ui.corporate.component.SidebarMenuType
import com.cexup.ui.corporate.screen.*
import com.cexup.ui.corporate.theme.CexupTheme

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: ViewModelTest by viewModels()
    private var isShowSetting = false
    private var currentLimitType = LimitType.HIGH_BP_LIMIT

    var hrAlarm: Boolean = false
    var spo2Alarm: Boolean = false
    var prAlarm: Boolean = false
    var tempAlarm: Boolean = false
    var systoleAlarm: Boolean = false
    var diastoleAlarm: Boolean = false
    var autoInterval: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShow.setOnClickListener {
            viewModel.showSetting(!isShowSetting)
            isShowSetting = !isShowSetting
        }

        //percobaan
        findViewById<ComposeView>(R.id.pm_setting).setContent {
            val obs = viewModel.isShowSetting.observeAsState()
            obs.value?.let { show ->
                DialogSettingMultiparameter(
                    show = show,
                    onDismiss = {
                        viewModel.showSetting(!isShowSetting)
                        isShowSetting = !isShowSetting
                        Log.e("Setting", isShowSetting.toString())
                    },
                    onLimitConfigSave = { limitType, upper, lower ->
                        currentLimitType = limitType
                        when (currentLimitType) {
                            LimitType.ECG_LIMIT -> {
//                                hrUpperLimit = upper.toInt()
//                                hrLowerLimit = lower.toInt()
                            }
                            LimitType.SPO2_LIMIT -> {
//                                spo2UpperLimit = upper.toInt()
//                                spo2LowerLimit = lower.toInt()
                            }
                            LimitType.PULSE_LIMIT -> {
//                                prUpperLimit = upper.toInt()
//                                prLowerLimit = lower.toInt()
                            }
                            LimitType.TEMPERATURE_LIMIT -> {
//                                tempUpperLimit = upper
//                                tempLowerLimit = lower
                            }
                            LimitType.HIGH_BP_LIMIT -> {
//                                systoleUpperLimit = upper.toInt()
//                                systoleLowerLimit = lower.toInt()
                            }
                            LimitType.LOW_BP_LIMIT -> {
//                                diastoleUpperLimit = upper.toInt()
//                                diastoleLowerLimit = lower.toInt()
                            }
                            LimitType.AUTOMATIC_INTERVAL -> {}
                        }
                    },
                    onSwitchChanged = { limitType, alarmValue ->
                        when (limitType) {
                            LimitType.ECG_LIMIT -> {
                                hrAlarm = alarmValue
                                Toast.makeText(
                                    this@MainActivity,
                                    "Alarm ECG Status : $hrAlarm",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            LimitType.SPO2_LIMIT -> {
                                spo2Alarm = alarmValue
                                Toast.makeText(
                                    this@MainActivity,
                                    "Alarm SPO2 Status : $spo2Alarm",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            LimitType.PULSE_LIMIT -> {
                                prAlarm = alarmValue
                                Toast.makeText(
                                    this@MainActivity,
                                    "Alarm PulseRate Status : $prAlarm",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            LimitType.TEMPERATURE_LIMIT -> {
                                tempAlarm = alarmValue
                                Toast.makeText(
                                    this@MainActivity,
                                    "Alarm Temp Status : $tempAlarm",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }

                            LimitType.HIGH_BP_LIMIT -> {
                                systoleAlarm = alarmValue
                                Toast.makeText(
                                    this@MainActivity,
                                    "Alarm Systole Status : $systoleAlarm",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            LimitType.LOW_BP_LIMIT -> {
                                diastoleAlarm = alarmValue
                                Toast.makeText(
                                    this@MainActivity,
                                    "Alarm Diastole Status : $diastoleAlarm",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            LimitType.AUTOMATIC_INTERVAL -> {
                                autoInterval = alarmValue
                            }
                        }
                    }
                )
            }
        }


        //kode asli
//        setContent {
//            CexupTheme {
////                val navController = rememberAnimatedNavController()
////                AnimatedNavHost(
////                    navController = navController,
////                    startDestination = "HOME",
////                ) {
////
////                }
//                val ctx = LocalContext.current
//                BaseScreen(
//                    currentRoute = "Kambing",
//                    onSearchPatient = { SearchPatientUIState() },
//                    onPatientDetail = {},
//                    onCheckUp = {},
//                    onProfile = { /*TODO*/ },
//                    onLogout = { /*TODO*/ },
//                    onNavigate = {},
//                    listMenuSidebar = listOf(
//                        SidebarMenuModel(
//                            name = com.cexup.ui.R.string.age,
//                            path = "Kambing",
//                            sizeData = "2",
//                            image = com.cexup.ui.R.drawable.ic_download,
//                            selectedImage = com.cexup.ui.R.drawable.ic_right_arrow_selected,
//                            type = SidebarMenuType.Link,
//                        ),
//                        SidebarMenuModel(
//                            name = com.cexup.ui.R.string.age,
//                            path = "uwu",
//                            image = com.cexup.ui.R.drawable.ic_baby,
//                            selectedImage = com.cexup.ui.R.drawable.ic_whatsapp,
//                            type = SidebarMenuType.Link,
//                        ),
//
//                        )
//                ) {
//                    ScreenPatients(
//                        patientsUIState = ScreenPatientsUIState(
//                            data = DataScreenPatients(
//                                totalPatients = 150, currentPage = 2, listDataPatients = listOf(
//                                    DataPatients(
//                                        patientsName = "Muhammad Sumbul",
//                                        patientsAge = 12,
//                                        patientsDiseases = "Penyakitan",
//                                        patientsPhone = "0828282",
//                                        patientsThumb = "",
//                                        patientsUserCode = "wewew"
//                                    ),
//                                    DataPatients(
//                                        patientsName = "Muhammad Sumbul",
//                                        patientsAge = 12,
//                                        patientsDiseases = "Penyakitan",
//                                        patientsPhone = "0828282",
//                                        patientsThumb = "",
//                                        patientsUserCode = "wewew"
//                                    ),
//                                    DataPatients(
//                                        patientsName = "Muhammad Sumbul",
//                                        patientsAge = 12,
//                                        patientsDiseases = "Penyakitan",
//                                        patientsPhone = "0828282",
//                                        patientsThumb = "",
//                                        patientsUserCode = "wewew"
//                                    ),
//                                    DataPatients(
//                                        patientsName = "Muhammad Sumbul",
//                                        patientsAge = 12,
//                                        patientsDiseases = "Penyakitan",
//                                        patientsPhone = "0828282",
//                                        patientsThumb = "",
//                                        patientsUserCode = "wewew"
//                                    ),
//                                    DataPatients(
//                                        patientsName = "Muhammad Sumbul",
//                                        patientsAge = 12,
//                                        patientsDiseases = "Penyakitan",
//                                        patientsPhone = "0828282",
//                                        patientsThumb = "",
//                                        patientsUserCode = "wewew"
//                                    )
//                                )
//                            )
//                        )
//                    )
//                }
//            }
//        }
    }
}
