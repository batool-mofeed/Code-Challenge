package com.batool.codechallenge.domain.usecases.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import java.util.concurrent.CancellationException

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
open class BaseRemoteUseCase {

    fun <RESPONSE_TYPE> requestApi(
        scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
        api: suspend () -> Response<RESPONSE_TYPE>,
        additionalOperations: suspend (RESPONSE_TYPE) -> Unit = {},
        onLoading: (Boolean) -> Unit = {},
        onSuccess: (RESPONSE_TYPE) -> Unit = {},
        onError: (String) -> Unit = {},
    ) {
        scope.launch {
            onLoading(true)
            try {
                val response = api()
                when {
                    response.isSuccessful -> {
                        additionalOperations(response.body()!!)
                        onSuccess(response.body()!!)
                    }
                    response.code() == 403 -> {
//                        onError(
//                            ErrorResponse(403, listOf(Error("Forbidden", "Forbidden")))
//                        )
                    }
                    response.code() == 401 -> {
                        onError(
                            "Un Authorized error"
                        )
                    }
                    response.code() in 400..499 -> {
                        onError(response.message())
                    }
                    response.code() == 500 -> {
                        onError(
                            "Un Authorized error"
                        )
                    }
                    response.code() in 500..550 -> {
                        onError(response.message())
                    }
                }
            } catch (e: CancellationException) {
                e.printStackTrace()
//                onError(handleAnotherErrorTypes(e))
                Timber.e("${e.message}")
            } catch (e: Exception) {
                e.printStackTrace()
                e.message?.let { onError(it) }
                Timber.e("${e.message}")
            }
            onLoading(false)
        }

    }


}