package com.jpure.rickandmorty.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.jpure.rickandmorty.R
import com.jpure.rickandmorty.adapters.RickAndMortyAdapter
import com.jpure.rickandmorty.databinding.FragmentHomeBinding
import com.jpure.rickandmorty.views.DataBindingFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest

/**
 * @author Jp
 * @date 2021/2/25.
 */
@FlowPreview
@ExperimentalCoroutinesApi
class HomeFragment : DataBindingFragment(R.layout.fragment_home) {

    private val mBinding:FragmentHomeBinding by binding()
    private val viewModel: HomeViewModel by activityViewModels()
    private val adapter by lazy { RickAndMortyAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.apply {
            roleList.adapter = adapter
            mBinding.lifecycleOwner = this@HomeFragment
        }
        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    Log.d("pipa", "is NotLoading")

                }
                is LoadState.Loading -> {
                    Log.d("pipa", "is Loading")
                }
                is LoadState.Error -> {
                    Log.d("pipa", "is Error")
                }
            }
        }
        loadData()
    }

    private fun loadData() {
        viewModel.loadRoleList().observe(viewLifecycleOwner,{
            adapter.submitData(lifecycle, it)
            mBinding.swipeRefresh.isEnabled = false
        })
        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { state ->
                mBinding.swipeRefresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}