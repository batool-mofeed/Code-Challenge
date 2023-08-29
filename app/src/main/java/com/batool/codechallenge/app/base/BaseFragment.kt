package com.batool.codechallenge.app.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.batool.codechallenge.app.util.uiutil.buildProgressDialog
import com.batool.codechallenge.app.util.uiutil.toast
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created By Batool Mofeed on 8/29/2023.
 **/
abstract class BaseFragment <DB : ViewDataBinding> : Fragment() {

    private var internalBinding: DB? = null
    val binding: DB get() = internalBinding!!
    abstract val layoutId: Int
    abstract val bindingVariable: Int
    abstract fun getViewModel(): BaseViewModel?

    private var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        internalBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        with(internalBinding!!) {
            lifecycleOwner = viewLifecycleOwner
            setVariable(bindingVariable, getViewModel())
            executePendingBindings()
        }
        return binding.root
    }

    fun toast(stringId: Int) {
        requireActivity().toast(stringId)
    }

    fun toast(string: String) {
        requireActivity().toast(string)
    }

    fun <T> LiveData<T>.observe(data: (T) -> Unit) {
        this.observe(viewLifecycleOwner) { data(it) }
    }

    fun <T> StateFlow<T>.collectFlow(data: (T) -> Unit) {
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                collect(data)
            }
        }
    }

    fun showProgressDialog(show: Boolean) {
        if (show) {
            showProgressDialog()
        } else {
            hideProgressDialog()
        }
    }

    fun showProgressDialog() {
        try {
            hideProgressDialog()
            progressDialog =
                requireActivity().buildProgressDialog()
            progressDialog?.show()
        } catch (e: java.lang.Exception) {
            Timber.e("${e.message}")
        }
    }

    fun hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        internalBinding = null
    }

}