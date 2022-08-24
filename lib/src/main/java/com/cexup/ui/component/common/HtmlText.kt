package com.cexup.ui.component.common

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

/**
 * show html format string to text
 * author Trian Damai
 * created_at 07/02/22 - 01.14
 * site https://trian.app
 */

@Composable
fun HtmlText(element:String){
    AndroidView(
        factory = {
            TextView(it)
        },
        update = {
            it.text = HtmlCompat.fromHtml(element,HtmlCompat.FROM_HTML_MODE_COMPACT)
        }

    )
}