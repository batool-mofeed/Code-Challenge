package com.batool.codechallenge.app.di

import com.batool.codechallenge.app.util.resource.ResourceProvider
import com.batool.codechallenge.app.util.resource.ResourceProviderImpl
import com.batool.codechallenge.domain.util.LocalizationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
@InstallIn(ViewModelComponent::class)
@Module
abstract class AppModule {
    @ViewModelScoped
    @Binds
    abstract fun provideResourceProvider(
        resourceProvider: ResourceProviderImpl
    ): ResourceProvider
}

/**
 * used to inject LocalizationManager property
 * in places that been processed before the Hilt
 * doing the injection
 * */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface LocalizationManagerEntryPoint {
    val localizationManager: LocalizationManager
}