package tech.thdev.jsonplaceholderexample.view.detail.adapter.viewmodel

import tech.thdev.jsonplaceholderexample.base.adapter.data.source.AdapterDataSource
import tech.thdev.jsonplaceholderexample.base.adapter.viewmodel.BaseAdapterViewModel

class DetailAdapterViewModel(adapterDataSource: AdapterDataSource) : BaseAdapterViewModel(adapterDataSource) {

    companion object {
        const val VIEW_TYPE_SECTION = 0
        const val VIEW_TYPE_CONTENT = 1000
        const val VIEW_TYPE_COMMENT = 2000
    }
}