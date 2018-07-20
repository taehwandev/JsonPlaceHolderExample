package tech.thdev.jsonplaceholderexample.view.detail.viewmodel

import android.app.Application
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import tech.thdev.jsonplaceholderexample.R
import tech.thdev.jsonplaceholderexample.base.viewmodel.BaseLifecycleAndroidViewModel
import tech.thdev.jsonplaceholderexample.data.Comment
import tech.thdev.jsonplaceholderexample.data.Post
import tech.thdev.jsonplaceholderexample.data.source.post.PostsDataSource
import tech.thdev.jsonplaceholderexample.util.plusAssign
import tech.thdev.jsonplaceholderexample.view.detail.adapter.viewmodel.DetailAdapterViewModel

class DetailViewModel(application: Application,
                      private val postsDataSource: PostsDataSource,
                      private val adapterViewModel: DetailAdapterViewModel) : BaseLifecycleAndroidViewModel(application) {

    lateinit var showProgress: () -> Unit
    lateinit var hideProgress: () -> Unit

    fun loadDetail(postId: Long) {
        disposables += Flowable.combineLatest(
                postsDataSource.getPost(postId),
                postsDataSource.getPostComments(postId),
                BiFunction<Post, List<Comment>, Pair<Post, List<Comment>>> { post: Post, comments: List<Comment> -> Pair(post, comments) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { (post, comment) ->
                    adapterViewModel.adapterDataSource.addItem(DetailAdapterViewModel.VIEW_TYPE_SECTION, post.title)
                    adapterViewModel.adapterDataSource.addItem(DetailAdapterViewModel.VIEW_TYPE_CONTENT, post)

                    adapterViewModel.adapterDataSource.addItem(DetailAdapterViewModel.VIEW_TYPE_SECTION, application.getString(R.string.label_comment, comment.size))
                    adapterViewModel.adapterDataSource.addItems(DetailAdapterViewModel.VIEW_TYPE_COMMENT, comment)
                }
                .doOnSubscribe {
                    showProgress()
                }
                .doOnComplete {
                    hideProgress()
                }
                .subscribe({
                    adapterViewModel.notifyDataSetChanged()
                }, {
                    it.printStackTrace()
                })
    }
}