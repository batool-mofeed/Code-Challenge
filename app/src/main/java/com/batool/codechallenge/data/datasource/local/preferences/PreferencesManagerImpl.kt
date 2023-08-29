package com.batool.codechallenge.data.datasource.local.preferences

import android.content.Context
import com.batool.codechallenge.data.model.UserDTO
import com.nedaluof.mihawk.MiHawk
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
class PreferencesManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferencesManager {

    init {
        MiHawk.init(context)
    }

    override fun setUser(userDTO: UserDTO) {
        MiHawk.put(PreferencesKeys.USER_KEY, userDTO)
    }

    override fun getUser(): UserDTO? = MiHawk.get<UserDTO>(PreferencesKeys.USER_KEY)

    override fun getLanguage() = MiHawk.get(PreferencesKeys.LANGUAGE, "en")

    override fun setLanguage(lang: String) {
        MiHawk.put(PreferencesKeys.LANGUAGE, lang)
    }

    override fun logoutUser(listOfTopics: (List<String>) -> Unit) {
        MiHawk.deleteAll {}
    }
}