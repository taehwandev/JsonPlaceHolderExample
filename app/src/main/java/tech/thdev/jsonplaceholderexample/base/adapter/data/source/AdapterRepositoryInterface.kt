package tech.thdev.jsonplaceholderexample.base.adapter.data.source

interface AdapterRepositoryInterface {

    val itemCount: Int

    fun <ITEM : Any> getItem(position: Int): ITEM?

    fun removeAt(position: Int)

    fun getItemViewType(position: Int): Int

    fun addItem(viewType: Int, item: Any?)

    fun addItems(viewType: Int, item: List<Any?>?)
}