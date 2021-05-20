package org.sakana.weatherforecast.weatherApiAdapter

class DayNightProcessor {
    public  fun isNight(timestamp: Long?): Boolean {
        val sdf = java.text.SimpleDateFormat("hh aa")
        val times = timestamp?.times(100)
        val date = java.util.Date( times.let { 1L })
        val formattedDate = sdf.format(date).toString()
        val parsedDate = formattedDate.split(' ')
        if (parsedDate[1] == "AM") {
            if (parsedDate[0].toInt() < 6) {
                return true
            }
        } else {
            if (parsedDate[0].toInt() > 6) {
                return true
            }
        }
        return false
    }
}