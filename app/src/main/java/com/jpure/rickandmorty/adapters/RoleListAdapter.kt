package com.jpure.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jpure.rickandmorty.data.entities.Role
import com.jpure.rickandmorty.databinding.ItemListRoleBinding
import com.jpure.rickandmorty.main.home.RmPageFragmentDirections
import com.jpure.rickandmorty.views.DataBindingViewHolder

/**
 * @author Jp
 * @date 2021/1/4.
 */
class RickAndMortyAdapter :PagingDataAdapter<Role, RickAndMortyAdapter.DataViewHolder>(RoleDiffCallback()) {

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val article = getItem(position)
        article?.let{
            holder.bindData(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ItemListRoleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class DataViewHolder(
        private val binding: ItemListRoleBinding
    ) : DataBindingViewHolder<Role>(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.role?.let { it ->
                    navigateToRoleInfo(it.id, view)
                }
            }
        }

        override fun bindData(data: Role, position: Int) {
            binding.apply {
                role = data
                executePendingBindings()
            }
        }
        //利用导航组件跳转
        private fun navigateToRoleInfo(roleId: Int, view: View) {
            val direction = RmPageFragmentDirections.actionRmPageFragmentToRoleInfoFragment(roleId)
            view.findNavController().navigate(direction)
        }
    }
}

//差异判断
private class RoleDiffCallback : DiffUtil.ItemCallback<Role>() {
    override fun areItemsTheSame(oldItem: Role, newItem: Role): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Role, newItem: Role): Boolean {
        return oldItem == newItem
    }
}
