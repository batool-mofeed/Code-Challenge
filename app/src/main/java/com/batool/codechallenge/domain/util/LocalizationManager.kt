package com.batool.codechallenge.domain.util

import android.content.Context
import android.os.Build
import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManager
import java.util.*
import javax.inject.Inject

/**
 * Created By Batool Mofeed - Vibes Solutions on 8/29/2023.
 **/
class LocalizationManager @Inject constructor(
    private val preferencesManager: PreferencesManager
) {

    fun loadSavedLocale(context: Context): Context {
        return updateResourcesNow(context)
    }

    private fun updateResourcesNow(context: Context): Context {
        val locale = Locale(getLanguage())
        Locale.setDefault(locale)
        Locale.getDefault().displayLanguage
        // updating the language for devices above android nougat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, locale)
        }
        // for devices having lower version of android os
        return updateResourcesLegacy(context, locale)
    }

    private fun updateResources(context: Context, locale: Locale): Context {
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    @Suppress("DEPRECATION")
    private fun updateResourcesLegacy(context: Context, locale: Locale): Context {
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

    private fun getLanguage() = preferencesManager.getLanguage()
}
