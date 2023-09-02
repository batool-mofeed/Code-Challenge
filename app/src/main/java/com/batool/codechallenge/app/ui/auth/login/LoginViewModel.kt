package com.batool.codechallenge.app.ui.auth.login

import androidx.lifecycle.MutableLiveData
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseViewModel
import com.batool.codechallenge.app.util.isValidEmail
import com.batool.codechallenge.app.util.md5
import com.batool.codechallenge.domain.usecases.GeneralUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val generalUseCases: GeneralUseCases
) : BaseViewModel() {

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _loggedIn = MutableStateFlow<Boolean?>(null)
    val loggedIn = _loggedIn.asStateFlow()


    val visiblePass = MutableLiveData(false)

    val emailError = MutableLiveData("")
    val email = MutableLiveData("")

    val passwordError = MutableLiveData("")
    val password = MutableLiveData("")

    fun validate(): Boolean {

        var invalidFields = false
        if (email.value.isNullOrEmpty()) {
            emailError.value = resourceProvider.provideString(R.string.email_required)
            invalidFields = true
        } else {
            if (!email.value?.isValidEmail()!!) {
                emailError.value = resourceProvider.provideString(R.string.email_invalid)
                invalidFields = true
            }
        }

        if (password.value.isNullOrEmpty()) {
            passwordError.value = resourceProvider.provideString(R.string.password_required)
            invalidFields = true
        }

        return !invalidFields

    }

    fun loginClicked() {
        if (validate()) {
            generalUseCases.getUser()?.let {
                if (it.email.lowercase() == email.value?.lowercase() && it.encryptedPassword == md5(password.value.toString())) {
                    it.isLoggedIn = true
                    generalUseCases.saveUser(it)
                    _loggedIn.value = true
                } else {
                    _errorMessage.value =
                        resourceProvider.provideString(R.string.invalid_credentials)
                }
            }
        }
    }

}