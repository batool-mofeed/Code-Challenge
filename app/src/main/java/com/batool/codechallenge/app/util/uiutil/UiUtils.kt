package com.batool.codechallenge.app.util.uiutil

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
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

fun getCircularProgressDrawable(ctx: Context) = CircularProgressDrawable(ctx).apply {
    strokeWidth = 5f
    backgroundColor = ContextCompat.getColor(ctx, R.color.black)
    centerRadius = 30f
    start()
}

fun textWatcher(newText: (String) -> Unit) = object : TextWatcher {
    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun onTextChanged(
        charSequence: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
    }

    override fun afterTextChanged(editable: Editable) {
        newText(editable.toString())
    }
}

fun hideSoftKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
}