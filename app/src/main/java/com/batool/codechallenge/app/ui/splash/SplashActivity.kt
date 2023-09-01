package com.batool.codechallenge.app.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseActivity
import com.batool.codechallenge.app.ui.auth.AuthActivity
import com.batool.codechallenge.app.ui.main.MainActivity
import com.batool.codechallenge.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val bindingVariable = 0
    override val layoutId = R.layout.activity_splash
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun getViewModel() = splashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            if (splashViewModel.isThereUser()) {
                //Go to dashboard
                startActivity(MainActivity.getIntent(this))
            } else {
                //Go to registration
                startActivity(AuthActivity.getIntent(this))
                finish()
            }
        }, 2500)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, SplashActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }
}