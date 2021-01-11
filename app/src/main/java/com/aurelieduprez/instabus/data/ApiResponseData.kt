package com.benjazor.instabus.data

data class ApiResponseData(
    val nearstations: List<Station>,
    val transport: String
)