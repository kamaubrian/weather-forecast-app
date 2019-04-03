package dev.mtoto.forecast.data.repository

import androidx.lifecycle.LiveData
import dev.mtoto.forecast.data.db.entity.WeatherLocation
import dev.mtoto.forecast.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import dev.mtoto.forecast.data.db.unitlocalized.future.detail.UnitSpecificDetailFutureWeatherDetail
import dev.mtoto.forecast.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean) :LiveData< out UnitSpecificCurrentWeatherEntry>

    suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean) : LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>>

    suspend fun getFutureWeatherByDate(date: LocalDate,metric: Boolean) : LiveData<out UnitSpecificDetailFutureWeatherDetail>

    suspend fun getCurrentWeatherLocation(): LiveData<WeatherLocation>
}