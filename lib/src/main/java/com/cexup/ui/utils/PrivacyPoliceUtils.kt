package com.cexup.ui.utils

import android.content.Context
import com.cexup.datum.PrivacyPolice

fun Context.getPrivacyPolice(): String {
    val id = this.packageName

    if (id == "com.cexup.app") {
        return PrivacyPolice.markdownPrivacyPolice
    }
    if (id == "com.cexup.app.pusdokkes") {
        return PrivacyPolice.markDownPrivacyPolicePusdok
    }

    return ""
}