package com.jpure.rickandmorty.main.home

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.jpure.rickandmorty.databinding.FragmentHomePageBinding
import com.nice.baselibrary.base.utils.LogUtils
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
class HomePageFragment : Fragment() {
    private lateinit var mBinding: FragmentHomePageBinding
    private var mIsFirst = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.d("onCreateView")
        mBinding = FragmentHomePageBinding.inflate(inflater, container, false)
        mBinding.apply {
            pages.adapter = Page2Adapter(this@HomePageFragment)
            TabLayoutMediator(tabs, pages) { tab, position ->
                tab.text = getTabTitle(position)
            }.attach()
        }
        return mBinding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            ROLE_PAGE_INDEX -> getString(R.string.tab_item_roles)
            LOCATION_PAGE_INDEX -> getString(R.string.tab_item_locations)
            EPISODE_PAGE_INDEX -> getString(R.string.tab_item_episodes)
            else -> null
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d("onDestroyView")
    }
    override fun onDestroy() {
        super.onDestroy()

        LogUtils.d("onDestroy")
    }
    override fun onPause() {
        LogUtils.d("onPause")
        super.onPause()
    }

    override fun onResume() {
        LogUtils.d("onResume")
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtils.d("onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LogUtils.d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        LogUtils.d("onAttach")
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        LogUtils.d("onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }
}