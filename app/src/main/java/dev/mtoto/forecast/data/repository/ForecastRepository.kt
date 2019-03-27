package dev.mtoto.forecast.data.repository

import androidx.lifecycle.LiveData
import dev.mtoto.forecast.data.db.entity.WeatherLocation
import dev.mtoto.forecast.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean) :LiveData< out UnitSpecificCurrentWeatherEntry>
    suspend fun getCurrentWeatherLocation(): LiveData<WeatherLocation>
}