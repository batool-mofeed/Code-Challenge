package com.batool.codechallenge.app.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseActivity
import com.batool.codechallenge.app.base.BaseViewModel
import com.batool.codechallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingVariable = 0
    override val layoutId = R.layout.activity_main
    override fun getViewModel(): BaseViewModel? = null

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavController()
        initBottomNavigationClicks()
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun initBottomNavigationClicks() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dashboard_nav -> {
                    if (R.id.dashboardFragment != navController.currentDestination?.id) {
                        navController.navigate(R.id.dashboardFragment)
                    }
                }
                R.id.more_nav -> {
                    if (R.id.moreFragment != navController.currentDestination?.id) {
                        navController.navigate(R.id.moreFragment)
                    }
                }
            }
            true
        }
    }


    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }

}