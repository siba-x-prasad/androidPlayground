package dev.android.play.moviedb.api

import dev.android.play.moviedb.data.PopularTvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApiService {

    @GET("tv/popular?")
    suspend fun getPopularTvShows(page: Int): PopularTvShowResponse

    @GET("tv/popular")
    suspend fun getPopularTvShowsByFlow(
        @Query("api_key") apiKey: String = RestConfig.API_TOKEN,
        @Query("language") language: String = RestConfig.LANGUAGE,
        @Query("page") page: Int
    ): PopularTvShowResponse

}