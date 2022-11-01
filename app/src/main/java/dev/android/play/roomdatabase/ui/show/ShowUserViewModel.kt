package dev.android.play.roomdatabase.ui.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.roomdatabase.data.AddressEntity
import dev.android.play.roomdatabase.data.UserEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowUserViewModel @Inject constructor(private val showUserUseCase: ShowUserUseCase) : ViewModel() {

    fun fetchAllUser() {
        viewModelScope.launch {
            val userList: List<UserEntity> = showUserUseCase.getAllUsers()
        }
    }

    fun fetchAllAddress() {
        viewModelScope.launch {
            val userList: List<AddressEntity> = showUserUseCase.getAllAddress()
        }
    }

}