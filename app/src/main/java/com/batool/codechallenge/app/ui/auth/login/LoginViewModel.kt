package com.batool.codechallenge.app.ui.auth.login

import androidx.lifecycle.MutableLiveData
import com.batool.codechallenge.app.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    val password = MutableLiveData("")
    val visiblePass = MutableLiveData(false)

}