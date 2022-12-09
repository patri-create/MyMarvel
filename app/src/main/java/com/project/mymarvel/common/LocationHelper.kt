package com.project.mymarvel.common

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.LocationServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationHelper @Inject constructor(application: Application) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    private val geocoder = Geocoder(application)

    @SuppressLint("MissingPermission") //Due to previous API versions
    fun getLocation(callback: (String) -> Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { callback.invoke(it.toAddress()) }
    }

    private fun Location?.toAddress(): String {
       val addresses = this?.let {
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return addresses?.firstOrNull()?.locality?: ""
    }
}