package com.batool.codechallenge.app.ui.auth.register

import androidx.lifecycle.MutableLiveData
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseViewModel
import com.batool.codechallenge.app.util.isPhoneNumberMatchCountryCode
import com.batool.codechallenge.app.util.isValidEmail
import com.batool.codechallenge.app.util.validatePhoneNumber
import com.batool.codechallenge.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
) : BaseViewModel() {

    val nameError = MutableLiveData("")
    val name = MutableLiveData("")

    val idError = MutableLiveData("")
    val id = MutableLiveData("")

    val emailError = MutableLiveData("")
    val email = MutableLiveData("")

    val ccp = MutableStateFlow("962")
    val phoneError = MutableLiveData("")
    val phone = MutableLiveData("")

    val dobError = MutableLiveData("")
    val dob = MutableLiveData("")

    val passwordError = MutableLiveData("")
    val password = MutableLiveData("")

    private var nameNotEmpty = false
    private fun validateName() {
        nameError.value = ""
        if (id.value.isNullOrEmpty()) {
            nameNotEmpty = false
            nameError.value = resourceProvider.provideString(R.string.name_required)
        }
    }

    private var idNotEmpty = false
    private fun validateID() {
        idError.value = ""
        if (id.value.isNullOrEmpty()) {
            idNotEmpty = false
            idError.value = resourceProvider.provideString(R.string.id_required)
        }
    }

    private var emailNotEmpty = false
    private fun validateEmail() {
        emailError.value = ""
        if (!email.value.isNullOrEmpty()) {
            if (email.value?.isValidEmail()!!) {
                emailNotEmpty = true
            } else {
                emailNotEmpty = false
                emailError.value = resourceProvider.provideString(R.string.email_invalid)
            }
        } else {
            emailNotEmpty = false
            emailError.value = resourceProvider.provideString(R.string.email_required)
        }
    }

    private var phoneNotEmpty = false
    private fun validatePhone() {
        phoneError.value = ""
        if (!phone.value.isNullOrEmpty()) {
            if (resourceProvider.provideAppContext().validatePhoneNumber(phone.value!!)) {
                if (!resourceProvider.provideAppContext().isPhoneNumberMatchCountryCode(
                        ccp.value.toString(),
                        (if (phone.value!!.startsWith("0")) phone.value!!.drop(1) else phone.value).toString()
                    )
                ) {
                    phoneNotEmpty = false
                    phoneError.value = resourceProvider.provideString(R.string.phone_invalid)
                }
            } else {
                phoneNotEmpty = false
                phoneError.value = resourceProvider.provideString(R.string.phone_invalid)
            }
        } else {
            phoneNotEmpty = false
            phoneError.value = resourceProvider.provideString(R.string.phone_required)
        }
    }

    private var dobNotEmpty = false
    private fun validateDob() {
        dobError.value = ""
        if (!dob.value.isNullOrEmpty()) {
            if (dob.value?.isValidEmail()!!) {
                dobNotEmpty = true
            }
        } else {
            dobNotEmpty = false
            dobError.value = resourceProvider.provideString(R.string.dob_required)
        }
    }

    private var passwordNotEmpty = false
    private fun validatePassword() {
        passwordError.value = ""
        if (!password.value.isNullOrEmpty()) {
            if (password.value?.isValidEmail()!!) {
                passwordNotEmpty = true
            }
        } else {
            passwordNotEmpty = false
            passwordError.value = resourceProvider.provideString(R.string.password)
        }
    }

    fun createAccount() {
        validateName()
        validateID()
        validateEmail()
        validatePhone()
        validateDob()
        validatePassword()
        validateAll()
    }

    val createAccountSuccess = MutableStateFlow(false)
    private fun validateAll() {
        if (emailNotEmpty && idNotEmpty && dobNotEmpty && phoneNotEmpty && nameNotEmpty && passwordNotEmpty) {
            preferencesManager.setUser(
                User(
                    id.value.toString(),
                    email.value.toString(),
                    phone.value.toString(),
                    dob.value.toString()
                )
            )
            createAccountSuccess.value = true
        }
    }

}