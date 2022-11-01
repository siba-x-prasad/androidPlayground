package dev.android.play.background.api

import dev.android.play.background.data.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WorkWeatherApiService {
    @GET("weather")
    suspend fun fetchWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("APPID") appid: String = "5ad7218f2e11df834b0eaf3a33a39d2a"
    ): Response<WeatherResponse>
}