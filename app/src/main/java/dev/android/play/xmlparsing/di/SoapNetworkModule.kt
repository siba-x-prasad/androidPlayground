package dev.android.play.xmlparsing.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.android.play.BuildConfig
import dev.android.play.moviedb.api.RestConfig
import dev.android.play.xmlparsing.api.SoapApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SoapNetworkModule {

    @Provides
    @Named("xmlParsing")
    fun provideSoapBaseUrl() = RestConfig.SOAP_API_SERVER

    @Singleton
    @Provides
    @Named("xmlParsing")
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    @Named("xmlParsing")
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named("xmlParsing") BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideSoapApiService(@Named("xmlParsing") retrofit: Retrofit): SoapApiService =
        retrofit.create(SoapApiService::class.java)

}