package com.cexup.ui.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.cexup.ui.R

fun Context.toastSuccess(message:String){
    val toast = Toast(this)

    val view = LayoutInflater.from(this)
        .inflate(R.layout.layout_toast,null)

    val tv = view.findViewById<TextView>(R.id.tvMessage)
    tv.text = message

    toast.view = view
}