package com.isaacsufyan.weatherapp.di

import com.isaacsufyan.weatherapp.business.domain.location.LocationTracker
import com.isaacsufyan.weatherapp.framework.location.MyLocationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(myLocationManager: MyLocationManager): LocationTracker
}