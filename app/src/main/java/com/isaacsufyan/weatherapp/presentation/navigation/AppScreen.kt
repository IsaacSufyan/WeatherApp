package com.isaacsufyan.weatherapp.presentation.navigation

sealed class AppScreen(val route: String) {
    data object SplashScreen : AppScreen(Routes.splashScreen)
    data object HomeScreen : AppScreen(Routes.homeScreen)
    data object WeatherDetailScreen : AppScreen(Routes.weatherDetails)
}
