package dev.mtoto.forecast.ui.weather.future.list

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dev.mtoto.forecast.R
import dev.mtoto.forecast.data.db.unitlocalized.future.UnitSpecificSimpleFutureWeatherEntry

class FutureWeatherItem (
    val weatherEntry: UnitSpecificSimpleFutureWeatherEntry
): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {

        }
    }

    override fun getLayout() = R.layout.future_list_weather_fragment

}