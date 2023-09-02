package com.batool.codechallenge.app.util

import android.content.Context
import android.util.Patterns
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
fun String?.isValidEmail(): Boolean {
    if (this.isNullOrEmpty() || this == null) {
        return false
    }
    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun Context?.validatePhoneNumber(phoneNumber: String?) = try {
    if (phoneNumber.isNullOrEmpty() || this == null) {
        false
    }
    val phoneUtil = PhoneNumberUtil.createInstance(this)
    if (phoneNumber?.startsWith("0") == true) {
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

fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}