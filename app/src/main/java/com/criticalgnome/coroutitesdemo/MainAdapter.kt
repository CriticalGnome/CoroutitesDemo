package com.criticalgnome.coroutitesdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.criticalgnome.coroutitesdemo.model.Post
import kotlinx.android.synthetic.main.post_item.view.*

class MainAdapter(var items: List<Post>) : RecyclerView.Adapter<MainAdapter.PostHolder>() {
    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    override fun onBindViewHolder(holder: PostHolder, position: Int) = holder.bind(items[position])

    inner class PostHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Post) = with(itemView) {
            postId.text = item.id.toString()
            postTitle.text = item.title
            postBody.text = item.body
        }
    }
}