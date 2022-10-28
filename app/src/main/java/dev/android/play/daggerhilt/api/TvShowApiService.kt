package dev.android.play.daggerhilt.api

import dev.android.play.data.PopularTvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApiService {
    @GET("tv/popular?")
    suspend fun getPopularTvShows(page: Int): PopularTvShowResponse

    @GET("tv/popular")
    suspend fun getPopularTvShowsByFlow(
        @Query("api_key") apiKey: String = "",
        @Query("language") language: String = "",
        @Query("page") page: Int
    ): PopularTvShowResponse
}