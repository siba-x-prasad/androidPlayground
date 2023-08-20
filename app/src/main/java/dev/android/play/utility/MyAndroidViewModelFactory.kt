package dev.android.play.utility

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.android.play.ui.adapters.ListViewModel

// https://vtsen.hashnode.dev/convert-view-model-to-use-hilt-dependency-injection

class MyAndroidViewModelFactory(
    private val app: Application
) : ViewModelProvider.AndroidViewModelFactory(app) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(
                ListViewModel::class.java
            )
        ) {

            return ListViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}