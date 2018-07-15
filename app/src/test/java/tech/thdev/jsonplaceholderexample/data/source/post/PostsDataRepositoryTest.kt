package tech.thdev.jsonplaceholderexample.data.source.post

import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Test

class PostsDataRepositoryTest {

    private val repository by lazy {
        PostsDataRepository
    }

    @Test
    fun getPosts() {
        val testSubscriber = TestSubscriber<Int>()
        repository.getPosts(0, 10)
                .subscribe {
                    testSubscriber.onNext(it.size)
                }

        testSubscriber.assertValue(10)
    }


    @Test
    fun getPostsIsLastCheck() {
        val testSubscriber = TestSubscriber<Boolean>()
        repository.getPosts(99, 10)
                .subscribe {
                    testSubscriber.onNext(repository.isLast)
                }

        testSubscriber.assertValue(true)
    }

    @Test
    fun getPost() {
        val testSubscriber = TestSubscriber<Boolean>()
        repository.getPost(1)
                .subscribe {
                    testSubscriber.onNext(it.id == 1L)
                }

        testSubscriber.assertValue(true)
    }

    @Test
    fun getPostComment() {
        val testSubscriber = TestSubscriber<Boolean>()
        repository.getPostComments(1)
                .subscribe {
                    testSubscriber.onNext(it.first().postId == 1L)
                }

        testSubscriber.assertValue(true)
    }

    @Test
    fun deletePostCache() {
        val testSubscriber = TestSubscriber<Int>()
        repository.getPosts(0, 10)
                .subscribe {
                    testSubscriber.onNext(it.size)
                }

        testSubscriber.assertValue(10)

        val testDeleteSubscriber = TestSubscriber<Boolean>()
        val testDeleteSizeSubscriber = TestSubscriber<Int>()
        repository.postDelete(1)
                .subscribe {
                    testDeleteSubscriber.onNext(it)
                    testDeleteSizeSubscriber.onNext(repository.cacheSize)
                }
        testDeleteSubscriber.assertValue(true)
        testDeleteSizeSubscriber.assertValue(9)
    }

    @Test
    fun deletePostNotCache() {
        val testSubscriber = TestSubscriber<Boolean>()
        repository.postDelete(1)
                .subscribe {
                    testSubscriber.onNext(it)
                }

        testSubscriber.assertValue(false)
    }

    @After
    fun tearDown() {
        repository.release()
    }
}