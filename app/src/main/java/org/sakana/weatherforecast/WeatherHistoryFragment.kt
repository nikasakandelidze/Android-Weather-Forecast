package org.sakana.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.sakana.weatherforecast.adapters.WeatherHistoryItemsAdapter
import org.sakana.weatherforecast.model.City
import org.sakana.weatherforecast.weatherApiAdapter.WeatherApiAdapter
import org.sakana.weatherforecast.weatherApiAdapter.iconsLoaderAdapter.IconLoader

class WeatherHistoryFragment(
    val weatherApiAdapter: WeatherApiAdapter,
    val listOfCities: List<City>,
    val iconLoaderAdapter: IconLoader
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
        weatherApiAdapter.getWeatherHistory({
            currentCityTextView.text = "Tbilisi"
            view.findViewById<RecyclerView>(R.id.weather_history_list_id).adapter =
                WeatherHistoryItemsAdapter(it?.list ?: listOf(), iconLoaderAdapter)
        }, "Tbilisi", view)
    }

    private fun setupCountryImageListeners(view: View) {
        listOfCities.forEach {
            setupListener(view, it.name, it.imageViewId)
        }
    }


    private fun setupListener(view: View, name: String, imageViewId: Int) {
        view.findViewById<ImageView>(imageViewId).setOnClickListener {
            weatherApiAdapter.getWeatherHistory({
                currentCityTextView.text = name
                view.findViewById<RecyclerView>(R.id.weather_history_list_id).adapter =
                    WeatherHistoryItemsAdapter(it?.list ?: listOf(), iconLoaderAdapter)
            }, name, view)
        }
    }

    private fun setupCurrentWeatherViewBindings(view: View) {
        currentCityTextView = view.findViewById(R.id.city_name)
    }
}