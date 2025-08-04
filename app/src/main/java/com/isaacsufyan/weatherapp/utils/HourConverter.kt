package com.isaacsufyan.weatherapp.utils

import com.isaacsufyan.weatherapp.business.domain.model.ForeCastHours
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertHour(hour: String): String {
    return if (hour.toInt() in 1..11) {
        "$hour AM"
    } else {
        when (hour) {
            "00" -> {
                "12 AM"
            }

            "12" -> {
                "12 PM"
            }

            else -> {
                "0${hour.toInt() - 12} PM"
            }
        }
    }
}

fun extractHourFromString(dateTimeString: String): String {
    try {
        val parts = dateTimeString.split(" ")
        if (parts.size == 2) {
            val timePart = parts[1]
            val hourPart = timePart.split(":")
            if (hourPart.size == 2) {
//                return hourPart[0] + ":" + hourPart[1]
                return timePart
            }
        }
        return ""
    } catch (e: Exception) {
        return ""
    }
}

fun extractDateFromString(dateTimeString: String?): String {
    if (dateTimeString.isNullOrBlank()) return ""
    try {
        val parts = dateTimeString.split(" ")
        if (parts.size == 2) {
            return parts[0]
        }
        return ""
    } catch (e: Exception) {
        return ""
    }
}

fun getCurrentTime(hoursList: List<ForeCastHours>): Int {
    var index = 0
    val currentTime = Date()
    val sdf = SimpleDateFormat("HH", Locale.getDefault())
    val formattedTime = sdf.format(currentTime)
    val timeString = "$formattedTime:00"
    hoursList.forEachIndexed { i, foreCastHours ->
        if (extractHourFromString(foreCastHours.time) == timeString) {
            index =  i
        }
    }
    return index
}