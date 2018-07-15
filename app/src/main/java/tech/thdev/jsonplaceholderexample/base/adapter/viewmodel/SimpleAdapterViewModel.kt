package tech.thdev.jsonplaceholderexample.base.adapter.viewmodel

import android.content.Context
import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.BaseRecyclerViewAdapter
import tech.thdev.jsonplaceholderexample.base.adapter.data.AdapterDataSource
import tech.thdev.jsonplaceholderexample.base.adapter.data.AdapterRepository
import tech.thdev.jsonplaceholderexample.base.adapter.holder.AndroidViewHolder

abstract class SimpleAdapterViewModel(
        val context: Context,
        private val adapterDataSource: AdapterDataSource = AdapterRepository()) {

    lateinit var onCreateViewHolder: (parent: ViewGroup, position: Int) -> AndroidViewHolder

    lateinit var notifyItemRangeInserted: (positionStart: Int, itemCount: Int) -> Unit

    lateinit var notifyItemRemoved: (position: Int) -> Unit

    val adapter: BaseRecyclerViewAdapter<*> by lazy {
        BaseRecyclerViewAdapter(this@SimpleAdapterViewModel)
    }

    init {
    }

    fun addItems(viewType: Int, items: List<Any?>?) {
        items?.forEach {
            adapterDataSource.addItem(viewType, it)
        }
    }

    val itemCount: Int
        get() = adapterDataSource.size

    fun getViewType(position: Int): Int =
            adapterDataSource.getItemViewType(position)

    fun getItem(position: Int): Any? =
            adapterDataSource.getItem(position)

    fun removeAt(position: Int) {
        adapterDataSource.removeItem(position)
    }
}