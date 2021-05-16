package org.sakana.weatherforecast.weatherApiAdapter.dto

public data class Main(
    val temp: Double,
    val feels_like: Float,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Long,
    val humidity: Long
) {
}