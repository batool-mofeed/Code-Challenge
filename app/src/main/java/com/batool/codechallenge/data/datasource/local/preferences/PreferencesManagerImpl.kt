package com.batool.codechallenge.data.datasource.local.preferences

import android.content.Context
import com.batool.codechallenge.data.model.User
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

    override fun setUser(userDTO: User) {
        MiHawk.put(PreferencesKeys.USER_KEY, userDTO)
    }

    override fun getUser(): User? = MiHawk.get<User>(PreferencesKeys.USER_KEY)

    override fun getLanguage() = MiHawk.get(PreferencesKeys.LANGUAGE, "en")

    override fun setLanguage(lang: String) {
        MiHawk.put(PreferencesKeys.LANGUAGE, lang)
    }

    override fun logoutUser(succeeded: () -> Unit) {
        if (getUser() != null) {
            val user = getUser()
            user?.isLoggedIn = false
            if (user != null) {
                setUser(user)
            }
        }
        succeeded()
    }
}