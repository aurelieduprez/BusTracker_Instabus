package com.aurelieduprez.instabus

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "com.aurelieduprez.instabus"
val TABLE_NAME = "Posts"
val COL_ID = "id"
val COL_STATIONID = "station_id"
val COL_CONTENT = "content"
val COL_PATH = "path"

class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL_STATIONID + " VARCHAR(256)," +
                COL_CONTENT + " VARCHAR(256)," +
                COL_PATH + " VARCHAR(256))"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }

    fun insertPost(post: Post) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_STATIONID, post.StationId)
        cv.put(COL_CONTENT, post.Content)
        cv.put(COL_PATH, post.Path)
        db.insert(TABLE_NAME, null, cv)

    }

    fun getPosts(stationId: String): List<Post> {
        var posts_list : MutableList<Post> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME + " where " + COL_STATIONID + "=" + stationId
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            do {
                val post = Post(
                    result.getString(result.getColumnIndex(COL_STATIONID)),
                    result.getString(result.getColumnIndex(COL_CONTENT)),
                    result.getString(result.getColumnIndex(COL_PATH))
                )
                posts_list.add(post)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return posts_list


    }

}