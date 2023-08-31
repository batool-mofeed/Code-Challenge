package com.batool.codechallenge.domain.model

/**
 * Created By Batool Mofeed n 8/31/2023.
 **/
sealed class Resource<T>(
    val data: T? = null, val loading: Boolean = false,val  error: String? = null) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Loading<T>(loading: Boolean = false) : Resource<T>(loading = loading)
    class Error<T>(error: String? = null) : Resource<T>(error = error)
}

sealed class LocalResource<T>(
    val data: T? = null, val loading: Boolean = false,val  error: String? = null) {
    class Success<T>(data: T? = null) : LocalResource<T>(data)
    class Loading<T>(loading: Boolean = false) : LocalResource<T>(loading = loading)
    class Error<T>(error: String? = null) : LocalResource<T>(error = error)
}
