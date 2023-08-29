package com.batool.codechallenge.data.repositories.apiinterceptor

import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManager
import javax.inject.Inject

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
class ApiInterceptorRepositoryImpl @Inject constructor(
    private val preferences: PreferencesManager
) : ApiInterceptorRepository {


    override fun getAppLanguage(): String {
        return preferences.getLanguage()
    }

    override fun checkResponseCode(responseCode: Int, comingBody: String) {
        if (responseCode == 401 || responseCode == 403 || responseCode == 500) {

        }
    }
}