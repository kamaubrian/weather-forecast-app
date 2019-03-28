package dev.mtoto.forecast.data.network.response

import com.google.gson.annotations.SerializedName
import dev.mtoto.forecast.data.db.entity.CurrentWeatherEntry
import dev.mtoto.forecast.data.db.entity.WeatherLocation

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    @SerializedName("location")
    val location: WeatherLocation

)