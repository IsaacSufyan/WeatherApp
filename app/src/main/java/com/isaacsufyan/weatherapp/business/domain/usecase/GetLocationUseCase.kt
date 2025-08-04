package com.isaacsufyan.weatherapp.business.domain.usecase

import com.isaacsufyan.weatherapp.framework.location.MyLocationManager
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val locationManager: MyLocationManager) {
    suspend fun getLocation() = locationManager.getCurrentLocation()
}