package com.batool.codechallenge.app.base

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By Batool Mofeed on 8/31/2023.
 **/
@SuppressLint("NotifyDataSetChanged")
abstract class BaseRecycler<M> : RecyclerView.Adapter<BaseVH>() {

    val items = ArrayList<M>()

    fun addItems(items: List<M>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: M) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = items.size
}

abstract class BaseVH(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(position: Int)
}