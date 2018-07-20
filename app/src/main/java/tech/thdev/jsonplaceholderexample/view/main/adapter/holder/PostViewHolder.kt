package tech.thdev.jsonplaceholderexample.view.main.adapter.holder

import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_post.*
import tech.thdev.jsonplaceholderexample.R
import tech.thdev.jsonplaceholderexample.base.adapter.holder.BaseViewHolder
import tech.thdev.jsonplaceholderexample.data.Post
import tech.thdev.jsonplaceholderexample.view.main.adapter.viewmodel.MainAdapterViewModel

class PostViewHolder(parent: ViewGroup) :
        BaseViewHolder<Post, MainAdapterViewModel>(R.layout.item_post, parent) {

    override fun MainAdapterViewModel.onInitViewModel() {
        containerView.setOnClickListener {
            onClickItem(adapterPosition)
        }

        containerView.setOnLongClickListener {
            onLongClickItem(adapterPosition)
        }
    }

    override fun onBindViewHolder(item: Post?) {
        tv_title.text = item?.title
        tv_body.text = item?.body
    }
}