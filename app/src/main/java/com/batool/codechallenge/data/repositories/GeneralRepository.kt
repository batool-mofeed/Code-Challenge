package com.batool.codechallenge.data.repositories

import com.batool.codechallenge.data.datasource.remote.responsemodel.ViewedArticlesResponse
import com.batool.codechallenge.data.model.User
import retrofit2.Response

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
interface GeneralRepository {

    fun isThereUser(): Boolean

    fun saveUser(user: User)

    fun getUser():User?

    suspend fun getViewedArticles(): Response<ViewedArticlesResponse>

}