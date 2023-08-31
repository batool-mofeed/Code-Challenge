package com.batool.codechallenge.domain.di

import com.batool.codechallenge.domain.usecases.GeneralUseCases
import com.batool.codechallenge.domain.usecases.GeneralUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created By Batool Mofeed on 8/31/2023.
 **/
@Module
@InstallIn(SingletonComponent::class)
abstract class AppUseCasesModule {

    @Singleton
    @Binds
    abstract fun bindGeneralUseCases(
        useCaseImpl: GeneralUseCasesImpl
    ): GeneralUseCases
}