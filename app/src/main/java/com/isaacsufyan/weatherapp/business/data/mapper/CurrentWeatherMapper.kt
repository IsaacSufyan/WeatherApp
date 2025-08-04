package com.isaacsufyan.weatherapp.business.data.mapper

import com.isaacsufyan.weatherapp.business.data.api.model.ConditionResponse
import com.isaacsufyan.weatherapp.business.data.api.model.CurrentWeatherResponse
import com.isaacsufyan.weatherapp.business.domain.mapper.IEntityMapper
import com.isaacsufyan.weatherapp.business.domain.model.CurrentWeather
import javax.inject.Inject

class CurrentWeatherMapper @Inject constructor(
    private val conditionMapper: ConditionMapper
) : IEntityMapper<CurrentWeatherResponse, CurrentWeather> {
    override fun mapToModel(entity: CurrentWeatherResponse): CurrentWeather {
        return CurrentWeather(
            lastUpdatedEpoch = entity.lastUpdatedEpoch ?: 1,
            lastUpdated = entity.lastUpdated ?: "",
            tempC = entity.tempC ?: 0.0,
            tempF = entity.tempF ?: 0.0,
            isDay = entity.isDay ?: 1,
            condition = conditionMapper.mapToModel(entity.condition ?: ConditionResponse()),
            windMph = entity.windMph ?: 0.0,
            windKph = entity.windKph ?: 0.0,
            windDegree = entity.windDegree ?: 1,
            windDir = entity.windDir ?: "",
            pressureMb = entity.pressureMb ?: 0.0,
            pressureIn = entity.pressureIn ?: 0.0,
            precipMm = entity.precipMm ?: 0.0,
            precipIn = entity.precipIn ?: 0.0,
            humidity = entity.humidity ?: 1,
            cloud = entity.cloud ?: 1,
            feelsLikeC = entity.feelsLikeC ?: 0.0,
            feelsLikeF = entity.feelsLikeF ?: 0.0,
            visKm = entity.visKm ?: 0.0,
            visMiles = entity.visMiles ?: 0.0,
            uv = entity.uv ?: 0.0,
            gustMph = entity.gustMph ?: 0.0,
            gustKph = entity.gustKph ?: 0.0
        )
    }

    override fun mapToEntity(model: CurrentWeather): CurrentWeatherResponse {
        TODO("Not yet implemented")
    }

}