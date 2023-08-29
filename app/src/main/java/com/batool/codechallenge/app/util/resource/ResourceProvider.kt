package com.batool.codechallenge.app.util.resource

import android.content.Context

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
interface ResourceProvider {
    fun provideString(stringId: Int): String
    fun provideAppContext(): Context
}