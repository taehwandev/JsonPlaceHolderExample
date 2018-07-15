package tech.thdev.jsonplaceholderexample.base.adapter.data

interface AdapterDataSource {

    val size: Int

    fun getItem(position: Int): Any?

    fun removeItem(position: Int)

    fun getItemViewType(position: Int): Int

    fun addItem(viewType: Int, item: Any?)
}