package tech.thdev.jsonplaceholderexample.view.main.adapter.viewmodel

import android.content.Context
import tech.thdev.jsonplaceholderexample.base.adapter.viewmodel.SimpleAdapterViewModel

class MainAdapterViewModel(context: Context) : SimpleAdapterViewModel(context = context) {

    companion object {
        const val VIEW_TYPE_POST = 0
    }

    lateinit var onClickItem: (position: Int) -> Unit

    lateinit var onLongClickItem: (position: Int) -> Unit
}