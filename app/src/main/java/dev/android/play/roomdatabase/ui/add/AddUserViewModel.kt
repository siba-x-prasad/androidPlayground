package dev.android.play.roomdatabase.ui.add

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.roomdatabase.data.AddressEntity
import dev.android.play.roomdatabase.data.UserEntity
import dev.android.play.utility.ObservableViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(private val addUserUseCase: AddUserUseCase) : ObservableViewModel() {

    val TAG = "AddUserViewModel"

    val fName = ObservableField<String>("")
    val lName = ObservableField<String>("")
    val email = ObservableField<String>("")
    val contact = ObservableField<String>("")
    val gender = ObservableField<String>("")


    val testData = MutableLiveData("")

    fun postTestData(){
        testData.postValue("Post test Value")
    }

    fun setTestData(){
        testData.value = "Set test Value"
    }

    var firstName: String
        @Bindable get() {
            return ""
        }
        set(value) {
            value.ifEmpty { "Error" }
        }

    var isMale: Boolean
        @Bindable get() {
            return false
        }
        set(value) {

        }

fun checkRunBlocking() = runBlocking {
    getGreeting()
    getGreeting2()
}

    suspend fun getGreeting(){
        delay(2000L)
        Log.i("RunBlocking", "Hello")
    }

    suspend fun getGreeting2(){
        delay(2000L)
        Log.i("RunBlocking","World")
    }

    fun addUser(userEntity: UserEntity) {

        runBlocking {

        }

        viewModelScope.launch {
            val result = addUserUseCase.insertUser(userEntity)
            Log.i(TAG, "User Inserted $result")
        }
    }

    fun addAddress(addressEntity: AddressEntity) {
        viewModelScope.launch {
            val result = addUserUseCase.insertAddress(addressEntity)
            Log.i(TAG, "Address Inserted $result")
        }
    }
}