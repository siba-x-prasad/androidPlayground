package dev.android.play.datastore

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.R
import dev.android.play.UserStore
import dev.android.play.utility.ObservableViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

private val Context.userStoreProto: DataStore<UserStore> by dataStore(
    fileName = PreferencesKeys.DATA_STORE_FILE_NAME,
    serializer = UserStoreSerializer
)

@HiltViewModel
class DataStoreViewModel @Inject constructor() : ObservableViewModel() {

    private val TAG = "DataStoreViewModel"

    @Bindable
    var genderData = MutableLiveData<Int>()

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

    init {
        genderData.postValue(R.id.radioMale)
    }

    fun fetchData(view: View) {
        Toast.makeText(view.context, "Data Fetching Started", Toast.LENGTH_SHORT).show()
        fetchDataFromDatastore(view.context)
    }

    private fun fetchDataFromDatastore(context: Context) {


        val datastorePreference = context.dataStore.data

        datastorePreference.map {
            it[PreferencesKeys.IS_MALE] ?: false
        }.asLiveData().observeForever {
            Log.i(TAG, "isMale $it")
            genderData.value = if (it) R.id.radioMale else R.id.radioFemale
        }

        datastorePreference.map {
            it[PreferencesKeys.EMAIL_ID] ?: ""
        }.asLiveData().observeForever {
            Log.i(TAG, "email Id $it")
            email = it
        }

        val passwordFlow: Flow<String> = datastorePreference.map {
            it[PreferencesKeys.PASSWORD] ?: ""
        }

        viewModelScope.launch {
            passwordFlow.collectLatest {
                runBlocking {
                    Log.i(TAG, "Pwd Id $it")
                    password = it
                    delay(5000)
                    Toast.makeText(context, "Data Fetched", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onSaveClicked(view: View) {
        var isMale = false
        when (genderData.value) {
            R.id.radioMale -> {
                isMale = true
                Log.i(
                    "RegisterViewModel", "radioMale"
                )
            }
            R.id.radioFemale -> {
                Log.i(
                    "RegisterViewModel", "radioFemale"
                )
            }
        }

        viewModelScope.launch {
            saveDataToDataStore(view.context)
        }
    }

    private suspend fun saveDataToDataStore(context: Context) {
        context.dataStore.edit {
            it[PreferencesKeys.IS_MALE] = genderData.value == R.id.radioMale
            it[PreferencesKeys.EMAIL_ID] = email!!
            it[PreferencesKeys.PASSWORD] = password!!
        }
        Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show()
    }


    /**************** SAVE PROTO DATA **************/
    @Bindable
    var userNameObservable = ObservableField<String>("")
    private var userName: String?
        get() = userNameObservable.get()
        set(value) = userNameObservable.set(value)


    var isLoggedIn: Boolean = false

    fun onLoggedInStatusChanged(buttonView: CompoundButton, isChecked: Boolean) {
        println("buttonView = [$buttonView], isChecked = [$isChecked]")
    }

    fun onSaveProtoClicked(view: View) {
        viewModelScope.launch {
            updateUserStoreData(view.context)
        }
    }

    fun fetchProtoData(view: View) {
        val userStoreFlow: Flow<UserStore> = view.context.userStoreProto.data
        viewModelScope.launch {
            userStoreFlow.collectLatest {
                userName = it.userName
                isLoggedIn = it.isLoggedIn
            }
        }
    }

    private suspend fun updateUserStoreData(context: Context) {
        context.userStoreProto.updateData { userStore ->
            userStore.toBuilder().setUserName("Hello").build()
            userStore.toBuilder().setIsLoggedIn(false).build()
        }
    }
}