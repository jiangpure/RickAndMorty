package com.jpure.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jpure.rickandmorty.data.entities.Role
import com.jpure.rickandmorty.databinding.ListItemRoleBinding

/**
 * @author Jp
 * @date 2021/1/4.
 */
class RickAndMortyAdapter :PagingDataAdapter<Role, RickAndMortyAdapter.DataViewHolder>(GalleryDiffCallback()) {


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bind(article)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            ListItemRoleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class DataViewHolder(
        private val binding: ListItemRoleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { _ ->
                binding.role?.let { _ ->
//                    val uri = Uri.parse(photo.user.attributionUrl)
//                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                    view.context.startActivity(intent)
                }
            }
        }

        fun bind(item: Role) {
            binding.apply {
                role = item
                executePendingBindings()
            }
        }
    }

}


private class GalleryDiffCallback : DiffUtil.ItemCallback<Role>() {
    override fun areItemsTheSame(oldItem: Role, newItem: Role): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Role, newItem: Role): Boolean {
        return oldItem == newItem
    }
}
