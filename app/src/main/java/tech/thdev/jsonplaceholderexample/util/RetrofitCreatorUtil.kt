package tech.thdev.jsonplaceholderexample.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun <T> createRetrofit(cls: Class<T>, baseUrl: String): T =
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build()
                .create(cls)


private fun createOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }.build()