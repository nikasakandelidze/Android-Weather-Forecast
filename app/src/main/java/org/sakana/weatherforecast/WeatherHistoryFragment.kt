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

class WeatherHistoryFragment(
    val weatherApiAdapter: WeatherApiAdapter,
    val listOfCities: List<City>
) : Fragment() {
    private lateinit var currentCityTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCountryImageListeners(view)
        setupCurrentWeatherViewBindings(view)
        //initialise fragments stuff here
    }

    private fun setupCountryImageListeners(view: View) {
        listOfCities.forEach {
            setupListener(view, it.name, it.imageViewId)
        }
    }

    private fun setupListener(view: View, name: String, imageViewId: Int) {
        view.findViewById<ImageView>(imageViewId).setOnClickListener {
            updateWeatherHistory(name, view)
        }
    }

    private fun updateWeatherHistory(city: String, view: View) {
        weatherApiAdapter.getWeatherHistory({
            currentCityTextView.text = city
            println(it.toString())
        }, city, view)
    }


    private fun setupCurrentWeatherViewBindings(view: View) {
        currentCityTextView = view.findViewById(R.id.city_name)
    }
}