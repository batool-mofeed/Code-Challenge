package com.batool.codechallenge.app.base

import androidx.lifecycle.ViewModel
import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManager
import com.batool.codechallenge.data.repositories.GeneralRepository
import javax.inject.Inject

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var generalRepository: GeneralRepository

    @Inject
    lateinit var preferencesManager: PreferencesManager

    fun getLanguage(): String {
        return preferencesManager.getLanguage()
    }

    fun isThereUser() = generalRepository.isThereUser()

}