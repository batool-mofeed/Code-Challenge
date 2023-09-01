package com.batool.codechallenge.app.ui.main.dashboard.adapters

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.batool.codechallenge.app.ui.main.dashboard.section.SectionFragment
import com.batool.codechallenge.data.datasource.remote.responsemodel.Article

/**
 * Created By Batool Mofeed on 9/1/2023.
 **/
class SectionPagerAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val sectionsCount: Int,
    private val sections: List<List<Article>>,
) : FragmentStateAdapter(manager, lifecycle) {

    override fun getItemCount() = sectionsCount

    override fun createFragment(position: Int) = SectionFragment.newInstance(sections[position])

}
