package com.isaacsufyan.weatherapp.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.isaacsufyan.weatherapp.business.data.api.model.CityResponse

private fun Context.readJsonFromAssets(fileName: String): String {
    return try {
        val inputStream = assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (e: Exception) {
        "Assets Reading Exception ${e.printStackTrace()}"
    }
}

fun Context.getCities(fileName: String): List<CityResponse> {
    val json = readJsonFromAssets(fileName)
    if (json.isNotEmpty()) {
        val gson = Gson()
        val listType = object : TypeToken<List<CityResponse>>() {}.type
        return gson.fromJson(json, listType)
    }
    return emptyList()
}