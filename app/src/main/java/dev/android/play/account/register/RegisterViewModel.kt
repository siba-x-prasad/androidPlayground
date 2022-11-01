package dev.android.play.account.register


import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.R
import dev.android.play.utility.ObservableViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ObservableViewModel() {

    @Bindable
    var genderData = MutableLiveData<Int>()

    @Bindable
    var emailObservable = ObservableField<String>("")

    @Bindable
    var nameObservable = ObservableField<String>("")

    @Bindable
    var mobileObservable = ObservableField<String>("")

    @Bindable
    var passwordObservable = ObservableField<String>("")

    init {
        genderData.postValue(R.id.radioMale)
    }

    fun onRegisterClick(view: View, data: String) {

        when (genderData.value) {
            R.id.radioMale -> {
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

        Log.i(
            "Datastore",
            "${emailObservable.get()} ${emailObservable.get()} ${passwordObservable.get()} "
        )
    }

    fun onLoginClick(view: View) {
        view.findNavController().navigate(
            R.id.action_registerFragment_to_loginFragment, null,
            NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
        )
    }
}