package tech.thdev.jsonplaceholderexample.network

object JsonPlaceRetrofit {

    private const val baseUrl = "https://jsonplaceholder.typicode.com"

    val api: JsonPlaceHolderInterface by lazy {
        createRetrofit(JsonPlaceHolderInterface::class.java, baseUrl)
    }
}