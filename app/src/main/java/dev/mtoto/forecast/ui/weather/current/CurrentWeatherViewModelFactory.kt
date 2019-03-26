package dev.mtoto.forecast.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.mtoto.forecast.data.provider.UnitProvider
import dev.mtoto.forecast.data.repository.ForecastRepository
import dev.mtoto.forecast.internal.UnitSystem

class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository,
    private val unitProvider:UnitProvider
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository,unitProvider) as T
    }
}