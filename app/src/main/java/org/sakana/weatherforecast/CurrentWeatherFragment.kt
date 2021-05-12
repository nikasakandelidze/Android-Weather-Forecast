package org.sakana.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.sakana.weatherforecast.weatherApiAdapter.WeatherApiAdapter
import org.sakana.weatherforecast.weatherApiAdapter.iconsLoaderAdapter.IconLoader


class CurrentWeatherFragment : Fragment() {
    private var weatherApiAdapter: WeatherApiAdapter = WeatherApiAdapter()
    private var iconLoaderAdapter: IconLoader = IconLoader()
    lateinit var currentWeatherIconImageView: ImageView
    lateinit var currentTemperatureTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentWeatherIconImageView = view.findViewById(R.id.current_weather_icon)
        currentTemperatureTextView = view.findViewById<TextView>(R.id.current_temperature)
        weatherApiAdapter.getCurrentWeather {
            val temperature = it?.main?.temp
            currentTemperatureTextView.text = temperature.toString()
            val iconName = it?.weather?.get(0)?.icon
            iconLoaderAdapter.loadIconWithNameIntoImageView(
                iconName.orEmpty(),
                currentWeatherIconImageView
            )
        }
    }
}