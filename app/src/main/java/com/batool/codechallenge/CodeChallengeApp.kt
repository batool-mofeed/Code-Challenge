package com.batool.codechallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
@HiltAndroidApp
class CodeChallengeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}