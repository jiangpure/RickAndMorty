package com.jpure.rickandmorty.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.jpure.rickandmorty.adapters.LocationsListAdapter
import com.jpure.rickandmorty.databinding.FragmentLocationsListBinding
import com.jpure.rickandmorty.databinding.FragmentRoleListBinding
import com.jpure.rickandmorty.views.footer.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest

/**
 * @author Jp
 * @date 2021/3/30.
 */
@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class LocationListFragment : Fragment() {
    private lateinit var mBinding: FragmentLocationsListBinding

    private val mViewModel: LocationListViewModel by activityViewModels()
    private var mIsFirst = true
    private val mAdapter by lazy { LocationsListAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!mIsFirst) {
            return mBinding.root
        }
        mBinding = FragmentLocationsListBinding.inflate(inflater, container, false)

        mBinding.apply {
            //添加底部加载状态item
            locationListRecycle.adapter = mAdapter.withLoadStateFooter(FooterAdapter() {
                mAdapter.retry()
            })
            lifecycleOwner = this@LocationListFragment
            locationRefresh.setOnRefreshListener {
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
                mBinding.locationRefresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }
        lifecycleScope.launchWhenCreated {
            mViewModel.getLocationList().collectLatest {
                mAdapter.submitData(lifecycle, it)
            }
        }
    }

}