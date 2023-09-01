package com.batool.codechallenge.app.ui.main.dashboard

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.batool.codechallenge.BR
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseFragment
import com.batool.codechallenge.app.util.uiutil.hideSoftKeyboard
import com.batool.codechallenge.app.util.uiutil.textWatcher
import com.batool.codechallenge.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.fragment_dashboard
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    override fun getViewModel() = dashboardViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initSearchViews()
        dashboardViewModel.getViewedArticles()
    }



    private fun observeViewModel() {
        with(dashboardViewModel) {
            loading.collectFlow(::showProgressDialog)
            errorMessage.collectFlow {
                if (it != null && it.isNotEmpty()) {
                    toast(it)
                }
            }
        }
    }

    private fun initSearchViews() {
        with(binding) {
            searchEdt.setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(
                    v: TextView?, actionId: Int, event: KeyEvent?
                ): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                        hideSoftKeyboard(requireActivity())
                        search()
                        return true
                    }
                    return false
                }
            })
            searchEdt.addTextChangedListener(textWatcher {
                if (it.isEmpty()) {
                    getMainData()
                    //TODO:Communicator to retrieve main data

//                    binding.recyclerView.scrollTo(0, 0)
                } else {
                    //TODO:Communicator to search form pager
                    search()
                }
            })
            clearSearchText.setOnClickListener {
                getMainData()
                searchEdt.setText("")
                searchEdt.clearFocus()
            }
        }
    }

    private fun search() {
//        articlesAdapter.clearItems()
//        articlesAdapter.addItems(dashboardViewModel.articles.value?.filter {
//            it.title?.lowercase(Locale("en"))?.contains(
//                binding.searchEdt.text.toString()
//                    .lowercase(Locale("en"))
//            ) == true
//        }!!)
    }

    private fun getMainData() {
//        articlesAdapter.clearItems()
//        dashboardViewModel.articles.value?.let { it1 -> articlesAdapter.addItems(it1) }
    }
}