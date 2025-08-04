package com.isaacsufyan.weatherapp.business.data.repository

import android.content.Context
import com.isaacsufyan.weatherapp.business.data.api.WeatherApi
import com.isaacsufyan.weatherapp.business.data.mapper.WeatherMapper
import com.isaacsufyan.weatherapp.business.domain.model.Weather
import com.isaacsufyan.weatherapp.business.domain.repository.WeatherRepo
import com.isaacsufyan.weatherapp.business.data.entity.APIState
import com.isaacsufyan.weatherapp.business.data.mapper.CityMapper
import com.isaacsufyan.weatherapp.business.domain.model.City
import com.isaacsufyan.weatherapp.utils.Constants
import com.isaacsufyan.weatherapp.utils.NetworkService
import com.isaacsufyan.weatherapp.utils.getCities
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val weatherMapper: WeatherMapper,
    private val weatherApi: WeatherApi,
    private val cityMapper: CityMapper,
) : WeatherRepo {

    override suspend fun getWeatherData(
        location: String
    ): APIState<Weather> {
        return try {
            APIState.Success(
                weatherMapper.mapToModel(
                    weatherApi.getCurrentWeather(
                        NetworkService.API_KEY,
                        location,
                        "no",
                        "7"
                    )
                )
            )
        } catch (e: Exception) {
            APIState.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }

    override suspend fun getCityList(context: Context): APIState<List<City>> {
        return try {
            APIState.Success(
                context.getCities("cities.json").map {
                    cityMapper.mapToModel(it)
                }
            )
        } catch (e: Exception) {
            APIState.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }
}