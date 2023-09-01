package com.batool.codechallenge.app.ui.main.more

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.batool.codechallenge.BR
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseFragment
import com.batool.codechallenge.app.ui.auth.AuthActivity
import com.batool.codechallenge.app.ui.splash.SplashActivity
import com.batool.codechallenge.app.util.uiutil.click
import com.batool.codechallenge.databinding.FragmentMoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment : BaseFragment<FragmentMoreBinding>() {

    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.fragment_more
    private val moreViewModel by viewModels<MoreViewModel>()
    override fun getViewModel() = moreViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
        observeViewModel()
        moreViewModel.loadCurrentLanguage()

    }

    private fun observeViewModel() {
        with(moreViewModel) {
            loggedOut.collectFlow {
                if (it) {
                    startActivity(AuthActivity.getIntent(requireActivity()))
                }
            }
            reloadApp.collectFlow {
                if (it) {
                    startActivity(SplashActivity.getIntent(requireActivity()))
                }
            }
        }
    }

    private fun initClicks() {
        with(binding) {
            languageLayout.click {
                moreViewModel.changeLanguage()
            }
            logoutLayout.click {
                moreViewModel.logout()
            }
        }
    }

}