package com.jpure.rickandmorty.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jpure.rickandmorty.main.home.EpisodeListFragment
import com.jpure.rickandmorty.main.home.HomePageFragment
import com.jpure.rickandmorty.main.home.LocationListFragment
import com.jpure.rickandmorty.main.home.RoleListFragment
import com.jpure.rickandmorty.main.my.MyFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * @author Pure Jiang
 * @date 2021/3/30.
 */
const val HOME_PAGE_INDEX = 0
const val MY_PAGE_INDEX = 1

@Deprecated("delete")
@FlowPreview
@ExperimentalCoroutinesApi
class MainPage2Adapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        HOME_PAGE_INDEX to { HomePageFragment() },
        MY_PAGE_INDEX to { MyFragment() },
    )

    override fun getItemCount() = tabFragmentsCreators.size


    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}