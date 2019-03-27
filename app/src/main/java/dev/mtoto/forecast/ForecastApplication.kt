package dev.mtoto.forecast

import android.app.Application
import android.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import dev.mtoto.forecast.data.db.ForecastDatabase
import dev.mtoto.forecast.data.network.*
import dev.mtoto.forecast.data.provider.LocationProvider
import dev.mtoto.forecast.data.provider.LocationProviderImpl
import dev.mtoto.forecast.data.provider.UnitProvider
import dev.mtoto.forecast.data.provider.UnitProviderImpl
import dev.mtoto.forecast.data.repository.ForecastRepository
import dev.mtoto.forecast.data.repository.ForecastRepositoryImpl
import dev.mtoto.forecast.ui.weather.current.CurrentWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton {instance<ForecastDatabase>().WeatherLocationDao()}
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl() }

        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(),instance(),instance(),instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(),instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false)
    }
}