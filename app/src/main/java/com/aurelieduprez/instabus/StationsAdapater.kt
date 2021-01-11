package com.aurelieduprez.instabus

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.benjazor.instabus.data.Station
import kotlinx.android.synthetic.main.station_row.view.*


class StationsAdapater(private val stations: List<Station>) : RecyclerView.Adapter<StationsAdapater.ViewHolder>() {


    private var parentContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.station_row, parent, false)
        this.parentContext = parent.context
        return ViewHolder(view)

    }

    override fun getItemCount() = stations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = stations[position]
        if (position%2==1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#DFDFDF"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))

        }
        holder.street_name.text = station.street_name
        holder.posts_button.setOnClickListener {
            val intent = Intent(parentContext, StationPosts::class.java)
            intent.putExtra("EXTRA_STATION_ID", station.id)
            intent.putExtra("EXTRA_STATION_NAME", station.street_name)
            parentContext?.startActivity(intent)
        }
    }


    /*private fun OpenStationPosts() {
        val intent = Intent(this, StationPosts::class.java)
        startActivity(intent)
    }*/


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val street_name: TextView = itemView.street_name
        val posts_button: ImageButton = itemView.photo
    }
}


