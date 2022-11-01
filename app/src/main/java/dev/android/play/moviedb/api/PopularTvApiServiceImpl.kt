package dev.android.play.moviedb.api

import dev.android.play.moviedb.data.PopularTvShowResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularTvApiServiceImpl @Inject constructor(
    private val apiService: TvShowApiService
) : PopularTvApiServiceHelper {
    override suspend fun getPopularTvShows(page: Int) = apiService.getPopularTvShows(page)
    override suspend fun getPopularTvShowsByFlow(page: Int): PopularTvShowResponse =
        apiService.getPopularTvShowsByFlow(page = page)
}