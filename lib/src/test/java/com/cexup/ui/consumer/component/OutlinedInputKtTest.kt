package com.cexup.ui.consumer.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.input.VisualTransformation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
internal class OutlinedInputKtTest{
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `outline input should show value and change value`(){
        rule.setContent {
            var value by remember {
                mutableStateOf("trian")
            }

            Column {
                OutlinedInput(
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

        rule.onNodeWithTag("outlined_input")
            .assertIsDisplayed()
            .assertIsEnabled()
            .performTextInput("trian")
    }
}