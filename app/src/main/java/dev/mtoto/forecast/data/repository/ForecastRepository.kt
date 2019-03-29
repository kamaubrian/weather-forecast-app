package dev.mtoto.forecast.data.repository

import androidx.lifecycle.LiveData
import dev.mtoto.forecast.data.db.entity.WeatherLocation
import dev.mtoto.forecast.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean) :LiveData< out UnitSpecificCurrentWeatherEntry>
    suspend fun getCurrentWeatherLocation(): LiveData<WeatherLocation>
}