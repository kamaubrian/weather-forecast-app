package dev.mtoto.forecast.data.repository

import androidx.lifecycle.LiveData
import dev.mtoto.forecast.data.db.dao.CurrentWeatherDao
import dev.mtoto.forecast.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import dev.mtoto.forecast.data.network.WeatherNetworkDataSource

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<UnitSpecificCurrentWeatherEntry> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}