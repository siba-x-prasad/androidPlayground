package dev.android.play.background.data

import androidx.annotation.Keep
import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Keep
data class WeatherResponse(
    var base: String, // stations

    val cod: Int, // 200

    val dt: Int, // 1636497002

    val id: Int, // 7778677

    val name: String, // Dublin City

    val timezone: Int
) : BaseObservable() {

    lateinit var clouds: Clouds

    lateinit var coord: Coord

    lateinit var main: Main

    lateinit var rain: Rain

    lateinit var weather: List<Weather>

    lateinit var wind: Wind

    lateinit var sys: Sys

}

@Keep
data class Clouds(
    val all: Int // 75
)

@Keep
data class Coord(
    val lat: Double, // 53.3551
    val lon: Double // -6.2492
)

@Keep
data class Main(
    val feels_like: Double, // 283.6
    val humidity: Int, // 85
    val pressure: Int, // 1019
    val temp: Double, // 284.22
    val temp_max: Double, // 284.55
    val temp_min: Double // 283.56
) {

    val feelLikeInCelcious: String
        get() = "%.2f".format(-273.15 + feels_like).toDouble().toString() + "° C"

    val tempInCelcious: String
        get() = "%.2f".format(-273.15 + temp).toDouble().toString() + "° C"

    val temp_maxInCelcious: String
        get() = "%.2f".format(-273.15 + temp_max).toDouble().toString() + "° C"

    val temp_minInCelcious: String
        get() = "%.2f".format(-273.15 + temp_min).toDouble().toString() + "° C"

}

@Keep
data class Rain(
    val `1h`: Double // 0.49
) {
    val rainPossibility: String
        get() = "%.2f".format(`1h`).toDouble().toString()

}

@Keep
data class Sys(
    val country: String, // IE
    val id: Int, // 2016139
    val sunrise: Int, // 1636443545
    val sunset: Int, // 1636475920
    val type: Int // 2
)

@Keep
@Entity
data class Weather(
    val description: String, // light rain
    val icon: String, // 10n
    @PrimaryKey
    val id: Int, // 500
    val main: String,
    val isFavorite: Int
)

@Keep
data class Wind(
    val deg: Int, // 215
    val gust: Double, // 4.47
    val speed: Double // 2.24
) {
    val degreeFormatted: String
        get() = "$deg °"
    val gustFormatted: String
        get() = "%.2f".format(gust).toDouble().toString()

    val speedFormatted: String
        get() = "%.2f".format(speed).toDouble().toString() + "Km/h"

}