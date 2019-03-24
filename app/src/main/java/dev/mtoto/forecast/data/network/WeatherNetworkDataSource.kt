package dev.mtoto.forecast.data.network

import androidx.lifecycle.LiveData
import dev.mtoto.forecast.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {

    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location :String,
        languageCode:String
    )
}