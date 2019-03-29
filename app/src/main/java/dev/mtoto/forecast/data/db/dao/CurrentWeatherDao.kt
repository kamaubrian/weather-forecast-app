package dev.mtoto.forecast.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.mtoto.forecast.data.db.entity.CURRENT_WEATHER_ID
import dev.mtoto.forecast.data.db.entity.CurrentWeatherEntry
import dev.mtoto.forecast.data.db.unitlocalized.current.ImperialCurrentWeatherEntry
import dev.mtoto.forecast.data.db.unitlocalized.current.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id = ${CURRENT_WEATHER_ID}")
    fun getWeatherMetric():LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id = ${CURRENT_WEATHER_ID}")
    fun getWeatherImperial():LiveData<ImperialCurrentWeatherEntry>
}