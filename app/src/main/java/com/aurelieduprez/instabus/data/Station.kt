package com.aurelieduprez.instabus.data

data class Station (
    val id: String,
    val street_name: String,
    val city: String,
    val utm_x: String,
    val utm_y:  String,
    val lat: Double,
    val lon: Double,
    val furniture: String,
    val buses: String,
    val distance: String
) { }