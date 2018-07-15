package tech.thdev.jsonplaceholderexample.base.adapter.data

@Suppress("UNCHECKED_CAST")
class AdapterRepository : AdapterDataSource {

    private val list = mutableListOf<AdapterData>()

    override val size: Int
        get() = list.size

    override fun getItemViewType(position: Int): Int =
            list[position].viewType

    override fun getItem(position: Int): Any? =
            list[position].item

    override fun removeItem(position: Int) {
        list.removeAt(position)
    }

    override fun addItem(viewType: Int, item: Any?) {
        list.add(AdapterData(viewType, item))
    }
}