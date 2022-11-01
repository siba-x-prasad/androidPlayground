package dev.android.play.recyclerViewHeader.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.android.play.recyclerViewHeader.network.NetworkResult
import dev.android.play.recyclerViewHeader.repo.MainRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(NetworkResult.Loading)
        try {
            emit(NetworkResult.Success(mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(NetworkResult.Error(message = exception.message ?: "Error Occurred!"))
        }
    }
}