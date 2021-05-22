package ge.nsakandelidze.weatherforecast.weatherApiAdapter.dto

public data class WeatherHistoryApiResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherApiResponse>
) {}