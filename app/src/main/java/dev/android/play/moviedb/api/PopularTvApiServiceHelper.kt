package dev.android.play.moviedb.api

import dev.android.play.moviedb.data.PopularTvShowResponse

interface PopularTvApiServiceHelper {
    suspend fun getPopularTvShows(page: Int): PopularTvShowResponse
    suspend fun getPopularTvShowsByFlow(page: Int): PopularTvShowResponse
}