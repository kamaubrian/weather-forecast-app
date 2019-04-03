package dev.mtoto.forecast.data.db.unitlocalized.future.detail

import androidx.room.ColumnInfo
import org.threeten.bp.LocalDate

data class MetricDetailFutureWeatherEntry(

    @ColumnInfo(name = "date")
    override val date: LocalDate,

    @ColumnInfo(name = "maxTempC")
    override val maxTemperature: Double,

    @ColumnInfo(name = "minTempC")
    override val minTemperature: Double,

    @ColumnInfo(name = "avgTempC")
    override val avgTemperature: Double,

    @ColumnInfo(name = "condition_text")
    override val conditionText: String,

    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,

    @ColumnInfo(name = "maxwindKph")
    override val maxWindSpeed: Double,

    @ColumnInfo(name = "totalprecipMm")
    override val totalPrecipitation: Double,

    @ColumnInfo(name = "avgvisKm")
    override val avgVisibilityDistance: Double,

    @ColumnInfo(name = "uv")
    override val uv: Double
):UnitSpecificDetailFutureWeatherDetail