package com.batool.codechallenge.app.ui.main.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.batool.codechallenge.BR
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseFragment
import com.batool.codechallenge.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.fragment_dashboard
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    override fun getViewModel() = dashboardViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}