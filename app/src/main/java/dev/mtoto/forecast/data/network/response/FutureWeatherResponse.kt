package dev.mtoto.forecast.data.network.response

import com.google.gson.annotations.SerializedName
import dev.mtoto.forecast.data.db.entity.WeatherLocation

data class FutureWeatherResponse(
    @SerializedName("forecast")
    val forecast: ForecastDayContainer,
    @SerializedName("location")
    val location: WeatherLocation
)