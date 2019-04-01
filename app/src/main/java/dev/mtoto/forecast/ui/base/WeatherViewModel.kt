package dev.mtoto.forecast.ui.base

import androidx.lifecycle.ViewModel
import dev.mtoto.forecast.data.provider.UnitProvider
import dev.mtoto.forecast.data.repository.ForecastRepository
import dev.mtoto.forecast.internal.UnitSystem
import dev.mtoto.forecast.internal.lazyDeferred

abstract class WeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC


    val weatherLocation by lazyDeferred {
        forecastRepository.getCurrentWeatherLocation()
    }
}