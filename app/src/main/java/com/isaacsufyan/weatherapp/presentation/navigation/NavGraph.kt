package com.isaacsufyan.weatherapp.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.isaacsufyan.weatherapp.business.domain.model.Weather
import com.isaacsufyan.weatherapp.presentation.home.HomeScreen
import com.isaacsufyan.weatherapp.presentation.home.HomeViewModel
import com.isaacsufyan.weatherapp.presentation.splash.SplashScreen
import com.isaacsufyan.weatherapp.presentation.weather_detail.WeatherDetailScreen

@Composable
fun NavGraph(
    viewModel: HomeViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.SplashScreen.route
    ) {
        composable(route = AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }

        composable(
            route = AppScreen.HomeScreen.route
        ) {
            HomeScreen(viewModel) {
                val jsonString = Uri.encode(Gson().toJson(it))
                navController.navigate(route = "weather_details/$jsonString")
            }
        }

        composable(route = AppScreen.WeatherDetailScreen.route,arguments = listOf(navArgument("model") {
            type = NavType.StringType
        })) { backStackEntry ->
            val jsonArgument = backStackEntry.arguments?.getString("model")
            val weather = Gson().fromJson(jsonArgument, Weather::class.java)
            WeatherDetailScreen(weather)
        }
    }
}