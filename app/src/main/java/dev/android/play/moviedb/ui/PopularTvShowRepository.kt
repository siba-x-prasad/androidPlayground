package dev.android.play.moviedb.ui

import dev.android.play.moviedb.api.TvShowApiService
import dev.android.play.moviedb.data.PopularTvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PopularTvShowRepository
@Inject constructor(private val apiService: TvShowApiService) {

    suspend fun getPersonalDetailsApi(page: Int) = apiService.getPopularTvShows(page)

    suspend fun getPopularTvShowByFlow(page: Int): Flow<PopularTvShowResponse> =
        flow<PopularTvShowResponse> {
            emit(apiService.getPopularTvShowsByFlow(page = page))
        }.flowOn(Dispatchers.IO)

}