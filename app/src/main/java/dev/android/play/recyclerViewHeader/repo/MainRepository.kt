package dev.android.play.recyclerViewHeader.repo

import dev.android.play.recyclerViewHeader.network.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}