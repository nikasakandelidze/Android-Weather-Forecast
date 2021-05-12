package org.sakana.weatherforecast.weatherApiAdapter.iconsLoaderAdapter

import android.widget.ImageView
import com.bumptech.glide.Glide

class IconLoader {
    public fun loadIconWithNameIntoImageView(iconName: String, imageView: ImageView): Unit {
        val name = iconName
        val iconGeneratorUrl: String = "https://openweathermap.org/img/wn/${name}@2x.png";
        Glide.with(imageView.rootView)
            .load(iconGeneratorUrl)
            .into(imageView)
    }
}