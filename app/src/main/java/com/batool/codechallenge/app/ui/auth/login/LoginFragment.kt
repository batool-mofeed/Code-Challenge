package com.batool.codechallenge.app.ui.auth.login

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.fragment.app.viewModels
import com.batool.codechallenge.BR
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseFragment
import com.batool.codechallenge.app.util.uiutil.click
import com.batool.codechallenge.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.fragment_login
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun getViewModel() = loginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPasswordField()
    }

    private fun initPasswordField() {
        with(binding) {
            showHidePassword.click {
                if (loginViewModel.visiblePass.value == true) {
                    passwordText.transformationMethod = PasswordTransformationMethod()
                } else {
                    passwordText.transformationMethod = null
                }
                !loginViewModel.visiblePass.value!!
                passwordText.setSelection(passwordText.length())
            }
        }
    }

    companion object {
        fun newInstance(
        ) = LoginFragment()
    }
}