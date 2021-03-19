package com.jpure.rickandmorty.adapters


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nice.baselibrary.base.adapter.BaseAdapter


/**
 * recyclerView适配器的上拉加载装饰器
 * @author JPlus
 * @date 2020/5/3.
 */
class LoadAdapterWrapper<T>(
    private val mAdapter: BaseAdapter<T>,
    private val mListener: CreatedListener
) : RecyclerView.Adapter<BaseAdapter.VH>() {
    internal enum class ItemType {
        FOOTER,
        NORMAL,
        EMPTY
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            mAdapter.itemCount -> ItemType.FOOTER.ordinal
            else -> ItemType.NORMAL.ordinal
        }
    }

    fun addItems(items: MutableList<T>) {
        mAdapter.addItems(items)
        notifyDataSetChanged()//这里要刷新装饰器的数据，而不是传进来的适配器的数据
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapter.VH {
        return when (viewType) {
            ItemType.FOOTER.ordinal -> BaseAdapter.VH(mListener.setFootView(parent))
            else -> mAdapter.onCreateViewHolder(parent, viewType)
        }
    }

    override fun getItemCount(): Int {
        return mAdapter.itemCount + 1
    }

    override fun onBindViewHolder(holder: BaseAdapter.VH, position: Int, payloads: MutableList<Any>) {
        when (position) {
            mAdapter.itemCount -> {
                mListener.bindFootView()
                return
            }
            else -> mAdapter.onBindViewHolder(holder, position, payloads)//这里使用原本adapter的onBindViewHolder才能保持不变
        }
    }

    override fun onBindViewHolder(holder: BaseAdapter.VH, position: Int) {

    }

    interface CreatedListener {
        /**
         * 配置结尾处的View
         */
        fun setFootView(parent: ViewGroup): View

        /**
         * 展示footView时
         */
        fun bindFootView()
    }

}