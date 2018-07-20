package tech.thdev.jsonplaceholderexample.view.main.adapter

import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.BaseRecyclerViewAdapter
import tech.thdev.jsonplaceholderexample.base.adapter.holder.BaseViewHolder
import tech.thdev.jsonplaceholderexample.view.main.adapter.holder.PostViewHolder
import tech.thdev.jsonplaceholderexample.view.main.adapter.viewmodel.MainAdapterViewModel

class PostRecyclerAdapter :
        BaseRecyclerViewAdapter<MainAdapterViewModel>(::MainAdapterViewModel) {

    override fun createViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder<*, MainAdapterViewModel> =
            PostViewHolder(parent)
}