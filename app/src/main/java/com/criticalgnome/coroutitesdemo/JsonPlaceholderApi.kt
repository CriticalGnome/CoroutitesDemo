@file:Suppress("unused")

package com.criticalgnome.coroutitesdemo

import com.criticalgnome.coroutitesdemo.model.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholderApi {

    @GET("/posts")          fun getPosts(): Deferred<Response<List<Post>>>
    @GET("/comments")       fun getComments(): Deferred<Response<List<Comment>>>
    @GET("/albums")         fun getAlbums(): Deferred<Response<List<Album>>>
    @GET("/photos")         fun getPhotos(): Deferred<Response<List<Photo>>>
    @GET("/todos")          fun getTodos(): Deferred<Response<List<Todo>>>
    @GET("/users")          fun getUsers(): Deferred<Response<List<User>>>

    @GET("/users/{id}")     fun getUser(@Path("id") id: Long): Deferred<Response<User>>
    @GET("/posts/{id}")     fun getPost(@Path("id") id: Long): Deferred<Response<Post>>
    @GET("/comments/{id}")  fun getComment(@Path("id") id: Long): Deferred<Response<Comment>>
    @GET("/albums/{id}")    fun getAlbum(@Path("id") id: Long): Deferred<Response<Album>>
    @GET("/photos/{id}")    fun getPhoto(@Path("id") id: Long): Deferred<Response<Photo>>
    @GET("/todos/{id}")     fun getTodo(@Path("id") id: Long): Deferred<Response<Todo>>

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"
        fun getApi(): JsonPlaceholderApi {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(getClient())
                    .build()
                    .create(JsonPlaceholderApi::class.java)
        }

        private fun getClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }
            return client.build()
        }
    }
}