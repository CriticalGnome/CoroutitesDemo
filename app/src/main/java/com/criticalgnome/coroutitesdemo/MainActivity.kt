package com.criticalgnome.coroutitesdemo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter(listOf())
        recycler.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            progress.setVisibility(true)

            val postsRequest = JsonPlaceholderApi.getApi().getPosts()
            val commentsRequest = JsonPlaceholderApi.getApi().getComments()
            val albumsRequest = JsonPlaceholderApi.getApi().getAlbums()
            val photosRequest = JsonPlaceholderApi.getApi().getPhotos()
            val todosRequest = JsonPlaceholderApi.getApi().getTodos()
            val usersRequest = JsonPlaceholderApi.getApi().getUsers()

            val postsResponse = postsRequest.await()
            val commentsResponse = commentsRequest.await()
            val albumsResponse = albumsRequest.await()
            val photosResponse = photosRequest.await()
            val todosResponse = todosRequest.await()
            val usersResponse = usersRequest.await()

            progress.setVisibility(false)
            if (postsResponse.isSuccessful) {
                adapter.items = postsResponse.body() ?: listOf()
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this@MainActivity, "Error ${postsResponse.code()}", Toast.LENGTH_SHORT).show()
            }

            if (postsResponse.isSuccessful) logger("Posts: ${postsResponse.body()?.size}") else logger("Error: ${postsResponse.code()}")
            if (commentsResponse.isSuccessful) logger("Comments: ${commentsResponse.body()?.size}") else logger("Error: ${commentsResponse.code()}")
            if (albumsResponse.isSuccessful) logger("Albums: ${albumsResponse.body()?.size}") else logger("Error: ${albumsResponse.code()}")
            if (photosResponse.isSuccessful) logger("Photos: ${photosResponse.body()?.size}") else logger("Error: ${photosResponse.code()}")
            if (todosResponse.isSuccessful) logger("Todos: ${todosResponse.body()?.size}") else logger("Error: ${todosResponse.code()}")
            if (usersResponse.isSuccessful) logger("Users: ${usersResponse.body()?.size}") else logger("Error: ${usersResponse.code()}")
        }
    }

    private fun View.setVisibility(isVisible: Boolean) {
        this.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun logger(msg: String) {
        Log.d("RESPONSE", msg)
    }

}