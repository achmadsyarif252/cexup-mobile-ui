package app.trian.socialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.cexup.ui.corporate.component.ValueHemoglobin
import com.cexup.ui.corporate.screen.*
import com.cexup.ui.corporate.theme.CexupTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class,
        ExperimentalPagerApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CexupTheme {
                val navController = rememberAnimatedNavController()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = "HOME",
                ) {
                    composable(route = "HOME"){
//                        BaseScreen(
//                            currentRoute = "HOME",
//                            onSearchPatient = {
//                              SearchPatientUIState()
//                            },
//                            onPatientDetail = {},
//                            onCheckUp = {},
//                            onAddPatient = { /*TODO*/ },
//                            onProfile = { /*TODO*/ },
//                            onLogout = { /*TODO*/ },
//                            onNavigate = {},
//                            listMenuSidebar = listOf(
//                                SidebarMenuModel(R.string.high, R.drawable.ic_pills, R.drawable.ic_glucose,"HOME",SidebarMenuType.Link)
//                            )
//                        ) {
                            ScreenGlucose(
                                glucoseDataUIState = GlucoseDataUIState(
                                    patientName = "Sumbul Muhammad",
                                    patientUserCode = "165150200111154",
                                    listDataGlucose1Day = listOf(
                                        ValueBloodGlucose(id = 1,
                                            value = 120,
                                            mealState = MealType.NoMeal,
                                            time = "2022-10-18 08:42"
                                        ),
                                        ValueBloodGlucose(id = 2,
                                            value = 70,
                                            mealState = MealType.NoMeal,
                                            time = "2022-10-18 10:42"
                                        ),
                                        ValueBloodGlucose(id = 3,
                                            value = 220,
                                            mealState = MealType.AfterMeal,
                                            time = "2022-10-18 13:42",
                                            insulin = 12,
                                            foodAndDrink = "Makan Sate Usus Minum Amer",
                                            isDetail = true
                                        ),
                                        ValueBloodGlucose(id = 3,
                                            value = 333,
                                            mealState = MealType.AfterMeal,
                                            time = "2022-10-18 15:42",
                                            insulin = 7,
                                            foodAndDrink = "Makan Sate Usus Minum Amer",
                                            isDetail = true
                                        ),
                                        ValueBloodGlucose(id = 4,
                                            value = 150,
                                            mealState = MealType.BeforeMeal,
                                            time = "2022-10-18 16:42"
                                        )
                                    ),
                                    listDataGlucose1Week = listOf(
                                        ValueBloodGlucose(id = 5,
                                            value = 200,
                                            mealState = MealType.NoMeal,
                                            time = "2022-10-18 16:42",
                                            pills = 7,
                                            foodAndDrink = "Makan Sate Usus Minum Amer",
                                            isDetail = true
                                        ),
                                        ValueBloodGlucose(id = 6,
                                            value = 55,
                                            mealState = MealType.NoMeal,
                                            time = "2022-10-18 16:42"
                                        ),
                                        ValueBloodGlucose(id = 7,
                                            value = 123,
                                            mealState = MealType.AfterMeal,
                                            time = "2022-10-18 16:42",
                                        ),
                                        ValueBloodGlucose(id = 8,
                                            value = 66,
                                            mealState = MealType.BeforeMeal,
                                            time = "2022-10-18 16:42"
                                        )
                                    ),
                                    listDataGlucose2Week = listOf(
                                        ValueBloodGlucose(id = 9,
                                            value = 120,
                                            mealState = MealType.NoMeal,
                                            time = "2022-10-18 16:42"
                                        ),
                                        ValueBloodGlucose(id = 10,
                                            value = 70,
                                            mealState = MealType.NoMeal,
                                            time = "2022-10-18 16:42"
                                        ),
                                        ValueBloodGlucose(id = 11,
                                            value = 220,
                                            mealState = MealType.AfterMeal,
                                            time = "2022-10-18 16:42",
                                            pills = 8,
                                            foodAndDrink = "Makan Sate Usus Minum Amer",
                                            isDetail = true
                                        ),
                                        ValueBloodGlucose(id = 12,
                                            value = 150,
                                            mealState = MealType.BeforeMeal,
                                            time = "2022-10-18 16:42"
                                        )
                                    ),
                                    listDataGlucose1Month = listOf(
                                        ValueBloodGlucose(id = 13,
                                            value = 99,
                                            mealState = MealType.NoMeal,
                                            time = "2022-10-18 16:42",
                                            insulin = 20,
                                            foodAndDrink = "Makan Sate Usus Minum Amer",
                                            isDetail = true
                                        ),
                                        ValueBloodGlucose(id = 14,
                                            value = 55,
                                            mealState = MealType.NoMeal,
                                            time = "2022-10-18 16:42"
                                        ),
                                        ValueBloodGlucose(id = 15,
                                            value = 123,
                                            mealState = MealType.AfterMeal,
                                            time = "2022-10-18 16:42",
                                        ),
                                        ValueBloodGlucose(id = 16,
                                            value = 66,
                                            mealState = MealType.BeforeMeal,
                                            time = "2022-10-18 16:42"
                                        )
                                    ),
                                    listDataHemoglobin = listOf(
                                        ValueHemoglobin(
                                            105,
                                            "2022-10-18 16:42"
                                        ),
                                        ValueHemoglobin(
                                            202,
                                            "2022-10-19 09:23"
                                        )
                                    ),
                                    totalGlucose1Day = 180,
                                    totalGlucose1Week = 200,
                                    totalGlucose2Week = 220,
                                    totalGlucose1Month = 300,
                                    totalPills1Day = 1,
                                    totalPills1Week = 7,
                                    totalPills2Week = 14,
                                    totalPills1Month = 30
                                ),
                                onButtonBackPressed = {},
                                onConnect = {},
                                onDisconnect = {},
                                onAddFoodAndDrink = {_,_ ->},
                                onAddGlucose = {_,_,_,_->},
                                onSync = {},
                                onAddHemoglobin = {_,_,_->},
                                onAddMedicine = {_,_,_,_,_,_ ->}
                            )
//                        }
                    }
                }
            }
        }
    }
}
