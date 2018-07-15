package tech.thdev.jsonplaceholderexample.view.detail.adapter.holder

import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_detail_comment.*
import tech.thdev.jsonplaceholderexample.R
import tech.thdev.jsonplaceholderexample.base.adapter.holder.BaseViewHolder
import tech.thdev.jsonplaceholderexample.data.Comment
import tech.thdev.jsonplaceholderexample.view.detail.adapter.viewmodel.DetailAdapterViewModel

class CommentViewHolder(viewModel: DetailAdapterViewModel, parent: ViewGroup) :
        BaseViewHolder<Comment, DetailAdapterViewModel>(viewModel, R.layout.item_detail_comment, parent) {

    override fun onBindViewHolder(item: Comment?) {
        tv_user_name.text = item?.name
        tv_comment.text = item?.body
    }
}