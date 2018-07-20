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

    private val perPage = 20
    private val defaultStartPage = 0

    lateinit var showProgress: () -> Unit
    lateinit var hideProgress: () -> Unit

    lateinit var showEmptyView: () -> Unit

    private var startPage = defaultStartPage

    fun loadPosts(page: Int = startPage++) {
        disposables += postsDataSource.getPosts(page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { list ->
                    val isShowEmptyView = list.size < perPage && postsDataSource.isLast
                    if (isShowEmptyView) {
                        showEmptyView()
                    }
                    !isShowEmptyView
                }
                .map {
                    val startPosition = adapterViewModel.adapterDataSource.itemCount
                    adapterViewModel.adapterDataSource.addItems(MainAdapterViewModel.VIEW_TYPE_POST, it)
                    Pair(startPosition, it.size)
                }
                // todo compose로 처리하기
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
        (adapterViewModel.adapterDataSource.getItem(adapterPosition) as? Post)?.let {
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
                            adapterViewModel.adapterDataSource.removeAt(adapterPosition)
                            adapterViewModel.notifyItemRemoved(adapterPosition)
                        }
                    }, {
                        it.printStackTrace()
                    })
        }
    }

    override fun onCleared() {
        super.onCleared()

        startPage = defaultStartPage
    }
}