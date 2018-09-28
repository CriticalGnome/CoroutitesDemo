package com.criticalgnome.coroutitesdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.criticalgnome.coroutitesdemo.model.Post
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_item.*

class MainAdapter(var items: List<Post>): RecyclerView.Adapter<MainAdapter.PostHolder>() {
    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    override fun onBindViewHolder(holder: PostHolder, position: Int) { holder.bind(items[position]) }

    inner class PostHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Post) {
            postId.text     = item.id.toString()
            postTitle.text  = item.title
            postBody.text   = item.body
        }
    }
}