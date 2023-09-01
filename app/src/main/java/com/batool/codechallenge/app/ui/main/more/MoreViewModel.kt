package com.batool.codechallenge.app.ui.main.more

import com.batool.codechallenge.app.base.BaseViewModel
import com.batool.codechallenge.data.model.User
import com.batool.codechallenge.domain.usecases.GeneralUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val generalUseCases: GeneralUseCases
) : BaseViewModel() {

    val isEnglishSelected = MutableStateFlow(false)

    private val _reloadApp = MutableStateFlow<Boolean>(false)
    val reloadApp = _reloadApp.asStateFlow()

    private val _loggedOut = MutableStateFlow(false)
    val loggedOut = _loggedOut.asStateFlow()

    val user = MutableStateFlow<User?>(null)

    private fun fetchUserData() {
        user.value = generalUseCases.getUser()
    }

    fun changeLanguage() {
        generalUseCases.setLanguage(
            if (isEnglishSelected.value) {
                "ar"
            } else {
                "en"
            }
        )
        _reloadApp.value = true
    }

    fun loadCurrentLanguage() {
        isEnglishSelected.value = generalRepository.getLanguage() == "en"
    }

    fun logout() {
        generalUseCases.logoutUserClicked {
            _loggedOut.value = true
        }
    }

    init {
        fetchUserData()
    }
}