package dev.mtoto.forecast.data.network

import androidx.lifecycle.LiveData
import dev.mtoto.forecast.data.network.response.CurrentWeatherResponse
import dev.mtoto.forecast.data.network.response.FutureWeatherResponse

interface WeatherNetworkDataSource {

    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    val downloadedFutureWeather: LiveData<FutureWeatherResponse>

    suspend fun fetchFutureWeather(
        location: String,
        languageCode: String
    )

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}