package dev.android.play.recyclerViewHeader.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var retrofit: Retrofit? = null
        get() {
            val gson = GsonBuilder().setLenient().create()
            if (field == null) {
                field = Retrofit.Builder()
                    .baseUrl("https://run.mocky.io/")
             .addConverterFactory(GsonConverterFactory.create(gson))
             .build()
            }
            return field
        }
        private set
}