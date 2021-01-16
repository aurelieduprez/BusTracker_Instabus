package com.aurelieduprez.instabus

import com.aurelieduprez.instabus.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
/**
Cette interface sert à déclarer le chemin d'accès pour l'API (base url)
 */
interface Api {

    @GET("/bus/nearstation/latlon/%2041.3985182/2.1917991/1.json")
    fun fetchAllStations() : Call<ApiResponse>

}