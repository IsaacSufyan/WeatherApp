package com.isaacsufyan.weatherapp.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.isaacsufyan.weatherapp.business.data.entity.APIState
import com.isaacsufyan.weatherapp.business.domain.model.Weather
import com.isaacsufyan.weatherapp.business.domain.usecase.GetLocationUseCase
import com.isaacsufyan.weatherapp.business.domain.usecase.WeatherUseCase
import com.isaacsufyan.weatherapp.utils.ExceptionTitles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentLocation: GetLocationUseCase,
    private val weatherUseCase: WeatherUseCase,
    private val application: Application
) : AndroidViewModel(application) {

    private val _cities = MutableStateFlow(emptyList<String>().toImmutableList())
    val cities get() = _cities.asStateFlow()

    private val _weatherData = MutableStateFlow<UiState<Weather>>(UiState.Loading)
    val weatherData get() = _weatherData.asStateFlow()


    fun getWeatherData() {
        _weatherData.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val locationData = getCurrentLocation.getLocation()
                if (locationData != null) {
                    val lat = locationData.latitude
                    val lng = locationData.longitude
                    getWeatherDataFromServer("$lat,$lng")
                } else {
                    _weatherData.value =
                        UiState.Error(ExceptionTitles.NO_INTERNET_CONNECTION)
                }
            } catch (e: Exception) {
                _weatherData.value = UiState.Error(e.message)
            }
        }
    }

    fun getLocationByCityName(cityName: String = "RahimYar Khan") {
        _weatherData.value = UiState.Loading
        viewModelScope.launch {
            getWeatherDataFromServer(cityName)
        }
    }

    private suspend fun getWeatherDataFromServer(location: String) {
        when (val result = weatherUseCase.getWeatherData(location = location)) {
            is APIState.Success -> {
                _weatherData.value = UiState.Success(result.data ?: Weather())
            }

            is APIState.Error -> {
                _weatherData.value = UiState.Error(result.message)
            }
        }
    }

    private fun getCities() {
        viewModelScope.launch {
            when (val result = weatherUseCase.getCities(application.baseContext)) {
                is APIState.Success -> {
                    val cityObjectList = result.data ?: emptyList()
                    val cityList = cityObjectList.map {
                        it.cityName ?: ""
                    }
                    _cities.value = cityList.toImmutableList()
                }

                is APIState.Error -> {
                    _cities.value = persistentListOf(
                        "Lahore",
                        "Faisalabad",
                        "London",
                        "New York",
                        "Islamabad"
                    ).toImmutableList()
                }
            }
        }
    }

    init {
        getCities()
    }
}