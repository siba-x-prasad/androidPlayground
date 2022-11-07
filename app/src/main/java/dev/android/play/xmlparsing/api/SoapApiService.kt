package dev.android.play.xmlparsing.api

import dev.android.play.xmlparsing.data.RssFeed
import dev.android.play.xmlparsing.data.SoapResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface SoapApiService {
    @GET("/webservice/xml")
    fun getXml(): Call<SoapResponse>

    @GET("rss")
    suspend fun getTrendingSearches(@Query("geo") countryCode: String = "US"): SoapResponse

    @GET("feed")
    suspend fun getRssFeeds(): RssFeed

    @GET("feed")
    fun getFeed(): Call<RssFeed>

}