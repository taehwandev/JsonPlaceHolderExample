@file:Suppress("UNCHECKED_CAST")

package tech.thdev.jsonplaceholderexample.base.adapter.helper

import android.view.ViewGroup
import tech.thdev.jsonplaceholderexample.base.adapter.holder.AndroidViewHolder

class ViewHolderCreateHelper {

    companion object {

        lateinit var defaultFactory: Factory

        /**
         * Use ViewHolderCreateHelper - Use default factory
         * ViewHolderCreateHelper.create(CommentViewHolder::class.java)
         */
        fun <T : AndroidViewHolder> create(viewHolderClass: Class<T>, factory: Factory? = null): T {
            return (factory ?: defaultFactory).createViewHolder(viewHolderClass)
        }
    }

    class DefaultFactory(private val parent: ViewGroup) : Factory {

        override fun <T : AndroidViewHolder> createViewHolder(viewHolderClass: Class<T>): T {
            try {
                return viewHolderClass.getConstructor(ViewGroup::class.java).newInstance(parent)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $viewHolderClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $viewHolderClass", e)
            }
        }
    }

    /**
     * Use ViewHolderCreateHelper and create Factory
     *
     * ViewHolderCreateHelper.create(PostViewHolder::class.java, PostViewHolderFactory(parent, viewModel))
     *
     * class PostViewHolderFactory(private val parent: ViewGroup, private val viewModel: BaseAdapterViewModel) : ViewHolderCreateHelper.Factory {
     *   override fun <T : AndroidViewHolder> createViewHolder(viewHolderClass: Class<T>): T {
     *      if (viewHolderClass.isAssignableFrom(SampleViewHolder::class.java)) {
     *          return SampleViewHolder(parent, viewModel) as T
     *      } else {
     *          throw IllegalArgumentException("Unknown ViewHolder class")
     *      }
     *   }
     * }
     */
    interface Factory {

        fun <T : AndroidViewHolder> createViewHolder(viewHolderClass: Class<T>): T
    }
}