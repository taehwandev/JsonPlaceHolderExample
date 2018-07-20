package tech.thdev.jsonplaceholderexample.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.data.source.AdapterRepositoryInterface
import tech.thdev.jsonplaceholderexample.base.adapter.data.source.AdapterRepository
import tech.thdev.jsonplaceholderexample.base.adapter.holder.BaseViewHolder
import tech.thdev.jsonplaceholderexample.base.adapter.viewmodel.BaseAdapterViewModel

@Suppress("UNCHECKED_CAST")
abstract class BaseRecyclerViewAdapter<VIEW_MODEL : BaseAdapterViewModel>(
        createViewModel: (adapterDataSource: AdapterRepositoryInterface) -> VIEW_MODEL) : RecyclerView.Adapter<BaseViewHolder<*, VIEW_MODEL>>() {

    // Adapter data.
    private val adapterDataSource: AdapterRepositoryInterface by lazy {
        AdapterRepository()
    }

    val viewModel: VIEW_MODEL = createViewModel(adapterDataSource)

    init {
        viewModel.run {
            notifyDataSetChanged = this@BaseRecyclerViewAdapter::notifyDataSetChanged
            notifyItemChanged = this@BaseRecyclerViewAdapter::notifyItemChanged
            notifyItemRangeChanged = this@BaseRecyclerViewAdapter::notifyItemRangeChanged
            notifyItemInserted = this@BaseRecyclerViewAdapter::notifyItemInserted
            notifyItemRangeInserted = this@BaseRecyclerViewAdapter::notifyItemRangeInserted
            notifyItemRemoved = this@BaseRecyclerViewAdapter::notifyItemRemoved
            notifyItemRangeRemoved = this@BaseRecyclerViewAdapter::notifyItemRangeRemoved
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, VIEW_MODEL> =
            createViewHolder(viewType, parent).also { it.viewModel = viewModel }

    abstract fun createViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder<*, VIEW_MODEL>

    override fun getItemCount(): Int =
            adapterDataSource.itemCount

    override fun getItemViewType(position: Int): Int =
            adapterDataSource.getItemViewType(position)

    override fun onBindViewHolder(holder: BaseViewHolder<*, VIEW_MODEL>, position: Int) {
        holder.checkItemAndBindViewHolder(adapterDataSource.getItem(position))
    }
}