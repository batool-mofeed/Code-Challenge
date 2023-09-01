package com.batool.codechallenge.app.ui.main.dashboard.section

import com.batool.codechallenge.app.base.BaseViewModel
import com.batool.codechallenge.data.datasource.remote.responsemodel.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor() : BaseViewModel() {

    val articles = MutableStateFlow<List<Article>>(emptyList())

    fun setArticles(art: List<Article>) {
        articles.value = art
    }

}