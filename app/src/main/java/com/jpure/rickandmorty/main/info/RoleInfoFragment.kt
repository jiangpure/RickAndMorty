package com.jpure.rickandmorty.main.info

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.jpure.rickandmorty.R
import com.jpure.rickandmorty.databinding.FragmentRoleInfoBinding
import com.jpure.rickandmorty.views.DataBindingFragment
import com.jpure.rickandmorty.views.footer.FooterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_role_info.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * @author Jp
 * @date 2021/3/10.
 */
@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class RoleInfoFragment:DataBindingFragment(R.layout.fragment_role_info) {
    private val mBinding:FragmentRoleInfoBinding by binding()
    private val mViewModel:RoleInfoViewModel by activityViewModels()
    private val args:RoleInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.apply {
            viewModel = mViewModel
            lifecycleOwner = this@RoleInfoFragment
            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
        }
        mViewModel.loadRoleInfo(args.roleId).observe(viewLifecycleOwner, Observer {

        })
        mViewModel.failure.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

}