package org.sakana.weatherforecast.weatherApiAdapter

import org.sakana.weatherforecast.weatherApiAdapter.model.WeatherApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {
    @GET("/data/2.5/weather")
    fun geteCurrentWeatherData(@Query("q") cityName: String, @Query("appid") apiToken: String, @Query("metric")unit: String): Call<WeatherApiResponse>
}