package com.batool.codechallenge.domain.usecases

import com.batool.codechallenge.data.model.User

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
interface GeneralUseCases {

    fun saveUser(user: User)
}