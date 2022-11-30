package com.cexup.ui.consumer.screen.auth

//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.test.*
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.test.platform.app.InstrumentationRegistry
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.robolectric.RobolectricTestRunner
//import org.robolectric.annotation.Config
//import com.cexup.ui.R
//import org.junit.Before
//import org.robolectric.shadows.ShadowLog
//
//
//@RunWith(RobolectricTestRunner::class)
//@Config(instrumentedPackages = ["androidx.loader.content"])
//internal class ScreenSignInKtTest {

//    @get:Rule
//    val rule = createComposeRule()
//
//     var ctx = InstrumentationRegistry.getInstrumentation().context
//    @Before
//    @Throws(Exception::class)
//    fun setUp(){
//        ShadowLog.stream = System.out
//
//        rule.apply {
//            setContent {
//                ScreenSignIn(
//                    goToRegister = { /*TODO*/ },
//                    gotToForgetPassword = { /*TODO*/ },
//                    onLogin = { _, _ -> }
//                ) {
//
//                }
//            }
//        }
//    }
//
//
//    @Test
//    fun displayText(){
//        rule
//            .onNodeWithTag(testTag = TAG_BODY_SCREEN_SIGNIN)
//            .assertIsDisplayed()
//
//        val header_screen_sign_in = ctx.getString(R.string.header_screen_signin)
//
//
//        rule.onNodeWithTag(TAG_TITLE)
//            .assertIsDisplayed()
//            .assert(hasText(header_screen_sign_in))
//
//        rule.onNodeWithTag(TAG_BTN_SIGN_IN)
//            .performClick()
//            .assert(hasClickAction())
//
//
//    }
//}