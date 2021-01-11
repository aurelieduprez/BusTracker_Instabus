package com.aurelieduprez.instabus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_station_posts.*
import kotlinx.android.synthetic.main.activity_station_posts.recyclerView

class StationPosts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_posts)

        val stationId:String = intent.getStringExtra("EXTRA_STATION_ID")
        val stationName:String = intent.getStringExtra("EXTRA_STATION_NAME")
        station_name.text = stationName + " Station" // Title
        // Action Button
        floatingActionButton.setOnClickListener {
            val addPostIntent = Intent(this, AddPostActivity::class.java)
            addPostIntent.putExtra("EXTRA_STATION_ID", stationId)
            addPostIntent.putExtra("EXTRA_STATION_NAME", stationName)
            startActivity(addPostIntent)
        }

        // Request to db
        val posts = DBHandler(this).getPosts(stationId)
        showPosts(posts)
    }

    private fun showPosts(posts: List<Post>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = PostsAdapter(posts)
        }
    }

}