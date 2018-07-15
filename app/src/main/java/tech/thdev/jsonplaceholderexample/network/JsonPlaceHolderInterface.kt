package tech.thdev.jsonplaceholderexample.network

import io.reactivex.Flowable
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tech.thdev.jsonplaceholderexample.data.Comment
import tech.thdev.jsonplaceholderexample.data.Post

interface JsonPlaceHolderInterface {

    /**
     * get posts using pagination
     * page = _start = 0
     * perPage = _limit = 10
     */
    @GET("/posts/")
    fun getPosts(@Query("_start") start: Int,
                 @Query("_limit") limit: Int): Flowable<List<Post>>

    /**
     * get posts detail.
     * post id = 1
     */
    @GET("/posts/{id}")
    fun getPost(@Path("id") id: Long): Flowable<Post>

    /**
     * get comments of a post
     * post id = 1
     */
    @GET("/posts/{id}/comments")
    fun getPostComments(@Path("id") id: Long): Flowable<List<Comment>>

    /**
     * delete post
     * post id = 1
     */
    @DELETE("/posts/{id}")
    fun deletePosts(@Path("id") id: Long): Flowable<Any>
}