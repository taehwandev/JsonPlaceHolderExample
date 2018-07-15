package tech.thdev.jsonplaceholderexample.view.main.viewmodel

import android.content.Context
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import tech.thdev.jsonplaceholderexample.data.source.post.PostsDataRepository
import tech.thdev.jsonplaceholderexample.view.main.adapter.viewmodel.MainAdapterViewModel

class PostsViewModelTest {

    private val postsDataSource = PostsDataRepository
    private lateinit var postsViewModel: PostsViewModel

    private lateinit var mainAdapterViewModel: MainAdapterViewModel

    private val context = mock<Context>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        mainAdapterViewModel = MainAdapterViewModel(context).apply {
            notifyItemRangeInserted = { _, _ -> }
        }
        postsViewModel = PostsViewModel(postsDataSource, mainAdapterViewModel)
        postsViewModel.hideProgress = {}
        postsViewModel.showProgress = {}
    }

    @Test
    fun loadTest() {
        val testSubscriber = TestSubscriber<Boolean>()

        postsViewModel.hideProgress = {
            testSubscriber.onNext(true)
        }

        postsViewModel.loadPosts()

        testSubscriber.awaitCount(1)
        testSubscriber.assertValue(true)

        println("loadTest end")
    }

    @Test
    fun loadNoItem() {
        val testSubscriber = TestSubscriber<Boolean>()

        postsViewModel.showEmptyView = {
            testSubscriber.onNext(postsDataSource.isLast)
        }

        postsViewModel.loadPosts(-1)

        testSubscriber.awaitCount(1)
        testSubscriber.assertValue(true)

        println("loadTest end")
    }
}