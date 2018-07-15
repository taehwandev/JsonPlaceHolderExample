package tech.thdev.jsonplaceholderexample.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.holder.AndroidViewHolder
import tech.thdev.jsonplaceholderexample.base.adapter.holder.BaseViewHolder
import tech.thdev.jsonplaceholderexample.base.adapter.viewmodel.SimpleAdapterViewModel

class BaseRecyclerViewAdapter<VIEW_MODEL : SimpleAdapterViewModel>(private val viewModel: VIEW_MODEL) : RecyclerView.Adapter<AndroidViewHolder>() {

    init {
        viewModel.run {
            notifyItemRangeInserted = this@BaseRecyclerViewAdapter::notifyItemRangeInserted
            notifyItemRemoved = this@BaseRecyclerViewAdapter::notifyItemRemoved
            notifyDataSetChanged = this@BaseRecyclerViewAdapter::notifyDataSetChanged
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AndroidViewHolder =
            viewModel.onCreateViewHolder(parent, viewType)

    override fun getItemCount(): Int =
            viewModel.itemCount

    override fun getItemViewType(position: Int): Int =
            viewModel.getViewType(position)

    override fun onBindViewHolder(holder: AndroidViewHolder, position: Int) {
        (holder as? BaseViewHolder<*, *>)?.checkItemAndBindViewHolder(viewModel.getItem(position))
    }
}