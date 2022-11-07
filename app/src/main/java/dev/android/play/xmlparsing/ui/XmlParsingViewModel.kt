package dev.android.play.xmlparsing.ui

import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.utility.ObservableViewModel
import dev.android.play.xmlparsing.api.SoapApiService
import dev.android.play.xmlparsing.data.RssFeed
import dev.android.play.xmlparsing.repository.SoapApiRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Inject


private const val BASE_URL = "https://howtodoinjava.com/"

private val builder = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(SimpleXmlConverterFactory.create())

private val loggingInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()


@HiltViewModel
class XmlParsingViewModel @Inject constructor(private val repository: SoapApiRepository) :
    ObservableViewModel() {

    private val TAG = "XmlParsingViewModel"

    @Bindable
    var emailObservable = ObservableField<String>("")
    private var email: String?
        get() = emailObservable.get()
        set(value) = emailObservable.set(value)

    @Bindable
    var passwordObservable = ObservableField<String>("")
    private var password: String?
        get() = passwordObservable.get()
        set(value) = passwordObservable.set(value)

    fun onLoginClick(view: View, data: String) {
        viewModelScope.launch {
//            repository.getTrendingSearches().collectLatest {
//                Log.i(TAG, it.toString())
//            }
            repository.getRssFeeds().collectLatest {
                Log.i(TAG, it.toString())
            }
        }
    }

    fun onRegisterClick(view: View) {
        fetchXmlData()
    }

    private fun fetchXmlData() {
        httpClient.addInterceptor(loggingInterceptor)
        builder.client(httpClient.build())

        val retrofit = builder.build()

        val rssService: SoapApiService = retrofit.create(SoapApiService::class.java)

        val callAsync = rssService.getFeed()

        callAsync.enqueue(object : Callback<RssFeed> {
            override fun onResponse(call: Call<RssFeed>, response: Response<RssFeed>) {
                if (response.isSuccessful) {
                    val apiResponse: RssFeed = response.body()!!
                    // API response
                    println(apiResponse)
                } else {
                    System.out.println("Request Error :: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<RssFeed?>, t: Throwable) {
                if (call.isCanceled()) {
                    println("Call was cancelled forcefully")
                } else {
                    println("Network Error :: " + t.localizedMessage)
                }
            }
        })
    }

}