package com.batool.codechallenge.data.model

import com.squareup.moshi.JsonClass

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
@JsonClass(generateAdapter = true)
data class UserDTO(
     val id: Int,
     val name: String
)