package dev.android.play.test

import androidx.annotation.VisibleForTesting
import dev.android.play.databinding.FragmentObservableComparisonBinding
import dev.android.play.utility.ObservableViewModel

class ValidatePrivateMethodsViewModel : ObservableViewModel() {


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    private fun validateLoginCredentials(email: String, password: String) =
        email.isNotEmpty() && password.isNotEmpty()



    fun foror(){

    }

}