package tech.thdev.jsonplaceholderexample.data.source.post

import io.reactivex.Flowable
import tech.thdev.jsonplaceholderexample.data.Post

object PostsDataRepository : PostsDataSource {

    private val remoteDataSource: PostsRemoteDataSource by lazy {
        PostsRemoteDataSource()
    }

    /**
     * Long : post id
     * Post : posts
     */
    private val postMap = mutableMapOf<Long, Post>()

    override var isLast: Boolean = false

    override val cacheSize: Int
        get() = postMap.size

    override fun getPosts(start: Int, limit: Int) =
            if (isLast) {
                Flowable.empty()
            } else {
                remoteDataSource.getPosts(start, limit)
                        .map {
                            it.also { isLast = it.size < limit }
                        }
                        .concatMapIterable { it }
                        .map {
                            it.also { postMap[it.id] = it }
                        }
                        .toList()
                        .toFlowable()!!
            }

    override fun getPost(id: Long) =
            postMap[id]?.let {
                Flowable.just(it)
            } ?: remoteDataSource.getPost(id)

    override fun getPostComments(id: Long) =
            remoteDataSource.getPostComments(id)

    override fun postDelete(id: Long) =
            postMap[id]?.let { removeItem ->
                remoteDataSource.postDelete(id)
                        .switchMap {
                            Flowable.just(postMap.remove(id, removeItem))
                        }
            } ?: Flowable.just(false)!!

    override fun release() {
        postMap.clear()
        isLast = false
    }
}