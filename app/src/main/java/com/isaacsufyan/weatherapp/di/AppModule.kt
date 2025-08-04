package com.isaacsufyan.weatherapp.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.isaacsufyan.weatherapp.BuildConfig
import com.isaacsufyan.weatherapp.utils.NetworkService
import com.isaacsufyan.weatherapp.business.data.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesAuthHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient().newBuilder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(interceptor)
            }
        }
            .connectTimeout(45, TimeUnit.SECONDS).writeTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS).retryOnConnectionFailure(false)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(okHttpClient: OkHttpClient) : WeatherApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(NetworkService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }
}