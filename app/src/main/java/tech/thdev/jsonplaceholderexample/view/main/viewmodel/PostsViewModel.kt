package tech.thdev.jsonplaceholderexample.view.main.viewmodel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.thdev.jsonplaceholderexample.base.viewmodel.BaseLifecycleViewModel
import tech.thdev.jsonplaceholderexample.data.Post
import tech.thdev.jsonplaceholderexample.data.source.post.PostsDataSource
import tech.thdev.jsonplaceholderexample.util.plusAssign
import tech.thdev.jsonplaceholderexample.view.main.adapter.viewmodel.MainAdapterViewModel

class PostsViewModel(private val postsDataSource: PostsDataSource,
                     private val adapterViewModel: MainAdapterViewModel) : BaseLifecycleViewModel() {

    companion object {
        const val PER_PAGE = 20
    }

    lateinit var showProgress: () -> Unit
    lateinit var hideProgress: () -> Unit

    lateinit var showEmptyView: () -> Unit

    lateinit var showOptionPopup: (adapterPosition: Int, postTitle: String) -> Unit
    lateinit var showDetailPage: (postId: Long) -> Unit

    init {
        adapterViewModel.run {
            onClickItem = { adapterPosition ->
                (getItem(adapterPosition) as? Post)?.let {
                    showDetailPage(it.id)
                }
            }

            onLongClickItem = { adapterPosition ->
                (getItem(adapterPosition) as? Post)?.let {
                    showOptionPopup(adapterPosition, it.title)
                }
            }
        }
    }

    private var startPage = -1

    fun loadPosts(page: Int = ++startPage) {
        disposables += postsDataSource.getPosts(page, PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter {
                    (it.size >= PER_PAGE && !postsDataSource.isLast).also {
                        if (!it) {
                            showEmptyView()
                        }
                    }
                }
                .map {
                    val startPosition = adapterViewModel.itemCount
                    adapterViewModel.addItems(MainAdapterViewModel.VIEW_TYPE_POST, it)
                    Pair(startPosition, it.size)
                }
                .doOnSubscribe {
                    showProgress()
                }
                .doOnComplete {
                    hideProgress()
                }
                .subscribe({ (startPosition, addItemSize) ->
                    adapterViewModel.notifyItemRangeInserted(startPosition, addItemSize)
                }, {
                    it.printStackTrace()
                })

    }

    fun deleteItem(adapterPosition: Int) {
        (adapterViewModel.getItem(adapterPosition) as? Post)?.let {
            disposables += postsDataSource.postDelete(it.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        showProgress()
                    }
                    .doOnComplete {
                        hideProgress()
                    }
                    .subscribe({
                        if (it) {
                            adapterViewModel.removeAt(adapterPosition)
                            adapterViewModel.notifyItemRemoved(adapterPosition)
                        }
                    }, {
                        it.printStackTrace()
                    })
        }
    }

    override fun onCleared() {
        super.onCleared()

        startPage = -1
    }
}