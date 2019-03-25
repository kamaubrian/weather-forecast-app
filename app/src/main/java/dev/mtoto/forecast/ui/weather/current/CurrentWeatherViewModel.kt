package dev.mtoto.forecast.ui.weather.current

import androidx.lifecycle.ViewModel;
import dev.mtoto.forecast.data.repository.ForecastRepository
import dev.mtoto.forecast.internal.UnitSystem
import dev.mtoto.forecast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

   val weather by lazyDeferred{
       forecastRepository.getCurrentWeather(isMetric)
   }
}
