package com.cexup.ui.utils

import java.text.NumberFormat
import java.util.*

fun String.capitalizeWords(): String =
    split(" ").map { it.replaceFirstChar(Char::titlecase) }.joinToString(" ")

fun Double?.formatToRupiah(): String {
    if (this == null) return "Rp0"
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(this).toString()
}

fun Long?.formatToRupiah():String {
    if(this==null) return "Rp0"
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(this).toString()
}


