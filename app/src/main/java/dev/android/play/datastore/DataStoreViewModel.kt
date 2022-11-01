package dev.android.play.datastore

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.R
import dev.android.play.utility.ObservableViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val USER_PREFERENCES_NAME = "user_preferences"
private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

@HiltViewModel
class DataStoreViewModel @Inject constructor() : ObservableViewModel() {

    @Bindable
    var emailObservable = ObservableField<String>("")

    @Bindable
    var passwordObservable = ObservableField<String>("")


    fun fetchData(context: Context) {
        val userPreferencesFlow: Flow<UserPreferences> = context.dataStore.data
            .map { preferences ->
                // Get our show completed value, defaulting to false if not set:
                val showCompleted = preferences[PreferencesKeys.SHOW_COMPLETED] ?: false
                UserPreferences(showCompleted)
            }
    }

    fun onLoginClick(view: View, data: String) {
        Log.i("LoginViewModel", data)
    }

    fun onRegisterClick(view: View) {
        view.findNavController().navigate(
            R.id.action_loginFragment_to_registerFragment, null,
            NavOptions.Builder().setPopUpTo(R.id.registerFragment, true).build()
        )
    }
}