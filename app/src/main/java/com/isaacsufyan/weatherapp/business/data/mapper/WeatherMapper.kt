package com.isaacsufyan.weatherapp.business.data.mapper

import com.isaacsufyan.weatherapp.business.data.api.model.CurrentWeatherResponse
import com.isaacsufyan.weatherapp.business.data.api.model.ForeCastResponse
import com.isaacsufyan.weatherapp.business.data.api.model.LocationResponse
import com.isaacsufyan.weatherapp.business.data.api.model.WeatherResponse
import com.isaacsufyan.weatherapp.business.domain.mapper.IEntityMapper
import com.isaacsufyan.weatherapp.business.domain.model.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor(
    private val locationMapper: LocationMapper,
    private val currentWeatherMapper: CurrentWeatherMapper,
    private val forecastMapper: ForecastMapper
) : IEntityMapper<WeatherResponse, Weather> {

    override fun mapToModel(entity: WeatherResponse): Weather {
        return Weather(
            location = locationMapper.mapToModel(entity.location ?: LocationResponse()),
            currentWeather = currentWeatherMapper.mapToModel(
                entity.currentWeather ?: CurrentWeatherResponse()
            ),
            forecast = forecastMapper.mapToModel(entity.forecastWeather ?: ForeCastResponse())
        )
    }

    override fun mapToEntity(model: Weather): WeatherResponse {
        TODO("Not yet implemented")
    }
}