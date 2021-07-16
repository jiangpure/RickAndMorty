package com.jpure.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jpure.rickandmorty.data.entities.Locations
import com.jpure.rickandmorty.databinding.ItemListLocationsBinding
import com.jpure.rickandmorty.base.DataBindingViewHolder

/**
 * @author Pure Jiang
 * @date 2021/1/4.
 */
class LocationsListAdapter :PagingDataAdapter<Locations, LocationsListAdapter.DataViewHolder>(LocationsDiffCallback()) {

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val article = getItem(position)
        article?.let{
            holder.bindData(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ItemListLocationsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class DataViewHolder(
        private val binding: ItemListLocationsBinding
    ) : DataBindingViewHolder<Locations>(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.locations?.let { it ->
//                    navigateToRoleInfo(it.id, view)
                }
            }
        }

        override fun bindData(data: Locations, position: Int) {
            binding.apply {
                locations = data
//                executePendingBindings()
            }
        }
        //利用导航组件跳转
//        private fun navigateToRoleInfo(roleId: Int, view: View) {
//            val direction = RmPageFragmentDirections.actionRmPageFragmentToRoleInfoFragment(roleId)
//            view.findNavController().navigate(direction)
//        }
    }
}

//差异判断
private class LocationsDiffCallback : DiffUtil.ItemCallback<Locations>() {
    override fun areItemsTheSame(oldItem: Locations, newItem: Locations): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Locations, newItem: Locations): Boolean {
        return oldItem == newItem
    }
}
