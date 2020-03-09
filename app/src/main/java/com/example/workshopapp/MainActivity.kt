package com.example.workshopapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var userLocation: Location
    private lateinit var viewModel: RouteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders(this).get(RouteViewModel::class.java)
        checkLocationPermission()
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // User has granted accesss -> get location
            getUserLocation()

        } else {
            // User has not granted access -> Show error
            Toast.makeText(this, "We must access your location", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {

            // Ask user for permission
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1333
            )
            Log.i("TAG", "not granted")

        } else {
            // Get user location
            getUserLocation()
        }

    }

    private fun getUserLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient
                .lastLocation
                .addOnSuccessListener {
                    userLocation = it
                    viewModel.queryNearByStops(userLocation)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Unable to get location", Toast.LENGTH_LONG).show()
                }


    }

    private fun observeData() {
        viewModel.nearByStopData.observe(this, Observer{
            showData(it.locationList.stopLocation)
        })

    }

    private fun showData(items: List<StopLocation>) {
        // Hide the progress bar
        progressBar = findViewById(R.id.progressBar)
        progressBar?.visibility = View.GONE

        recyclerView?.apply {
            visibility = View.VISIBLE

        }
    }
}