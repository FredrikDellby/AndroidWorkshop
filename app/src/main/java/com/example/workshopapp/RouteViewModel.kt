package com.example.workshopapp

import android.app.Application
import android.location.Location
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RouteViewModel(application: Application): AndroidViewModel(application) {

    val nearByStopData = MutableLiveData<StopData>()
    val error = MutableLiveData<Int>()

    fun queryNearByStops(location: Location){
        RetrofitClient
                .create()
                .getNearbyStops(location.latitude, location.longitude, "json")
                .enqueue(object: Callback<StopData> {
                    override fun onFailure(call: Call<StopData>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<StopData>, response: Response<StopData>) {
                        when (response?.code()) {
                            200 -> {
                                nearByStopData.postValue(response.body())
                            }
                            else -> {

                            }
                        }

                    }
                })
    }
}