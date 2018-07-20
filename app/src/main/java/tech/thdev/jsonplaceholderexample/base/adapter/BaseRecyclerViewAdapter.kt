package tech.thdev.jsonplaceholderexample.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.data.source.AdapterDataSource
import tech.thdev.jsonplaceholderexample.base.adapter.data.source.AdapterRepository
import tech.thdev.jsonplaceholderexample.base.adapter.helper.ViewHolderCreateHelper
import tech.thdev.jsonplaceholderexample.base.adapter.holder.AndroidViewHolder
import tech.thdev.jsonplaceholderexample.base.adapter.holder.BaseViewHolder
import tech.thdev.jsonplaceholderexample.base.adapter.viewmodel.BaseAdapterViewModel

@Suppress("UNCHECKED_CAST")
abstract class BaseRecyclerViewAdapter<VIEW_MODEL : BaseAdapterViewModel>(
        createViewModel: (adapterDataSource: AdapterDataSource) -> VIEW_MODEL) : RecyclerView.Adapter<AndroidViewHolder>() {

    // Adapter data.
    private val adapterDataSource: AdapterDataSource by lazy {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AndroidViewHolder {
        ViewHolderCreateHelper.defaultFactory = ViewHolderCreateHelper.DefaultFactory(parent)
        return (newCreateViewHolder(viewType, parent) as BaseViewHolder<*, VIEW_MODEL>).also { it.viewModel = viewModel }
    }

    /**
     * How to createViewHolder.
     * SampleViewHolder(parent, viewModel, ...)
     *
     * Or use ViewHolderCreateHelper - Use default factory
     * ViewHolderCreateHelper.create(CommentViewHolder::class.java)
     *
     *
     * Or use ViewHolderCreateHelper and create Factory
     *
     * ViewHolderCreateHelper.create(PostViewHolder::class.java, PostViewHolderFactory(parent, viewModel))
     *
     * class PostViewHolderFactory(private val parent: ViewGroup, private val viewModel: BaseAdapterViewModel) : ViewHolderCreateHelper.Factory {
     *   override fun <T : AndroidViewHolder> createViewHolder(viewHolderClass: Class<T>): T {
     *      if (viewHolderClass.isAssignableFrom(SampleViewHolder::class.java)) {
     *          return SampleViewHolder(parent, viewModel) as T
     *      } else {
     *          throw IllegalArgumentException("Unknown ViewHolder class")
     *      }
     *   }
     * }
     *
     * Or use simple create - Use ViewHolderCreateHelperExtension.
     * ViewHolder(parent).create()
     */
    abstract fun newCreateViewHolder(viewType: Int, parent: ViewGroup): AndroidViewHolder

    override fun getItemCount(): Int =
            adapterDataSource.itemCount

    override fun getItemViewType(position: Int): Int =
            adapterDataSource.getItemViewType(position)

    override fun onBindViewHolder(holder: AndroidViewHolder, position: Int) {
        (holder as? BaseViewHolder<*, *>)?.checkItemAndBindViewHolder(adapterDataSource.getItem(position))
    }
}