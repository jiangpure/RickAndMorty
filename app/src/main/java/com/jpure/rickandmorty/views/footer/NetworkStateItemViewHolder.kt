package com.jpure.rickandmorty.views.footer

import android.view.View
import androidx.paging.LoadState
import com.jpure.rickandmorty.databinding.ItemRecyclerNetStateBinding
import com.jpure.rickandmorty.views.DataBindingViewHolder
import com.nice.baselibrary.base.utils.showNormalToast


class NetworkStateItemViewHolder(view: View, private val retryCallback: () -> Unit) :
    DataBindingViewHolder<LoadState>(view) {

     private val mBinding: ItemRecyclerNetStateBinding by viewHolderBinding(view)

    override fun bindData(data: LoadState, position: Int) {
        mBinding.apply {
            // 正在加载，显示进度条
            pbLoad.isVisible = data is LoadState.Loading
            // 加载失败，显示并点击重试按钮
            btnRetry.isVisible = data is LoadState.Error
            btnRetry.setOnClickListener { retryCallback() }
            // 加载失败显示错误原因
            if(!(data as? LoadState.Error)?.error?.message.isNullOrBlank()){
                context().showNormalToast((data as? LoadState.Error)?.error?.message?:"unknown")
            }
            //新版不用手动去调
//            executePendingBindings()
        }
    }
}

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }