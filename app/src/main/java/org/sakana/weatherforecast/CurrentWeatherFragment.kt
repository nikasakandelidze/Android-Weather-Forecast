package org.sakana.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.sakana.weatherforecast.model.City
import org.sakana.weatherforecast.weatherApiAdapter.WeatherApiAdapter
import org.sakana.weatherforecast.weatherApiAdapter.iconsLoaderAdapter.IconLoader
import org.sakana.weatherforecast.weatherApiAdapter.dto.WeatherApiResponse


class CurrentWeatherFragment(val weatherApiAdapter: WeatherApiAdapter, val listOfCities: List<City>) : Fragment() {
    private var iconLoaderAdapter: IconLoader = IconLoader()
    private lateinit var currentWeatherIconImageView: ImageView
    private lateinit var currentTemperatureTextView: TextView
    private lateinit var currentWeatherDescription: TextView
    private lateinit var currentCityTextView: TextView

    private var listOfWeatherDetailsViewIds: List<Int> = listOf(
        R.id.temperature_value,
        R.id.feels_like_value,
        R.id.humidty_value,
        R.id.pressure_value
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCurrentWeatherViewBindings(view)
        setupCountryImageListeners(view)
        updateCurrentWeather("Tbilisi", view)
    }

    private fun setupCountryImageListeners(view: View) {
        listOfCities.forEach {
            setupListener(view, it.name, it.imageViewId)
        }
    }

    private fun setupListener(view: View, name: String, imageViewId: Int) {
        view.findViewById<ImageView>(imageViewId).setOnClickListener {
            updateCurrentWeather(name, view)
        }
    }

    private fun updateCurrentWeather(city: String, view: View) {
        weatherApiAdapter.getCurrentWeather({
            currentCityTextView.text = city
            val temperature = it?.main?.temp
            currentTemperatureTextView.text = temperature.toString()
            val tempWeather = it?.weather?.get(0)
            val iconName = tempWeather?.icon.orEmpty()
            iconLoaderAdapter.loadIconWithNameIntoImageView(iconName, currentWeatherIconImageView)
            currentWeatherDescription.text = tempWeather?.description
            updateDetails(it, view)
        }, city, view)
    }

    private fun updateDetails(weatherApiResponse: WeatherApiResponse?, view: View): Unit {
        view.findViewById<TextView>(listOfWeatherDetailsViewIds[0]).text =
            weatherApiResponse?.main?.temp.toString()
        view.findViewById<TextView>(listOfWeatherDetailsViewIds[1]).text =
            weatherApiResponse?.main?.feels_like.toString()
        view.findViewById<TextView>(listOfWeatherDetailsViewIds[2]).text =
            weatherApiResponse?.main?.humidity.toString()
        view.findViewById<TextView>(listOfWeatherDetailsViewIds[3]).text =
            weatherApiResponse?.main?.pressure.toString()

    }

    private fun setupCurrentWeatherViewBindings(view: View) {
        currentWeatherIconImageView = view.findViewById(R.id.current_weather_icon)
        currentTemperatureTextView = view.findViewById<TextView>(R.id.current_temperature)
        currentCityTextView = view.findViewById(R.id.city_name)
        currentWeatherDescription = view.findViewById(R.id.weather_description)
    }
}