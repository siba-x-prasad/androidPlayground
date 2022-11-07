package dev.android.play.xmlparsing.repository

import dev.android.play.xmlparsing.api.SoapApiService
import dev.android.play.xmlparsing.data.RssFeed
import dev.android.play.xmlparsing.data.SoapResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SoapApiRepository
@Inject constructor(private val apiService: SoapApiService) {
    suspend fun getTrendingSearches(country: String = "US"): Flow<SoapResponse> =
        flow {
            emit(apiService.getTrendingSearches(countryCode = country))
        }.flowOn(Dispatchers.IO)

    suspend fun getRssFeeds(): Flow<RssFeed> =
        flow {
            emit(apiService.getRssFeeds())
        }.flowOn(Dispatchers.IO)
}