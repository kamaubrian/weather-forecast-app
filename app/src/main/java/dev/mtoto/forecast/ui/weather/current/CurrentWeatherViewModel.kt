package dev.mtoto.forecast.ui.weather.current

import androidx.lifecycle.ViewModel;
import dev.mtoto.forecast.data.provider.UnitProvider
import dev.mtoto.forecast.data.repository.ForecastRepository
import dev.mtoto.forecast.internal.UnitSystem
import dev.mtoto.forecast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

   val weather by lazyDeferred{
       forecastRepository.getCurrentWeather(isMetric)
   }
   val weatherLocation by lazyDeferred{
       forecastRepository.getCurrentWeatherLocation()
   }
}
