package tech.thdev.jsonplaceholderexample.view.detail.adapter.viewmodel

import android.content.Context
import tech.thdev.jsonplaceholderexample.base.adapter.viewmodel.SimpleAdapterViewModel

class DetailAdapterViewModel(context: Context) : SimpleAdapterViewModel(context = context) {

    companion object {
        const val VIEW_TYPE_SECTION = 0
        const val VIEW_TYPE_CONTENT = 1000
        const val VIEW_TYPE_COMMENT = 2000
    }
}