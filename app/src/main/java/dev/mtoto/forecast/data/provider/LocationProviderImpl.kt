package dev.mtoto.forecast.data.provider

import android.content.Context
import dev.mtoto.forecast.data.db.entity.WeatherLocation

class LocationProviderImpl(context:Context) : PreferenceProvider(context), LocationProvider {


    override suspend fun getPreferredLocationString(): String {
        return "Kiambu"
    }

    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }
}