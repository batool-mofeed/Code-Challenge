package com.batool.codechallenge.app.ui.main.dashboard.section

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.batool.codechallenge.BR
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseFragment
import com.batool.codechallenge.app.ui.main.dashboard.ArticlesAdapter
import com.batool.codechallenge.databinding.FragmentSectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionFragment : BaseFragment<FragmentSectionBinding>() {


    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.fragment_section
    private val sectionViewModel by viewModels<SectionViewModel>()
    override fun getViewModel() = sectionViewModel

    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArticlesRecyclerView()
    }

    private fun initArticlesRecyclerView() {
        binding.recyclerView.apply {
            articlesAdapter = ArticlesAdapter { article ->
            }
            adapter = articlesAdapter
        }
    }
}