package com.cexup.ui.consumer.screen

//import androidx.compose.ui.test.*
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.test.platform.app.InstrumentationRegistry
//import com.cexup.ui.R
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.robolectric.RobolectricTestRunner
//import org.robolectric.annotation.Config
//import org.robolectric.shadows.ShadowLog
//
//
//@RunWith(RobolectricTestRunner::class)
//@Config(instrumentedPackages = ["androidx.loader.content"])
//internal class ScreenOnBoardingKtTest{
//    @get:Rule
//    val rule = createComposeRule()
//
//    val ctx = InstrumentationRegistry.getInstrumentation().context
//
//    @Before
//    @Throws(Exception::class)
//    fun setUp(){
//        ShadowLog.stream = System.out
//    }
//
//    @Test
//    fun `test onboard screen `(){
//
//        rule.setContent {
//            ScreenOnBoarding(
//                data = listOf(
//                    OnBoardingModelUIState(
//                        text = R.string.text_onboarding1,
//                        description = R.string.description_onboarding1,
//                        image = R.drawable.image_onboard1
//                    ),
//                    OnBoardingModelUIState(
//                        text = R.string.text_onboarding2,
//                        description = R.string.description_onboarding2,
//                        image = R.drawable.image_onboard2
//                    ),
//                    OnBoardingModelUIState(
//                        text = R.string.text_onboarding3,
//                        description = R.string.description_onboarding3,
//                        image = R.drawable.image_onboard3
//                    )
//                )
//            )
//        }
//
//        rule.onNodeWithText("Swipe Left", useUnmergedTree = true)
//            .assertIsDisplayed()
//            .performTouchInput {
//                this.swipeLeft()
//            }
//            .performTouchInput {
//                this.swipeLeft()
//            }
//
//
//
//
//
//    }
//}