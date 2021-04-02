package com.jpure.rickandmorty.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.jpure.rickandmorty.R
import com.jpure.rickandmorty.adapters.EPISODE_PAGE_INDEX
import com.jpure.rickandmorty.adapters.LOCATION_PAGE_INDEX
import com.jpure.rickandmorty.adapters.Page2Adapter
import com.jpure.rickandmorty.adapters.ROLE_PAGE_INDEX
import com.jpure.rickandmorty.databinding.FragmentRmPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


/**
 * @author Jp
 * @date 2021/3/30.
 */
@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class RmPageFragment : Fragment() {
    private lateinit var mBinding: FragmentRmPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentRmPageBinding.inflate(inflater, container, false)
        mBinding.apply {
            pages.adapter = Page2Adapter(this@RmPageFragment)
            TabLayoutMediator(tabs, pages) { tab, position ->
                tab.text = getTabTitle(position)
            }.attach()
        }

        return mBinding.root
    }
//    private fun getTabIcon(position: Int): Int {
//        return when (position) {
//            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
//            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
//            else -> throw IndexOutOfBoundsException()
//        }
//    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            ROLE_PAGE_INDEX -> getString(R.string.tab_item_roles)
            LOCATION_PAGE_INDEX -> getString(R.string.tab_item_locations)
            EPISODE_PAGE_INDEX -> getString(R.string.tab_item_episodes)
            else -> null
        }
    }
}