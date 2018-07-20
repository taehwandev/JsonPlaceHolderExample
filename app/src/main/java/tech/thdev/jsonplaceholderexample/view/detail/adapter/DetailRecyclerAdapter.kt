package tech.thdev.jsonplaceholderexample.view.detail.adapter

import android.content.res.Resources
import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.BaseRecyclerViewAdapter
import tech.thdev.jsonplaceholderexample.base.adapter.helper.ViewHolderCreateHelper
import tech.thdev.jsonplaceholderexample.base.adapter.helper.create
import tech.thdev.jsonplaceholderexample.base.adapter.holder.AndroidViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.holder.CommentViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.holder.ContentViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.holder.SectionViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.viewmodel.DetailAdapterViewModel

class DetailRecyclerAdapter : BaseRecyclerViewAdapter<DetailAdapterViewModel>(::DetailAdapterViewModel) {

    override fun newCreateViewHolder(viewType: Int, parent: ViewGroup): AndroidViewHolder =
            when (viewType) {
                DetailAdapterViewModel.VIEW_TYPE_SECTION -> SectionViewHolder(parent) /*ViewHolderCreateHelper.create(SectionViewHolder::class.java)*/
                DetailAdapterViewModel.VIEW_TYPE_CONTENT -> ViewHolderCreateHelper.create(ContentViewHolder::class.java, ContentViewHolderFactory(parent))
                DetailAdapterViewModel.VIEW_TYPE_COMMENT -> CommentViewHolder(parent).create()
                else -> throw Resources.NotFoundException("ViewType error")
            }

    class ContentViewHolderFactory(private val parent: ViewGroup) : ViewHolderCreateHelper.Factory {

        override fun <T : AndroidViewHolder> createViewHolder(viewHolderClass: Class<T>): T {
            if (viewHolderClass.isAssignableFrom(ContentViewHolder::class.java)) {
                android.util.Log.d("TEMP", "create viewHolder?????")
                return ContentViewHolder(parent) as T
            } else {
                throw IllegalArgumentException("Unknown ViewHolder class")
            }
        }
    }
}