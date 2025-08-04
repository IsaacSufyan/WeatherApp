package com.isaacsufyan.weatherapp.business.domain.repository

import android.content.Context
import com.isaacsufyan.weatherapp.business.domain.model.Weather
import com.isaacsufyan.weatherapp.business.data.entity.APIState
import com.isaacsufyan.weatherapp.business.domain.model.City

interface WeatherRepo {
    suspend fun getWeatherData(
        location: String
    ): APIState<Weather>

    suspend fun getCityList(context: Context) : APIState<List<City>>
}