package org.sakana.weatherforecast.weatherApiAdapter

import org.sakana.weatherforecast.weatherApiAdapter.dto.WeatherApiResponse
import org.sakana.weatherforecast.weatherApiAdapter.dto.WeatherHistoryApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {
    @GET("/data/2.5/weather")
    fun geteCurrentWeatherData(@Query("q") cityName: String, @Query("appid") apiToken: String, @Query("units")unit: String): Call<WeatherApiResponse>
    @GET("/data/2.5/forecast")
    fun geteWeatherHistoryData(@Query("q") cityName: String, @Query("appid") apiToken: String, @Query("units")unit: String): Call<WeatherHistoryApiResponse>
}