package com.batool.codechallenge.data.repositories

import android.content.Context
import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManager
import com.batool.codechallenge.data.datasource.remote.GeneralApiServices
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
class GeneralRepositoryImpl @Inject constructor(
    private val preferences: PreferencesManager,
    private val generalApiServices: GeneralApiServices,
    @ApplicationContext private val context: Context
) : GeneralRepository {

    override fun isThereUser() = preferences.getUser() != null

}