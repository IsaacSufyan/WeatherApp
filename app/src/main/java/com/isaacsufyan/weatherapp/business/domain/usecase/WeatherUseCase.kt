package com.isaacsufyan.weatherapp.business.domain.usecase

import android.content.Context
import com.isaacsufyan.weatherapp.business.data.entity.APIState
import com.isaacsufyan.weatherapp.business.domain.model.City
import com.isaacsufyan.weatherapp.business.domain.model.Weather
import com.isaacsufyan.weatherapp.business.domain.repository.WeatherRepo
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val weatherRepo: WeatherRepo) {

    suspend fun getWeatherData(location: String): APIState<Weather> =
        weatherRepo.getWeatherData(location)

    suspend fun getCities(context: Context): APIState<List<City>> =
        weatherRepo.getCityList(context)

}