package com.aurelieduprez.instabus

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.benjazor.instabus.data.ApiResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.INTERNET)
            != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_CONTACTS)) {

            } else {

                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.INTERNET),
                    123)
            }
        } else {
        }




        // Setup retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://barcelonaapi.marcpous.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(Api::class.java)

        // Make the request
        api.fetchAllStations().enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Internet is required to load the stations", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                showStations(response.body()!!)
            }
        })
    }

    private fun showStations(api_response: ApiResponse) {
        val stations = api_response.data.nearstations

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = StationsAdapater(stations)
        }
    }
}