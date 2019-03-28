package dev.mtoto.forecast.ui.weather.current

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.mtoto.forecast.R
import dev.mtoto.forecast.internal.glide.GlideApp
import dev.mtoto.forecast.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()


    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)
        bindUI()

    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String {
        val unitAbbreviation = if(viewModel.isMetric) metric else imperial
        return unitAbbreviation
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()
        val weatherLocation = viewModel.weatherLocation.await()

        weatherLocation.observe(this@CurrentWeatherFragment, Observer { location->
            if(location == null) return@Observer
            updateLocation(location.name)


        })
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) return@Observer
            Log.d("Current Weather", it.windDirection)
            group_loading.visibility = View.GONE
            updateDateToToday()
            updateTemperatures(it.temperature,it.feelsLikeTemperature)
            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
            updateVisibility(it.visibilityDistance)
            updateWind(it.windDirection,it.windSpeed)
            GlideApp.with(this@CurrentWeatherFragment)
                .load("http:${it.conditionIconUrl}")
                .into(imageView_condition_icon)
            //text_current_weather.text = it.conditionIconUrl
        })

    }

    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday() {
        (activity as AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C","°F")
        textView_temperature.text = "$temperature$unitAbbreviation"
        textview_feels_like_temperature.text = "Feels like $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition: String) {
        textView_condition.text = condition
    }

    private fun updatePrecipitation(precipitationVolume: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm","in")
        textView_precipitation.text= "Precipitation: $precipitationVolume $unitAbbreviation"
    }
    private fun updateVisibility(visibilityLength: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km","mi")
        textView_visibility.text = "Visibility $visibilityLength $unitAbbreviation"
    }

    private fun updateWind(windDirection: String, windSpeed:Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph", "mph")
        textView_wind.text = "Wind: $windDirection, $windSpeed $unitAbbreviation"
    }
}
