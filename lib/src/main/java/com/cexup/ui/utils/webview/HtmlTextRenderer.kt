package com.cexup.ui.utils.webview

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.cexup.ui.utils.mediaquery.textUnitToMultipleScreen

/**
 * `Support Different Screen Size`
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 14/10/2021
 */

@Composable
fun HtmlTextRenderer(html: String) {
    AndroidView(factory = {
        TextView(it).apply {
            text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    })
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HtmlToString(html: String, currentDiagonal: Dp) {
    Text(
        text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT).toString(),
        style = MaterialTheme.typography.subtitle1.copy(
            fontSize = 12.sp.textUnitToMultipleScreen(currentDiagonal),
            fontWeight = FontWeight.Medium,
            lineHeight = 16.sp.textUnitToMultipleScreen(currentDiagonal),
            letterSpacing = 0.8.sp.textUnitToMultipleScreen(currentDiagonal),
        ),
        maxLines = 3,
        overflow = TextOverflow.Ellipsis
    )
}