package tech.thdev.jsonplaceholderexample.view.main.adapter.viewmodel

import tech.thdev.jsonplaceholderexample.base.adapter.data.source.AdapterRepositoryInterface
import tech.thdev.jsonplaceholderexample.base.adapter.viewmodel.BaseAdapterViewModel
import tech.thdev.jsonplaceholderexample.data.Post

class MainAdapterViewModel(adapterDataSource: AdapterRepositoryInterface) : BaseAdapterViewModel(adapterDataSource) {

    companion object {
        const val VIEW_TYPE_POST = 0
    }

    lateinit var showOptionPopup: (adapterPosition: Int, postTitle: String) -> Unit
    lateinit var showDetailPage: (postId: Long) -> Unit

    fun onClickItem(adapterPosition: Int) {
        adapterDataSource.getItem<Post>(adapterPosition)?.let {
            showDetailPage(it.id)
        }
    }

    fun onLongClickItem(adapterPosition: Int): Boolean =
            adapterDataSource.getItem<Post>(adapterPosition)?.let {
                showOptionPopup(adapterPosition, it.title)
                false
            } ?: true
}