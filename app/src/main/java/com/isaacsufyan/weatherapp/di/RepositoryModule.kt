package com.isaacsufyan.weatherapp.di

import com.isaacsufyan.weatherapp.business.data.repository.WeatherRepoImpl
import com.isaacsufyan.weatherapp.business.domain.repository.WeatherRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindForecastRepository(forecastRepositoryImpl: WeatherRepoImpl): WeatherRepo
}