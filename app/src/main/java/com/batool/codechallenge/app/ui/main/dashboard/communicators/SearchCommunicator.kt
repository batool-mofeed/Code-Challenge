package com.batool.codechallenge.app.ui.main.dashboard.communicators

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Created By Batool Mofeed on 9/1/2023.
 **/
object SearchCommunicator {

    private val search = MutableLiveData<String?>(null)

    private lateinit var observer: Observer<String?>

    fun postSearch(value: String?) {
        search.value = value
    }

    //observe
    fun observeSearch(reloadNow: (String?) -> Unit) {
        observer = Observer { it ->
            reloadNow(it)
        }
        search.observeForever(observer)
    }

    //remove observer when the client finishing its work
    fun removeObserver() {
        search.removeObserver(observer)
    }
}