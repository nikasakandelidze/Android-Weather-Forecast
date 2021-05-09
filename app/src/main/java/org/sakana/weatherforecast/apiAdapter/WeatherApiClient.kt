package org.sakana.weatherforecast.apiAdapter

import org.sakana.weatherforecast.apiAdapter.model.WeatherApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApiClient {
    @GET("/data/2.5/weather?q=Tbilisi&appid={key}&units=metric")
    fun geteCurrentWeatherData(): Call<WeatherApiResponse>
}