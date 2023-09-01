package com.batool.codechallenge.app.ui.main.dashboard

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.batool.codechallenge.BR
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseFragment
import com.batool.codechallenge.app.ui.main.dashboard.adapters.SectionPagerAdapter
import com.batool.codechallenge.app.ui.main.dashboard.adapters.TabsAdapter
import com.batool.codechallenge.app.ui.main.dashboard.communicators.SearchCommunicator
import com.batool.codechallenge.app.ui.main.dashboard.communicators.SortCommunicator
import com.batool.codechallenge.app.util.uiutil.CenterSmoothScroller
import com.batool.codechallenge.app.util.uiutil.click
import com.batool.codechallenge.app.util.uiutil.hideSoftKeyboard
import com.batool.codechallenge.app.util.uiutil.textWatcher
import com.batool.codechallenge.data.datasource.remote.responsemodel.Article
import com.batool.codechallenge.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.fragment_dashboard
    private val dashboardViewModel by viewModels<DashboardViewModel>()
    override fun getViewModel() = dashboardViewModel

    private lateinit var tabsAdapter: TabsAdapter


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initSearchViews()
        initClicks()
    }

    private fun initClicks() {
        with(binding) {
            sortLayout.click {
                SortCommunicator.postSort(true)
            }
        }
    }

    private fun observeViewModel() {
        with(dashboardViewModel) {
            loading.collectFlow(::showProgressDialog)
            errorMessage.collectFlow {
                if (it != null && it.isNotEmpty()) {
                    toast(it)
                }
            }
            sections.collectFlow {
                if (it != null) {
                    if (it.first.isNotEmpty()) {
                        initTabsRecyclerView(it.first)
                        initViewPager(it)
                    } else {
                        binding.noSectionsMessage.isVisible = true
                    }
                }
            }
        }
    }

    private fun initViewPager(pair: Pair<List<String>, List<List<Article>>>) {
        with(binding.viewPager) {
            val pageAdapter =
                SectionPagerAdapter(childFragmentManager, lifecycle, pair.first.size, pair.second)
            offscreenPageLimit = pair.first.size;
            this.adapter = pageAdapter
        }
    }

    private fun initTabsRecyclerView(tabs: List<String>) {
        binding.tabsRecycler.apply {
            tabsAdapter = TabsAdapter { position ->
                binding.viewPager.setCurrentItem(position, true)
                adapter!!.notifyDataSetChanged()
            }.apply {
                addItems(tabs)
            }
            adapter = tabsAdapter
        }
    }

    private val viewPagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            val smoothScroller: RecyclerView.SmoothScroller = CenterSmoothScroller(context)
            smoothScroller.targetPosition = position
            binding.tabsRecycler.layoutManager?.startSmoothScroll(smoothScroller)

            binding.tabsRecycler.findViewHolderForAdapterPosition(position)?.itemView?.performClick()
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
                        SearchCommunicator.postSearch(binding.searchEdt.text.toString())
                        return true
                    }
                    return false
                }
            })
            searchEdt.addTextChangedListener(textWatcher {
                SearchCommunicator.postSearch(binding.searchEdt.text.toString())
            })
            clearSearchText.setOnClickListener {
                SearchCommunicator.postSearch("")
                searchEdt.setText("")
                searchEdt.clearFocus()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        binding.viewPager.registerOnPageChangeCallback(viewPagerCallback)
        dashboardViewModel.getViewedArticles()
    }

    override fun onStop() {
        super.onStop()
        binding.viewPager.unregisterOnPageChangeCallback(viewPagerCallback)
    }

}