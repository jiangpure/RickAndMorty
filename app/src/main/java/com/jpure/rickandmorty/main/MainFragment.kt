package com.jpure.rickandmorty.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jpure.rickandmorty.R
import com.jpure.rickandmorty.adapters.MainPage2Adapter
import com.jpure.rickandmorty.adapters.Page2Adapter
import com.jpure.rickandmorty.databinding.FragmentMainBinding
import com.nice.baselibrary.base.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * @author Pure Jiang
 * @date 2021/4/8.
 */

@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainFragment:Fragment() {
    private lateinit var mBinding:FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.d("onCreateView")

        mBinding = FragmentMainBinding.inflate(inflater, container, false)
        mBinding.apply {
            mainPage.adapter = MainPage2Adapter(this@MainFragment)
            //不可滑动
            mainPage.isUserInputEnabled = false
            mainBottomNav.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.nav_home_fragment->{mainPage.setCurrentItem(0, false)}
                    R.id.nav_my_fragment->{mainPage.setCurrentItem(1, false)}
                }
                true
            }
        }

        return mBinding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d("onDestroyView")
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

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.d("onDetach")
    }
}