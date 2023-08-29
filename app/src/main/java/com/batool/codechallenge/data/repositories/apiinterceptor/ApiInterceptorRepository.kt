package com.batool.codechallenge.data.repositories.apiinterceptor

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
interface ApiInterceptorRepository {
    fun getAppLanguage(): String
    fun checkResponseCode(responseCode: Int, comingBody: String)
}