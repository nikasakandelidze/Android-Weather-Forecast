package ge.nsakandelidze.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.nsakandelidze.weatherforecast.adapters.WeatherHistoryItemsAdapter
import ge.nsakandelidze.weatherforecast.model.City
import ge.nsakandelidze.weatherforecast.weatherApiAdapter.WeatherApiAdapter
import ge.nsakandelidze.weatherforecast.weatherApiAdapter.dto.WeatherApiResponse
import ge.nsakandelidze.weatherforecast.weatherApiAdapter.iconsLoaderAdapter.IconLoader

class WeatherHistoryFragment(
    val weatherApiAdapter: WeatherApiAdapter,
    val listOfCities: List<City>,
    val iconLoaderAdapter: IconLoader
) : Fragment() {
    private lateinit var currentCityTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private var listOfWeatherItems: MutableList<WeatherApiResponse> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.weather_history_list_id)
        recyclerView.adapter =
            WeatherHistoryItemsAdapter(listOfWeatherItems, iconLoaderAdapter)
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration =
            DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(getDrawable(recyclerView.context, R.drawable.divider)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)
        weatherApiAdapter.getWeatherHistory({
            currentCityTextView.text = "Tbilisi"
            listOfWeatherItems.addAll((it?.list ?: listOf()))
            recyclerView.adapter?.notifyDataSetChanged()
        }, "Tbilisi", view)
        setupCountryImageListeners(view)
        setupCurrentWeatherViewBindings(view)
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
                listOfWeatherItems.clear()
                listOfWeatherItems.addAll(it?.list ?: listOf())
                recyclerView.adapter?.notifyDataSetChanged()
            }, name, view)
        }
    }

    private fun setupCurrentWeatherViewBindings(view: View) {
        currentCityTextView = view.findViewById(R.id.city_name)
    }
}