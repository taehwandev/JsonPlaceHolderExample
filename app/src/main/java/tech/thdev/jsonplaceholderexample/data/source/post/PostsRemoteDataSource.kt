package tech.thdev.jsonplaceholderexample.data.source.post

import tech.thdev.jsonplaceholderexample.network.JsonPlaceHolderInterface
import tech.thdev.jsonplaceholderexample.network.JsonPlaceRetrofit

class PostsRemoteDataSource {

    private val api: JsonPlaceHolderInterface by lazy {
        JsonPlaceRetrofit.api
    }

    fun getPosts(start: Int, limit: Int) =
            api.getPosts(start, limit)

    fun getPost(id: Long) =
            api.getPost(id)

    fun getPostComments(id: Long) =
            api.getPostComments(id)

    fun postDelete(id: Long) =
            api.deletePosts(id)
}