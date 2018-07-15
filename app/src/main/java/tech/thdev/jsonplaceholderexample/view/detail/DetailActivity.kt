package tech.thdev.jsonplaceholderexample.view.detail

import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*
import tech.thdev.jsonplaceholderexample.KEY_POST_ID
import tech.thdev.jsonplaceholderexample.R
import tech.thdev.jsonplaceholderexample.data.source.post.PostsDataRepository
import tech.thdev.jsonplaceholderexample.util.inject
import tech.thdev.jsonplaceholderexample.view.detail.adapter.holder.CommentViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.holder.ContentViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.holder.SectionViewHolder
import tech.thdev.jsonplaceholderexample.view.detail.adapter.viewmodel.DetailAdapterViewModel
import tech.thdev.jsonplaceholderexample.view.detail.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val detailAdapterViewModel: DetailAdapterViewModel by lazy {
        DetailAdapterViewModel(this@DetailActivity).apply {
            onCreateViewHolder = { parent, viewType ->
                when (viewType) {
                    DetailAdapterViewModel.VIEW_TYPE_SECTION -> SectionViewHolder(this, parent)
                    DetailAdapterViewModel.VIEW_TYPE_CONTENT -> ContentViewHolder(this, parent)
                    DetailAdapterViewModel.VIEW_TYPE_COMMENT -> CommentViewHolder(this, parent)
                    else -> throw Resources.NotFoundException("ViewType error")
                }
            }
        }
    }

    private val detailViewModel: DetailViewModel by lazy {
        DetailViewModel(application, PostsDataRepository, detailAdapterViewModel).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()
        detailViewModel.run {
            init()
            loadDetail(intent?.getLongExtra(KEY_POST_ID, -1) ?: -1)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initView() {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        recycler_view.run {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            adapter = detailAdapterViewModel.adapter
        }
    }

    private fun DetailViewModel.init() {
        showProgress = {
            lottie_progress.visibility = View.VISIBLE
        }

        hideProgress = {
            lottie_progress.visibility = View.GONE
        }
    }
}