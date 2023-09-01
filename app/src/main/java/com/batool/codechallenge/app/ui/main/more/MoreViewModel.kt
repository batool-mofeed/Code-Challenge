package com.batool.codechallenge.app.ui.main.more

import com.batool.codechallenge.app.base.BaseViewModel
import com.batool.codechallenge.data.model.User
import com.batool.codechallenge.domain.usecases.GeneralUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val generalUseCases: GeneralUseCases
) : BaseViewModel() {

    val user = MutableStateFlow<User?>(null)

    private fun fetchUserData() {
        user.value = generalUseCases.getUser()
    }

    init {
        fetchUserData()
    }
}