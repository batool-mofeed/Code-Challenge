package com.batool.codechallenge.app.ui.main.dashboard.section

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.batool.codechallenge.BR
import com.batool.codechallenge.R
import com.batool.codechallenge.app.base.BaseFragment
import com.batool.codechallenge.app.ui.main.dashboard.ArticlesAdapter
import com.batool.codechallenge.app.ui.main.dashboard.SortCommunicator
import com.batool.codechallenge.data.datasource.remote.responsemodel.Article
import com.batool.codechallenge.databinding.FragmentSectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionFragment : BaseFragment<FragmentSectionBinding>() {

    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.fragment_section
    private val sectionViewModel by viewModels<SectionViewModel>()
    override fun getViewModel() = sectionViewModel

    private lateinit var articlesAdapter: ArticlesAdapter
    private var articleList: List<Article> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArticlesRecyclerView()
        listenToSortCommunicator()
        articleList =
            arguments?.getParcelableArrayList(ARTICLES) ?: emptyList()
        sectionViewModel.setArticles(articleList)
    }

    private fun listenToSortCommunicator() {
        SortCommunicator.observeSort {
            if (it) {
                sectionViewModel.setArticles(emptyList())
                sectionViewModel.setArticles(articleList.sortedByDescending { it.updated })
            }
        }
    }

    private fun initArticlesRecyclerView() {
        binding.recyclerView.apply {
            articlesAdapter = ArticlesAdapter { article ->
            }
            adapter = articlesAdapter
        }
    }

    companion object {
        private const val ARTICLES = "ARTICLES"
        fun newInstance(
            articles: List<Article>
        ) = SectionFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(ARTICLES, articles as ArrayList)
            }
        }
    }
}