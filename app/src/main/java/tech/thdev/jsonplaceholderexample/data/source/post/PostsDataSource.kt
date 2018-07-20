package tech.thdev.jsonplaceholderexample.data.source.post

import io.reactivex.Flowable
import tech.thdev.jsonplaceholderexample.data.Comment
import tech.thdev.jsonplaceholderexample.data.Post

interface PostsDataSource {

    var isLast: Boolean

    /**
     * Cache item itemCount
     */
    val cacheSize: Int

    /**
     * get posts using pagination
     */
    fun getPosts(start: Int, limit: Int): Flowable<List<Post>>

    /**
     * get posts detail.
     */
    fun getPost(id: Long): Flowable<Post>

    /**
     * get comments of a post
     */
    fun getPostComments(id: Long): Flowable<List<Comment>>

    /**
     * delete post
     */
    fun postDelete(id: Long): Flowable<Boolean>

    /**
     * All data release
     */
    fun release()
}