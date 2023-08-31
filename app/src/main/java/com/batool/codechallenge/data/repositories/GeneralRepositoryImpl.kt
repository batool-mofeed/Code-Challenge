package com.batool.codechallenge.data.repositories

import android.content.Context
import com.batool.codechallenge.BuildConfig
import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManager
import com.batool.codechallenge.data.datasource.remote.api.GeneralApiServices
import com.batool.codechallenge.data.datasource.remote.responsemodel.ViewedArticlesResponse
import com.batool.codechallenge.data.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
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

    override fun saveUser(user: User) {
        preferences.setUser(user)
    }

    override suspend fun getViewedArticles(): Response<ViewedArticlesResponse> {
        return generalApiServices.getViewedArticles(BuildConfig.API_KEY)
    }
}