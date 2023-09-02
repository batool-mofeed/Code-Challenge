package com.batool.codechallenge.app.ui.auth.register

import android.os.Build
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.batool.codechallenge.BR
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseFragment
import com.batool.codechallenge.app.ui.main.MainActivity
import com.batool.codechallenge.app.util.customviews.showDatePickerDialog
import com.batool.codechallenge.app.util.uiutil.click
import com.batool.codechallenge.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.fragment_register
    private val registerViewModel by viewModels<RegisterViewModel>()
    override fun getViewModel() = registerViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
        observeViewModel()
    }


    private fun observeViewModel() {
        with(registerViewModel) {
            createAccountSuccess.collectFlow {
                if (it) {
                    startActivity(MainActivity.getIntent(requireActivity()))
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initClicks() {
        with(binding) {
            dobEdt.click {
                requireActivity().showDatePickerDialog({ viewDate, date ->
                    registerViewModel.setDateOfBirth(viewDate, date)
                }
                ) {
                    toast(R.string.age_validation_message)
                }
            }
            showHidePassword.click {
                if (registerViewModel.visiblePass.value == true) {
                    passwordText.transformationMethod = PasswordTransformationMethod()
                } else {
                    passwordText.transformationMethod = null
                }
                registerViewModel.visiblePass.value =
                    !registerViewModel.visiblePass.value!!
                passwordText.setSelection(passwordText.length())
            }
            showHideConfirmPassword.click {
                if (registerViewModel.visibleConfirmPass.value == true) {
                    confirmPasswordText.transformationMethod = PasswordTransformationMethod()
                } else {
                    confirmPasswordText.transformationMethod = null
                }
                registerViewModel.visibleConfirmPass.value =
                    !registerViewModel.visibleConfirmPass.value!!
                confirmPasswordText.setSelection(confirmPasswordText.length())
            }
        }
    }

    companion object {
        fun newInstance(
        ) = RegisterFragment()
    }

}