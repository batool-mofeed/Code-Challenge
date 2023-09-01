package com.batool.codechallenge.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.batool.codechallenge.data.datasource.local.room.dao.ArticleDao
import com.batool.codechallenge.data.datasource.remote.responsemodel.Article

/**
 * Created By Batool Mofeed on 9/1/2023.
 **/


@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticlesDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}