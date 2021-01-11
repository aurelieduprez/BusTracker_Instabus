package com.aurelieduprez.instabus

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_post.*

class AddPostActivity : AppCompatActivity() {

    private var URI_STRING = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        val stationId:String = intent.getStringExtra("EXTRA_STATION_ID")
        val stationName:String = intent.getStringExtra("EXTRA_STATION_NAME")

        post_title.text = "Add a picture to " + stationName

        addImage.setOnClickListener {
            pickImageFromGallery()
        }

        AddPost.setOnClickListener {
            if (URI_STRING == "") {
                Toast.makeText(applicationContext, "Image not found", Toast.LENGTH_SHORT).show()
            } else {
                if (addDescription.text.toString() == "") {
                    Toast.makeText(applicationContext, "Empty description", Toast.LENGTH_SHORT).show()
                } else {
                    val newPost = Post(stationId, addDescription.text.toString(), this.URI_STRING)
                    DBHandler(this)
                        .insertPost(newPost)
                    this.onBackPressed();

                }
            }
        }


    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startActivityForResult(intent, 1234)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1234) {
            this.URI_STRING = data?.data.toString()
            addImage.setImageURI(data?.data)
        }
    }
}