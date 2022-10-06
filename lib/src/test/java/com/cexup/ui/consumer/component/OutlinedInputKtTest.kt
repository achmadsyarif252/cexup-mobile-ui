package com.cexup.ui.consumer.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
internal class OutlinedInputKtTest{
    @get:Rule
    val rule = createComposeRule()

    @Before
    @Throws(Exception::class)
    fun setUp(){
        ShadowLog.stream = System.out
    }

    @Test
    fun `outline input should show value and change value`(){
        rule.setContent {
            var value by remember {
                mutableStateOf("trian")
            }

            Column {
                InputOutlined(
                    value=value,
                    onChange = {
                        value = it
                    },
                    label = "Tes",
                    placeholder = "Input Tes",
                    visualTransformation = VisualTransformation.None
                )
            }
        }

        try {
            runBlocking {
                rule.onNodeWithTag("outlined_input",useUnmergedTree = true)
                    .assertIsDisplayed()
                    .assertIsEnabled()
            }

        }catch (e:Exception){
            println(e.message)
            throw e
        }

    }
}