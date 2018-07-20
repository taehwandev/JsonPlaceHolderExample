@file:Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")

package tech.thdev.jsonplaceholderexample.base.adapter.helper

import tech.thdev.jsonplaceholderexample.base.adapter.holder.AndroidViewHolder

/**
 * How to use.
 *
 * Create class and .create()
 *
 * ex)
 * SampleViewHolder(parent).create()
 *
 * class SampleViewHolder(parent: ViewGroup) : BaseViewHolder<Any, BaseAdapterViewModel>(layoutRes, parent) {
 *     override fun onInitViewModel() { }
 *
 *     override fun onBindViewHolder(item: String?) {
 *          tv_title.text = item
 *     }
 * }
 */

inline fun <T : AndroidViewHolder> T.create(): T =
        ViewHolderCreateHelper.create(this.javaClass, createViewHolder(this))

fun <T : AndroidViewHolder> createViewHolder(model: T) = object : ViewHolderCreateHelper.Factory {
    override fun <T : AndroidViewHolder> createViewHolder(viewHolderClass: Class<T>): T =
            model as T
}
