package com.batool.codechallenge.data.di

import android.content.Context
import androidx.room.Room
import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManager
import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManagerImpl
import com.batool.codechallenge.data.datasource.local.room.ArticlesDatabase
import com.batool.codechallenge.data.datasource.local.room.dao.ArticleDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
@InstallIn(SingletonComponent::class)
@Module
abstract class LocalModule {

    @Singleton
    @Binds
    abstract fun providePreferencesManager(
        preferencesManagerImpl: PreferencesManagerImpl
    ): PreferencesManager
}

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideChannelDao(appDatabase: ArticlesDatabase): ArticleDao {
        return appDatabase.articleDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ArticlesDatabase {
        return Room.databaseBuilder(
            appContext,
            ArticlesDatabase::class.java,
            "articles_database"
        ).allowMainThreadQueries().build()
    }
}