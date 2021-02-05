package com.aurelieduprez.instabus

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurelieduprez.instabus.data.ApiResponse
import com.aurelieduprez.instabus.data.Station
import com.google.android.gms.tasks.Tasks.await
import kotlinx.android.synthetic.main.fragment_item.view.*
import kotlinx.android.synthetic.main.post_preview.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 Activity principale de l'app et definition des permissions
  */



class MainActivity : AppCompatActivity() {
    companion object{
        var station : List<Station> = listOf();
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        }
//splash screen at first when opening the app
        setContentView(R.layout.activity_splash_screen)
        if (station.isEmpty()){
            setContentView(R.layout.activity_splash_screen)
            // Setup retrofit to fetch stations infos
            val retrofit = Retrofit.Builder()
                .baseUrl("http://barcelonaapi.marcpous.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = retrofit.create(Api::class.java)

            api.fetchAllStations().enqueue(object : Callback<ApiResponse> {
                //error message
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "Internet connection is needed.", Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    station = response.body()!!.data.nearstations
                    Thread.sleep(1000)
                    gotoMainActivity()
                    Log.d("station", station.toString());
                }
            })
        }else{
            setContentView(R.layout.activity_main)
            val navView: BottomNavigationView = findViewById(R.id.nav_view)

            val navController = findNavController(R.id.nav_host_fragment)
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home, R.id.navigation_dashboard
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }


    }
    private fun gotoMainActivity(){
        val mainActivityIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }
//fetch street name of bus station for details view
    public fun showDetails(v: View){
        setContentView(R.layout.station_preview)
        val t1 = findViewById<View>(R.id.street_name) as TextView
        t1.text = v.content.text

    }
//button to return to the station list
    public fun returnButton (v: View){
        val mainActivityIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }


    //redirect when clickling on the pic posting part
    public fun postPic(v:View){
        setContentView(R.layout.post_preview)
    }


    //view to add a picture
    public fun addPic(v:View){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startActivityForResult(intent, 666)
    }


}