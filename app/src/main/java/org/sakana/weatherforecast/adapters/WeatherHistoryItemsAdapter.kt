package org.sakana.weatherforecast.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.sakana.weatherforecast.R
import org.sakana.weatherforecast.weatherApiAdapter.dto.WeatherApiResponse
import org.sakana.weatherforecast.weatherApiAdapter.iconsLoaderAdapter.IconLoader

class WeatherHistoryItemsAdapter(var listOfWeatherHistory:List<WeatherApiResponse>, val iconLoaderAdapter: IconLoader) :
    RecyclerView.Adapter<WeatherHistoryItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherHistoryItemViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_history_item, parent, false)
        return WeatherHistoryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherHistoryItemViewHolder, position: Int) {
        var tempItem = listOfWeatherHistory[position]
        holder.temperatureView.text = tempItem.main.temp.toString()
        holder.temperatureView.setTextColor(Color.WHITE)
        holder.currentDate.text = tempItem.dt.toString()
        holder.currentDate.setTextColor(Color.WHITE)
        holder.descriptionView.text = tempItem.weather[0].description
        holder.descriptionView.setTextColor(Color.WHITE)
        iconLoaderAdapter.loadIconWithNameIntoImageView(tempItem.weather[0].icon, holder.iconView)
    }

    override fun getItemCount(): Int {
        return listOfWeatherHistory.size
    }

}

class WeatherHistoryItemViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
    var currentDate = itemView.findViewById<TextView>(R.id.date_text_view_id);
    var iconView = itemView.findViewById<ImageView>(R.id.weather_icon_id)
    var temperatureView = itemView.findViewById<TextView>(R.id.temperature_text_view_id)
    var descriptionView = itemView.findViewById<TextView>(R.id.weather_description_id)
}