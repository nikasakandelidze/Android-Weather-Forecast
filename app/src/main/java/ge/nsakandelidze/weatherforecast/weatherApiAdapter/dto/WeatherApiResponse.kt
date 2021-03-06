package ge.nsakandelidze.weatherforecast.weatherApiAdapter.dto

public data class WeatherApiResponse(
    val coordinates: Coordinate,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Int
) {
}