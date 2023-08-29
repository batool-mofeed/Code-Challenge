package com.batool.codechallenge.app.ui.auth

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseActivity
import com.batool.codechallenge.app.base.BaseViewModel
import com.batool.codechallenge.app.ui.auth.login.LoginFragment
import com.batool.codechallenge.app.ui.auth.register.RegisterFragment
import com.batool.codechallenge.app.util.uiutil.replaceFragment
import com.batool.codechallenge.databinding.ActivityAuthBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {

    override val bindingVariable = 0
    override val layoutId = R.layout.activity_auth
    override fun getViewModel(): BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectRegister()
        initClicks()
    }

    private fun selectRegister() {
        Timber.e("select register")
        loadFragment(1)
        with(binding) {
            registerBtn.selectButton()
            loginBtn.selectButton(false)
        }
    }

    private fun selectLogin() {
        Timber.e("select login")
        loadFragment(2)
        with(binding) {
            registerBtn.selectButton(false)
            loginBtn.selectButton()
        }
    }

    private fun initClicks() {
        with(binding) {
            registerBtn.setOnClickListener {
                selectRegister()
            }
            loginBtn.setOnClickListener {
                selectLogin()
            }
        }
    }

    private fun loadFragment(fragmentType: Int) {
        val fragment = when (fragmentType) {
            1 -> RegisterFragment.newInstance()
            else -> LoginFragment.newInstance()
        }
        supportFragmentManager.replaceFragment(
            fragment,
            R.id.register_frame
        )
    }

    fun MaterialButton.selectButton(
        isSelected: Boolean = true
    ) {
        with(this) {
            val context = this.context
            if (isSelected) {
                setTextColor(Color.WHITE)
                background.setTint(
                    ContextCompat.getColor(
                        context,
                        R.color.purple_200
                    )
                )
            } else {
                background.setTint(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )
                setTextColor(Color.BLACK)
            }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, AuthActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }
}