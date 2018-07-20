package tech.thdev.jsonplaceholderexample.view.detail.adapter.holder

import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_detail_section.*
import tech.thdev.jsonplaceholderexample.R
import tech.thdev.jsonplaceholderexample.base.adapter.holder.BaseViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.viewmodel.DetailAdapterViewModel

class SectionViewHolder(parent: ViewGroup) :
        BaseViewHolder<String, DetailAdapterViewModel>(R.layout.item_detail_section, parent) {

    override fun DetailAdapterViewModel.onInitViewModel() {

    }

    override fun onBindViewHolder(item: String?) {
        tv_title.text = item
    }
}