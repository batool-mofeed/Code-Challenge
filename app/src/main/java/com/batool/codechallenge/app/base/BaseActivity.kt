package com.batool.codechallenge.app.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.batool.codechallenge.app.di.LocalizationManagerEntryPoint
import com.batool.codechallenge.app.util.uiutil.buildProgressDialog
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.StateFlow

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutId: Int
    abstract val bindingVariable: Int
    abstract fun getViewModel(): BaseViewModel?
    private var internalBinding: DB? = null
    val binding: DB get() = internalBinding!!
    private var progressDialog: Dialog? = null

    override fun attachBaseContext(newBase: Context?) {
        val localizationManager = EntryPointAccessors.fromApplication(
            newBase!!, LocalizationManagerEntryPoint::class.java
        ).localizationManager
        super.attachBaseContext(localizationManager.loadSavedLocale(newBase))
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //handling data binding in activity creation
        internalBinding = DataBindingUtil.setContentView(this, layoutId)
        with(binding) {
            setVariable(bindingVariable, getViewModel())
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }
    }

    fun logoutUser() {
    }

    fun showProgressDialog(show: Boolean) {
        if (show) {
            showProgressDialog()
        } else {
            hideProgress()
        }
    }

    fun showProgressDialog() {
        progressDialog = buildProgressDialog().also { it.show() }
    }

    private fun hideProgress() {
        if (progressDialog != null) progressDialog?.dismiss()
    }

    fun <T> LiveData<T>.observe(data: (T) -> Unit) {
        this.observe(this@BaseActivity) { data(it) }
    }

    fun <T> StateFlow<T>.collectFlow(data: (T) -> Unit) {
        lifecycleScope.launchWhenCreated {
            collect(data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        internalBinding = null
    }
}