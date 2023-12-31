package com.batool.codechallenge.data.repositories

import com.batool.codechallenge.BuildConfig
import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManager
import com.batool.codechallenge.data.datasource.local.room.dao.ArticleDao
import com.batool.codechallenge.data.datasource.remote.api.GeneralApiServices
import com.batool.codechallenge.data.datasource.remote.responsemodel.Article
import com.batool.codechallenge.data.datasource.remote.responsemodel.ViewedArticlesResponse
import com.batool.codechallenge.data.model.User
import retrofit2.Response
import javax.inject.Inject

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
class GeneralRepositoryImpl @Inject constructor(
    private val preferences: PreferencesManager,
    private val generalApiServices: GeneralApiServices,
    private val articleDao: ArticleDao
) : GeneralRepository {

    override fun setLanguage(lang: String) {
        preferences.setLanguage(lang)
    }

    override fun getLanguage(): String {
      return preferences.getLanguage()
    }

    override fun isThereUser() = preferences.getUser() != null && preferences.getUser()?.isLoggedIn == true

    override fun saveUser(user: User) {
        preferences.setUser(user)
    }

    override fun getUser(): User? {
        return preferences.getUser()
    }

    override fun saveArticle(article: Article) {
        articleDao.insertArticle(article)
    }

    override fun getSavedArticles(): List<Article> {
        return articleDao.getSavedArticles()
    }

    override suspend fun getViewedArticles(): Response<ViewedArticlesResponse> {
        return generalApiServices.getViewedArticles(BuildConfig.API_KEY)
    }

    override fun logoutUserClicked(succeeded: () -> Unit) {
        preferences.logoutUser {
            succeeded()
        }
    }
}