package com.batool.codechallenge.app.util.resource

import android.content.Context
import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
class ResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferencesManager: PreferencesManager
) : ResourceProvider {

    override fun provideString(stringId: Int): String {
        val config = context.resources.configuration
        config.setLocale(Locale(getLanguage()))
        val newContext = context.createConfigurationContext(config)
        return newContext.getString(stringId)
    }

    override fun provideAppContext() = context

    private fun getLanguage() = preferencesManager.getLanguage()

}