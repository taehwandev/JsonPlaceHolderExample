package tech.thdev.jsonplaceholderexample.view.main.adapter

import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.BaseRecyclerViewAdapter
import tech.thdev.jsonplaceholderexample.base.adapter.helper.ViewHolderCreateHelper
import tech.thdev.jsonplaceholderexample.base.adapter.holder.AndroidViewHolder
import tech.thdev.jsonplaceholderexample.view.main.adapter.holder.PostViewHolder
import tech.thdev.jsonplaceholderexample.view.main.adapter.viewmodel.MainAdapterViewModel

class PostRecyclerAdapter :
        BaseRecyclerViewAdapter<MainAdapterViewModel>(::MainAdapterViewModel) {

    override fun newCreateViewHolder(viewType: Int, parent: ViewGroup): AndroidViewHolder {
        android.util.Log.d("TEMP", "viewType $viewType")
        return ViewHolderCreateHelper.create(PostViewHolder::class.java, PostViewHolderFactory(parent))
    }

    class PostViewHolderFactory(private val parent: ViewGroup) : ViewHolderCreateHelper.Factory {

        override fun <T : AndroidViewHolder> createViewHolder(viewHolderClass: Class<T>): T {
            if (viewHolderClass.isAssignableFrom(PostViewHolder::class.java)) {
                android.util.Log.d("TEMP", "create viewHolder?????")
                return PostViewHolder(parent) as T
            } else {
                throw IllegalArgumentException("Unknown ViewHolder class")
            }
        }
    }
}