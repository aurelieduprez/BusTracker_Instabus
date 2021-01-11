package com.aurelieduprez.instabus

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_row.view.*

class PostsAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private var parentContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        this.parentContext = parent.context
        return ViewHolder(view)

    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position%2==1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#DFDFDF")) // tamer
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))

        }
        val post = posts[position]
        holder.post_content.text = post.Content
        holder.post_image.setImageURI(Uri.parse(post.Path))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val post_content: TextView = itemView.post_content
        val post_image: ImageView = itemView.post_image
    }
}


