package com.isaacsufyan.weatherapp.business.data.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("name") @Expose val name: String? = null,
    @SerializedName("region") @Expose val region: String? = null,
    @SerializedName("country") @Expose val country: String? = null,
    @SerializedName("lat") @Expose val lat: Double? = null,
    @SerializedName("lon") @Expose val lon: Double? = null,
    @SerializedName("tz_id") @Expose val tzId: String? = null,
    @SerializedName("localtime_epoch") @Expose val localTimeEpoch: Long? = null,
    @SerializedName("localtime") @Expose val localtime: String? = null
)

data class ConditionResponse(
    @SerializedName("text") @Expose val text: String? = null,
    @SerializedName("icon") @Expose val icon: String? = null,
    @SerializedName("code") @Expose val code: Int? = null
)

data class CurrentWeatherResponse(
    @SerializedName("last_updated_epoch") @Expose val lastUpdatedEpoch: Long? = null,
    @SerializedName("last_updated") @Expose val lastUpdated: String? = null,
    @SerializedName("temp_c") @Expose val tempC: Double? = null,
    @SerializedName("temp_f") @Expose val tempF: Double? = null,
    @SerializedName("is_day") @Expose val isDay: Int? = null,
    @SerializedName("condition") @Expose val condition: ConditionResponse? = null,
    @SerializedName("wind_mph") @Expose val windMph: Double? = null,
    @SerializedName("wind_kph") @Expose val windKph: Double? = null,
    @SerializedName("wind_degree") @Expose val windDegree: Int? = null,
    @SerializedName("wind_dir") @Expose val windDir: String? = null,
    @SerializedName("pressure_mb") @Expose val pressureMb: Double? = null,
    @SerializedName("pressure_in") @Expose val pressureIn: Double? = null,
    @SerializedName("precip_mm") @Expose val precipMm: Double? = null,
    @SerializedName("precip_in") @Expose val precipIn: Double? = null,
    @SerializedName("humidity") @Expose val humidity: Int? = null,
    @SerializedName("cloud") @Expose val cloud: Int? = null,
    @SerializedName("feelslike_c") @Expose val feelsLikeC: Double? = null,
    @SerializedName("feelslike_f") @Expose val feelsLikeF: Double? = null,
    @SerializedName("vis_km") @Expose val visKm: Double? = null,
    @SerializedName("vis_miles") @Expose val visMiles: Double? = null,
    @SerializedName("uv") @Expose val uv: Double? = null,
    @SerializedName("gust_mph") @Expose val gustMph: Double? = null,
    @SerializedName("gust_kph") @Expose val gustKph: Double? = null
)

data class WeatherResponse(
    @SerializedName("location") @Expose val location: LocationResponse? = null,
    @SerializedName("current") @Expose val currentWeather: CurrentWeatherResponse? = null,
    @SerializedName("forecast") @Expose val forecastWeather: ForeCastResponse? = null
)


data class ForeCastResponse(
    @SerializedName("forecastday") @Expose val forecastDataList: List<ForeCastDataResponse>? = null
)

data class ForeCastDataResponse(
    @SerializedName("day") @Expose val day: ForeCastDayResponse? = null,
    @SerializedName("astro") @Expose val astro: ForeCastAstroResponse? = null,
    @SerializedName("hour") @Expose val hour: List<ForeCastHoursResponse>? = null,
)

data class ForeCastDayResponse(
    @SerializedName("maxtemp_c") @Expose val maxtemp_c: String? = null,
    @SerializedName("mintemp_c") @Expose val mintemp_c: String? = null,
    @SerializedName("condition") @Expose val condition: ConditionResponse? = null
)

data class ForeCastAstroResponse(
    @SerializedName("sunrise") @Expose val sunrise: String? = null,
    @SerializedName("sunset") @Expose val sunset: String? = null,
    @SerializedName("moonrise") @Expose val moonrise: String? = null,
    @SerializedName("moonset") @Expose val moonset: String? = null
)

data class ForeCastHoursResponse(
    @Expose @SerializedName("time_epoch") val timeEpoch: Long? = null,
    @Expose @SerializedName("time") val time: String? = null,
    @Expose @SerializedName("temp_c") val temperatureCelsius: Double? = null,
    @Expose @SerializedName("condition") val condition: ConditionResponse? = null
)