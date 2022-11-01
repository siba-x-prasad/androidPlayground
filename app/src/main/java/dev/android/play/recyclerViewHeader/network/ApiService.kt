package dev.android.play.recyclerViewHeader.network

import dev.android.play.recyclerViewHeader.data.ItemData
import retrofit2.http.GET

interface ApiService {
    @GET("v3/ed7af09d-601e-49fa-b033-159d16a45942")
    suspend fun getUsers(): List<ItemData>
}
