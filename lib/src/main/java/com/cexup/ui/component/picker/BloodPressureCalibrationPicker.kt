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
fun NumberPickerBloodPressure(
    min: Int,
    max: Int,
    onValueChange: (old: Int, new: Int) -> Unit
){
    val isDark = isSystemInDarkTheme()
    AndroidView({
        NumberPicker(
            ContextThemeWrapper(it, R.style.Chart).apply { }
        )
    }, update = {

            view ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            view.textColor = if(isDark) Color.WHITE else Color.DKGRAY
        }

        view.maxValue = max
        view.minValue = min
        view.setFormatter { value -> String.format("%02d", value) }
        view.setOnValueChangedListener { _, oldval, newval ->
            onValueChange(oldval, newval)
        }
    })
}

@Preview(
    name = "preview NumberPickerBloodPressure dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "preview NumberPickerBloodPressure light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewNumberPickerBloodPressure() {
    MaterialTheme {
        NumberPickerBloodPressure(min = 10, max = 100, onValueChange = {
                _, _ ->
        })
    }

}