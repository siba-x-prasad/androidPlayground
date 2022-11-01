package dev.android.play.recyclerViewHeader.network

class ApiHelper(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}