package com.batool.codechallenge.data.di

import com.batool.codechallenge.data.repositories.GeneralRepository
import com.batool.codechallenge.data.repositories.GeneralRepositoryImpl
import com.batool.codechallenge.data.repositories.apiinterceptor.ApiInterceptorRepository
import com.batool.codechallenge.data.repositories.apiinterceptor.ApiInterceptorRepositoryImpl
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
abstract class RepositoriesModule {

    @Singleton
    @Binds
    abstract fun bindApiInterceptorRepository(
        repository: ApiInterceptorRepositoryImpl
    ): ApiInterceptorRepository


    @Singleton
    @Binds
    abstract fun bindGeneralRepository(
        repository: GeneralRepositoryImpl
    ): GeneralRepository
}