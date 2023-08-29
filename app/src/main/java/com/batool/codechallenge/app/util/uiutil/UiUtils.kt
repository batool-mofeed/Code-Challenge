package com.batool.codechallenge.app.util.uiutil

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import com.batool.codechallenge.R

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
fun Context.buildProgressDialog() = Dialog(this).apply {
    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    setContentView(R.layout.dialog_progress)
    setCancelable(false)
}

fun Context.toast(stringId: Int) {
    Toast.makeText(this, this.getString(stringId), Toast.LENGTH_LONG).show()
}

fun Context.toast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}
