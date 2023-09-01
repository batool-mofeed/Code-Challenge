package com.batool.codechallenge.data.datasource.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.batool.codechallenge.data.datasource.remote.responsemodel.Article

/**
 * Created By Batool Mofeed on 9/1/2023.
 **/
@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getSavedArticles(): List<Article>

    @Insert
    fun insertArticle(article: Article)
}