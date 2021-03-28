package com.jpure.rickandmorty.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.jpure.rickandmorty.adapters.RickAndMortyAdapter
import com.jpure.rickandmorty.databinding.FragmentRoleListBinding
import com.jpure.rickandmorty.views.footer.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest

/**
 * @author Jp
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
        if(!mIsFirst){
            return mBinding.root
        }
        mBinding = FragmentRoleListBinding.inflate(inflater, container, false)

        mBinding.apply {
            //添加底部加载状态item
            roleListRecycle.adapter = mAdapter.withLoadStateFooter(FooterAdapter(mAdapter))
            lifecycleOwner = this@RoleListFragment
        }
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
        mIsFirst = false
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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