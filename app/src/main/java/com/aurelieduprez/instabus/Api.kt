package com.aurelieduprez.instabus

import com.benjazor.instabus.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/bus/nearstation/latlon/%2041.3985182/2.1917991/1.json")
    fun fetchAllStations() : Call<ApiResponse>

}