package com.batool.codechallenge.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.batool.codechallenge.R
import com.batool.codechallenge.data.datasource.remote.responsemodel.ViewedArticlesResponse
import com.batool.codechallenge.data.model.User
import com.batool.codechallenge.data.repositories.GeneralRepository
import com.batool.codechallenge.domain.model.Resource
import com.batool.codechallenge.domain.usecases.base.BaseRemoteUseCase
import kotlinx.coroutines.CoroutineScope
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
class GeneralUseCasesImpl @Inject constructor(
    private val repo: GeneralRepository,
) : GeneralUseCases, BaseRemoteUseCase() {

    override fun saveUser(user: User) {
        repo.saveUser(user)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getViewedArticles(
        scope: CoroutineScope,
        resource: (Resource<ViewedArticlesResponse>) -> Unit
    ) {
        requestApi(
            scope,
            api = { repo.getViewedArticles() },
            onLoading = { loading -> resource(Resource.Loading(loading)) },
            onError = { error -> resource(Resource.Error(error)) },
            additionalOperations = { response ->
                //To be saved to room
            },
            onSuccess = { response ->
                resource(
                    Resource.Success(response)
                )
            }
        )
    }



}