package com.isaacsufyan.weatherapp.business.data.api

import com.isaacsufyan.weatherapp.business.data.api.model.WeatherResponse
import com.isaacsufyan.weatherapp.utils.NetworkService
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(NetworkService.FORECAST_WEATHER)
    suspend fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") location: String,
        @Query("aqi") aqi: String,
        @Query("days") days: String,
    ): WeatherResponse

}