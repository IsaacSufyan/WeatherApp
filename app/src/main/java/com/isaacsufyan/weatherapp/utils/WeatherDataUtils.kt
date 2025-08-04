package com.isaacsufyan.weatherapp.utils

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.isaacsufyan.coreui.theme.gradientColor3E2D8F
import com.isaacsufyan.coreui.theme.gradientColor8E78C8
import com.isaacsufyan.coreui.theme.gradientColor9D52AC
import com.isaacsufyan.weatherapp.R
import java.util.Calendar

object WeatherDataUtils {
    private val dayOfWeek = arrayListOf<String>()
    private val calendar: Calendar = Calendar.getInstance()

    init {
        dayOfWeek.add("Sun")
        dayOfWeek.add("Mon")
        dayOfWeek.add("Tue")
        dayOfWeek.add("Wed")
        dayOfWeek.add("Thu")
        dayOfWeek.add("Fri")
        dayOfWeek.add("Sat")
    }

    val sunnyOrClearBgColors = listOf(
        gradientColor3E2D8F,
        gradientColor9D52AC
    )
    val otherWeathersBgColor = listOf(
        gradientColor3E2D8F,
        gradientColor8E78C8
    )

    fun getForecastItemBg(code: Int? = 1000): List<Color> {
        return code?.let {
            if (it == 1000)
                return sunnyOrClearBgColors
            else
                otherWeathersBgColor
        } ?: sunnyOrClearBgColors
    }

    fun getDayOfMonth(index: Int): String {
        val currentDay = (calendar.get(Calendar.DAY_OF_WEEK) - 1) + index
        return dayOfWeek[currentDay % dayOfWeek.size]
    }

    fun getUiIndexValue(uvIndex: Int, context: Context): String {
        return if (uvIndex in 3..5)
            context.getString(R.string.moderate)
        else if (uvIndex in 6..7)
            context.getString(R.string.high)
        else if (uvIndex in 8..10)
            context.getString(R.string.very_high)
        else if (uvIndex > 10)
            context.getString(R.string.extreme)
        else
            context.getString(R.string.low)
    }

    fun isDay(isDay: Int, context: Context): String {
        return if (isDay == 0)
            context.getString(R.string.night)
        else
            context.getString(R.string.day)

    }

}