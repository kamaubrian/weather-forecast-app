package dev.mtoto.forecast.data.repository

import androidx.lifecycle.LiveData
import dev.mtoto.forecast.data.db.dao.CurrentWeatherDao
import dev.mtoto.forecast.data.db.dao.FutureWeatherDao
import dev.mtoto.forecast.data.db.dao.WeatherLocationDao
import dev.mtoto.forecast.data.db.entity.WeatherLocation
import dev.mtoto.forecast.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import dev.mtoto.forecast.data.db.unitlocalized.future.detail.UnitSpecificDetailFutureWeatherDetail
import dev.mtoto.forecast.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import dev.mtoto.forecast.data.network.FORECAST_DAY_COUNT
import dev.mtoto.forecast.data.network.WeatherNetworkDataSource
import dev.mtoto.forecast.data.network.response.CurrentWeatherResponse
import dev.mtoto.forecast.data.network.response.FutureWeatherResponse
import dev.mtoto.forecast.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val futureWeatherDao: FutureWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : ForecastRepository {


    init {
        weatherNetworkDataSource.apply {
            downloadedCurrentWeather.observeForever { newCurrentWeather ->
                persistFetchedCurrentWeather(newCurrentWeather)
            }

            downloadedFutureWeather.observeForever { newFutureWeather ->
                persistFetchedFutureWeather(newFutureWeather)
            }

        }


    }


    override suspend fun getFutureWeatherByDate(
        date: LocalDate,
        metric: Boolean
    ): LiveData<out UnitSpecificDetailFutureWeatherDetail> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext if(metric) futureWeatherDao.getDetailedWeatherByDateMetric(date)
            else futureWeatherDao.getDetailedWeatherByDateImperial(date)
        }
    }

    override suspend fun getFutureWeatherList(
        startDate: LocalDate,
        metric: Boolean
    ): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) futureWeatherDao.getSimpleWeatherForecastsMetric(startDate)
            else futureWeatherDao.getSimpleWeatherForecastsImperial(startDate)
        }

    }


    override suspend fun getCurrentWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocationDao.getLocation()
        }
    }


    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
            weatherLocationDao.upsert(fetchedWeather.location)
        }
    }

    private fun persistFetchedFutureWeather(fetchFutureWeatherResponse: FutureWeatherResponse) {
        fun deleteOldForecastData() {
            val today = LocalDate.now()
            futureWeatherDao.deleteOldEntries(today)
        }
        GlobalScope.launch(Dispatchers.IO) {
            deleteOldForecastData()
            val futureWeatherList = fetchFutureWeatherResponse.forecast.entries
            futureWeatherDao.insert(futureWeatherList)
            weatherLocationDao.upsert(fetchFutureWeatherResponse.location)
        }
    }

    private suspend fun initWeatherData() {

        val lastWeatherLocation = weatherLocationDao.getLocationNonLive()
        if (lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather()
            fetchFutureWeather()
            return
        }
        if (isFetchCurrentNeeded(lastWeatherLocation.zonedDateTime))
            fetchCurrentWeather()

        if (isFetchedFutureNeeded())
            fetchFutureWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language
        )
    }

    private suspend fun fetchFutureWeather() {
        weatherNetworkDataSource.fetchFutureWeather(
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thiryMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thiryMinutesAgo)
    }

    private fun isFetchedFutureNeeded(): Boolean {
        val today = LocalDate.now()
        val futureWeatherCount = futureWeatherDao.countFutureWeather(today)
        return futureWeatherCount < FORECAST_DAY_COUNT
    }
}