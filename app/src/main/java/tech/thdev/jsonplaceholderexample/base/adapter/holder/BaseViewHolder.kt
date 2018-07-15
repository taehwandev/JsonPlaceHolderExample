package tech.thdev.jsonplaceholderexample.base.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.viewmodel.SimpleAdapterViewModel

@Suppress("UNCHECKED_CAST")
abstract class BaseViewHolder<in ITEM : Any, out VIEW_MODEL : SimpleAdapterViewModel>(
        val viewModel: VIEW_MODEL,
        layoutRes: Int,
        parent: ViewGroup) : AndroidViewHolder(
        LayoutInflater.from(viewModel.context).inflate(layoutRes, parent, false)) {

    fun checkItemAndBindViewHolder(item: Any?) {
        try {
            onBindViewHolder(item as? ITEM)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    abstract fun onBindViewHolder(item: ITEM?)
}