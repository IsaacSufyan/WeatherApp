package com.isaacsufyan.weatherapp.business.domain.model

data class Weather(
    val location: Location? = null,
    val currentWeather: CurrentWeather? = null,
    val forecast: ForeCast? = null
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tzId: String,
    val localTimeEpoch: Long,
    val localtime: String
)

data class CurrentWeather(
    val lastUpdatedEpoch: Long,
    val lastUpdated: String,
    val tempC: Double,
    val tempF: Double,
    val isDay: Int,
    val condition: Condition,
    val windMph: Double,
    val windKph: Double,
    val windDegree: Int,
    val windDir: String,
    val pressureMb: Double,
    val pressureIn: Double,
    val precipMm: Double,
    val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    val feelsLikeC: Double,
    val feelsLikeF: Double,
    val visKm: Double,
    val visMiles: Double,
    val uv: Double,
    val gustMph: Double,
    val gustKph: Double
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

data class ForeCast(
    val foreCastList: List<ForeCastData>
)

data class ForeCastData(
    val day: ForeCastDay,
    val astro: ForeCastAstro,
    val listOfHours: List<ForeCastHours>
)

data class ForeCastDay(
    val maxtemp_c: String,
    val mintemp_c: String,
    val condition: Condition
)

data class ForeCastAstro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String
)

data class ForeCastHours(
    val timeEpoch: Long,
    val time: String,
    val temperatureCelsius: Double,
    val condition: Condition
)