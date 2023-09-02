package com.batool.codechallenge.data.model

import com.squareup.moshi.JsonClass

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
@JsonClass(generateAdapter = true)
data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val dob: String,
    val encryptedPassword: String,
    var isLoggedIn:Boolean
)