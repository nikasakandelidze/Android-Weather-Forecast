package org.sakana.weatherforecast.weatherApiAdapter

import android.view.View
import android.widget.Toast
import com.google.gson.GsonBuilder
import org.sakana.weatherforecast.weatherApiAdapter.dto.WeatherApiResponse
import org.sakana.weatherforecast.weatherApiAdapter.dto.WeatherHistoryApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiAdapter {

    private val apiToken: String = "b792876327ea8f510d96527d72eba1a9"

    fun getCurrentWeather(weatherDataCallback: (input: WeatherApiResponse?) -> Unit, cityName: String, view: View){

        val gson = GsonBuilder()
            .setLenient()
            .create();

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        val apiFetcher = retrofit.create<WeatherApiClient>(WeatherApiClient::class.java)

        apiFetcher.geteCurrentWeatherData(cityName, apiToken, "metric").enqueue(object : Callback<WeatherApiResponse> {
            override fun onFailure(call: Call<WeatherApiResponse>, t: Throwable) {
                showToastMessage(view)
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

    fun getWeatherHistory(weatherDataCallback: (input: WeatherHistoryApiResponse?) -> Unit, cityName: String, view:View) {
        val gson = GsonBuilder()
            .setLenient()
            .create();

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        val apiFetcher = retrofit.create<WeatherApiClient>(WeatherApiClient::class.java)

        apiFetcher.geteWeatherHistoryData(cityName, apiToken, "metric").enqueue(object : Callback<WeatherHistoryApiResponse> {
            override fun onFailure(call: Call<WeatherHistoryApiResponse>, t: Throwable) {
                showToastMessage(view)
            }

            override fun onResponse(
                call: Call<WeatherHistoryApiResponse>,
                response: Response<WeatherHistoryApiResponse>
            ) {
                if (response.isSuccessful) {
                    weatherDataCallback(response.body())
                }
            }

        })
    }

    private fun showToastMessage(view:View){
        val text = "Fetching API problem"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(view.context, text, duration)
        toast.show()
    }

}