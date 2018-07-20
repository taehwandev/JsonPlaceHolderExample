package tech.thdev.jsonplaceholderexample.base.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.viewmodel.BaseAdapterViewModel

@Suppress("UNCHECKED_CAST")
abstract class BaseViewHolder<in ITEM : Any, VIEW_MODEL : BaseAdapterViewModel>(
        layoutRes: Int,
        private val parent: ViewGroup) : AndroidViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

    private lateinit var _viewModel: VIEW_MODEL

    var viewModel: VIEW_MODEL
        get() = _viewModel
        set(value) {
            _viewModel = value
            _viewModel.onInitViewModel()
        }

    protected val context: Context
        get() = parent.context

    fun checkItemAndBindViewHolder(item: Any?) {
        try {
            onBindViewHolder(item as ITEM)
        } catch (e: Exception) {
            onBindViewHolder(null)
        }
    }

    /**
     * How to use.
     *
     * viewModel.onClick.... or the others use viewMode
     */
    abstract fun VIEW_MODEL.onInitViewModel()

    /**
     * Use viewHolder
     */
    abstract fun onBindViewHolder(item: ITEM?)
}