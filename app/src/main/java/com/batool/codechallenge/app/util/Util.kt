package com.batool.codechallenge.app.util

import android.content.Context
import android.util.Patterns
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil

/**
 * Created By Batool Mofeed - Vibes Solutions on 8/29/2023.
 **/
fun String.isValidEmail() =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Context.validatePhoneNumber(phoneNumber: String) = try {
    val phoneUtil = PhoneNumberUtil.createInstance(this)
    if (phoneNumber.startsWith("0")) {
        phoneNumber.drop(1)
    }
    val phNumberProto = phoneUtil.parse("+962$phoneNumber", null)
    phoneUtil.isValidNumber(phNumberProto)
} catch (e: Exception) {
    e.printStackTrace()
    false
}

fun Context.isPhoneNumberMatchCountryCode(countryCode: String, phoneNumber: String) = try {
    val phoneUtil = PhoneNumberUtil.createInstance(this)
    if (phoneNumber.startsWith("0")) {
        phoneNumber.drop(1)
    }
    val phNumberProto = phoneUtil.parse("+$countryCode$phoneNumber", null)
    phoneUtil.isValidNumber(phNumberProto)
} catch (e: Exception) {
    e.printStackTrace()
    false
}