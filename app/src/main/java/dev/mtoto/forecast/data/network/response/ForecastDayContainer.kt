package dev.mtoto.forecast.data.network.response

import com.google.gson.annotations.SerializedName
import dev.mtoto.forecast.data.db.entity.FutureWeatherEntry

data class ForecastDayContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)