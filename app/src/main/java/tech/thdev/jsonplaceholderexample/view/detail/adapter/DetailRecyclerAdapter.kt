package tech.thdev.jsonplaceholderexample.view.detail.adapter

import android.content.res.Resources
import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.BaseRecyclerViewAdapter
import tech.thdev.jsonplaceholderexample.base.adapter.holder.BaseViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.holder.CommentViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.holder.ContentViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.holder.SectionViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.viewmodel.DetailAdapterViewModel

class DetailRecyclerAdapter : BaseRecyclerViewAdapter<DetailAdapterViewModel>(::DetailAdapterViewModel) {

    override fun createViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder<*, DetailAdapterViewModel> =
            when (viewType) {
                DetailAdapterViewModel.VIEW_TYPE_SECTION -> SectionViewHolder(parent)
                DetailAdapterViewModel.VIEW_TYPE_CONTENT -> ContentViewHolder(parent)
                DetailAdapterViewModel.VIEW_TYPE_COMMENT -> CommentViewHolder(parent)
                else -> throw Resources.NotFoundException("ViewType error")
            }
}