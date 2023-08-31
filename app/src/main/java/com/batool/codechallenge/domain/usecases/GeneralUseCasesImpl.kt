package com.batool.codechallenge.domain.usecases

import com.batool.codechallenge.data.model.User
import com.batool.codechallenge.data.repositories.GeneralRepository
import com.batool.codechallenge.domain.usecases.base.BaseRemoteUseCase
import javax.inject.Inject

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
class GeneralUseCasesImpl  @Inject constructor(
    private val repo: GeneralRepository,
) : GeneralUseCases, BaseRemoteUseCase() {

    override fun saveUser(user: User) {
        repo.saveUser(user)
    }
}