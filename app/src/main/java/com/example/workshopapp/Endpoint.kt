package com.example.workshopapp

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Endpoint {
    @Headers("Authorization: ${Credentials}")
    @GET("bin/rest.exe/v2/location.nearbystops")
    fun getNearbyStops(@Query("originCoordLat") latitude: Double,
                       @Query("originCoordLong") longitude: Double,
                       @Query("format") format: String) {

    }
}