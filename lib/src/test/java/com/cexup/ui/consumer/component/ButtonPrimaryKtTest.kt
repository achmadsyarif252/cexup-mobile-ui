package com.cexup.ui.consumer.component

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog


@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
internal class ButtonPrimaryKtTest{

    @get:Rule
    val rule = createComposeRule()

    @Before
    @Throws(Exception::class)
    fun setUp(){
        ShadowLog.stream = System.out
    }

    @Test
    fun `show  text on button primary and button clickable`(){
        rule.setContent {
            ButtonPrimary(
                text = "Sign In"
            )
        }

        rule.onNodeWithText("Sign In")
            .assertIsDisplayed()
            .performClick()
            .assert(hasClickAction())

    }

    @Test
    fun `show text on button primary and button not clickable`(){
        rule.setContent {
            ButtonPrimary(
                text = "Sign In",
                enabled = false
            )
        }

        rule.onNodeWithText("Sign In")
            .assertIsDisplayed()
            .assertIsNotEnabled()


    }
}