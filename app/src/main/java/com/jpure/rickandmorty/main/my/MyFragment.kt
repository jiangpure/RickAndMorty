package com.jpure.rickandmorty.main.my

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.jpure.rickandmorty.R
import com.jpure.rickandmorty.adapters.RickAndMortyAdapter
import com.jpure.rickandmorty.databinding.FragmentMyBinding
import com.jpure.rickandmorty.databinding.FragmentRoleListBinding
import com.jpure.rickandmorty.main.home.RoleListViewModel
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
class MyFragment : Fragment() {

    private lateinit var mBinding: FragmentMyBinding
    private val mViewModel: MyViewModel by activityViewModels()
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
        mBinding = FragmentMyBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@MyFragment
            click = Click()
        }

        mIsFirst = false
        return mBinding.root
    }

    inner class Click{
        fun toSetting(){
            this@MyFragment.findNavController().navigate(R.id.action_main_fragment_to_setting_fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mIsFirst = true
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