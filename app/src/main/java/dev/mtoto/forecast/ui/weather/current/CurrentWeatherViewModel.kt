package dev.mtoto.forecast.ui.weather.current

import dev.mtoto.forecast.data.provider.UnitProvider
import dev.mtoto.forecast.data.repository.ForecastRepository
import dev.mtoto.forecast.internal.lazyDeferred
import dev.mtoto.forecast.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository,unitProvider) {


   val weather by lazyDeferred{
       forecastRepository.getCurrentWeather(super.isMetricUnit)
   }

}
