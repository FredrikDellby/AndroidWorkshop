package com.example.workshopapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Endpoint {
    @Headers("Authorization: ${Credentials.VASTTRAFIK_KEY}")
    @GET("bin/rest.exe/v2/location.nearbystops")
    fun getNearbyStops(@Query("originCoordLat") latitude: Double,
                       @Query("originCoordLong") longitude: Double,
                       @Query("format") format: String) : Call<StopData>
}