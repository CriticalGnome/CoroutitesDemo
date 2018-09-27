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
            progress.visibility = View.VISIBLE

            val postsRequest = JsonPlaceholderApi.getApi().getPosts()
            val commentsRequest = JsonPlaceholderApi.getApi().getComments()
            val albumsRequest = JsonPlaceholderApi.getApi().getAlbums()
            val photosRequest = JsonPlaceholderApi.getApi().getPhotos()
            val todosRequest = JsonPlaceholderApi.getApi().getTodos()
            val usersRequest = JsonPlaceholderApi.getApi().getUsers()

            Log.d("LOGTAG", " All loaders armed!")

            val postsResponse = postsRequest.await()
            val commentsResponse = commentsRequest.await()
            val albumsResponse = albumsRequest.await()
            val photosResponse = photosRequest.await()
            val todosResponse = todosRequest.await()
            val usersResponse = usersRequest.await()

            progress.visibility = View.GONE
            if (postsResponse.isSuccessful) {
                adapter.items = postsResponse.body() ?: listOf()
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this@MainActivity, "Error ${postsResponse.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
