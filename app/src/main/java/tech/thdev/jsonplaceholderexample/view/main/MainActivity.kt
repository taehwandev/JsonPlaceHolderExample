package tech.thdev.jsonplaceholderexample.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import tech.thdev.jsonplaceholderexample.R
import tech.thdev.jsonplaceholderexample.data.source.post.PostsDataRepository
import tech.thdev.jsonplaceholderexample.util.createDialog
import tech.thdev.jsonplaceholderexample.util.inject
import tech.thdev.jsonplaceholderexample.view.main.adapter.holder.PostViewHolder
import tech.thdev.jsonplaceholderexample.view.main.adapter.viewmodel.MainAdapterViewModel
import tech.thdev.jsonplaceholderexample.view.main.viewmodel.PostsViewModel

class MainActivity : AppCompatActivity() {

    private val mainAdapterViewModel: MainAdapterViewModel by lazy {
        MainAdapterViewModel(this@MainActivity).apply {
            onCreateViewHolder = { parent: ViewGroup, _: Int ->
                PostViewHolder(this, parent)
            }
        }
    }

    private val postsViewModel: PostsViewModel by lazy {
        PostsViewModel(PostsDataRepository, mainAdapterViewModel).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        postsViewModel.init()

        postsViewModel.loadPosts()
    }

    private fun initView() {
        recycler_view.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapterViewModel.adapter

            addOnScrollListener(scrollListener)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        recycler_view?.removeOnScrollListener(scrollListener)
    }

    private fun PostsViewModel.init() {
        showProgress = {
            group_progress.visibility = View.VISIBLE
        }

        hideProgress = {
            group_progress.visibility = View.GONE
        }

        showEmptyView = {
            lottie_empty.visibility = View.VISIBLE
        }

        showDetailPage = { postId ->
            Toast.makeText(this@MainActivity, "Show detail page $postId", Toast.LENGTH_SHORT).show()
        }

        showOptionPopup = { adapterPosition, postTitle ->
            createDialog(
                    title = getString(R.string.label_delete),
                    message = getString(R.string.message_item_remove, postTitle),
                    positiveButtonListener = { _, _ ->
                        postsViewModel.deleteItem(adapterPosition)
                    }).show()
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            if (isFinishing) return

            val visibleItemCount = recyclerView.childCount
            val totalItemCount = mainAdapterViewModel.itemCount
            val firstVisibleItem = (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
                    ?: 0

            if (group_progress.visibility != View.VISIBLE && (firstVisibleItem + visibleItemCount) >= totalItemCount - 5) {
                postsViewModel.loadPosts()
            }
        }
    }
}
