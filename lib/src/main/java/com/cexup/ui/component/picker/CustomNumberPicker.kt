package com.cexup.ui.component.picker

import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.widget.NumberPicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.cexup.ui.R


@Composable
fun CustomNumberPicker(
    min: Int,
    max: Int,
    value:Int=0,
    items: Array<String>,
    onValueChange: (old: Int, new: Int) -> Unit,
) {
    val isDark = isSystemInDarkTheme()
    AndroidView({
        NumberPicker(
            ContextThemeWrapper(it, R.style.Chart).apply { }

        ).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                textColor = if (isDark) Color.WHITE else Color.BLACK
            }
        }
    }, update = {

            view ->
        view.maxValue = max
        view.minValue = min
        view.displayedValues = items
        view.value = value

        view.setOnValueChangedListener { _, oldval, newval ->
            onValueChange(oldval, newval)
        }
    })
}


@Preview(
    name = "preview CustomNumberPicker dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "preview CustomNumberPicker light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewCustomNumberPicker() {
    MaterialTheme {
        CustomNumberPicker(min = 0, max = 1, items = arrayOf("a","b"), onValueChange = { _, _ ->  })
    }
}