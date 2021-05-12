package org.sakana.weatherforecast.weatherApiAdapter.model

public data class Sys(
    val type: Int,
    val id: Long,
    val country: String,
    val sunrise: String,
    val sunset: String
) {
}