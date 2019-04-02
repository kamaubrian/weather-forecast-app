package dev.mtoto.forecast.ui.weather.future.list

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dev.mtoto.forecast.R
import dev.mtoto.forecast.data.db.unitlocalized.future.UnitSpecificSimpleFutureWeatherEntry
import kotlinx.android.synthetic.main.item_future_weather.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class FutureWeatherItem (
    val weatherEntry: UnitSpecificSimpleFutureWeatherEntry
): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_condition.text = weatherEntry.conditionText
            textView_temperature.text = weatherEntry.avgTemperature.toString()
            textView_date.text = weatherEntry.date.toString()
            updateDate()

        }
    }

    override fun getLayout() = R.layout.future_list_weather_fragment

    private fun ViewHolder.updateDate(){
        val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        textView_date.text = weatherEntry.date.format(dateFormatter)
    }

}