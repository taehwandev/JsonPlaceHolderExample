package tech.thdev.jsonplaceholderexample.base.adapter.data.source

import tech.thdev.jsonplaceholderexample.base.adapter.data.AdapterData

class AdapterRepository : AdapterRepositoryInterface {

    private val list = mutableListOf<AdapterData>()

    override val itemCount: Int
        get() = list.size

    override fun getItemViewType(position: Int): Int =
            list[position].viewType

    override fun addItems(viewType: Int, item: List<Any?>?) {
        item?.forEach {
            addItem(viewType, it)
        }
    }

    override fun <ITEM : Any> getItem(position: Int): ITEM? =
            list[position].item as? ITEM

    override fun removeAt(position: Int) {
        list.removeAt(position)
    }

    override fun addItem(viewType: Int, item: Any?) {
        list.add(AdapterData(viewType, item))
    }
}