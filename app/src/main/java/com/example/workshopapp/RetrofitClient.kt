package com.example.workshopapp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object Factory {
        private var retrofit: Retrofit? = null
        private const val baseUrl = "https://api.vasttrafik.se/"

        fun create(): Endpoint {
            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(baseUrl)
                        .build()
            }
            return retrofit!!.create(Endpoint::class.java)
        }
    }

}