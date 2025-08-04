package com.isaacsufyan.weatherapp.presentation.home


sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Error(val message: String? = "") : UiState<Nothing>()
    class Success<T>(val data: T) : UiState<T>()
}