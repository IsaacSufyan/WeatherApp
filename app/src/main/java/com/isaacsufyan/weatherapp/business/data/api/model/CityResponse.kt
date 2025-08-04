package com.isaacsufyan.weatherapp.business.data.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("name") @Expose val cityName: String? = null,
    @SerializedName("country") @Expose val country: String? = null,
    @SerializedName("lat") @Expose val lat: Double? = null,
    @SerializedName("lng") @Expose val lng: Double? = null
)
