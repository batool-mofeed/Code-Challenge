package com.batool.codechallenge.app.ui.main.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import com.batool.codechallenge.app.base.BaseRecycler
import com.batool.codechallenge.app.base.BaseVH
import com.batool.codechallenge.data.datasource.remote.responsemodel.Article
import com.batool.codechallenge.databinding.ItemArticleBinding

/**
 * Created By Batool Mofeed on 8/31/2023.
 **/
class ArticlesAdapter(
    val onItemClick: (Article) -> Unit
) : BaseRecycler<Article>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticleVH(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    inner class ArticleVH(
        private val binding: ItemArticleBinding
    ) : BaseVH(binding.root) {
        override fun bind(position: Int) {
            binding.run {
                root.setOnClickListener {
                    onItemClick.invoke(items[position])
                }
                article = items[position]
                executePendingBindings()
            }
        }
    }
}