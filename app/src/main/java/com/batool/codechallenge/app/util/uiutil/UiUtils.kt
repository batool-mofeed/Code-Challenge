package com.batool.codechallenge.app.util.uiutil

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.batool.codechallenge.R
import java.util.regex.Matcher
import java.util.regex.Pattern

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

fun FragmentManager.replaceFragment(
    fragment: Fragment,
    frameId: Int
) {
    this.beginTransaction()
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        .replace(frameId, fragment)
        .addToBackStack(null)
        .commit()
}

fun View.click(block: () -> Unit) {
    setOnClickListener { block() }
}

fun isValidPassword(password: String?): Boolean {
    val pattern: Pattern
    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@*#$%^&+=])(?=\\S+$).{8,}$"
    pattern = Pattern.compile(PASSWORD_PATTERN)
    val matcher: Matcher = pattern.matcher(password)
    return matcher.matches()
}

fun String.isProbablyArabic(): Boolean {
    var i = 0
    while (i < this.length) {
        val c = this.codePointAt(i)
        if (c in 0x0600..0x06E0) return true
        i += Character.charCount(c)
    }
    return false
}