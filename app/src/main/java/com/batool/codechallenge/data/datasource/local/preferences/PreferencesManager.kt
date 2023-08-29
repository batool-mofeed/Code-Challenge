package com.batool.codechallenge.data.datasource.local.preferences

import com.batool.codechallenge.data.model.UserDTO

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
interface PreferencesManager {

    fun setUser(userDTO: UserDTO)
    fun getUser(): UserDTO?

    fun getLanguage(): String
    fun setLanguage(lang: String)

    fun logoutUser(listOfTopics: (List<String>) -> Unit)

}