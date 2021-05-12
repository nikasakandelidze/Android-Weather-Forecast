package org.sakana.weatherforecast.weatherApiAdapter

import android.widget.ImageView
import com.google.gson.GsonBuilder
import org.sakana.weatherforecast.weatherApiAdapter.model.WeatherApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiAdapter {

    public fun getCurrentWeather(weatherDataCallback: (input: WeatherApiResponse?) -> Unit): Unit {

        val gson = GsonBuilder()
            .setLenient()
            .create();

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        val apiFetcher = retrofit.create<WeatherApiClient>(WeatherApiClient::class.java)

        apiFetcher.geteCurrentWeatherData().enqueue(object : Callback<WeatherApiResponse> {
            override fun onFailure(call: Call<WeatherApiResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<WeatherApiResponse>,
                response: Response<WeatherApiResponse>
            ) {
                if (response.isSuccessful) {
                    weatherDataCallback(response.body())
                }
            }

        })
    }


}