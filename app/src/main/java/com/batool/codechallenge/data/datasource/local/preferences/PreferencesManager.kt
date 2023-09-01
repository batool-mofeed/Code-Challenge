package com.batool.codechallenge.data.datasource.local.preferences

import com.batool.codechallenge.data.model.User

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
interface PreferencesManager {

    fun setUser(user: User)
    fun getUser(): User?

    fun getLanguage(): String
    fun setLanguage(lang: String)

    fun logoutUser(succeeded:() -> Unit)

}