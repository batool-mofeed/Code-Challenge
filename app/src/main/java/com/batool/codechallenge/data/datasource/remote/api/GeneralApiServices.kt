package com.batool.codechallenge.data.datasource.remote.api

import com.batool.codechallenge.data.datasource.remote.responsemodel.ViewedArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
interface GeneralApiServices {

    @GET("viewed/1.json")
    suspend fun getViewedArticles(
        @Query("api-key") api_key: String,
    ): Response<ViewedArticlesResponse>


}