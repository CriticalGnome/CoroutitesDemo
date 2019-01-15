package com.criticalgnome.coroutitesdemo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch

class MainActivity : Activity() {

    private val jsonPlaceholderApi = JsonPlaceholderApi.getApi()
    private val adapter = MainAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            progress.visibility = View.VISIBLE
            val postsResponse = jsonPlaceholderApi.getPosts().await()
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