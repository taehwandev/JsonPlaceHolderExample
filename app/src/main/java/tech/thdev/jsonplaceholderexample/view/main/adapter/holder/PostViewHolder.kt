package tech.thdev.jsonplaceholderexample.view.main.adapter.holder

import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_post.*
import tech.thdev.jsonplaceholderexample.R
import tech.thdev.jsonplaceholderexample.base.adapter.holder.BaseViewHolder
import tech.thdev.jsonplaceholderexample.data.Post
import tech.thdev.jsonplaceholderexample.view.main.adapter.viewmodel.MainAdapterViewModel

class PostViewHolder(viewModel: MainAdapterViewModel, parent: ViewGroup) :
        BaseViewHolder<Post, MainAdapterViewModel>(viewModel, R.layout.item_post, parent) {

    init {
        containerView.setOnClickListener {
            viewModel.onClickItem(adapterPosition)
        }

        containerView.setOnLongClickListener {
            viewModel.onLongClickItem(adapterPosition)
            false
        }
    }

    override fun onBindViewHolder(item: Post?) {
        tv_title.text = item?.title
        tv_body.text = item?.body
    }
}