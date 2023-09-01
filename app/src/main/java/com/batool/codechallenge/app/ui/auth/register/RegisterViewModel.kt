package com.batool.codechallenge.app.ui.auth.register

import androidx.lifecycle.MutableLiveData
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseViewModel
import com.batool.codechallenge.app.util.isPhoneNumberMatchCountryCode
import com.batool.codechallenge.app.util.isValidEmail
import com.batool.codechallenge.app.util.uiutil.isValidPassword
import com.batool.codechallenge.app.util.validatePhoneNumber
import com.batool.codechallenge.data.model.User
import com.batool.codechallenge.domain.usecases.GeneralUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val generalUseCases: GeneralUseCases
) : BaseViewModel() {

    val visiblePass = MutableLiveData(false)
    val visibleConfirmPass = MutableLiveData(false)
    val createAccountSuccess = MutableStateFlow(false)

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

    val confirmPasswordError = MutableLiveData("")
    val confirmPassword = MutableLiveData("")

    var date = ""

    fun setDateOfBirth(viewDate: String, date: String) {
        dob.value = viewDate
        this.date = date
    }

    private fun invalidateErrors() {
        nameError.value = ""
        idError.value = ""
        emailError.value = ""
        phoneError.value = ""
        dobError.value = ""
        passwordError.value = ""
        confirmPasswordError.value = ""
    }

    private fun validateFields(): Boolean {

        invalidateErrors()

        if (name.value.isNullOrEmpty()) {
            nameError.value = resourceProvider.provideString(R.string.name_required)
            return false
        } else if (id.value.isNullOrEmpty()) {
            idError.value = resourceProvider.provideString(R.string.id_required)
            return false
        } else if (email.value.isNullOrEmpty()) {
            emailError.value = resourceProvider.provideString(R.string.email_required)
            return false
        } else if (!email.value?.isValidEmail()!!) {
            emailError.value = resourceProvider.provideString(R.string.email_invalid)
            return false
        } else if (phone.value.isNullOrEmpty()) {
            phoneError.value = resourceProvider.provideString(R.string.phone_required)
            return false
        } else if (!resourceProvider.provideAppContext().validatePhoneNumber(phone.value!!)) {
            phoneError.value = resourceProvider.provideString(R.string.phone_invalid)
            return false
        } else if (!resourceProvider.provideAppContext().isPhoneNumberMatchCountryCode(
                ccp.value,
                (if (phone.value!!.startsWith("0")) phone.value!!.drop(1) else phone.value).toString()
            )
        ) {
            phoneError.value = resourceProvider.provideString(R.string.phone_invalid)
            return false
        } else if (dob.value.isNullOrEmpty()) {
            dobError.value = resourceProvider.provideString(R.string.dob_required)
            return false
        } else if (password.value.isNullOrEmpty()) {
            passwordError.value = resourceProvider.provideString(R.string.password_required)
            return false
        } else if (confirmPassword.value.isNullOrEmpty()) {
            confirmPasswordError.value = resourceProvider.provideString(R.string.password_required)
            return false
        } else if (password.value != confirmPassword.value) {
            passwordError.value = resourceProvider.provideString(R.string.password_not_match)
            confirmPasswordError.value = resourceProvider.provideString(R.string.password_not_match)
            return false
        } else if (!isValidPassword(password.value)) {
            passwordError.value = resourceProvider.provideString(R.string.weak_password)
            confirmPasswordError.value = resourceProvider.provideString(R.string.weak_password)
            return false
        }
        return true
    }

    fun createAccount() {
        if (validateFields()) {
            generalUseCases.saveUser(
                User(
                    id.value.toString(),
                    name.value.toString(),
                    email.value.toString(),
                    phone.value.toString(),
                    dob.value.toString()
                )
            )
            createAccountSuccess.value = true
        }
    }


}