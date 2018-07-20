package tech.thdev.jsonplaceholderexample.network

import tech.thdev.jsonplaceholderexample.util.createRetrofit

object RetrofitFactory {

    private const val baseUrl = "https://jsonplaceholder.typicode.com"

    val api: JsonPlaceHolderInterface by lazy {
        createRetrofit(JsonPlaceHolderInterface::class.java, baseUrl)
    }
}