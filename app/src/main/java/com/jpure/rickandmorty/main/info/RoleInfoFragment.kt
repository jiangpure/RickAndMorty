package com.jpure.rickandmorty.main.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jpure.rickandmorty.R
import com.jpure.rickandmorty.databinding.FragmentRoleInfoBinding
import com.jpure.rickandmorty.base.DataBindingFragment
import com.nice.baselibrary.base.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * @author Pure Jiang
 * @date 2021/3/10.
 */
@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class RoleInfoFragment : DataBindingFragment(R.layout.fragment_role_info) {
    private val mBinding: FragmentRoleInfoBinding by binding()
    private val mViewModel: RoleInfoViewModel by activityViewModels()
    private val args: RoleInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LogUtils.d("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        mBinding.apply {
            viewModel = mViewModel
            lifecycleOwner = this@RoleInfoFragment
            toolbar.apply{
                setNavigationOnClickListener { view ->
                    view.findNavController().navigateUp()
                }
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_share -> {
                            this@RoleInfoFragment.findNavController().navigate(RoleInfoFragmentDirections.actionRoleInfoFragmentToListFragment())
                        }
                    }
                    true
                }
            }

        }

        mViewModel.loadRoleInfo(args.roleId).observe(viewLifecycleOwner, Observer {

        })

        mViewModel.failure.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.d("onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
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