package com.jpure.rickandmorty.main.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.gson.Gson
import com.jpure.rickandmorty.adapters.RickAndMortyAdapter
import com.jpure.rickandmorty.databinding.FragmentRoleListBinding
import com.jpure.rickandmorty.views.footer.FooterAdapter
import com.nice.baselibrary.base.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest

/**
 * @author Pure Jiang
 * @date 2021/2/25.
 */
@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class RoleListFragment : Fragment() {

    private lateinit var mBinding: FragmentRoleListBinding
    private val mViewModel: RoleListViewModel by activityViewModels()
    private val mAdapter = RickAndMortyAdapter()
    private var mIsFirst = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        LogUtils.d("onCreateView")
        if (!mIsFirst) {
            return mBinding.root
        }
        mBinding = FragmentRoleListBinding.inflate(inflater, container, false)

        mBinding.apply {
            //添加底部加载状态item
            roleListRecycle.adapter = mAdapter.withLoadStateFooter(FooterAdapter() {
                mAdapter.retry()
            })
            lifecycleOwner = this@RoleListFragment
            roleRefresh.setOnRefreshListener {
                loadData()
            }
        }
        loadData()
        mIsFirst = false
        return mBinding.root
    }


    private fun loadData() {
        lifecycleScope.launchWhenCreated {
            mAdapter.loadStateFlow.collectLatest { state ->
                mBinding.roleRefresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launchWhenCreated {
            mViewModel.loadRoleList().collectLatest {
                mAdapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d("onDestroyView")
    }
    override fun onDestroy() {
        super.onDestroy()
        mIsFirst = true
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