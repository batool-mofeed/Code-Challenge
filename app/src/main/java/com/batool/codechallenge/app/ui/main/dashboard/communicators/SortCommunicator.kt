package com.batool.codechallenge.app.ui.main.dashboard.communicators

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Created By Batool Mofeed on 9/1/2023.
 **/
object SortCommunicator {

    private val sort = MutableLiveData(false)

    private lateinit var observer: Observer<Boolean>

    fun postSort(value: Boolean) {
        sort.value = value
    }

    //observe
    fun observeSort(reloadNow: (Boolean) -> Unit) {
        observer = Observer<Boolean> { it ->
            reloadNow(it)
        }
        sort.observeForever(observer)
    }

    //remove observer when the client finishing its work
    fun removeObserver() {
        sort.removeObserver(observer)
    }
}