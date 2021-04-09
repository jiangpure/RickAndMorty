package com.jpure.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jpure.rickandmorty.data.entities.Episode
import com.jpure.rickandmorty.databinding.ItemListEpisodeBinding
import com.jpure.rickandmorty.views.DataBindingViewHolder

/**
 * @author Jp
 * @date 2021/1/4.
 */
class EpisodeListAdapter :PagingDataAdapter<Episode, EpisodeListAdapter.DataViewHolder>(EpisodeDiffCallback()) {

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val article = getItem(position)
        article?.let{
            holder.bindData(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ItemListEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class DataViewHolder(
        private val binding: ItemListEpisodeBinding
    ) : DataBindingViewHolder<Episode>(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.episode?.let { it ->
//                    navigateToRoleInfo(it.id, view)
                }
            }
        }

        override fun bindData(data: Episode, position: Int) {
            binding.apply {
                episode = data
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
private class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}
