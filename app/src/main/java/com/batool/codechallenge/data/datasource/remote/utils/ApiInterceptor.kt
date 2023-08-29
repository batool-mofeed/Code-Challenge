package com.batool.codechallenge.data.datasource.remote.utils

import com.batool.codechallenge.data.repositories.apiinterceptor.ApiInterceptorRepository
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
class ApiInterceptor @Inject constructor(
    private val repository: ApiInterceptorRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
            .addHeader("Accept", "application/json")

        val response = chain.proceed(builder.build())
        val comingBody = response.body!!.string()
        repository.checkResponseCode(response.code, comingBody)
        //to avoid closed response.body issues
        return response.newBuilder()
            .body(comingBody.toResponseBody(response.body?.contentType()))
            .build()
    }
}