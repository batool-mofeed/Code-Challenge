package com.batool.codechallenge.data.repositories

import com.batool.codechallenge.data.model.User

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
interface GeneralRepository {

    fun isThereUser(): Boolean

    fun saveUser(user: User)
}