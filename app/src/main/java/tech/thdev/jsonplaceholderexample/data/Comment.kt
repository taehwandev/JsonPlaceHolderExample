package tech.thdev.jsonplaceholderexample.data

data class Comment(
        val postId: Long,
        val id: Int,
        val name: String,
        val email: String,
        val body: String)