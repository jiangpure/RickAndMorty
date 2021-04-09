package com.jpure.rickandmorty.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jpure.rickandmorty.main.home.EpisodeListFragment
import com.jpure.rickandmorty.main.home.LocationListFragment
import com.jpure.rickandmorty.main.home.RoleListFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * @author Jp
 * @date 2021/3/30.
 */
const val ROLE_PAGE_INDEX = 0
const val LOCATION_PAGE_INDEX = 1
const val EPISODE_PAGE_INDEX = 2

@FlowPreview
@ExperimentalCoroutinesApi
class Page2Adapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        ROLE_PAGE_INDEX to { RoleListFragment() },
        LOCATION_PAGE_INDEX to { LocationListFragment() },
        EPISODE_PAGE_INDEX to { EpisodeListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size


    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}