package com.batool.codechallenge.data.di

import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManager
import com.batool.codechallenge.data.datasource.local.preferences.PreferencesManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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