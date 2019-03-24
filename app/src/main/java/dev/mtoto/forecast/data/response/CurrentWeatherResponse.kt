package dev.mtoto.forecast.data.response

import com.google.gson.annotations.SerializedName
import dev.mtoto.forecast.data.db.entity.CurrentWeatherEntry
import dev.mtoto.forecast.data.db.entity.Location

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)