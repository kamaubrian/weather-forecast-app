package dev.mtoto.forecast.ui.weather.future.futureweatherdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.mtoto.forecast.data.provider.UnitProvider
import dev.mtoto.forecast.data.repository.ForecastRepository
import org.threeten.bp.LocalDate

class FutureWeatherDetailViewModelFactory(
    private val detailedDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider

) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureWeatherDetailViewModel(detailedDate,forecastRepository,unitProvider) as T
    }
}