package dev.android.play.account.login

import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.android.play.R
import dev.android.play.utility.ObservableViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ObservableViewModel() {

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