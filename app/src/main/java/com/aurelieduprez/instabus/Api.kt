package com.aurelieduprez.instabus

import com.aurelieduprez.instabus.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
/**
This interface is used to call the fetchAllStations from the API, and precises the rest of the base URL
 */
interface Api {

    @GET("/bus/nearstation/latlon/%2041.3985182/2.1917991/1.json")
    fun fetchAllStations() : Call<ApiResponse>

}