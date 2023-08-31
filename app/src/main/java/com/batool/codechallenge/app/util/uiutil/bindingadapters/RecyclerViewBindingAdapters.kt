package com.batool.codechallenge.app.util.uiutil.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.batool.codechallenge.app.base.BaseRecycler

/**
 * Created By Batool Mofeed on 8/31/2023.
 **/
object RecyclerViewBindingAdapters {
    /**
     * Used With base recycler view adapter
     * */
    @BindingAdapter("items")
    @JvmStatic
    fun <Any> addItems(recyclerView: RecyclerView, items: List<Any>?) {
        if (items != null) {
            val adapter = recyclerView.adapter as? BaseRecycler<Any>
            adapter?.clearItems()
            items.let { adapter?.addItems(it) }
        }
    }
}