package tech.thdev.jsonplaceholderexample.view.detail.adapter.holder

import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_detail_content.*
import tech.thdev.jsonplaceholderexample.R
import tech.thdev.jsonplaceholderexample.base.adapter.holder.BaseViewHolder
import tech.thdev.jsonplaceholderexample.data.Post
import tech.thdev.jsonplaceholderexample.view.detail.adapter.viewmodel.DetailAdapterViewModel

class ContentViewHolder(parent: ViewGroup) :
        BaseViewHolder<Post, DetailAdapterViewModel>(R.layout.item_detail_content, parent) {

    override fun DetailAdapterViewModel.onInitViewModel() {

    }

    override fun onBindViewHolder(item: Post?) {
        tv_body.text = item?.body
    }
}