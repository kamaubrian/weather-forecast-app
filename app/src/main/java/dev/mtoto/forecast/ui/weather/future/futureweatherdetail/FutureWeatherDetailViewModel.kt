package dev.mtoto.forecast.ui.weather.future.futureweatherdetail

import dev.mtoto.forecast.data.provider.UnitProvider
import dev.mtoto.forecast.data.repository.ForecastRepository
import dev.mtoto.forecast.internal.lazyDeferred
import dev.mtoto.forecast.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureWeatherDetailViewModel(
    private val detailedDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
    ) : WeatherViewModel(forecastRepository,unitProvider) {


    val weather by lazyDeferred{
        forecastRepository.getFutureWeatherByDate(detailedDate,super.isMetricUnit)
    }
}
