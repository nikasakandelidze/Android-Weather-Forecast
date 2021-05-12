package org.sakana.weatherforecast.weatherApiAdapter

import org.sakana.weatherforecast.weatherApiAdapter.model.WeatherApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApiClient {
    @GET("/data/2.5/weather?q=Tbilisi&appid=b792876327ea8f510d96527d72eba1a9&units=metric")
    fun geteCurrentWeatherData(): Call<WeatherApiResponse>
}