package com.batool.codechallenge.app.ui.main.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.batool.codechallenge.app.base.BaseRecycler
import com.batool.codechallenge.app.base.BaseVH
import com.batool.codechallenge.databinding.ItemTabBinding

/**
 * Created By Batool Mofeed on 9/1/2023.
 **/
class TabsAdapter(
    val onItemClick: (Int) -> Unit
) : BaseRecycler<String>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TabVH(
            ItemTabBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    var itemClicked = 0

    inner class TabVH(
        private val binding: ItemTabBinding
    ) : BaseVH(binding.root) {
        override fun bind(position: Int) {
            val item = items[position]
            binding.run {
                root.setOnClickListener {
                    itemClicked = position
                    onItemClick.invoke(position)
                }
                name = item
                indicatorView.isVisible = position == itemClicked
                executePendingBindings()
            }
        }
    }
}