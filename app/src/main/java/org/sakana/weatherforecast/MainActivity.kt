package org.sakana.weatherforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import org.sakana.weatherforecast.adapters.WeatherFragmentAdapter
import org.sakana.weatherforecast.model.City
import org.sakana.weatherforecast.weatherApiAdapter.WeatherApiAdapter
import org.sakana.weatherforecast.weatherApiAdapter.iconsLoaderAdapter.IconLoader

class MainActivity : AppCompatActivity() {
    private val listOfCities: List<City> = listOf(
        City("Tbilisi", R.id.georgia_icon),
        City("London", R.id.london_icon),
        City("Jamaica", R.id.jamaica_icon)
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var weatherApiAdapter: WeatherApiAdapter = WeatherApiAdapter()
        val iconLoaderAdapter: IconLoader = IconLoader()

        val weatherHistoryFragment =
            WeatherHistoryFragment(weatherApiAdapter, listOfCities, iconLoaderAdapter)
        val currentWeatherFragment =
            CurrentWeatherFragment(weatherApiAdapter, listOfCities, iconLoaderAdapter);

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter =
            WeatherFragmentAdapter(listOf(currentWeatherFragment, weatherHistoryFragment), this)
        viewPager.adapter = adapter
        initFragmentSwitchButton(R.id.firstButton, 0, viewPager)
        initFragmentSwitchButton(R.id.secondButton, 1, viewPager)
    }

    private fun initFragmentSwitchButton(
        buttonId: Int,
        itemIndex: Int,
        viewPager: ViewPager2
    ): Unit {
        findViewById<ImageView>(buttonId).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                viewPager.setCurrentItem(itemIndex, true)
                commit()
            }
        }
    }
}