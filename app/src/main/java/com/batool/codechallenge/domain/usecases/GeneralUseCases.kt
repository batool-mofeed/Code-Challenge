package com.batool.codechallenge.domain.usecases

import com.batool.codechallenge.data.datasource.remote.responsemodel.Article
import com.batool.codechallenge.data.model.User
import com.batool.codechallenge.domain.model.Resource
import kotlinx.coroutines.CoroutineScope

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
interface GeneralUseCases {

    fun setLanguage(lang: String)

    fun saveUser(user: User)

    fun getUser(): User?

    fun getViewedArticles(
        scope: CoroutineScope,
        resource: (Resource<List<Article>>) -> Unit
    )

    fun logoutUserClicked(succeeded: () -> Unit)

}