package com.jpure.rickandmorty.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.jpure.rickandmorty.R
import com.jpure.rickandmorty.adapters.RickAndMortyAdapter
import com.jpure.rickandmorty.databinding.FragmentRoleListBinding
import com.jpure.rickandmorty.views.DataBindingFragment
import com.jpure.rickandmorty.views.footer.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_role_info.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @author Jp
 * @date 2021/2/25.
 */
@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class RoleListFragment : DataBindingFragment(R.layout.fragment_role_list) {

    private val mBinding: FragmentRoleListBinding by binding()
    private val mViewModel: RoleListViewModel by activityViewModels()
    private val mAdapter = RickAndMortyAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.apply {
            //添加底部加载状态item
            roleListRecycle.adapter = mAdapter.withLoadStateFooter(FooterAdapter(mAdapter))
        }
//        Log.d("pipa", "onViewCreated, mBinding:"+mBinding+",roleListRecycle "+mBinding.roleListRecycle)
        mAdapter.addLoadStateListener {
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

        mViewModel.loadRoleList().observe(viewLifecycleOwner, {

            mAdapter.submitData(lifecycle, it)
            mBinding.swipeRefresh.isEnabled = false
        })
        lifecycleScope.launchWhenCreated {
            mAdapter.loadStateFlow.collectLatest { state ->
                mBinding.swipeRefresh.isRefreshing = state.refresh is LoadState.Loading
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}